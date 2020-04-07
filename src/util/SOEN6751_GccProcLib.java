package util;

import java.io.*;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class SOEN6751_GccProcLib {
    public SOEN6751_GccProcLib() throws IOException, InterruptedException {
    }

    public static void run(String cmd) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder(cmd);
        SetupCommand(builder, cmd);
        builder.redirectErrorStream(true);
        Process process = builder.start();
        /*InputStream is = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }*/
        StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
        Executors.newSingleThreadExecutor().submit(streamGobbler);
        int exitCode = process.waitFor();
        assert exitCode == 0;
    }

    private static void SetupCommand(ProcessBuilder builder, String cmd) {
        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        builder.directory(new File(System.getProperty("user.home")));
        if (isWindows) {
            builder.command("cmd.exe", "/c", cmd);
        } else {
            builder.command("sh", "-c", cmd);
        }
    }

    //https://www.javaworld.com/article/2071275/when-runtime-exec---won-t.html
    private static class StreamGobbler implements Runnable {
        private InputStream inputStream;
        private Consumer<String> consumer;

        public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
            this.inputStream = inputStream;
            this.consumer = consumer;
        }

        @Override
        public void run() {
            new BufferedReader(new InputStreamReader(inputStream)).lines().forEach(consumer);
        }
    }
}
