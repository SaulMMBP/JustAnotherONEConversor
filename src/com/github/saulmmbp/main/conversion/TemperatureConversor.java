package com.github.saulmmbp.main.conversion;

import com.github.saulmmbp.main.conversion.types.*;

/**
 * Conversor de Temperatura
 * @author SAUL MALAGON MARTINEZ
 *
 */
public class TemperatureConversor implements Conversor {

    @Override
    public float convert(float cantidad, Convertible from, Convertible to) {
        Temperature localFrom = (Temperature) from;
        Temperature localTo = (Temperature) to;
        return switch (localFrom) {
            case CELSIUS -> switch (localTo) {
                case CELSIUS -> 1;
                case KELVIN -> cantidad + 273.15f;
                case FAHRENHEIT -> (cantidad * (9f / 5f)) + 32f;
            };
            case KELVIN -> switch (localTo) {
                case CELSIUS -> cantidad - 273.15f;
                case KELVIN -> 1;
                case FAHRENHEIT -> ((cantidad - 273.15f) * (9f / 5f)) + 32f;
            };
            case FAHRENHEIT -> switch (localTo) {
                case CELSIUS -> (cantidad - 32f) * (5f / 9f);
                case KELVIN -> ((cantidad - 32f ) * (5f / 9f)) + 273.15f;
                case FAHRENHEIT -> 1;
            };
        };
    }

}
