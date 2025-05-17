package cz.cvut.fel.pjv.bukovja4.utils.engine;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBImage;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

import static org.lwjgl.opengl.GL11.*;

/**
 * Represents a 2D texture loaded from an image file for rendering with OpenGL.
 * Handles texture loading, storage and drawing operations.
 * 
 * @see org.lwjgl.stb.STBImage For the underlying image loading implementation
 * @see org.lwjgl.opengl.GL11 For texture rendering functions
 */
public class Sprite {
    /** OpenGL texture identifier */
    private int textureID;
    /** Buffer containing the sprite width in pixels */
    private IntBuffer width;
    /** Buffer containing the sprite height in pixels */
    private IntBuffer height;
    /** Buffer containing the number of color channels */
    private IntBuffer channels;

    /**
     * Gets the width of the sprite in pixels.
     * 
     * @return The sprite width
     */
    public int getWidth() {
        return width.get(0);
    }

    /**
     * Gets the height of the sprite in pixels.
     * 
     * @return The sprite height
     */
    public int getHeight() {
        return height.get(0);
    }

    /**
     * Creates a new sprite by loading an image from the specified resource path.
     * Converts the image data into an OpenGL texture with RGBA format.
     * 
     * @param resourcePath Path to the image resource
     */
    public Sprite(String resourcePath) throws URISyntaxException {
        this(resourcePath, false);
    }

    /**
     * Creates a new sprite by loading an image from the specified resource path.
     * Converts the image data into an OpenGL texture with RGBA format.
     * 
     * @param resourcePath Path to the image resource
     * @param blackAsAlpha If true, black pixels are treated as transparent
     */
    public Sprite(String resourcePath, boolean blackAsAlpha) throws URISyntaxException {
        // String path = Main.class.getResource(resourcePath).getPath();
        String path = null;
        width = BufferUtils.createIntBuffer(1);
        height = BufferUtils.createIntBuffer(1);
        channels = BufferUtils.createIntBuffer(1);

        try {
            path = new File(getClass().getResource(resourcePath).toURI()).getPath();
            LOG.debug(("Loading sprite: " + path));
        } catch (URISyntaxException exception) {
            LOG.error("Failed to load sprite: " + resourcePath, exception);
        }

        ByteBuffer bb = STBImage.stbi_load(path, width, height, channels, 4);

        for (int i = 0; i < width.get(0) * height.get(0) * 4; i += 4) {
            byte red = bb.get(i);
            byte green = bb.get(i + 1);
            byte blue = bb.get(i + 2);

            if (red == 0 && green == 0 && blue == 0 && blackAsAlpha) {
                bb.put(i + 3, (byte) 0);
            }
        }

        textureID = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, textureID);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, getWidth(), getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, bb);

        STBImage.stbi_image_free(bb);
    }

    public void draw(int x, int y, float scale, int width, int height, float topLeftX, float topLeftY, float topRightX,
            float topRightY, float bottomRightX, float bottomRightY, float bottomLeftX, float bottomLeftY, float red,
            float green, float blue, float alpha) {
        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, textureID);
        glColor4f(red, green, blue, alpha);

        glBegin(GL_QUADS);
        glTexCoord2f(topLeftX, topLeftY);
        glVertex2f(x, y);
        glTexCoord2f(topRightX, topRightY);
        glVertex2f(x + width * scale, y);
        glTexCoord2f(bottomRightX, bottomRightY);
        glVertex2f(x + width * scale, y + height * scale);
        glTexCoord2f(bottomLeftX, bottomLeftY);
        glVertex2f(x, y + height * scale);
        glEnd();

        glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        glDisable(GL_TEXTURE_2D);
    }

    /**
     * Draws the sprite at the specified position with scaling.
     * Uses OpenGL quad rendering with texture coordinates.
     * 
     * @param x     X coordinate for the top-left corner
     * @param y     Y coordinate for the top-left corner
     * @param scale Scaling factor for the sprite dimensions
     * @see org.lwjgl.opengl.GL11#glBegin(int) For starting the rendering
     * @see org.lwjgl.opengl.GL11#glTexCoord2f(float, float) For texture mapping
     */
    public void draw(int x, int y, float scale) {
        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, textureID);

        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2f(x, y);
        glTexCoord2f(1, 0);
        glVertex2f(x + getWidth() * scale, y);
        glTexCoord2f(1, 1);
        glVertex2f(x + getWidth() * scale, y + getHeight() * scale);
        glTexCoord2f(0, 1);
        glVertex2f(x, y + getHeight() * scale);
        glEnd();

        glDisable(GL_TEXTURE_2D);
    }

    /**
     * Draws the sprite at the specified position with scaling.
     * Uses OpenGL quad rendering with texture coordinates.
     * 
     * @param x     X coordinate for the top-left corner
     * @param y     Y coordinate for the top-left corner
     * @param scale Scaling factor for the sprite dimensions
     */
    public void draw(float x, float y, float scale) {
        draw((int) x, (int) y, scale);
    }

    /**
     * Draws the sprite at the specified position with scaling.
     * Uses OpenGL quad rendering with texture coordinates.
     * 
     * @param x     X coordinate for the top-left corner
     * @param y     Y coordinate for the top-left corner
     * @param scale Scaling factor for the sprite dimensions
     */
    public void draw(float x, float y, int scale) {
        draw((int) x, (int) y, (float) scale);
    }
}
