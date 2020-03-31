package com.chillipharm.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import static com.chillipharm.utilities.CommonUtility.absolutePath;

public class ReadConfig {

    static Properties prop = new Properties();
    static String configPropFileName = "/src/main/resources/config/config.properties";

    static
    {
        readConfigPropertyFile();
    }

    public static void readConfigPropertyFile() {
        InputStream input;

        try {
            input = new FileInputStream(absolutePath + configPropFileName);
            prop.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties readConfigProperty() {
        return prop;
    }
}
