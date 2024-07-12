package org.cgn.simulation.base.exception;

import java.util.Optional;

public class ClientException extends AbstractException{
    public ClientException(String errorMessage) {
        super(errorMessage, null, BaseErrorCode.SERVICE_ERROR);
    }

    public ClientException(IErrorCode errorCode) {
        this(null, errorCode);
    }

    public ClientException(String errorMessage, IErrorCode errorCode) {
        this(errorMessage, null, errorCode);
    }

    public ClientException(String message, Throwable throwable, IErrorCode errorCode) {
        super(Optional.ofNullable(message).orElse(errorCode.message()), throwable, errorCode);
    }

    @Override
    public String toString() {
        return "ClientException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                "}";
    }
}
