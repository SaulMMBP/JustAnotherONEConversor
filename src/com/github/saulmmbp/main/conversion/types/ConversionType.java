package com.github.saulmmbp.main.conversion.types;

/**
 * Tipos de conversión que soporta el convertidor
 * @author SAUL MALAGON MARTINEZ
 *
 */
public enum ConversionType {

    CURRENCY,
    TEMPERATURE,
    LENGTH;

    @Override
    public String toString() {
        return super.toString().charAt(0) + super.toString().substring(1).toLowerCase();
    }
    
    
}
