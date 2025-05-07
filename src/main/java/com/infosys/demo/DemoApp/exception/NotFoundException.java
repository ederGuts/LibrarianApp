package com.infosys.demo.DemoApp.exception;

import com.infosys.demo.DemoApp.util.Constants;

public class NotFoundException extends RuntimeException {

    Constants.ErrorCodes errorCodes = Constants.ErrorCodes.NOT_FOUND;

    public NotFoundException(String message) {
        super(message);
    }
    
}
