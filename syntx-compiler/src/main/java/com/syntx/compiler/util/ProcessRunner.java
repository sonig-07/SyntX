package com.syntx.compiler.util;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class ProcessRunner {

    private static final int TIMEOUT_SECONDS = 5;

    public static String run(ProcessBuilder pb) throws Exception {
        pb.redirectErrorStream(true);
        Process p = pb.start();

        StringBuilder output = new StringBuilder();

        Thread reader = new Thread(() -> {
            try (BufferedReader br =
                         new BufferedReader(
                                 new InputStreamReader(p.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    output.append(line).append("\n");
                }
            } catch (IOException ignored) {}
        });
        reader.start();

        boolean finished = p.waitFor(TIMEOUT_SECONDS, TimeUnit.SECONDS);

        if (!finished) {
            p.destroyForcibly();
            return "❌ Execution timed out";
        }

        reader.join();
        return output.toString();
    }
}
