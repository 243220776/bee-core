package com.xx.core.boot;


/**
 */
public class InitializeException extends RuntimeException {

    /** */
    private static final long serialVersionUID = 1789473797358416376L;
    
    public InitializeException(String msg) {
        super(msg);
    }

    public InitializeException(String msg, Throwable e) {
        super(msg, e);
    }
}
