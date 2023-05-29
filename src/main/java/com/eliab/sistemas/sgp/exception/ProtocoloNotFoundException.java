package com.eliab.sistemas.sgp.exception;


import org.webjars.NotFoundException;


public class ProtocoloNotFoundException extends RuntimeException {

    public ProtocoloNotFoundException(String exception) {
        super(exception);
    }

}
