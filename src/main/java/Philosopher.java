import java.util.ArrayList;

public class Philosopher {
    private String name;
    private int hunger;
    private boolean isAlive;
    private enum state {THINKING, EATING, HUNGRY, DEAD};
    private static String[] nameList = new String[]{"Plato", "Aristotle", "Diogenes", "Nietsche", "Confusius",
            "Sigmund Freud", "Immanuel Kant", "Rene Descartes", "Immanuel Kant", "Sindre", "Malin", "Jonas" };

    /**
     * Creates an instance of the Philosopher class. A name is selected from the index.
     * If the index is out of bounds, uses the provided name instead.
     * @param nameIndex
     * @param alternativeName
     */
    public Philosopher(int nameIndex, String alternativeName) {
        setNameFromList(nameIndex);
    }

    public Philosopher(String name) {
        name = this.name;
    }


    public boolean setNameFromList(int index) {
        boolean nameChanged = false;
        //If the index is within bounds, set the name to the name in the index
        if (index < nameList.length) {
            this.name = nameList[index];
            nameChanged = true;
        }
        return nameChanged;
    }
}
