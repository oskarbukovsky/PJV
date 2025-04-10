package cz.cvut.fel.pjv.bukovja4.utils.engine;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.STBImage;

import static org.lwjgl.opengl.GL11.*;

public class Sprite {
    private int textureID;
    private IntBuffer width;
    private IntBuffer height;
    private IntBuffer channels;

    public int getWidth() {
        return width.get(0);
    }

    public int getHeight() {
        return height.get(0);
    }

    public Sprite(String resourcePath) {
        String path = null;
        width = BufferUtils.createIntBuffer(1);
        height = BufferUtils.createIntBuffer(1);
        channels = BufferUtils.createIntBuffer(1);

        try {
            path = new File(getClass().getResource(resourcePath).toURI()).getPath();
        } catch (final Exception ignored) {
        }

        ByteBuffer bb = STBImage.stbi_load(path, width, height, channels, 4);

        textureID = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, textureID);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, getWidth(), getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, bb);

        STBImage.stbi_image_free(bb);
    }

    public void draw(int x, int y, float scale) {
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(x, y);
        GL11.glTexCoord2f(1, 0);
        GL11.glVertex2f(x + getWidth() * scale, y);
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex2f(x + getWidth() * scale, y + getHeight() * scale);
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex2f(x, y + getHeight() * scale);
        GL11.glEnd();

        GL11.glDisable(GL11.GL_TEXTURE_2D);
    }
}
