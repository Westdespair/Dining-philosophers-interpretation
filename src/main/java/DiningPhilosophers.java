import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class DiningPhilosophers extends Thread {

    public static void main(String[] args) {
        DiningPhilosophers diningPhilosophers = new DiningPhilosophers();
        DiningTable diningTable = new DiningTable(1);
        diningTable.getPhilosopher(0).run();
    }
}
