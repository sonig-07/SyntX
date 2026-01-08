package com.syntx.compiler.controller;

import org.springframework.web.bind.annotation.*;
import com.syntx.compiler.dto.*;
import com.syntx.compiler.service.CodeExecutionService;

@RestController
@RequestMapping("/execute")
public class CodeExecutionController {

    private final CodeExecutionService service;

    public CodeExecutionController(CodeExecutionService service) {
        this.service = service;
    }

    @PostMapping
    public CodeResponse execute(@RequestBody CodeRequest request) throws Exception {
        return new CodeResponse(
                service.execute(request.getLanguage(), request.getCode())
        );
    }
}
