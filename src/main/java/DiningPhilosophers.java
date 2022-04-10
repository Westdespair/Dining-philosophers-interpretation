import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Runs a simulation of the dining philosophers problem. Philosophers sit on a round table, thinking and eating.
 * They don't have enough chopsticks for everyone, so two philosophers sitting next to eachother can not eat at the same time.
 */
public class DiningPhilosophers extends Thread {

    public static void main(String[] args) {
        int diners = 5;

        ExecutorService threadExecutor = Executors.newFixedThreadPool(diners);
        DiningTable diningTable = new DiningTable(diners);

        //Starts a given number of threads, one for each philosopher.
        for (int i = 0; i < diners; i++) {
            threadExecutor.execute(diningTable.getPhilosopher(i));
        }

        //As long as there's at least one philosopher alive, continue the application
        while (!diningTable.everyoneIsDead()) {
            if(diningTable.everyoneIsDead()) {
                System.out.println("Everyone has died of starvation.");
                threadExecutor.shutdown();
            }
            //Check the permissions for everybody, granting philosophers with no adjacent eaters eating permission.
            for (int i = 0; i < diners; i++) {
                diningTable.setEatingPermission(i);
            }
        }
    }
}
