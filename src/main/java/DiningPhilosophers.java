import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DiningPhilosophers extends Thread {

    public static void main(String[] args) {
        int diners = 10;
        ExecutorService threadExecutor = Executors.newFixedThreadPool(diners);
        DiningPhilosophers diningPhilosophers = new DiningPhilosophers();
        DiningTable diningTable = new DiningTable(diners);
        for (int i = 0; i < diners; i++) {
            threadExecutor.execute(diningTable.getPhilosopher(i));
        }
    }
}
