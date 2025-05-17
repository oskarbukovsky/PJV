package cz.cvut.fel.pjv.bukovja4.engine.scenes;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
// import java.util.function.Function;

import org.yaml.snakeyaml.Yaml;

import cz.cvut.fel.pjv.bukovja4.GameLoop;
import cz.cvut.fel.pjv.bukovja4.Main;
import cz.cvut.fel.pjv.bukovja4.engine.actions.Actions;
import cz.cvut.fel.pjv.bukovja4.engine.elements.BaseElement;
import cz.cvut.fel.pjv.bukovja4.engine.elements.ElementFactory;
import cz.cvut.fel.pjv.bukovja4.engine.elements.ElementTypes;
import cz.cvut.fel.pjv.bukovja4.engine.logic.GameState;
import cz.cvut.fel.pjv.bukovja4.utils.audio.Audio;
import cz.cvut.fel.pjv.bukovja4.utils.audio.Playback;
import cz.cvut.fel.pjv.bukovja4.utils.engine.Box;
import cz.cvut.fel.pjv.bukovja4.utils.engine.Pos;
import cz.cvut.fel.pjv.bukovja4.utils.engine.TextUtils;
import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;
import cz.cvut.fel.pjv.bukovja4.engine.logic.controls.*;

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
        file = sceneName;
        LOG.info("Loading scene: " + sceneName);

        Yaml yaml = new Yaml();
        Map<String, Object> data = yaml.load(Main.class.getResourceAsStream("/scenes/" + sceneName));
        LOG.debug("Scene: " + data.toString());

        int dim = (int) data.get("dimension");

        @SuppressWarnings("rawtypes")
        ElementFactory factory = ElementFactory.getFactory(dim);

        for (Map<String, Object> dataEntry : (ArrayList<Map<String, Object>>) data.get("elements")) {

            BaseElement<?> element = null;

            String type = ((String) dataEntry.get("type")).toUpperCase();
            // LOG.warn(type);

            ArrayList<ArrayList<Object>> position = (ArrayList<ArrayList<Object>>) dataEntry.get("position");
            String align = (String) dataEntry.get("align");

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

            @SuppressWarnings("rawtypes")
            Box bounds;
            if (type.equals("LABEL")) {
                switch (dim) {
                    case 1 -> {
                        bounds = new Box<>(
                                new Pos<>(((Number) position.get(0).get(0)).floatValue()),
                                new Pos<>(
                                        ((Number) position.get(0).get(0)).floatValue()
                                                + ((String) dataEntry.get("text")).length() * TextUtils.FONT_WIDTH
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
                                                + ((String) dataEntry.get("text")).length() * TextUtils.FONT_WIDTH
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
                        element.init((String) dataEntry.get("text"), ((Number) dataEntry.get("scale")).floatValue());
                    } else {
                        element.init((String) dataEntry.get("text"));
                    }
                }
                case ElementTypes.IMAGE -> {
                    if (dataEntry.get("scale") != null) {
                        LOG.warn("Scale: " + ((Number) dataEntry.get("scale")).floatValue());
                        element.init(
                                "scenes/" + sceneName.substring(0, sceneName.lastIndexOf("/")) + "/"
                                        + (String) dataEntry.get("src"),
                                ((Number) dataEntry.get("scale")).floatValue());
                    } else {
                        element.init("scenes/" + sceneName.substring(0, sceneName.lastIndexOf("/")) + "/"
                                + (String) dataEntry.get("src"));
                    }
                }
                case ElementTypes.BUTTON -> element.init((String) dataEntry.get("texture"));
                default -> throw new IllegalArgumentException("Invalid element type: " + type);
            }

            BaseScene.elements.add(element);

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
