package Database;

import Helpers.PropertyHelper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBCredentials {

    private static final String propertyFilePath = System.getProperty("user.dir") + "/src/main/java/database/config.properties";
    private static Properties prop = new Properties();

    public DBCredentials() throws Exception {
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

    public String getDataBaseURL() throws Exception {
        return getProperty("DATABASE_URL");
    }

    public String getDataBasePORT() throws Exception {
        return getProperty("DATABASE_PORT");
    }

    public String getDataBaseNAME() throws Exception {
        return getProperty("DATABASE_NAME");
    }

    public String getDataBaseLOGIN() throws Exception {
        return getProperty("LOGIN");
    }

    public String getDataBasePASS() throws Exception {
        return getProperty("PASSWORD");
    }


}
