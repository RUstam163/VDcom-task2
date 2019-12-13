import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        String file = "out.txt";
        System.out.println("Введите число больше 0 и кратное 2");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int n = Integer.parseInt(br.readLine());
            if (!NumberHelper.validNumber(n)) {
                System.out.println("Вы ввели некоректное число");
                return;
            }
            FileHelper.createFile(file);

            List<Runnable> tasks = new ArrayList<>();
            tasks.add(new MyThread("Thread1", file,n));
            tasks.add(new MyThread("Thread2", file,n));

            ExecutorService worker = Executors.newSingleThreadExecutor();
            worker.submit(() -> {
                while (!Thread.interrupted())
                    tasks.forEach(Runnable::run);
            });
            worker.shutdown();
        } catch (IOException | NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
