package cz.cvut.fel.pjv.bukovja4.utils.constants;

public final class Const {
    public static final int MAX_COMPRESSED_LOGS_PER_DAY = 10;
    public static final int MAX_COMPRESSED_LOGS_TOTAL = 70;
    public static final int DESIRED_TPS = 20;
    public static final String APP_TITLE = "^nD Game";

    private Const() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
