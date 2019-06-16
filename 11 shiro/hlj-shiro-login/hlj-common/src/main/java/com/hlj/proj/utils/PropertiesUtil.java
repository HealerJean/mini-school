package com.hlj.proj.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * @author HealerJean
 * @version 1.0v
 * @ClassName PropertiesUtil
 * @date 2019/6/13  19:27.
 * @Description
 */


public class PropertiesUtil {

    public static Properties properties = new Properties();

    public static String getProperty(String key) {
        return properties.getProperty(key) == null ? "" : properties.get(key).toString();
    }

    static {
        String profile = System.getProperty("profiles.active");
        System.out.println(profile);

        String[]  props = new String[] {"db.properties", "resource.properties" };
        for(String prop:props){
            InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(prop);
            if (inputStream != null) {
                Properties propertiest = new Properties();
                try {
                    propertiest.load(inputStream);
                    properties.putAll(propertiest);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
