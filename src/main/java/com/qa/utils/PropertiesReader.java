package com.qa.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    private Properties prop;

    public Properties init_prop() {
        String configPath = "./src/test/resources/config/Config.properties";
        return getProperties(configPath);
    }

    public Properties getProperties(String configPath) {
        prop = new Properties();
        try {

            FileInputStream fis = new FileInputStream(configPath);

            prop.load(fis);
        } catch (FileNotFoundException ignored) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

}
