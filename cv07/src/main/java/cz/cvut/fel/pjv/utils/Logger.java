package cz.cvut.fel.pjv.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.*;
import java.text.SimpleDateFormat;
import java.util.Date;

// import com.diogonunes.jcolor.AnsiFormat;
// import com.diogonunes.jcolor.Attribute;
// import com.diogonunes.jcolor.*;
import static com.diogonunes.jcolor.Attribute.*;
// import static com.diogonunes.jcolor.AnsiFormat.*;
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
        StringWriter stringWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(stringWriter));
        String stackTrace = stringWriter.toString();
        LOG.finer(message + ": " + stackTrace);
    }

    public void trace(Throwable exception) {
        StringWriter stringWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(stringWriter));
        String stackTrace = stringWriter.toString();
        LOG.finer(stackTrace);
    }

    private String colorizeLog(String message, ColorAttribute color) {
        return colorize(message, TEXT_COLOR(color.r, color.g, color.b));
    }

    private String colorLog(LogRecord record, SimpleDateFormat dateFormatter) {
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
                color = new ColorAttribute(255, 80, 80);
                break;
        }
        result.append("[");
        result.append(time + " " + record.getSourceMethodName().toUpperCase());
        result.append("] ");
        result.append(record.getMessage() + " " + "\n");
        return colorizeLog(result.toString(), color);
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
                return colorLog(record, dateFormatter);
            }
        });
        LOG.addHandler(stdout);
        try {
            LOG.addHandler(
                    new FileHandler("C:\\Users\\janos\\OneDrive\\Dokumenty\\CVUT\\S02\\PJV\\cv07\\log.txt", false));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
