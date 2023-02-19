package com.github.saulmmbp.main.conversion.types;

/**
 * Unidades de longitud
 * @author SAUL MALAGON MARTINEZ
 *
 */
public enum Length implements Convertible {

    NANOMETROS,
    MICRONES,
    MILIMETROS,
    CENTIMETROS,
    METROS,
    KILOMETROS,
    PULGADAS,
    PIES,
    YARDAS,
    MILLAS,
    MILLAS_NAUTICAS;
    
    @Override
    public String toString() {
        return super.toString().charAt(0) + super.toString().substring(1).toLowerCase().replace('_', ' ');
    }
}
