package com.fpa.testeuol.projetocliente.exception;

public class ClienteNotFoundException extends Exception {

    public ClienteNotFoundException(String message){
        super(message);
    }

    public ClienteNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

}
