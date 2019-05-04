package ru.job4j.service;

import java.io.InputStream;
import java.util.Properties;

/**
 * Сlass Config.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 28.04.2019
 */
public class Config {
    private final Properties values = new Properties();
    private final String propName;

    public Config(String fileName) {
        this.propName = fileName;
        init();
    }

    /**
     * Initiates the config file.
     */
    public void init() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream(this.propName)) {
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
