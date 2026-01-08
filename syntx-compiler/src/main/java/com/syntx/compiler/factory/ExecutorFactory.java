package com.syntx.compiler.factory;

import com.syntx.compiler.executor.*;

public class ExecutorFactory {

    public static CodeExecutor getExecutor(String language) {

        return switch (language.toLowerCase()) {
            case "java" -> new JavaExecutor();
            case "python" -> new PythonExecutor();
            case "js", "javascript" -> new JSExecutor();
            default -> throw new IllegalArgumentException("Unsupported language");
        };
    }
}
