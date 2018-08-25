package com.fpa.testeuol.projetocliente.exception;

public class GeoSaveException extends Exception {

    public GeoSaveException(String message){
        super(message);
    }

    public GeoSaveException(String message, Throwable cause){
        super(message, cause);
    }

}
