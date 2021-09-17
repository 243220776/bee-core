package com.xx.core.script.impl;

import com.xx.core.script.ScriptException;
import com.xx.core.script.ScriptVariableProcessor;
import com.xx.core.script.Script;
import org.apache.commons.lang3.StringUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zcy 2019年5月23日
 */
public class ScriptFactory implements Script {

    private static ScriptEngineManager manager = new ScriptEngineManager();

    public static Script getScript(String type) {
        return new ScriptFactory(type);
    }

    public static Script getScript(String type, ScriptVariableProcessor processor) {
        return new ScriptFactory(type, processor);
    }

    private ScriptEngine engine;

    private String varRegex = "\\$\\{(.*?)\\}";

    private ScriptVariableProcessor processor;

    private ScriptFactory(String type) {
        this(type, null);
    }

    private ScriptFactory(String type, ScriptVariableProcessor processor) {
        this.engine = manager.getEngineByName(type);
        this.processor = processor;
    }

    @Override
    public Object eval(String script) {
        return eval(script, null);
    }

    @Override
    public Object eval(String script, ScriptVariableProcessor processor) {
        String backCondition = script;
        // 判断条件是否匹配
        if (StringUtils.isBlank(script)) {
            return null;
        }
        Pattern p = Pattern.compile(varRegex);
        Matcher m = p.matcher(script);
        while (m.find()) {
            String varExr = m.group(1);
            Object varVal = null;
            if (processor != null) {
                varVal = processor.process(varExr);
            }
            if (varVal == null && this.processor != null) {
                varVal = this.processor.process(varExr);
            }
            String varKey = varExr.replaceAll("\\.", "_").replaceAll("-", "_");;
            script = script.replaceAll("\\$\\{" + varExr + "\\}", varKey);
            engine.put(varKey, varVal);
        }
        try {
            return engine.eval(script);
        } catch (Throwable e) {
            throw new ScriptException("eval script [" + backCondition + "] error:" + e.getMessage(), e);
        }
    }
    
}
