import java.io.BufferedWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHelper {

    public static void createFile(String file) throws IOException {
        System.out.println("create file: " + file);
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(file))) {
            writer.write("0");
        }
    }
}