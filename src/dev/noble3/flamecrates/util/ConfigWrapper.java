package dev.noble3.flamecrates.util;

import java.util.List;

import org.bukkit.configuration.Configuration;

public class ConfigWrapper {
    private final Configuration config;

    public ConfigWrapper(final Configuration config) {
        this.config = config;
    }    

    public List<String> getOrDefault(final String key, final List<String> def) {
        final List<String> value = config.contains(key) ? config.getStringList(key) : def;
        return StringUtil.translateColorCodes(value);
    }
    

    public String getOrDefault(final String key, final String def) {
        final String value = config.contains(key) ? config.getString(key) : def;
        return StringUtil.translateColorCodes(value);
    }
    

    public int getOrDefault(final String key, final int def) {
        return config.contains(key) ? config.getInt(key) : def;
    }

    public String getString(String key) {
        return config.getString(key);
    }
}
