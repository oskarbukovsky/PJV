package cz.cvut.fel.pjv.bukovja4.utils.constants;

/**
 * Utility class containing application-wide constants.
 * Provides fixed values used throughout the application.
 */
public final class Const {
    /** Maximum number of compressed log files to keep per day */
    public static final int MAX_COMPRESSED_LOGS_PER_DAY = 10;

    /** Target ticks per second for the game clock */
    public static final int DESIRED_TPS = 20;

    /** Application title displayed in the window */
    public static final String APP_TITLE = "^nD Game";

    /**
     * Private constructor to prevent instantiation.
     * This is a utility class that should not be instantiated.
     * 
     * @throws UnsupportedOperationException if instantiation is attempted
     */
    private Const() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
