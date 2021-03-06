public class Philosopher implements Runnable {
    private String name;
    private int starvation = 0;
    private int starvationLimit = 100;
    private int starvationRate = 1;
    private int eatingRate = 2;
    private boolean isAlive = true;
    public boolean permissionToEat = false;
    public enum State {THINKING, EATING, HUNGRY, DEAD};
    private State currentState;
    private final String[] nameList = new String[]{"Plato", "Aristotle", "Diogenes", "Nietsche", "Confusius",
            "Sigmund Freud", "Immanuel Kant", "Rene Descartes", "Immanuel Kant", "Sindre", "Malin", "Jonas" };

    /**
     * Makes the philosopher act, as long as it is still alive
     */
    @Override
    public void run() {
        while (isAlive) {
            State previousState = currentState;
            act();
            //Only prints the status if it has changed.
            if(currentState != previousState) {
                printStatus();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Creates an instance of the Philosopher class. A name is selected from the index.
     * If the index is out of bounds, uses the provided name instead.
     * @param nameIndex
     * @param alternativeName
     */
    public Philosopher(int nameIndex, String alternativeName) {
        //Attempts to give a name based on the index. If the index is out of bounds, uses alternative name instead.
        if(!setNameFromList(nameIndex)) {
            this.name = alternativeName;
        }
    }

    /**
     * Alternate method of constructing a philosopher. Only needs a provided name
     * @param name the name of the philosopher.
     */
    public Philosopher(String name) {
        this.name = name;
    }

    /**
     * @param index the index of the name to be used. If the index is too high, do nothing.
     * @return true if the name was successfully changed, false if not.
     */
    public boolean setNameFromList(int index) {
        boolean nameChanged = false;
        //If the index is within bounds, set the name to the name in the index
        if (index < nameList.length) {
            this.name = nameList[index];
            nameChanged = true;
        }
        return nameChanged;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Increments the philosophers hunger, and sets the new state based on the current hunger.
     * If the philosopher is eating or dead, do appropriate actions for those states.
     */
    private void act() {
        //Hunger is always growing
        starve();
        setCurrentState();
        switch(currentState) {
            case EATING:
                eat();
                break;

            case DEAD:
                isAlive = false;
                break;
        }
    }

    /**
     * sets the current state of the philosopher, based on their hunger level.
     */
    private void setCurrentState() {
        int hungerLimit = (int)(starvationLimit/2);

            //If the philosopher is starving more than their limit and is currently not eating, set their state to hungry
            if(hungerLimit < starvation && currentState != State.EATING) {
                currentState = State.HUNGRY;
                if(permissionToEat){
                    currentState = State.EATING;
                }

                if(starvationLimit <= starvation) {
                    currentState = State.DEAD;
                }

            } else {
                currentState = State.THINKING;
            }
        }

    /**
     * Prints the current state of the philosopher.
     */
    public void printStatus() {
        System.out.println(name + " is " + currentState.toString());
    }

    /**
     * The philosopher starves, increasing their starvation based on the starvationrate.
     */
    public void starve() {
        starvation += starvationRate;
    }

    /**
     * The philosopher eats, reducing their starvation based on their eatingrate.
     */
    public void eat() {
        starvation -= eatingRate;
    }

    public State getCurrentState() {
        return this.currentState;
    }

    public boolean isEating() {
        return getCurrentState() == State.EATING;
    }

    public void getPermissionToEat () {
        permissionToEat = true;
    }

    public void losePermissionToEat (){
        permissionToEat = false;
    }
 }
