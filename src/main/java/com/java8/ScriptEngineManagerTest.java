package com.java8;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author xsy
 * @create 2018-11-20 14:21
 * @desc 运行JS应用
 **/
public class ScriptEngineManagerTest {
    public static void main(String[] args) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        System.out.println(engine.getClass().getName());
        try {
            Object eval  = engine.eval("function f() { return 1; }; f() + 1;");
            System.out.println("Result:" + eval);
        } catch (ScriptException e) {
            System.out.println("执行脚本错误: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
