package com.github.saulmmbp.main.controller;

import com.github.saulmmbp.main.conversion.*;
import com.github.saulmmbp.main.conversion.types.*;

/**
 * Controla la conversión, llamando al método de conversión correcto
 * para cada caso
 * @author SAUL MALAGON MARTINEZ
 *
 */
public class Controller {
    
    private static Conversor currencyConversor;
    private static Conversor TemperatureConversor;
    
    public Controller() {
        init();
    }
    
    /**
     * Inicializa conversores
     */
    public void init() {
        currencyConversor = new CurrencyConversor();
        TemperatureConversor = new TemperatureConversor();
    }

    /**
     * Convierte valores de unidades from a to
     * @param value
     * @param from
     * @param to
     * @return
     */
    public String convert(float value, Convertible from, Convertible to) {
        float conversion = 0.0f;
        if(from instanceof Currency) {
             conversion = currencyConversor.convert(value, from, to);
        } else if( from instanceof Temperature) {
            conversion = TemperatureConversor.convert(value, from, to);
        }
        
        return String.valueOf(conversion);
    }
}
