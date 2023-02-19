package com.github.saulmmbp.main.conversion;

import com.github.saulmmbp.main.conversion.types.Convertible;

/**
 * Define un Conversor
 * @author SAUL MALAGON MARTINEZ SAUL
 *
 */
public interface Conversor {

    /**
     * Convierte valores de unidades from a to
     * @param cantidad
     * @param from
     * @param to
     * @return
     */
    float convert(float cantidad, Convertible from, Convertible to);
    
}
