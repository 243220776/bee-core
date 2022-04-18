package com.yestae.bee.script;

/**
 *
 */
public class ScriptHelper {

    private static Script script;

    public static Script getJavaScript() {
        if (script == null) {
            throw new NullPointerException("script not instantiated ");
        }
        return script;
    }

    public static void inject(Script script) {
        if (script == null) {
            throw new NullPointerException("javaScript can not be null.");
        }
        ScriptHelper.script = script;
    }
}
