package com.xx.core.script;

/**
 */
public class ScriptException extends RuntimeException {

    /** */
    private static final long serialVersionUID = 2708268154243763868L;

    public ScriptException(String msg) {
        super(msg);
    }

    public ScriptException(Throwable e) {
        super(e);
    }

    public ScriptException(String msg, Throwable e) {
        super(msg, e);
    }
}
