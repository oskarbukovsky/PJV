package cz.cvut.fel.pjv.bukovja4.utils.config;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

final class UpdateConfig {
    protected static final void Update(AppConfig config) throws Throwable {
        try {
            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            options.setPrettyFlow(true);
            options.setIndent(2);

            Map<String, Object> configMap = PrepareYaml(config);

            Yaml yaml = new Yaml(options);
            File configFile = new File(System.getProperty("user.dir") + "\\config\\config.yml");
            try (FileWriter writer = new FileWriter(configFile)) {
                yaml.dump(configMap, writer);
                LOG.info("Config updated successfully");
            }
        } catch (Exception e) {
            LOG.error("Error updating config: ", e);
        }
    }

    private static Map<String, Object> PrepareYaml(AppConfig config) throws Throwable {
        Map<String, Object> configMap = new HashMap<>();

        if (config.window != null) {
            configMap.put("window", objectToMap(config.window));
        }

        return configMap;
    }

    private static Map<String, Object> objectToMap(Object obj) throws Throwable {
        Map<String, Object> map = new HashMap<>();

        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            try {
                field.setAccessible(true);

                String name = field.getName();
                Object value = field.get(obj);

                if (value != null) {
                    if (!isPrimitive(value)) {
                        value = objectToMap(value);
                    }

                    map.put(name, value);
                }
            } catch (IllegalAccessException e) {
                LOG.error("Error accessing field " + field.getName() + ": ", e);
            }
        }

        return map;
    }

    private static boolean isPrimitive(Object obj) {
        return obj instanceof String ||
                obj instanceof Number ||
                obj instanceof Boolean ||
                obj instanceof Character ||
                obj.getClass().isPrimitive();
    }
}
