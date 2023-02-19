package com.github.saulmmbp.main.conversion.types;

/**
 * Unidades de temperatura
 * @author SAUL MALAGON MARTINEZ
 *
 */
public enum Temperature implements Convertible {

    CELSIUS, 
    FAHRENHEIT, 
    KELVIN;
    
    @Override
    public String toString() {
        return super.toString().charAt(0) + super.toString().substring(1).toLowerCase();
    }
}
