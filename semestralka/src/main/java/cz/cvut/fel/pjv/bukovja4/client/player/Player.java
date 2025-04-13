package cz.cvut.fel.pjv.bukovja4.client.player;

import cz.cvut.fel.pjv.bukovja4.utils.engine.*;
import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.Dim;

/**
 * Represents a player character in the game world.
 * This class manages the player's position, view direction, and field of view.
 *
 * @param <D> The dimensional type of the space the player exists in
 */
public class Player<D extends Dim> {
    /**
     * The current position of the player in the game world
     */
    public Pos<D> position;

    /**
     * The direction the player is looking in
     */
    public Vector<D> viewAngle;

    /**
     * The player's field of view angle in degrees
     */
    public float fov;

    /**
     * Creates a new player with the specified position, view direction, and field
     * of view.
     *
     * @param position  The initial position of the player as a {@link Pos} object
     * @param viewAngle The initial view direction of the player as a {@link Vector}
     *                  object
     * @param fov       The field of view angle in degrees as a float value
     */
    public Player(Pos<D> position, Vector<D> viewAngle, float fov) {
        this.position = position;
        this.viewAngle = viewAngle;
        this.fov = fov;
    }
}
