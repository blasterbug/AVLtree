package Union;

import java.util.Vector;

/**
 * Classe is a set data structure.
 * The data structure has a representative.
 * The most useful method there is find whic can
 * determinate if the data structure contains a element.
 * Class is in fact a tree, implemented in tables.
 * @author Benjamin Sientzoff
 * @version 0.1 14 nov. 2014
 */
public class Class<T> {
    /**
     * The sons table give element value.
     * The first element in the table is the representative of this class.
     */
    private Vector<T> sons;
    /** The fathers table give for the same index number as sons the father
     * of a element in the tree.
     * If a element is his own father, then it's the root of tree.
     */
    private Vector<Integer> fathers;

    /**
     * Constructor
     * When creating a class, you need at least one element
     * @param element The first element in the class
     */
    public Class(T element){
        sons = new Vector<T>();
        fathers = new Vector<Integer>();
        sons.add(0, element);
        fathers.add(0, 0);
    }

    /**
     * Get a string representation of a class
     * @return String representation of the class
     */
    public String toString(){
        return fathers + "\n" + sons;
    }
}
