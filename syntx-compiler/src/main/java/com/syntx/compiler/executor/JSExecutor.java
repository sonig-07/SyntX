package com.syntx.compiler.executor;

import com.syntx.compiler.util.ProcessRunner;
import java.io.*;
import java.nio.file.Files;

public class JSExecutor implements CodeExecutor {

    @Override
    public String execute(String sourceCode) throws Exception {

        File dir = Files.createTempDirectory("syntx-js-").toFile();
        File f = new File(dir, "script.js");

        try (FileWriter w = new FileWriter(f)) {
            w.write(sourceCode);
        }

        ProcessBuilder pb =
                new ProcessBuilder("cmd", "/c", "node script.js");
        pb.directory(dir);

        return ProcessRunner.run(pb);
    }
}
