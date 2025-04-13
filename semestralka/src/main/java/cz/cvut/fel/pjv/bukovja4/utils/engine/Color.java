package cz.cvut.fel.pjv.bukovja4.utils.engine;

/**
 * Represents RGBA colors for rendering in the game engine.
 * Provides standard predefined colors and support for custom color creation.
 */
public final class Color {

    /** Fully transparent color (0,0,0,0) */
    public static final Color TRANSPARENT = new Color(0.0f, 0.0f, 0.0f, 0.0f);
    /** White color (1,1,1,1) */
    public static final Color WHITE = new Color(1.0f, 1.0f, 1.0f, 1.0f);
    /** Yellow color (1,1,0,1) */
    public static final Color YELLOW = new Color(1.0f, 1.0f, 0, 1.0f);
    /** Red color (1,0,0,1) */
    public static final Color RED = new Color(1.0f, 0, 0, 1.0f);
    /** Blue color (0,0,1,1) */
    public static final Color BLUE = new Color(0, 0, 1.0f, 1.0f);
    /** Green color (0,1,0,1) */
    public static final Color GREEN = new Color(0, 1.0f, 0, 1.0f);
    /** Black color (0,0,0,1) */
    public static final Color BLACK = new Color(0, 0, 0, 1.0f);
    /** Gray color (0.5,0.5,0.5,1) */
    public static final Color GRAY = new Color(0.5f, 0.5f, 0.5f, 1.0f);
    /** Cyan color (0,1,1,1) */
    public static final Color CYAN = new Color(0, 1.0f, 1.0f, 1.0f);
    /** Dark gray color (0.3,0.3,0.3,1) */
    public static final Color DARK_GRAY = new Color(0.3f, 0.3f, 0.3f, 1.0f);
    /** Light gray color (0.7,0.7,0.7,1) */
    public static final Color LIGHT_GRAY = new Color(0.7f, 0.7f, 0.7f, 1.0f);
    /** Pink color (255,175,175,255) */
    public final static Color PINK = new Color(255, 175, 175, 255);
    /** Orange color (255,200,0,255) */
    public final static Color ORANGE = new Color(255, 200, 0, 255);
    /** Magenta color (255,0,255,255) */
    public final static Color MAGENTA = new Color(255, 0, 255, 255);

    /** Red component (0.0-1.0) */
    public float r;
    /** Green component (0.0-1.0) */
    public float g;
    /** Blue component (0.0-1.0) */
    public float b;
    /** Alpha/transparency component (0.0-1.0) */
    public float a;

    /**
     * Creates a color with the specified RGBA components in float format (0.0-1.0).
     * 
     * @param r Red component (0.0-1.0)
     * @param g Green component (0.0-1.0)
     * @param b Blue component (0.0-1.0)
     * @param a Alpha component (0.0-1.0)
     */
    public Color(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    /**
     * Creates an opaque color with the specified RGB components in float format (0.0-1.0).
     * Alpha is set to 1.0 (fully opaque).
     * 
     * @param r Red component (0.0-1.0)
     * @param g Green component (0.0-1.0)
     * @param b Blue component (0.0-1.0)
     * @see #Color(float, float, float, float) For specifying alpha
     */
    public Color(float r, float g, float b) {
        this(r, g, b, 1f);
    }

    /**
     * Creates a color with the specified RGBA components in integer format (0-255).
     * Values are converted to the float range (0.0-1.0).
     * 
     * @param r Red component (0-255)
     * @param g Green component (0-255)
     * @param b Blue component (0-255) 
     * @param a Alpha component (0-255)
     * @see #Color(float, float, float, float) For float format
     */
    public Color(int r, int g, int b, int a) {
        this(r / 255f, g / 255f, b / 255f, a / 255f);
    }

    /**
     * Creates an opaque color with the specified RGB components in integer format (0-255).
     * Alpha is set to 255 (fully opaque).
     * 
     * @param r Red component (0-255)
     * @param g Green component (0-255)
     * @param b Blue component (0-255)
     * @see #Color(int, int, int, int) For specifying alpha
     */
    public Color(int r, int g, int b) {
        this(r, g, b, 255);
    }
}