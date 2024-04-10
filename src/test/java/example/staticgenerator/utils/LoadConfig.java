package example.staticgenerator.utils;

import example.MyStaticSiteGenerator;

import java.io.IOException;
import java.util.Properties;

public class LoadConfig {
    public static Properties LoadRouter() {
        Properties routes = new Properties();
        try {
            routes.load(MyStaticSiteGenerator.class.getResourceAsStream("/routes.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return routes;
    }
}
