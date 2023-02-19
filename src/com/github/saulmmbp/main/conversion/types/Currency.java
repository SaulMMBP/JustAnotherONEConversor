package com.github.saulmmbp.main.conversion.types;

/**
 * Divisas
 * @author SAUL MALAGON MARTINEZ
 *
 */
public enum Currency implements Convertible{
    
    PESO, 
    DOLAR, 
    EURO, 
    LIBRA_ESTERLINA, 
    YEN_JAPONES, 
    WON_SURCOREANO;

    @Override
    public String toString() {
        return super.toString().charAt(0) + super.toString().substring(1).toLowerCase().replace('_', ' ');
    }
    
    
    
}
