package com.github.saulmmbp.main.dataaccess;

import java.io.*;
import java.util.Properties;

public class DataAccess {

    private Properties properties;

    public DataAccess() {
        this.properties = new Properties();
        try {
            this.properties.load(new FileInputStream(new File("conversion.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Properties getProperties() {
        return this.properties;
    }
    
    public Float findByKey(String key) {
        return Float.parseFloat(this.properties.getProperty(key));
    }
    
    public boolean containsKey(String key) {
        return this.properties.containsKey(key);
    }
}
