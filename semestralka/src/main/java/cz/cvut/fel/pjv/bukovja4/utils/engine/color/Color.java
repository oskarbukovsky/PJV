package cz.cvut.fel.pjv.bukovja4.utils.engine.color;

public final class Color {

    public static final Color TRANSPARENT = new Color(0.0f, 0.0f, 0.0f, 0.0f);
    public static final Color WHITE = new Color(1.0f, 1.0f, 1.0f, 1.0f);
    public static final Color YELLOW = new Color(1.0f, 1.0f, 0, 1.0f);
    public static final Color RED = new Color(1.0f, 0, 0, 1.0f);
    public static final Color BLUE = new Color(0, 0, 1.0f, 1.0f);
    public static final Color GREEN = new Color(0, 1.0f, 0, 1.0f);
    public static final Color BLACK = new Color(0, 0, 0, 1.0f);
    public static final Color GRAY = new Color(0.5f, 0.5f, 0.5f, 1.0f);
    public static final Color CYAN = new Color(0, 1.0f, 1.0f, 1.0f);
    public static final Color DARK_GRAY = new Color(0.3f, 0.3f, 0.3f, 1.0f);
    public static final Color LIGHT_GRAY = new Color(0.7f, 0.7f, 0.7f, 1.0f);
    public final static Color PINK = new Color(255, 175, 175, 255);
    public final static Color ORANGE = new Color(255, 200, 0, 255);
    public final static Color MAGENTA = new Color(255, 0, 255, 255);

    public float r;
    public float g;
    public float b;
    public float a;

    public Color(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public Color(float r, float g, float b) {
        this(r, g, b, 1f);
    }

    public Color(int r, int g, int b, int a) {
        this(r / 255f, g / 255f, b / 255f, a / 255f);
    }

    public Color(int r, int g, int b) {
        this(r, g, b, 255);
    }
}