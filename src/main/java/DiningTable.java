import java.util.ArrayList;
import java.util.ListIterator;

public class DiningTable {
    private ArrayList<Philosopher> philosopherList = new ArrayList<>();
    public final int tableLimit = 100;
    private ListIterator<Philosopher>iterator = philosopherList.listIterator();


    public DiningTable(int philosopherAmount) {
        int i = 0;

        //Puts philosophers at the table. If the amount of philosophers exceed the limit, exit the loop.
        //We don't want it to become crowded in here, after all.
        while (i < philosopherAmount && i < tableLimit) {
            philosopherList.add(new Philosopher("Philosopher " + i));
            System.out.println(philosopherList.get(i).getName() + " has joined the table!");
            i++;
        }
    }

    public Philosopher getPhilosopher (int index) {
        return philosopherList.get(index);
    }

    public boolean everyoneIsDead() {
      boolean everyoneIsDead = false;
      int deadAmount = 0;

        for (Philosopher philosopher : philosopherList) {
            if (philosopher.getCurrentState() == Philosopher.State.DEAD) {
                deadAmount += 1;
            }
            if(deadAmount >= philosopherList.size()) {
                everyoneIsDead = true;
            }
        }
        return everyoneIsDead;
    }

    /**
     * Checks if the adjacent philosophers on the table are eating.
     * If none are eating, grant eating permission to the given philosopher
     * @param index
     */
    public void setEatingPermission(int index){
        Philosopher targetPhilosopher = getPhilosopher(index);
        Philosopher leftPhilosopher = philosopherList.get((index + philosopherList.size()+1) % philosopherList.size());
        Philosopher rightPhilosopher = philosopherList.get((index + philosopherList.size()-1) % philosopherList.size());

        if (philosopherList.size() < index) {
            index = 0;
        }
        targetPhilosopher.losePermissionToEat();

        if(leftPhilosopher.getCurrentState() != Philosopher.State.EATING && rightPhilosopher.getCurrentState() != Philosopher.State.EATING) {
            targetPhilosopher.getPermissionToEat();
        }
    }
}
