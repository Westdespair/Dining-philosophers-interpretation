public class Philosopher implements Runnable {
    private String name;
    private int starvation = 0;
    private int starvationLimit = 100;
    private int starvationRate = 1;
    private int eatingRate = 1;
    private boolean isAlive = true;
    private enum State {THINKING, EATING, HUNGRY, DEAD};
    private State currentState = State.THINKING;
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
            if(currentState != previousState) {
                printStatus();
            }
            try {
                Thread.sleep(200);
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
        name = this.name;
    }

    /**
     *
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

    private void act() {
        //Hunger is always growing
        starve();
        setCurrentState();
        switch(currentState) {
            case THINKING:
                break;

            case HUNGRY:
                break;

            case EATING:
                eat();
                if(starvation == 0) {

                }
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

            if(hungerLimit < starvation && currentState != State.EATING) {
                currentState = State.HUNGRY;

                if(starvationLimit <= starvation) {
                    currentState = State.DEAD;
                }

            } else {
                currentState = State.THINKING;
            }
        }

        public void forceState(State state) {
            switch(state) {
                case THINKING:
                    currentState = State.THINKING;
                    break;
                case HUNGRY:
                    currentState = State.HUNGRY;
                    break;

                case EATING:
                    currentState = State.EATING;

                case DEAD:
                    currentState = State.DEAD;
                    break;
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
}
