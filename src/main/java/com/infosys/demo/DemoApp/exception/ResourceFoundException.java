package com.infosys.demo.DemoApp.exception;

import com.infosys.demo.DemoApp.util.Constants.ErrorCodes;

public class ResourceFoundException extends RuntimeException {

    private ErrorCodes errorCode = ErrorCodes.RESOURCE_ALREADY_EXIST;

    public ResourceFoundException(String message) {
        super(message);
    }

    public ErrorCodes getErrorCode() {
        return errorCode;
    }
 
}
