package com.floe.maker.meta;

import javax.annotation.processing.SupportedOptions;

public class MetaException extends RuntimeException{
    public MetaException(String message){
        super(message);
    }
    public MetaException(String message,Throwable cause){
        super(message,cause);
    }
}
