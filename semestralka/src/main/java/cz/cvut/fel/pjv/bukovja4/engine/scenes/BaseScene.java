package cz.cvut.fel.pjv.bukovja4.engine.scenes;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
// import java.util.function.Function;

import org.yaml.snakeyaml.Yaml;

import cz.cvut.fel.pjv.bukovja4.GameLoop;
import cz.cvut.fel.pjv.bukovja4.Main;
import cz.cvut.fel.pjv.bukovja4.client.Window;
import cz.cvut.fel.pjv.bukovja4.engine.actions.Actions;
import cz.cvut.fel.pjv.bukovja4.engine.elements.BaseElement;
import cz.cvut.fel.pjv.bukovja4.engine.elements.ElementFactory;
import cz.cvut.fel.pjv.bukovja4.engine.elements.ElementTypes;
import cz.cvut.fel.pjv.bukovja4.engine.elements.specific.*;
import cz.cvut.fel.pjv.bukovja4.engine.logic.GameState;
import cz.cvut.fel.pjv.bukovja4.utils.audio.Audio;
import cz.cvut.fel.pjv.bukovja4.utils.audio.Playback;
import cz.cvut.fel.pjv.bukovja4.utils.engine.Box;
import cz.cvut.fel.pjv.bukovja4.utils.engine.Pos;
import cz.cvut.fel.pjv.bukovja4.utils.engine.TextUtils;
import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;
import cz.cvut.fel.pjv.bukovja4.utils.maze.CellTypes;
import cz.cvut.fel.pjv.bukovja4.utils.maze.Maze;
import cz.cvut.fel.pjv.bukovja4.utils.maze.MazeCell;
import cz.cvut.fel.pjv.bukovja4.engine.logic.controls.*;
// import cz.cvut.fel.pjv.bukovja4.engine.scenes.types.Game2d;

/**
 * Abstract base class for all game scenes.
 * Provides functionality for loading scene data from YAML files,
 * managing scene elements, and handling scene lifecycle.
 */
public abstract class BaseScene {
    // Name of the scene file being used
    private static String file;

    // Audio playback instance for the scene
    private static Playback audioPlayback;

    // List of all elements contained within the scene
    protected static ArrayList<BaseElement<?>> elements = new ArrayList<>();

    public static int[] mazeStart = new int[] { 0, 0 };
    public static int[] mazeEnd = new int[] { 0, 0 };
    public static float scale = 1;

    /**
     * Gets elements of the scene.
     * 
     * @return List of elements in the scene
     */
    public ArrayList<BaseElement<?>> getElements() {
        return BaseScene.elements;
    }

    /**
     * Loads a scene from a YAML file.
     * 
     * @param sceneName Name of the scene file to load (without extension)
     * @throws Throwable If any error occurs during scene loading
     */
    @SuppressWarnings("unchecked")
    public static void Load(String sceneName) throws Throwable, Exception {
        BaseScene.file = sceneName;
        LOG.info("Loading scene: " + sceneName);

        Yaml yaml = new Yaml();
        Map<String, Object> data = yaml.load(Main.class.getResourceAsStream("/scenes/" + sceneName));
        LOG.debug("Scene: " + data.toString());

        // Setting some values
        Number _dimension = (Number) data.get("dimension");
        int dim = 2;
        if (_dimension != null) {
            dim = _dimension.intValue();
        }
        String subtitle = (String) data.get("title");
        Number _scaleMultiplier = ((Number) data.get("scale"));
        if (_scaleMultiplier != null) {
            scale = _scaleMultiplier.floatValue();
        }
        if (subtitle != null) {
            Window.setSubtitle(subtitle);
        } else {
            Window.setSubtitle("");
        }
        ElementFactory<?> factory = ElementFactory.getFactory(dim);

        // Prepare maze for level scene types
        Number _seedValue = ((Number) data.get("seed"));
        int seed = 0;
        if (_seedValue != null) {
            seed = _seedValue.intValue();
        }
        if (sceneName.contains("levels")) {
            ArrayList<Map<String, Object>> levelDimensions = (ArrayList<Map<String, Object>>) data.get("size");
            Maze.prepareMaze(seed, ((Number) levelDimensions.get(0)).intValue(),
                    ((Number) levelDimensions.get(1)).intValue());
            GameState.next_level = (String) data.get("next_level");
        }
        ArrayList<Object> _startingPos = (ArrayList<Object>) data.get("mazeStart");

        if (_startingPos != null) {
            mazeStart = new int[] { ((Number) _startingPos.get(0)).intValue(),
                    ((Number) _startingPos.get(1)).intValue() };
        }
        ArrayList<Object> _endingPos = (ArrayList<Object>) data.get("mazeEnd");

        if (_endingPos != null) {
            mazeEnd = new int[] { ((Number) _endingPos.get(0)).intValue(), ((Number) _endingPos.get(1)).intValue() };
        }

        if (!sceneName.contains("levels")) {
            for (Map<String, Object> dataEntry : (ArrayList<Map<String, Object>>) data.get("elements")) {

                // Setup menu specific elements things
                BaseElement<? extends BaseElement<?>> element = null;
                String type = ((String) dataEntry.get("type")).toUpperCase();
                ArrayList<ArrayList<Object>> position = (ArrayList<ArrayList<Object>>) dataEntry.get("position");
                String align = (String) dataEntry.get("align");

                // Adjust position values
                for (int i = 0; i < position.size(); i++) {
                    ArrayList<Object> pos = position.get(i);
                    ArrayList<Object> newPos = new ArrayList<>(pos.size());

                    // Convert special screen variables to actual coordinates
                    for (Object item : pos) {
                        switch (item.toString()) {
                            case "screen.minX" -> newPos.add(0f);
                            case "screen.minY" -> newPos.add(0f);
                            case "screen.maxX" -> newPos.add((float) GameLoop.getConfig().window.width);
                            case "screen.maxY" -> newPos.add((float) GameLoop.getConfig().window.height);
                            default -> newPos.add(item);
                        }
                    }

                    // Apply alignment transform if specified
                    if (align != null) {
                        float screenWidth = (float) GameLoop.getConfig().window.width;
                        float screenHeight = (float) GameLoop.getConfig().window.height;
                        float screenCenterX = screenWidth / 2.0f;
                        float screenCenterY = screenHeight / 2.0f;

                        ArrayList<Object> alignedPos = new ArrayList<>(newPos.size());

                        switch (align) {
                            case "center" -> {
                                if (newPos.size() >= 2) {
                                    alignedPos.add(screenCenterX + ((Number) newPos.get(0)).floatValue());
                                    alignedPos.add(screenCenterY + ((Number) newPos.get(1)).floatValue());
                                    for (int j = 2; j < newPos.size(); j++) {
                                        alignedPos.add(newPos.get(j));
                                    }
                                }
                            }
                            case "left" -> {
                                if (newPos.size() >= 2) {
                                    alignedPos.add(((Number) newPos.get(0)).floatValue());
                                    alignedPos.add(screenCenterY + ((Number) newPos.get(1)).floatValue());

                                    for (int j = 2; j < newPos.size(); j++) {
                                        alignedPos.add(newPos.get(j));
                                    }
                                }
                            }
                            case "right" -> {
                                if (newPos.size() >= 2) {
                                    alignedPos.add(screenWidth - ((Number) newPos.get(0)).floatValue());
                                    alignedPos.add(screenCenterY + ((Number) newPos.get(1)).floatValue());

                                    for (int j = 2; j < newPos.size(); j++) {
                                        alignedPos.add(newPos.get(j));
                                    }
                                }
                            }
                            case "top" -> {
                                if (newPos.size() >= 2) {
                                    alignedPos.add(screenCenterX + ((Number) newPos.get(0)).floatValue());
                                    alignedPos.add(((Number) newPos.get(1)).floatValue());

                                    for (int j = 2; j < newPos.size(); j++) {
                                        alignedPos.add(newPos.get(j));
                                    }
                                }
                            }
                            case "bottom" -> {
                                if (newPos.size() >= 2) {
                                    alignedPos.add(screenCenterX + ((Number) newPos.get(0)).floatValue());
                                    alignedPos.add(screenHeight - ((Number) newPos.get(1)).floatValue());

                                    for (int j = 2; j < newPos.size(); j++) {
                                        alignedPos.add(newPos.get(j));
                                    }
                                }
                            }
                            default -> {
                                alignedPos = newPos;
                            }
                        }
                        position.set(i, alignedPos);
                    } else {
                        position.set(i, newPos);
                    }
                }

                // Prepare bounding box for element

                @SuppressWarnings("rawtypes")
                Box bounds;
                // LOG.warn(type);
                if (type.equals("LABEL") || type.equals("GOAL") || type.equals("PLAYER")) {
                    if (type.equals("GOAL") || type.equals("PLAYER")) {
                        if (type.equals("PLAYER")) {
                            mazeStart[0] = ((Number) position.get(0).get(0)).intValue();
                            mazeStart[1] = ((Number) position.get(0).get(1)).intValue();
                        } else {
                            mazeEnd[0] = ((Number) position.get(0).get(0)).intValue();
                            mazeEnd[1] = ((Number) position.get(0).get(1)).intValue();
                        }
                        bounds = new Box<>(
                                new Pos<>(
                                        ((Number) position.get(0).get(0)).floatValue() * (dataEntry.get("scale") != null
                                                ? ((Number) dataEntry.get("scale")).floatValue()
                                                : 1.0f) * 20,
                                        ((Number) position.get(0).get(1)).floatValue() * (dataEntry.get("scale") != null
                                                ? ((Number) dataEntry.get("scale")).floatValue()
                                                : 1.0f) * 20),
                                new Pos<>(
                                        (((Number) position.get(0).get(0)).floatValue() + 1)
                                                * (dataEntry.get("scale") != null
                                                        ? ((Number) dataEntry.get("scale")).floatValue()
                                                        : 1.0f)
                                                * 20,
                                        (((Number) position.get(0).get(1)).floatValue() + 1)
                                                * (dataEntry.get("scale") != null
                                                        ? ((Number) dataEntry.get("scale")).floatValue()
                                                        : 1.0f)
                                                * 20));
                    } else {
                        switch (dim) {
                            case 1 -> {
                                bounds = new Box<>(
                                        new Pos<>(((Number) position.get(0).get(0)).floatValue()),
                                        new Pos<>(
                                                ((Number) position.get(0).get(0)).floatValue()
                                                        + ((String) dataEntry.get("text")).length()
                                                                * TextUtils.FONT_WIDTH
                                                                * (dataEntry.get("scale") != null
                                                                        ? ((Number) dataEntry.get("scale")).floatValue()
                                                                        : 1.0f)));
                            }
                            case 2 -> {
                                bounds = new Box<>(
                                        new Pos<>(
                                                ((Number) position.get(0).get(0)).floatValue(),
                                                ((Number) position.get(0).get(1)).floatValue()),
                                        new Pos<>(
                                                ((Number) position.get(0).get(0)).floatValue()
                                                        + ((String) dataEntry.get("text")).length()
                                                                * TextUtils.FONT_WIDTH
                                                                * (dataEntry.get("scale") != null
                                                                        ? ((Number) dataEntry.get("scale")).floatValue()
                                                                        : 1.0f),
                                                ((Number) position.get(0).get(1)).floatValue() + TextUtils.FONT_HEIGHT
                                                        * (dataEntry.get("scale") != null
                                                                ? ((Number) dataEntry.get("scale")).floatValue()
                                                                : 1.0f)));
                            }
                            default -> throw new IllegalArgumentException("Invalid dimension: " + dim);
                        }
                    }
                } else {
                    switch (dim) {
                        case 1 -> {
                            bounds = new Box<>(
                                    new Pos<>(((Number) position.get(0).get(0)).floatValue()),
                                    new Pos<>(((Number) position.get(1).get(0)).floatValue()));
                        }
                        case 2 -> {
                            bounds = new Box<>(
                                    new Pos<>(
                                            ((Number) position.get(0).get(0)).floatValue(),
                                            ((Number) position.get(0).get(1)).floatValue()),
                                    new Pos<>(
                                            ((Number) position.get(1).get(0)).floatValue(),
                                            ((Number) position.get(1).get(1)).floatValue()));
                        }
                        case 3 -> {
                            bounds = new Box<>(
                                    new Pos<>(
                                            ((Number) position.get(0).get(0)).floatValue(),
                                            ((Number) position.get(0).get(1)).floatValue(),
                                            ((Number) position.get(0).get(2)).floatValue()),
                                    new Pos<>(
                                            ((Number) position.get(1).get(0)).floatValue(),
                                            ((Number) position.get(1).get(1)).floatValue(),
                                            ((Number) position.get(1).get(2)).floatValue()));
                        }
                        case 4 -> {
                            bounds = new Box<>(
                                    new Pos<>(
                                            ((Number) position.get(0).get(0)).floatValue(),
                                            ((Number) position.get(0).get(1)).floatValue(),
                                            ((Number) position.get(0).get(2)).floatValue(),
                                            ((Number) position.get(0).get(3)).floatValue()),
                                    new Pos<>(
                                            ((Number) position.get(1).get(0)).floatValue(),
                                            ((Number) position.get(1).get(1)).floatValue(),
                                            ((Number) position.get(1).get(2)).floatValue(),
                                            ((Number) position.get(1).get(3)).floatValue()));
                        }
                        default -> throw new IllegalArgumentException("Invalid dimension: " + dim);
                    }
                }

                element = factory.create(ElementTypes.valueOf(type), bounds);

                switch (ElementTypes.valueOf(type)) {
                    case ElementTypes.LABEL -> {
                        if (dataEntry.get("scale") != null) {
                            element.init((String) dataEntry.get("text"),
                                    ((Number) dataEntry.get("scale")).floatValue());
                        } else {
                            element.init((String) dataEntry.get("text"));
                        }
                    }
                    case ElementTypes.IMAGE -> {
                        if (dataEntry.get("scale") != null) {
                            LOG.warn("Scale: " + ((Number) dataEntry.get("scale")).floatValue());
                            element.init(
                                    "imgs/" + sceneName.substring(0, sceneName.lastIndexOf("/")) + "/"
                                            + (String) dataEntry.get("src"),
                                    ((Number) dataEntry.get("scale")).floatValue());
                        } else {
                            element.init("imgs/" + sceneName.substring(0, sceneName.lastIndexOf("/")) + "/"
                                    + (String) dataEntry.get("src"));
                        }
                    }
                    case ElementTypes.WALL -> {
                        ((Wall<?>) element).dispatchInit((dataEntry.get("scale") != null
                                ? ((Number) dataEntry.get("scale")).floatValue()
                                : 1.0f));
                    }
                    case ElementTypes.GROUND -> {
                        ((Ground<?>) element).dispatchInit((dataEntry.get("scale") != null
                                ? ((Number) dataEntry.get("scale")).floatValue()
                                : 1.0f));
                    }
                    case ElementTypes.PLAYER -> {
                        LOG.warn(element.getClass().getName());
                        ((Player<?>) element).dispatchInit((dataEntry.get("scale") != null
                                ? ((Number) dataEntry.get("scale")).floatValue()
                                : 1.0f));
                    }
                    case ElementTypes.GOAL -> {
                        ((Goal<?>) element).dispatchInit((dataEntry.get("scale") != null
                                ? ((Number) dataEntry.get("scale")).floatValue()
                                : 1.0f));
                    }
                    case ElementTypes.BUTTON -> element.init((String) dataEntry.get("texture"));
                    default -> throw new IllegalArgumentException("Invalid element type: " + type);
                }

                BaseScene.elements.add(element);

                if (sceneName.contains("levels")) {
                    GameState.maze[((Number) position.get(0).get(0)).intValue()][((Number) position.get(0).get(1))
                            .intValue()] = new MazeCell(CellTypes.valueOf(type),
                                    element);
                }

                ArrayList<LinkedHashMap<String, String>> actions = (ArrayList<LinkedHashMap<String, String>>) dataEntry
                        .get("actions");
                if (actions != null) {
                    for (LinkedHashMap<String, String> action : actions) {
                        for (Map.Entry<String, String> entry : action.entrySet()) {
                            ControlTypes controlType = ControlTypes.valueOf(entry.getKey().toUpperCase());
                            Method callbackMethod = Actions.class.getMethod(entry.getValue());

                            GameState.controls.register(new Selector(element, controlType), (args) -> {
                                try {
                                    callbackMethod.invoke(null);
                                } catch (final Exception e) {
                                    LOG.error("Error invoking action: " + entry.getValue(), e, true);
                                }
                                return null;
                            });
                        }
                    }
                }
            }
        }

        // Audio
        LinkedHashMap<String, LinkedHashMap<String, ?>> audioData = (LinkedHashMap<String, LinkedHashMap<String, ?>>) data
                .get("audio");
        if (audioData != null) {
            String audioSrc = (String) (Object) audioData.get("src");
            if (audioSrc != null) {
                Boolean audioLoop = (Boolean) (Object) audioData.get("loop");
                if (audioLoop == null) {
                    audioLoop = false;
                }
                audioPlayback = Audio.play(sceneName.substring(0, sceneName.lastIndexOf("/")) + "/" + audioSrc,
                        audioLoop);
            }
        }

        if (sceneName.contains("levels")) {
            Maze.generateMaze(BaseScene.elements, mazeStart[0], mazeStart[1], mazeEnd[0], mazeEnd[1]);
            Maze.finishMaze(scale);
        }

        LOG.info("Loaded scene: " + sceneName);
    }

    /**
     * Unloads the scene and cleans up resources.
     * This method unregisters all control handlers and logs the unloading process.
     */
    public void Unload() {
        GameState.controls.unRegisterAll();
        BaseScene.elements.clear();
        audioPlayback.stop();
        LOG.info("Unloaded scene: " + file);
    }

    /**
     * Updates the scene state for the current frame.
     * This method must be implemented by any concrete scene class.
     * After executing this method, the scene elements will be updated
     * automatically.
     */
    public abstract void Tick();

    /**
     * Updates the scene state for the current frame.
     * This method must be called in Tick() to ensure proper scene updates.
     */
    public final void tick() {
        this.Tick();
        for (BaseElement<?> element : elements) {
            element.tick();
        }
    }
}
