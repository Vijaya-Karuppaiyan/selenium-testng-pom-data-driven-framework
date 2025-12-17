package com.saucedemo.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
	private static final Properties prop = new Properties();
    private static final String DEFAULT_FILE = "config.properties";

    static {
        loadProperties();
    }

    private ConfigReader() {}

    private static void loadProperties() {
        try {
            String env = System.getProperty("env"); // -Denv=qa
            String fileName = DEFAULT_FILE;

            if (env != null && !env.isBlank()) {
                String envFile = String.format("config.%s.properties", env.trim().toLowerCase());
                if (ConfigReader.class.getClassLoader().getResource(envFile) != null) {
                    fileName = envFile;
                } else {
                    System.out.println("Env file not found: " + envFile + " — using " + DEFAULT_FILE);
                }
            }

            try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(fileName)) {
                if (input == null) {
                    throw new RuntimeException(fileName + " not found in classpath.");
                }
                prop.load(input);
            }

            // allow overrides via -Dproperty=value
            String[] overridable = {"browser","baseUrl","username","password","headless"};
            for (String key : overridable) {
                String sys = System.getProperty(key);
                if (sys != null && !sys.isBlank()) prop.setProperty(key, sys);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config: " + e.getMessage(), e);
        }
    }

    public static String get(String key) {
        String value = prop.getProperty(key);
        if (value == null) throw new RuntimeException("Property '" + key + "' not found.");
        return value;
    }

    public static String get(String key, String defaultVal) {
        return prop.getProperty(key, defaultVal);
    }

    public static int getInt(String key, int defaultVal) {
        try { return Integer.parseInt(prop.getProperty(key)); }
        catch (Exception e) { return defaultVal; }
    }

    public static boolean getBoolean(String key, boolean defaultVal) {
        try { return Boolean.parseBoolean(prop.getProperty(key)); }
        catch (Exception e) { return defaultVal; }
    }

}
