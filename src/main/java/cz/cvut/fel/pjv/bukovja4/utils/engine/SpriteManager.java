package cz.cvut.fel.pjv.bukovja4.utils.engine;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages the loading and retrieval of sprites.
 * Provides functionality to load and access sprites by their file paths.
 */

public class SpriteManager {
    /// Map to store loaded sprites
    private static final Map<String, Sprite> sprites = new HashMap<>();

    /**
     * Loads a sprite from the specified file path.
     * If the sprite is already loaded, it retrieves it from the map.
     * 
     * @param path The file path of the sprite
     * @return The loaded sprite
     */
    public static Sprite loadSprite(String path) throws URISyntaxException {
        if (sprites.containsKey(path)) {
            return sprites.get(path);
        } else {
            Sprite sprite = new Sprite("/" + path);
            sprites.put(path, sprite);
            return sprite;
        }
    }

    /**
     * Retrieves a sprite by its file path.
     * 
     * @param path The file path of the sprite
     * @return The sprite if it exists, null otherwise
     */
    public static Sprite getSprite(String path) {
        return sprites.get(path);
    }

    /**
     * Clears all loaded sprites from the manager.
     */

    public static void clearSprites() {
        sprites.clear();
    }
}
