import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DiningPhilosophers extends Thread {

    public static void main(String[] args) {
        boolean running = true;
        int diners = 5;

        ExecutorService threadExecutor = Executors.newFixedThreadPool(diners);
        DiningPhilosophers diningPhilosophers = new DiningPhilosophers();
        DiningTable diningTable = new DiningTable(diners);

        //Starts a given number of threads, one for each philosopher.
        for (int i = 0; i < diners; i++) {
            threadExecutor.execute(diningTable.getPhilosopher(i));
        }

        while (!diningTable.everyoneIsDead()) {

            if(diningTable.everyoneIsDead()) {
                System.out.println("Everyone has died of starvation.");
                threadExecutor.shutdown();
            }
            for (int i = 0; i < diners; i++) {
                diningTable.setEatingPermission(i);
            }
        }
    }
}
