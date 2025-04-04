package cz.cvut.fel.pjv.bukovja4.utils.logging;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.*;
import java.util.stream.Collectors;
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

public final class LOG {
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

    public static final java.util.logging.Logger Logger = java.util.logging.Logger.getLogger(Logger.class.getName());

    private static String getStackTrace(Throwable exception) {
        StringWriter stringWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString().replaceAll("[\\r\\n]+$", "");
    }

    public static void debug(String message) {
        Logger.fine(message);
    }

    public static void info(String message) {
        Logger.info(message);
    }

    public static void warn(String message) {
        Logger.warning(message);
    }

    public static void error(String message) {
        Logger.severe(message);
    }

    public static void error(String message, Throwable exception) throws Throwable {
        Logger.severe(message + ": " + getStackTrace(exception));
        throw exception;
    }

    public static void error(String message, RuntimeException exception) throws RuntimeException {
        Logger.severe(message + ": " + getStackTrace(exception));
        throw exception;
    }

    public static void error(String message, IllegalArgumentException exception) throws IllegalArgumentException {
        Logger.severe(message + ": " + getStackTrace(exception));
        throw exception;
    }

    public static void error(String message, IOException exception) throws IOException {
        Logger.severe(message + ": " + getStackTrace(exception));
        throw exception;
    }

    public static void trace(Throwable exception) throws Throwable {
        Logger.finer(getStackTrace(exception));
        throw exception;
    }

    private static String formatLog(LogRecord record, SimpleDateFormat dateFormatter, boolean console) {
        StringBuilder result = new StringBuilder();
        String time = dateFormatter.format(new Date(record.getMillis()));
        ColorAttribute color = new ColorAttribute(0, 0, 0);
        String level;
        switch (record.getLevel().getName()) {
            case "SEVERE":
                level = "ERROR";
                color = new ColorAttribute(255, 0, 0);
                break;
            case "WARNING":
                level = "WARN";
                color = new ColorAttribute(255, 196, 0);
                break;
            case "INFO":
                level = "INFO";
                color = new ColorAttribute(0, 0, 255);
                break;
            case "FINE":
                level = "DEBUG";
                color = new ColorAttribute(152, 16, 255);
                break;
            default:
                level = "TRACE";
                color = new ColorAttribute(255, 96, 96);
                break;
        }
        result.append("[" + time);
        if (console) {
            result.append(" ");
        } else {
            result.append("] [" + Thread.currentThread().getName() + "/");
        }
        result.append(level);
        result.append("]");
        if (console) {
            result.append(":");
        } else {
            result.append(" [" + Thread.currentThread().getStackTrace()[12].getClassName() + "/]:");
        }
        result.append(" " + record.getMessage() + " " + "\n");
        if (console) {
            return colorize(result.toString(), TEXT_COLOR(color.r, color.g, color.b));
        }
        return result.toString();
    }

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
                .collect(Collectors.toList());

        int highestIndex = logs.size() > 0 ? Integer
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

        // TODO: Const.MAX_COMPRESSED_LOGS_TOTAL

        return dir.resolve(baseDate + "_" + (highestIndex + 1) + extension);
    }

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
            } catch (Throwable f) {
            }
        }
    }
}
