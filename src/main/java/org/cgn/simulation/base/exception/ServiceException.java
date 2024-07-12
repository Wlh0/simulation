package org.cgn.simulation.base.exception;

import java.util.Optional;

public class ServiceException extends AbstractException{

    public ServiceException(String errorMessage) {
        super(errorMessage, null, BaseErrorCode.SERVICE_ERROR);
    }

    public ServiceException(IErrorCode errorCode) {
        this(null, errorCode);
    }

    public ServiceException(String errorMessage, IErrorCode errorCode) {
        this(errorMessage, null, errorCode);
    }

    public ServiceException(String message, Throwable throwable, IErrorCode errorCode) {
        super(Optional.ofNullable(message).orElse(errorCode.message()), throwable, errorCode);
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                "}";
    }
}
