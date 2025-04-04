package cz.cvut.fel.pjv.bukovja4.utils.config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import cz.cvut.fel.pjv.bukovja4.utils.Utils;
import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

final class LoadConfig {
    protected static final AppConfig Load() throws Throwable {
        File configFolder = null;
        try {
            configFolder = new File(System.getProperty("user.dir") + "\\config");
            if (!configFolder.exists()) {
                Files.createDirectory(Path.of(System.getProperty("user.dir") + "\\config"));
            }
        } catch (IOException e) {
            LOG.error("Failed to create config folder: ", e);
        }
        File configFile = null;
        configFile = new File(configFolder + "\\config.yml");
        if (!configFile.exists()) {
            LOG.info("Config file not found, creating a new one.");
            Utils.copyFromResources(configFile.getAbsolutePath(), "config/default_config.yml");
            LOG.info("Config file created successfully.");
        }
        Yaml yaml = new Yaml();
        AppConfig data = null;
        try (FileReader reader = new FileReader(configFile)) {
            data = yaml.loadAs(reader, AppConfig.class);
            if (data == null) {
                throw new YAMLException("Config data is probably empty");
            }
            LOG.info("Config file loaded successfully.");
            LOG.debug("Config data: " + data);
        } catch (IOException e) {
            LOG.error("Failed to load config file", e);
        } catch (YAMLException e) {
            LOG.error("Failed to parse config file", e);
        }
        return data;
    }
}
