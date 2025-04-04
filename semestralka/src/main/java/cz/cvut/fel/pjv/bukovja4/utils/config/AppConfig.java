package cz.cvut.fel.pjv.bukovja4.utils.config;

public class AppConfig extends YAMLPrint {
    public static class Window extends YAMLPrint {
        public float fov;
        
        public boolean fullscreen;
        
        public int fpsLock;
        
        public int width;

        public int height;

        public Window() {
        }
    }

    public Window window;

    public AppConfig() {
    }
}