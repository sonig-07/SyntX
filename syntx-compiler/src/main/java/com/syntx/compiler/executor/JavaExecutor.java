package com.syntx.compiler.executor;

import com.syntx.compiler.util.ProcessRunner;
import java.io.*;
import java.nio.file.Files;

public class JavaExecutor implements CodeExecutor {

    @Override
    public String execute(String sourceCode) throws Exception {

        File dir = Files.createTempDirectory("syntx-java-").toFile();

        File src = new File(dir, "Main.java");
        try (FileWriter w = new FileWriter(src)) {
            w.write(sourceCode);
        }

        ProcessBuilder compile =
                new ProcessBuilder("cmd", "/c", "javac Main.java");
        compile.directory(dir);

        String compileOut = ProcessRunner.run(compile);
        if (!compileOut.isBlank()) return compileOut;

        ProcessBuilder run =
                new ProcessBuilder("cmd", "/c", "java Main");
        run.directory(dir);

        return ProcessRunner.run(run);
    }
}
