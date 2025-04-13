package cz.cvut.fel.pjv.bukovja4.engine.scenes;

import java.util.ArrayList;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import cz.cvut.fel.pjv.bukovja4.Main;
import cz.cvut.fel.pjv.bukovja4.client.Window;
import cz.cvut.fel.pjv.bukovja4.engine.elements.BaseElement;
import cz.cvut.fel.pjv.bukovja4.engine.elements.ElementFactory;
import cz.cvut.fel.pjv.bukovja4.engine.elements.ElementTypes;
import cz.cvut.fel.pjv.bukovja4.engine.logic.GameState;
import cz.cvut.fel.pjv.bukovja4.utils.engine.Box;
import cz.cvut.fel.pjv.bukovja4.utils.engine.Pos;
import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

public abstract class BaseScene {
    private static String file;
    protected static ArrayList<BaseElement<?>> elements = new ArrayList<>();

    // private float specialValues(Object value) {
    //     return 0f;
    // }

    @SuppressWarnings("unchecked")
    public static void Load(String sceneName) throws Throwable {
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
            ArrayList<ArrayList<Object>> position = (ArrayList<ArrayList<Object>>) dataEntry.get("position");
        
            for (int i = 0; i < position.size(); i++) {
                ArrayList<Object> pos = position.get(i);
                ArrayList<Object> newPos = new ArrayList<>(pos.size());
                
                for (Object item : pos) {
                    if ("screen.minX".equals(item)) {
                        newPos.add(0f);
                    } else if ("screen.minY".equals(item)) {
                        newPos.add(0f);
                    } else if ("screen.maxX".equals(item)) {
                        newPos.add((float)Window.getWidth());
                    } else if ("screen.maxY".equals(item)) {
                        newPos.add((float)Window.getHeight());
                    } else {
                        newPos.add(item);
                    }
                }
                position.set(i, newPos);
            }

            @SuppressWarnings("rawtypes")
            Box bounds;
            switch (dim) {
                case 1 -> {
                    bounds = new Box<>(
                            new Pos<>(((Number)position.get(0).get(0)).floatValue()),
                            new Pos<>(((Number)position.get(1).get(0)).floatValue()));
                }
                case 2 -> {
                    bounds = new Box<>(
                            new Pos<>(
                                    ((Number)position.get(0).get(0)).floatValue(),
                                    ((Number)position.get(0).get(1)).floatValue()),
                            new Pos<>(
                                    ((Number)position.get(1).get(0)).floatValue(),
                                    ((Number)position.get(1).get(1)).floatValue()));
                }
                case 3 -> {
                    bounds = new Box<>(
                            new Pos<>(
                                    ((Number)position.get(0).get(0)).floatValue(),
                                    ((Number)position.get(0).get(1)).floatValue(),
                                    ((Number)position.get(0).get(2)).floatValue()),
                            new Pos<>(
                                    ((Number)position.get(1).get(0)).floatValue(),
                                    ((Number)position.get(1).get(1)).floatValue(),
                                    ((Number)position.get(1).get(2)).floatValue()));
                }
                case 4 -> {
                    bounds = new Box<>(
                            new Pos<>(
                                    ((Number)position.get(0).get(0)).floatValue(),
                                    ((Number)position.get(0).get(1)).floatValue(),
                                    ((Number)position.get(0).get(2)).floatValue(),
                                    ((Number)position.get(0).get(3)).floatValue()),
                            new Pos<>(
                                    ((Number)position.get(1).get(0)).floatValue(),
                                    ((Number)position.get(1).get(1)).floatValue(),
                                    ((Number)position.get(1).get(2)).floatValue(),
                                    ((Number)position.get(1).get(3)).floatValue()));
                }
                default -> throw new IllegalArgumentException("Invalid dimension: " + dim);
            }

            element = factory.create(ElementTypes.valueOf(type), bounds);
            elements.add(element);
        }
        // LOG.warn(data.get("elements").toString());
        LOG.info("Loaded scene: " + sceneName);
    }

    public void Unload() {
        GameState.controls.unRegisterAll();
        LOG.info("Unloaded scene: " + file);
    }

    public abstract void Tick();
}
