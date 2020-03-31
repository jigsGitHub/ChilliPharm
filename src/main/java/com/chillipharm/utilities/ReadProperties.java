package com.chillipharm.utilities;

import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {

    private static final String PROPERTIES_DIR = "/config/";
    private static final String USER_PROPERTIES_FILE_PATH = PROPERTIES_DIR + "users.properties";
    private static final String TEST_PROPERTIES_FILE_PATH = PROPERTIES_DIR + "config.properties";
    private static final Properties props;

    static {
        props = new Properties();
        loadUserPropertiesFromFile();
        loadTestPropertiesFromFile();
    }

    private static void loadUserPropertiesFromFile() {
        loadPropertiesFromFile(USER_PROPERTIES_FILE_PATH);
    }

    private static void loadTestPropertiesFromFile() {
        loadPropertiesFromFile(TEST_PROPERTIES_FILE_PATH);
    }

    private static void loadPropertiesFromFile(String propertiesFilePath) {
        try {
            InputStream propsStream;
            propsStream = ReadProperties.class.getResourceAsStream(propertiesFilePath);
            props.load(propsStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Problem loading test properties file. Is " + (propertiesFilePath + " on classpath?"), e);
        }
    }

    private static String getProp(String key) {
        String s = props.getProperty(key);
        return (s != null) ? s.trim() : null;
    }

    public String getEmail( ) {
        return getProp("EMAIL");
    }

    public String getInvalidPassword( ) {
        return getProp("INVALID_PASSWORD");
    }


}
