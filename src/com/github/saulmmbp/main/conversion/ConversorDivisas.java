package com.github.saulmmbp.main.conversion;

/**
 * Clase que contiene la utilidad de conversión de divisas
 * @author SAUL
 *
 */
public class ConversorDivisas {

    private static final float DOLAR_PESO = 18.361f;
    private static final float DOLAR_YEN_JAPONES = 134.11f;
    private static final float DOLAR_WON_SURCOREANO = 1295.9f;

    private static final float EURO_PESO = 19.685f;
    private static final float EURO_DOLAR = 1.0721f;
    private static final float EURO_YEN_JAPONES = 143.78f;
    private static final float EURO_WON_SURCOREANO = 1389.1f;

    private static final float LIBRA_ESTERLINA_PESO = 22.105f;
    private static final float LIBRA_ESTERLINA_DOLAR = 1.2039f;
    private static final float LIBRA_ESTERLINA_EURO = 1.1229f;
    private static final float LIBRA_ESTERLINA_YEN_JAPONES = 161.45f;
    private static final float LIBRA_ESTERLINA_WON_SURCOREANO = 1560.0f;

    private static final float PESO_YEN_JAPONES = 7.3043f;
    private static final float PESO_WON_SURCOREANO = 70.574f;

    private static final float YEN_JAPONES_WON_SURCOREANO = 9.6612f;
    
    /**
     * Convierte la cantidad ingresada de <<from>> a <<to>>
     * @param cantidad
     * @param from Divisa ingresada
     * @param to Divisa resultante
     * @return Cantidad convertida a la divisa <<to>>
     */
    public static float convert(float cantidad, Divisa from, Divisa to) {
        return switch (from) {
            case PESO -> switch (to) {
                case PESO -> cantidad;
                case DOLAR -> cantidad / DOLAR_PESO;
                case EURO -> cantidad / EURO_PESO;
                case LIBRA_ESTERLINA -> cantidad / LIBRA_ESTERLINA_PESO;
                case YEN_JAPONES -> cantidad * PESO_YEN_JAPONES;
                case WON_SURCOREANO -> cantidad * PESO_WON_SURCOREANO;
            };
            case DOLAR -> switch (to) {
                case PESO -> cantidad * DOLAR_PESO;
                case DOLAR -> cantidad;
                case EURO -> cantidad / EURO_DOLAR;
                case LIBRA_ESTERLINA -> cantidad / LIBRA_ESTERLINA_DOLAR;
                case YEN_JAPONES -> cantidad * DOLAR_YEN_JAPONES;
                case WON_SURCOREANO -> cantidad * DOLAR_WON_SURCOREANO;
            };
            case EURO -> switch (to) {
                case PESO -> cantidad * EURO_PESO;
                case DOLAR -> cantidad * EURO_DOLAR;
                case EURO -> cantidad;
                case LIBRA_ESTERLINA -> cantidad / LIBRA_ESTERLINA_EURO;
                case YEN_JAPONES -> cantidad * EURO_YEN_JAPONES;
                case WON_SURCOREANO -> cantidad * EURO_WON_SURCOREANO;
            };
            case LIBRA_ESTERLINA -> switch (to) {
                case PESO -> cantidad * LIBRA_ESTERLINA_PESO;
                case DOLAR -> cantidad * LIBRA_ESTERLINA_DOLAR;
                case EURO -> cantidad * LIBRA_ESTERLINA_EURO;
                case LIBRA_ESTERLINA -> cantidad;
                case YEN_JAPONES -> cantidad * LIBRA_ESTERLINA_YEN_JAPONES;
                case WON_SURCOREANO -> cantidad * LIBRA_ESTERLINA_WON_SURCOREANO;
            };
            case YEN_JAPONES -> switch (to) {
                case PESO -> cantidad / PESO_YEN_JAPONES;
                case DOLAR -> cantidad / DOLAR_YEN_JAPONES;
                case EURO -> cantidad / EURO_YEN_JAPONES;
                case LIBRA_ESTERLINA -> cantidad / LIBRA_ESTERLINA_YEN_JAPONES;
                case YEN_JAPONES -> cantidad;
                case WON_SURCOREANO -> cantidad * YEN_JAPONES_WON_SURCOREANO;
            };
            case WON_SURCOREANO -> switch (to) {
                case PESO -> cantidad / PESO_WON_SURCOREANO;
                case DOLAR -> cantidad / DOLAR_WON_SURCOREANO;
                case EURO -> cantidad / EURO_WON_SURCOREANO;
                case LIBRA_ESTERLINA -> cantidad / LIBRA_ESTERLINA_WON_SURCOREANO;
                case YEN_JAPONES -> cantidad / YEN_JAPONES_WON_SURCOREANO;
                case WON_SURCOREANO -> cantidad;
            };
        };
    }
}
