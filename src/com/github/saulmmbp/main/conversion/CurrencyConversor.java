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
    
    private float conversion(float cantidad, Currency from, Currency to) {
        String searchString1 = from.name() + "_" + to.name();
        String searchString2 = to.name() + "_" + from.name();
        if(properties.containsKey(searchString1)) {
            return cantidad * Float.parseFloat(properties.getProperty(searchString1));
        } else {
            return cantidad / Float.parseFloat(properties.getProperty(searchString2));
        }
    }
    
    @Override
    public float convert(float cantidad, Convertible from, Convertible to) {
        Currency localFrom = (Currency) from;
        Currency localTo = (Currency) to;
        return switch (localFrom) {
            case PESO -> switch (localTo) {
                case PESO -> cantidad;
                case DOLAR -> conversion(cantidad, localFrom, localTo);
                case EURO -> conversion(cantidad, localFrom, localTo);
                case LIBRA_ESTERLINA -> conversion(cantidad, localFrom, localTo);
                case YEN_JAPONES -> conversion(cantidad, localFrom, localTo);
                case WON_SURCOREANO -> conversion(cantidad, localFrom, localTo);
            };
            case DOLAR -> switch (localTo) {
                case PESO -> conversion(cantidad, localFrom, localTo);
                case DOLAR -> cantidad;
                case EURO -> conversion(cantidad, localFrom, localTo);
                case LIBRA_ESTERLINA -> conversion(cantidad, localFrom, localTo);
                case YEN_JAPONES -> conversion(cantidad, localFrom, localTo);
                case WON_SURCOREANO -> conversion(cantidad, localFrom, localTo);
            };
            case EURO -> switch (localTo) {
                case PESO -> conversion(cantidad, localFrom, localTo);
                case DOLAR -> conversion(cantidad, localFrom, localTo);
                case EURO -> cantidad;
                case LIBRA_ESTERLINA -> conversion(cantidad, localFrom, localTo);
                case YEN_JAPONES -> conversion(cantidad, localFrom, localTo);
                case WON_SURCOREANO -> conversion(cantidad, localFrom, localTo);
            };
            case LIBRA_ESTERLINA -> switch (localTo) {
                case PESO -> conversion(cantidad, localFrom, localTo);
                case DOLAR -> conversion(cantidad, localFrom, localTo);
                case EURO -> conversion(cantidad, localFrom, localTo);
                case LIBRA_ESTERLINA -> cantidad;
                case YEN_JAPONES -> conversion(cantidad, localFrom, localTo);
                case WON_SURCOREANO -> conversion(cantidad, localFrom, localTo);
            };
            case YEN_JAPONES -> switch (localTo) {
                case PESO -> conversion(cantidad, localFrom, localTo);
                case DOLAR -> conversion(cantidad, localFrom, localTo);
                case EURO -> conversion(cantidad, localFrom, localTo);
                case LIBRA_ESTERLINA -> conversion(cantidad, localFrom, localTo);
                case YEN_JAPONES -> cantidad;
                case WON_SURCOREANO -> conversion(cantidad, localFrom, localTo);
            };
            case WON_SURCOREANO -> switch (localTo) {
                case PESO -> conversion(cantidad, localFrom, localTo);
                case DOLAR -> conversion(cantidad, localFrom, localTo);
                case EURO -> conversion(cantidad, localFrom, localTo);
                case LIBRA_ESTERLINA -> conversion(cantidad, localFrom, localTo);
                case YEN_JAPONES -> conversion(cantidad, localFrom, localTo);
                case WON_SURCOREANO -> cantidad;
            };
        };
    }
}
