package cz.cvut.fel.pjv.bukovja4.utils.config;

public final class Config {
    private AppConfig config;

    public AppConfig getConfig() {
        return config;
    }

    public void Load() throws Throwable {
        this.config = LoadConfig.Load();
    }
}
