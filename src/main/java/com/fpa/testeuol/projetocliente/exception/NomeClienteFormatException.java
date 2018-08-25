package com.fpa.testeuol.projetocliente.exception;

public class NomeClienteFormatException extends Exception {

    public NomeClienteFormatException(String message){
        super(message);
    }

    public NomeClienteFormatException(String message, Throwable cause){
        super(message, cause);
    }

}
