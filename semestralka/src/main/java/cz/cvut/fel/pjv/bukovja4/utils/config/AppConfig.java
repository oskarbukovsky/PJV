package cz.cvut.fel.pjv.bukovja4.utils.config;

public class AppConfig extends YAMLPrint {
    public static class Window extends YAMLPrint {
        public int width;

        public int height;

        public boolean fullscreen;

        public int fpsLock;

        public Window() {
        }
    }

    public Window window;

    public AppConfig() {
    }
}