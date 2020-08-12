package framework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetAppiumData {
    public Properties getProperties(){
        Properties prop = new Properties();
        String fileName = "appium.config";
        ClassLoader  classloader = Thread.currentThread().getContextClassLoader();

        InputStream is = classloader.getResourceAsStream("appium.config");;
        /*try {
            is = new FileInputStream(fileName);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.fillInStackTrace());
        }*/
        try {
            prop.load(is);
        } catch (IOException ex) {
            System.out.println(ex.fillInStackTrace());
        }
        return prop;
    }
}
