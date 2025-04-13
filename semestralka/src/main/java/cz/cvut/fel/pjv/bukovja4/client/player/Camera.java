package cz.cvut.fel.pjv.bukovja4.client.player;

import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.*;

/**
 * Represents a camera in the game world that follows a player.
 * This class manages the zoom level and tracks the player being followed.
 *
 * @param <D> The dimensional type of the space the camera operates in
 */
public class Camera<D extends Dim> {
    // The player that the camera is following
    public static Player<?> player;

    // Current zoom level of the camera
    private static float zoom;

    /**
     * Gets the player that the camera is following.
     * 
     * @return The player object currently being tracked by the camera
     */
    public static Player<?> getPlayer() {
        return Camera.player;
    }

    /**
     * Creates a new camera that follows the specified player with the given zoom
     * level.
     * 
     * @param player The player to follow
     * @param zoom   The initial zoom level (higher values = zoomed out further)
     */
    Camera(Player<D> player, float zoom) {
        Camera.player = player;
        Camera.zoom = zoom;
    }

    /**
     * Gets the current zoom level of the camera.
     * 
     * @return The current zoom factor
     */
    public static float getZoom() {
        return zoom;
    }

    /**
     * Sets the zoom level of the camera.
     * Only accepts positive values as zoom levels.
     * 
     * @param zoom The new zoom level to set (must be > 0)
     */
    public static void setZoom(float zoom) {
        if (zoom > 0) {
            Camera.zoom = zoom;
        }
    }
}
