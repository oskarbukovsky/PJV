package cz.cvut.fel.pjv.bukovja4.utils.engine;


import org.lwjgl.opengl.GL11;

import cz.cvut.fel.pjv.bukovja4.utils.constants.Const;

public class TextUtils {
    public final static int FONT_WIDTH = 8;
    public final static int FONT_HEIGHT = 12;

    private static float[] hexToRGBA(int color, int alpha) {
        float[] rgba = new float[4];

        rgba[0] = ((color & 0xFF0000) >> 16) / 255.0f;
        rgba[1] = ((color & 0x00FF00) >> 8) / 255.0f;
        rgba[2] = ((color & 0x0000FF) >> 0) / 255.0f;
        rgba[3] = alpha / 255.0f;

        return rgba;
    }

    public static void drawText(String string, int x, int y) {
        drawText(string, x, y, 1.0f, 0xFFFFFF, 0xFF);
    }

    public static void drawText(String string, int x, int y, float scale) {
        drawText(string, x, y, scale, 0xFFFFFF, 0xFF);
    }

    public static void drawText(String string, int x, int y, int color, int alpha) {
        drawText(string, x, y, 1.0f, color, alpha);
    }

    public static void drawText(String string, int x, int y, int color) {
        drawText(string, x, y, 1.0f, color, 0xFF);
    }

    public static void drawText(String string, int x, int y, float scale, int color, int alpha) {
        Sprite font = SpriteManager.getSprite(Const.DEFAULT_FONT);

        float[] rgba = hexToRGBA(color, alpha);

        for (int i = 0; i < string.length(); i++) {
            float offsetStart = (1.0f / 128) * string.charAt(i);
            float offsetEnd = offsetStart + (1.0f / 128);

            font.draw((int) (x + i * FONT_WIDTH * scale), y, scale, FONT_WIDTH, FONT_HEIGHT, offsetStart, 0, offsetEnd, 0, offsetEnd, 1, offsetStart, 1, rgba[0], rgba[1], rgba[2], rgba[3]);
        }
    }

    public static void drawChar(int c, int x, int y, int color, int alpha) {
        float offsetStart = (1.0f / 128) * c;
        float offsetEnd = offsetStart + (1.0f / 128);

        float[] rgba = hexToRGBA(color, alpha);

        Sprite font = SpriteManager.getSprite(Const.DEFAULT_FONT);
        font.draw(x, y, 1.0f, FONT_WIDTH, FONT_HEIGHT, offsetStart, 0, offsetEnd, 0, offsetEnd, 1, offsetStart, 1, rgba[0], rgba[1], rgba[2], rgba[3]);
    }

    public static void drawRect(int x, int y, int width, int height, int color, int alpha) {
        float[] rgba = hexToRGBA(color, alpha);

        GL11.glColor4f(rgba[0], rgba[1], rgba[2], rgba[3]);

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(x, y);
        GL11.glVertex2f(x + width, y);
        GL11.glVertex2f(x + width, y + height);
        GL11.glVertex2f(x, y + height);
        GL11.glEnd();

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
}
