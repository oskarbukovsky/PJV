package cz.cvut.fel.pjv.bukovja4.utils.config;

import cz.cvut.fel.pjv.bukovja4.utils.Exceptions.ConfigException;

/**
 * Configuration manager that loads app settings from YAML files.
 * Creates and manages AppConfig instances.
 */
public final class Config {
    /** The loaded application configuration */
    private AppConfig config;

    /**
     * Gets the loaded application configuration.
     * 
     * @return The current AppConfig instance
     */
    public AppConfig getConfig() {
        return config;
    }

    /**
     * Loads the configuration from the default location.
     * 
     * @throws ConfigException if loading the configuration fails
     * @see {@link LoadConfig#Load()}
     */
    public AppConfig Load() throws ConfigException {
        try {
            this.config = LoadConfig.Load();
        } catch (Exception e) {
            throw new ConfigException(e);
        }
        return config;
    }

    /**
     * Updates the configuration with the provided values.
     * 
     * @param config The new AppConfig instance
     * @throws Throwable if updating the configuration fails
     */
    public void Update(AppConfig config) throws Throwable {
        this.config = config;
        UpdateConfig.Update(config);
    }
}
