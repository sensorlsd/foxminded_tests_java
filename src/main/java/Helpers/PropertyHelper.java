package Helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyHelper {

    private static final String propertyFilePath = System.getProperty("user.dir") + "/src/configuration.properties";
    private static Properties prop = new Properties();

    public PropertyHelper() throws Exception {
        FileInputStream input = new FileInputStream(propertyFilePath);
        try {
            prop.load(input);
        } catch (IOException e) {
            throw new Exception(" There was a problem in reading property file \n" + e.getMessage());
        }
        prop.putAll(System.getProperties());
    }

    private static String getProperty(String property) throws Exception {
        if (prop == null) {
            new PropertyHelper();
        }
        return prop.getProperty(property);
    }

    public String getChromeDriver() throws Exception {
        return getProperty("CHROME_DRIVER_MAC");
    }

    public String getFireFoxDriver() throws Exception {
        return getProperty("FIREFOX_DRIVER_MAC");
    }

    public String getURL() throws Exception {
        return getProperty("URL");
    }


}


