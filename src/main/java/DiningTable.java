import java.util.ArrayList;

public class DiningTable {
    private ArrayList<Philosopher> philosopherList = new ArrayList<>();
    public final int tableLimit = 100;

    public DiningTable(int philosopherAmount) {
        int i = 0;

        //Puts philosophers at the table. If the amount of philosophers exceed the limit, exit the loop.
        //We don't want it to become crowded in here, after all.
        while (i < philosopherAmount && i < tableLimit) {
            philosopherList.add(new Philosopher(i, "Philosopher " + i));
            System.out.println(philosopherList.get(i).getName() + " has joined the table!");
            i++;
        }
    }

    public Philosopher getPhilosopher (int index) {
        return philosopherList.get(index);
    }
}
