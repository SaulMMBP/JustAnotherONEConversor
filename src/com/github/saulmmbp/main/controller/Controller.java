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
    
    private Conversor currencyConversor;
    private Conversor temperatureConversor;
    private Conversor lengthConversor;
    
    public Controller() {
        init();
    }
    
    /**
     * Inicializa conversores
     */
    public void init() {
        this.currencyConversor = new CurrencyConversor();
        this.temperatureConversor = new TemperatureConversor();
        this.lengthConversor = new LengthConversor();
    }

    /**
     * Convierte valores de unidades from a to
     * @param value
     * @param from
     * @param to
     * @return
     */
    public String convert(float value, Convertible from, Convertible to, ConversionType conversionType) {
        float conversion = switch(conversionType) {
            case CURRENCY -> currencyConversor.convert(value, from, to);
            case TEMPERATURE -> temperatureConversor.convert(value, from, to);
            case LENGTH -> lengthConversor.convert(value, from, to);
        };
        
        return String.valueOf(conversion);
    }
}
