import java.util.ArrayList;

/**
 * Seats an amount of Philosopher instances around a table.
 * Contains logic to give and revoke philosophers permission to eat.
 */
public class DiningTable {
    private ArrayList<Philosopher> philosopherList = new ArrayList<>();
    public final int tableLimit = 5;

    /**
     * Constructor for the class DiningTable.
     * Creates an instance of DiningTable, and generates a given amount of Philosopher instances around the table.
     * @param philosopherAmount The amount of philosophers to be seated around the table
     */
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

    /**
     * Returns the given philosopher on the index of the philosopherlist.
     * @param index
     * @return
     */
    public Philosopher getPhilosopher (int index) {
        return philosopherList.get(index);
    }

    /**
     * Checks if every single philosopher seated at the table is dead.
     * @return True if everyone is dead, false if at least one philosopher is alive.
     */
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
     * @param index The given Philosopher instance in the list.
     */
    public void setEatingPermission(int index){
        Philosopher targetPhilosopher = getPhilosopher(index);
        Philosopher leftPhilosopher = philosopherList.get((index + philosopherList.size() - 1) % philosopherList.size());
        Philosopher rightPhilosopher = philosopherList.get((index + 1) % philosopherList.size());

        //Give the given philosopher permission to eat, but revoke it if any of their neighbours are eating.
        targetPhilosopher.getPermissionToEat();
        if(leftPhilosopher.isEating() || rightPhilosopher.isEating()) {
            targetPhilosopher.losePermissionToEat();
        } else {
            //Index philosopher now has permission to eat, and the permission is revoked from the adjacent philosophers.
            leftPhilosopher.losePermissionToEat();
            rightPhilosopher.losePermissionToEat();
        }
    }
}
