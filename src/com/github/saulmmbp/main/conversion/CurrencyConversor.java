package com.github.saulmmbp.main.conversion;

import java.io.*;
import java.util.Properties;

import com.github.saulmmbp.main.conversion.types.*;

/**
 * Conversor de Divisas
 * @author SAUL MALAGON MARTINEZ
 *
 */
public class CurrencyConversor implements Conversor {
    
    private Properties properties;

    public CurrencyConversor() {
        this.properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("conversion.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public float convert(float cantidad, Convertible from, Convertible to) {
        Currency localFrom = (Currency) from;
        Currency localTo = (Currency) to;
        String searchString1 = localFrom.name() + "_" + localTo.name();
        String searchString2 = localTo.name() + "_" + localFrom.name();
        if(properties.containsKey(searchString1)) {
            return cantidad * Float.parseFloat(properties.getProperty(searchString1));
        } else if(properties.containsKey(searchString2)) {
            return cantidad / Float.parseFloat(properties.getProperty(searchString2));
        } else {
            return cantidad;
        }
    }
}
