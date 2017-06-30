package loaders;

import java.io.*;
import java.util.Properties;

/**
 * Created by Vi on 18.03.2017.
 */
public class PropertyLoader {
    private static PropertyLoader instance;
    Properties prop;
    private static final String propFileName = "/application.properties";

    private PropertyLoader() {
        InputStream inputStream = null;
        try {
            prop = new Properties();

            inputStream = PropertyLoader.class.getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getProperty(String key) {
        return prop.getProperty(key);
    }

    public String setProperty(String key, String value) {
        File fileTest = new File(value);
        if (fileTest.isDirectory() && fileTest.exists()) {
            try {
                prop.clear();
                prop.setProperty(key, value);
                File file = new File("src/main/resources/" + propFileName);
                FileOutputStream fileOut = new FileOutputStream(file);
                prop.store(fileOut, "Favorite Things");
                fileOut.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return "1";
        } else {
            return "0";
        }
    }

    public void showPropertys() {
        prop.entrySet().stream().forEach(System.out::println);
    }

    public static PropertyLoader getInstance() {
        if (instance == null) {
            instance = new PropertyLoader();
        }
        return instance;
    }
}
