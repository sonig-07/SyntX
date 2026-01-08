package com.syntx.compiler.service;

import org.springframework.stereotype.Service;
import com.syntx.compiler.factory.ExecutorFactory;

@Service
public class CodeExecutionService {

    public String execute(String language, String code) throws Exception {
        return ExecutorFactory.getExecutor(language).execute(code);
    }
}
