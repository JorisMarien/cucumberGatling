package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class GatlingRunner {
    public static void runSimulation() throws IOException, InterruptedException {
        try {
            // Load dependency classpath from Maven
            String classpath = getClasspath();

            // Add compiled classes to classpath
            classpath = "target/test-classes" + File.pathSeparator + classpath;

            List<String> command = new ArrayList<>();
            command.add("java");
            command.add("-cp");
            command.add(classpath);
            command.add("io.gatling.app.Gatling");
            command.add("-s");
            command.add("simulation.BasicSimulation"); // Your simulation class

            ProcessBuilder pb = new ProcessBuilder(command);
            pb.inheritIO();

            Process process = pb.start();
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                throw new RuntimeException("Gatling exited with code: " + exitCode);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to run Gatling simulation", e);
        }
    }

    private static String getClasspath() throws IOException {
        File file = new File("classpath.txt");
        if (!file.exists()) {
            throw new FileNotFoundException("classpath.txt not found. Run: mvn dependency:build-classpath -Dmdep.outputFile=classpath.txt");
        }

        return new String(Files.readAllBytes(file.toPath())).trim();
    }
}

