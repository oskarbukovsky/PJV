package cz.cvut.fel.pjv.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.diogonunes.jcolor.Attribute.*;
import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.TEXT_COLOR;

public final class Logger {
    private static final class ColorAttribute {
        public int r;
        public int g;
        public int b;

        public ColorAttribute(int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }
    }

    public static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(Logger.class.getName());
    private static boolean isInitialized = false;

    private String getStackTrace(Throwable exception) {
        StringWriter stringWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString().replaceAll("[\\r\\n]+$", "");
    }

    public void debug(String message) {
        LOG.fine(message);
    }

    public void info(String message) {
        LOG.info(message);
    }

    public void warn(String message) {
        LOG.warning(message);
    }

    public void error(String message) {
        LOG.severe(message);
    }

    public void error(String message, Throwable exception) {
        LOG.severe(message + ": " + getStackTrace(exception));
    }

    public void trace(Throwable exception) {
        LOG.finer(getStackTrace(exception));
    }

    private String formatLog(LogRecord record, SimpleDateFormat dateFormatter, boolean colorize) {
        StringBuilder result = new StringBuilder();
        String time = dateFormatter.format(new Date(record.getMillis()));
        ColorAttribute color = new ColorAttribute(0, 0, 0);
        switch (record.getLevel().getName()) {
            case "SEVERE":
                color = new ColorAttribute(255, 0, 0);
                break;
            case "WARNING":
                color = new ColorAttribute(255, 196, 0);
                break;
            case "INFO":
                color = new ColorAttribute(0, 0, 255);
                break;
            case "FINE":
                color = new ColorAttribute(152, 16, 255);
                break;
            default:
                color = new ColorAttribute(255, 96, 96);
                break;
        }
        result.append("[");
        result.append(time + " " + record.getSourceMethodName().toUpperCase());
        result.append("] ");
        result.append(record.getMessage() + " " + "\n");
        if (colorize) {
            return colorize(result.toString(), TEXT_COLOR(color.r, color.g, color.b));
        }
        return result.toString();
    }

    public Logger() {
        if (isInitialized) {
            return;
        }
        isInitialized = true;

        LOG.setLevel(Level.FINEST);
        LOG.setUseParentHandlers(false);
        Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
            @Override
            public void publish(LogRecord record) {
                super.publish(record);
                flush();
            }

            @Override
            public boolean isLoggable(LogRecord record) {
                return record.getLevel().intValue() >= Level.ALL.intValue();
            }
        };

        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss");
        stdout.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return formatLog(record, dateFormatter, true);
            }
        });
        try {
            FileHandler logFile = new FileHandler(
                    System.getProperty("user.dir") + "\\log.log", false);
            logFile.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    return formatLog(record, dateFormatter, false);
                }
            });
            LOG.addHandler(stdout);
            LOG.addHandler(logFile);
        } catch (IOException e) {
            trace(e);
        }

    }

}
