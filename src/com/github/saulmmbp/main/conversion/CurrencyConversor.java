package com.github.saulmmbp.main.conversion;

import com.github.saulmmbp.main.conversion.types.*;
import com.github.saulmmbp.main.dataaccess.DataAccess;

/**
 * Conversor de Divisas
 * @author SAUL MALAGON MARTINEZ
 *
 */
public class CurrencyConversor implements Conversor {
    
    private DataAccess dataAccess;

    public CurrencyConversor() {
        this.dataAccess = new DataAccess();
    }
    
    @Override
    public float convert(float cantidad, Convertible from, Convertible to) {
        String searchString1 = ((Currency) from).name() + "_" + ((Currency) to).name();
        String searchString2 = ((Currency) to).name() + "_" + ((Currency) from).name();
        
        if(dataAccess.containsKey(searchString1)) {
            return cantidad * dataAccess.findByKey(searchString1);
        } else if(dataAccess.containsKey(searchString2)) {
            return cantidad / dataAccess.findByKey(searchString2);
        } else {
            return cantidad;
        }
    }
}
