package ru.job4j.storesql;

import java.io.InputStream;
import java.util.Properties;

/**
 * Сlass Config.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 20.04.2019
 */
public class Config {
    private final Properties values = new Properties();

    public Config() {
        init();
    }

    /**
     * Initiates the config file.
     */
    public void init() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            values.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Returns the value of the property with the key
     *
     * @param key type String.
     * @return value type String.
     */
    public String get(String key) {
        return this.values.getProperty(key);
    }
}
