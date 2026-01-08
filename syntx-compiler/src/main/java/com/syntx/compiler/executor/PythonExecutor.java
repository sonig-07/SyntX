package com.syntx.compiler.executor;

import com.syntx.compiler.util.ProcessRunner;
import java.io.*;
import java.nio.file.Files;

public class PythonExecutor implements CodeExecutor {

    @Override
    public String execute(String sourceCode) throws Exception {

        File dir = Files.createTempDirectory("syntx-python-").toFile();
        File file = new File(dir, "script.py");

        try (FileWriter w = new FileWriter(file)) {
            w.write(sourceCode);
        }

        ProcessBuilder pb =
                new ProcessBuilder("cmd", "/c", "python script.py");
        pb.directory(dir);

        return ProcessRunner.run(pb);
    }
}
