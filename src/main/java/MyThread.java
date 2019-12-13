import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyThread implements Runnable {

    private String threadName;
    private final String file;
    private int number;

    public MyThread(String threadName, String file, int number) {
        this.threadName = threadName;
        this.file = file;
        this.number = number;
    }

    private Integer readFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder sb = new StringBuilder();

        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        String s = sb.toString();
        int n = Integer.parseInt(s);
        if (n != number) {
            System.out.println(s);
        }
        return n;
    }

    private void writeToFile(Integer n) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(file));
        System.out.println(++n + " " + threadName);
        writer.write((n).toString());
        writer.flush();
    }

    @Override
    public void run() {
        synchronized (file) {
            try {
                int n;
                if (!((n = readFile()) == number))
                    writeToFile(n);
                else Thread.currentThread().interrupt();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
