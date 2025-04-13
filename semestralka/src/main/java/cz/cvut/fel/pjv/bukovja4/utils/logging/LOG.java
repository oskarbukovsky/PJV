package cz.cvut.fel.pjv.bukovja4.utils.logging;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.*;
import java.util.stream.Stream;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import cz.cvut.fel.pjv.bukovja4.utils.constants.Const;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.TEXT_COLOR;

/**
 * Utility class for application logging with multiple severity levels.
 * Provides colored console output and file logging with automatic rotation.
 * Log files are stored in the "logs" directory and compressed when rotated.
 * 
 * @see java.util.logging.Logger The underlying logging implementation
 * @see ILogObserver For implementing custom log observers
 */
public final class LOG {
    /**
     * Log severity levels supported by the logging system.
     * 
     * @see #setLevel(LogLevel) For changing the active log level
     */
    public static enum LogLevel {
        /** Finest detail level for tracing execution */
        TRACE,
        /** Detailed information for debugging purposes */
        DEBUG,
        /** Standard operational messages */
        INFO,
        /** Potential issues that aren't immediately problematic */
        WARN,
        /** Serious issues that need attention */
        ERROR
    }

    /**
     * Internal class for representing RGB color values.
     * Used for console output colorization.
     */
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

    /** The underlying Java Logger instance */
    public static final java.util.logging.Logger Logger = java.util.logging.Logger.getLogger(Logger.class.getName());

    /**
     * Sets the minimum log level to display.
     * Messages below this level will be filtered out.
     * 
     * @param level The minimum log level to display
     */
    public static void setLevel(LogLevel level) {
        switch (level) {
            case TRACE -> Logger.setLevel(Level.FINEST);
            case DEBUG -> Logger.setLevel(Level.FINE);
            case INFO -> Logger.setLevel(Level.INFO);
            case WARN -> Logger.setLevel(Level.WARNING);
            case ERROR -> Logger.setLevel(Level.SEVERE);
        }
    }

    /**
     * Converts an exception's stack trace to a string.
     * 
     * @param exception The exception to convert
     * @return String representation of the stack trace
     */
    private static String getStackTrace(Throwable exception) {
        StringWriter stringWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString().replaceAll("[\\r\\n]+$", "");
    }

    /**
     * Logs a debug message.
     * For detailed information during development.
     * 
     * @param message The debug message to log
     */
    public static void debug(String message) {
        Logger.fine(message);
    }

    /**
     * Logs an informational message.
     * For regular operational messages.
     * 
     * @param message The info message to log
     */
    public static void info(String message) {
        Logger.info(message);
    }

    /**
     * Logs a warning message.
     * For potential issues that aren't critical.
     * 
     * @param message The warning message to log
     */
    public static void warn(String message) {
        Logger.warning(message);
    }

    /**
     * Logs an error message.
     * For serious issues that need attention.
     * 
     * @param message The error message to log
     */
    public static void error(String message) {
        Logger.severe(message);
    }

    /**
     * Logs an error message with exception details and rethrows.
     * 
     * @param message   The error message to log
     * @param exception The exception to log and rethrow
     * @throws Throwable The original exception is rethrown
     */
    public static void error(String message, Throwable exception) throws Throwable {
        Logger.severe(message + ": " + getStackTrace(exception));
        throw exception;
    }

    /**
     * Logs an error message with runtime exception details and rethrows.
     * 
     * @param message   The error message to log
     * @param exception The exception to log and rethrow
     * @throws RuntimeException The original exception is rethrown
     */
    public static void error(String message, RuntimeException exception) throws RuntimeException {
        Logger.severe(message + ": " + getStackTrace(exception));
        throw exception;
    }

    /**
     * Logs an error message with illegal argument exception details and rethrows.
     * 
     * @param message   The error message to log
     * @param exception The exception to log and rethrow
     * @throws IllegalArgumentException The original exception is rethrown
     */
    public static void error(String message, IllegalArgumentException exception) throws IllegalArgumentException {
        Logger.severe(message + ": " + getStackTrace(exception));
        throw exception;
    }

    /**
     * Logs an error message with IO exception details and rethrows.
     * 
     * @param message   The error message to log
     * @param exception The exception to log and rethrow
     * @throws IOException The original exception is rethrown
     */
    public static void error(String message, IOException exception) throws IOException {
        Logger.severe(message + ": " + getStackTrace(exception));
        throw exception;
    }

    /**
     * Logs and rethrows an exception.
     * 
     * @param exception The exception to log and rethrow
     * @throws Throwable The original exception is rethrown
     */
    public static void error(Throwable exception) throws Throwable {
        Logger.severe(getStackTrace(exception));
        throw exception;
    }

    /**
     * Logs trace information for an exception and rethrows.
     * 
     * @param exception The exception to log and rethrow
     * @throws Throwable The original exception is rethrown
     */
    public static void trace(Throwable exception) throws Throwable {
        Logger.finer(getStackTrace(exception));
        throw exception;
    }

    /**
     * Formats a log record for output.
     * Applies different formatting for console vs file output.
     * 
     * @param record        The log record to format
     * @param dateFormatter Date formatter to use
     * @param console       Whether output is for console (true) or file (false)
     * @return The formatted log message
     */
    private static String formatLog(LogRecord record, SimpleDateFormat dateFormatter, boolean console) {
        StringBuilder result = new StringBuilder();
        String time = dateFormatter.format(new Date(record.getMillis()));
        ColorAttribute color = new ColorAttribute(0, 0, 0);
        String level;
        color = switch (record.getLevel().getName()) {
            case "SEVERE" -> {
                level = "ERROR";
                yield new ColorAttribute(255, 0, 0);
            }
            case "WARNING" -> {
                level = "WARN";
                yield new ColorAttribute(255, 196, 0);
            }
            case "INFO" -> {
                level = "INFO";
                yield new ColorAttribute(0, 0, 255);
            }
            case "FINE" -> {
                level = "DEBUG";
                yield new ColorAttribute(152, 16, 255);
            }
            default -> {
                level = "TRACE";
                yield new ColorAttribute(255, 96, 96);
            }
        };
        result.append("[").append(time);
        if (console) {
            result.append(" ");
        } else {
            result.append("] [").append(Thread.currentThread().getName()).append("/");
        }
        result.append(level);
        result.append("]");
        if (console) {
            result.append(":");
        } else {
            result.append(" [").append(Thread.currentThread().getStackTrace()[12].getClassName()).append("/]:");
        }
        result.append(" ").append(record.getMessage()).append(" ").append("\n");
        if (console) {
            return colorize(result.toString(), TEXT_COLOR(color.r, color.g, color.b));
        }
        return result.toString();
    }

    /**
     * Gets the path for the next available log file.
     * Manages log rotation and deletion of old logs.
     * 
     * @param dir       Directory for log files
     * @param baseDate  Base date string for the filename
     * @param extension File extension to use
     * @return Path to use for the next log file
     * @throws Throwable if an error occurs while managing log files
     * @see Const#MAX_COMPRESSED_LOGS_PER_DAY For maximum log files kept
     */
    private static Path getNextAvailableFilePath(Path dir, String baseDate, String extension) throws Throwable {
        List<String> logs = Stream.of(dir.toFile().listFiles())
                .filter(file -> file.getName().contains(baseDate) && file.getName().endsWith(extension))
                .sorted((f1, f2) -> {
                    String name1 = f1.getName();
                    String name2 = f2.getName();
                    int index1 = Integer.parseInt(name1.substring(name1.lastIndexOf("_") + 1, name1.indexOf(".")));
                    int index2 = Integer.parseInt(name2.substring(name2.lastIndexOf("_") + 1, name2.indexOf(".")));
                    return Integer.compare(index1, index2);
                })
                .map(File::getName)
                .toList();

        int highestIndex = !logs.isEmpty() ? Integer
                .parseInt(logs.getLast().substring(logs.getLast().lastIndexOf("_") + 1, logs.getLast().indexOf(".")))
                : -1;

        if (logs.toArray().length >= Const.MAX_COMPRESSED_LOGS_PER_DAY) {
            for (int i = 0; i < logs.toArray().length - Const.MAX_COMPRESSED_LOGS_PER_DAY + 1; i++) {
                Path oldPath = dir.resolve(logs.get(i));
                try {
                    Files.delete(oldPath);
                } catch (IOException e) {
                    error("Error while deleting old log file", e);
                }
            }
        }

        return dir.resolve(baseDate + "_" + (highestIndex + 1) + extension);
    }

    // Static initializer block that sets up logging
    static {
        Logger.setLevel(Level.FINEST);
        Logger.setUseParentHandlers(false);

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
        SimpleDateFormat dateFormatterFile = new SimpleDateFormat("HH:mm:ss.SSS");
        stdout.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return formatLog(record, dateFormatter, true);
            }
        });
        Logger.addHandler(stdout);

        try {
            File logFolder = new File(System.getProperty("user.dir") + "\\logs");
            if (!logFolder.exists()) {
                Files.createDirectory(Path.of(System.getProperty("user.dir") + "\\logs"));
            }
            File logFileLatest = new File(
                    System.getProperty("user.dir") + "\\logs\\debug.log");
            if (logFileLatest.exists()) {
                LocalDate today = LocalDate.now();

                Path compressedOldLogPath = null;
                try {
                    compressedOldLogPath = getNextAvailableFilePath(logFolder.toPath(),
                            today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), ".log.gz");
                } catch (Throwable e) {
                    System.exit(1);
                }
                ZipFile zipFile = new ZipFile(compressedOldLogPath.toFile());

                ZipParameters parameters = new ZipParameters();
                parameters.setCompressionMethod(CompressionMethod.DEFLATE);
                parameters.setCompressionLevel(CompressionLevel.NORMAL);
                parameters
                        .setFileNameInZip(compressedOldLogPath.getFileName().toString().replaceAll(".log.gz", ".log"));

                zipFile.addFile(logFileLatest, parameters);
                zipFile.close();
            }
            FileHandler logHandler = new FileHandler(
                    logFileLatest.getAbsolutePath(), false);
            logHandler.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    return formatLog(record, dateFormatterFile, false);
                }
            });
            Logger.addHandler(logHandler);
        } catch (IOException e) {
            try {
                error("Error while logging", e);
            } catch (final Throwable ignored) {
            }
        }
    }
}
