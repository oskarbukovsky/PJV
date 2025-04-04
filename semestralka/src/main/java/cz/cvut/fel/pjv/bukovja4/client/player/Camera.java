package cz.cvut.fel.pjv.bukovja4.client.player;

import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.*;

public class Camera<D extends Dim> {
    public static Player<?> player;
    private static float zoom;

    public static Player<?> getPlayer() {
        return Camera.player;
    }

    Camera(Player<D> player, float zoom) {
        Camera.player = player;
        Camera.zoom = zoom;
    }

    public static float getZoom() {
        return zoom;
    }

    public static void setZoom(float zoom) {
        if (zoom > 0) {
            Camera.zoom = zoom;
        }
    }
}
