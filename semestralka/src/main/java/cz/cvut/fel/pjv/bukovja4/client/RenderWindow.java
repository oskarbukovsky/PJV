package cz.cvut.fel.pjv.bukovja4.client;

import org.lwjgl.opengl.GL;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.stb.STBImage;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class RenderWindow {
    public static final void Render() {
        glClearColor(0.8f, 0f, 0.7f, 0f);
    }
}
