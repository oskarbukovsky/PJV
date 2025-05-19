package cz.cvut.fel.pjv.bukovja4.utils.config;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

/**
 * Utility class for updating application configuration files.
 * Serializes AppConfig objects to YAML format and saves them to disk.
 */
final class UpdateConfig {
    /**
     * Updates the configuration file with the provided AppConfig object.
     * Serializes the object to YAML and writes it to the config file.
     * 
     * @param config The configuration object to save
     * @throws Throwable if an error occurs during the update process
     * @see {@link LoadConfig#Load()} For loading configuration
     */
    static void Update(AppConfig config) throws Throwable {
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

    /**
     * Prepares a configuration object for YAML serialization.
     * Converts the AppConfig object to a Map structure.
     * 
     * @param config The configuration object to prepare
     * @return A Map representation of the configuration
     * @throws Throwable if an error occurs during conversion
     */
    private static Map<String, Object> PrepareYaml(AppConfig config) throws Throwable {
        Map<String, Object> configMap = new HashMap<>();

        if (config.window != null) {
            configMap.put("window", objectToMap(config.window));
            configMap.put("gameState", objectToMap(config.gameState));
        }

        return configMap;
    }

    /**
     * Recursively converts an object to a Map using reflection.
     * Handles nested objects by recursively converting them to maps.
     * 
     * @param obj The object to convert to a Map
     * @return A Map representation of the object
     * @throws IllegalAccessException if an error occurs during conversion
     */
    private static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
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

    /**
     * Checks if an object is a primitive or primitive wrapper type.
     * Used to determine if an object should be serialized directly or recursively.
     * 
     * @param obj The object to check
     * @return true if the object is a primitive type, false otherwise
     */
    private static boolean isPrimitive(Object obj) {
        return obj instanceof String ||
                obj instanceof Number ||
                obj instanceof Boolean ||
                obj instanceof Character ||
                obj.getClass().isPrimitive();
    }
}
