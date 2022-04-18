package com.yestae.bee.script;

/**
 *
 */
public interface ScriptVariableProcessor {

    /**
     * 传入的变量key不包含${}
     *
     * @param var
     * @return
     */
    public Object process(String var);
}
