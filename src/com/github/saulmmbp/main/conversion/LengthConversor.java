package com.github.saulmmbp.main.conversion;

import com.github.saulmmbp.main.conversion.types.*;
import com.github.saulmmbp.main.dataaccess.DataAccess;

public class LengthConversor implements Conversor {

    private DataAccess dataAccess;
    
    public LengthConversor() {
        this.dataAccess = new DataAccess();
    }
    
    @Override
    public float convert(float cantidad, Convertible from, Convertible to) {
        String searchString1 = ((Length) from).name() + "_" + ((Length) to).name();
        String searchString2 = ((Length) to).name() + "_" + ((Length) from).name();
        
        if(dataAccess.containsKey(searchString1)) {
            return cantidad * dataAccess.findByKey(searchString1);
        } else if(dataAccess.containsKey(searchString2)) {
            return cantidad / dataAccess.findByKey(searchString2);
        } else {
            return cantidad;
        }
        
    }

}
