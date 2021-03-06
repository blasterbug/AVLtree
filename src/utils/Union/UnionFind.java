package utils.Union;

import java.util.Collection;
import java.util.Vector;

/**
 * Class is a set data structure.
 * The data structure has a <i>representative</i>.
 * The most useful methods there are :
 *  - find to determinate if the data structure contains an element or not
 *  - union to join to classes
 * Class is in fact a tree, implemented in tables.
 * @author Benjamin Sientzoff
 * @version 0.1 14 nov. 2014
 */
public class UnionFind<T>
{
    /**
     * The tags table give element value.
     */
    private Vector<T> tags;
    /** The fathers table give for the same index number in tag the father
     * of a element in the tree.
     * If a element is his own father, then it's the root of tree.
     */
    private Vector<Integer> fathers;

    /**
     * Constructor
     * When creating a class, you need at least one element
     * @param elements The classes in the set
     */
    public UnionFind( Collection<T> elements )
    {
        tags = new Vector<T>();
        fathers = new Vector<Integer>();
        int i = 0;
        for(T element : elements) {
            tags.add(i , element);
            fathers.add(i ,i);
            ++i;
        }
    }

    /**
     * Constructor
     * When creating a class, you need at least one element
     * @param elements The classes in a tabular
     */
    public UnionFind( T[] elements )
    {
        tags = new Vector<T>();
        fathers = new Vector<Integer>();
        int i = 0;
        for(T element : elements)
        {
            tags.add( i , element );
            fathers.add( i ,i );
            ++i;
        }
    }

    /**
     * Is an element in the class ?
     * @param element element to test
     * @return true if the element is here else false
     */
    public boolean contains( T element )
    {
        return -1 != tags.indexOf( element );
    }


    /**
     * Who is the <i>representative</i> of a class ?
     * @param element Element to get his representative
     * @return The <i>representative</i> of the class which contains element
     */
    public T find( T element )
    {
        try
        {
            int idx = tags.indexOf( element );
            // si pas à la racine de l'arbre
            if ( !( fathers.get( idx ).equals( idx ) ) )
            {
                // compresser le chemin
                // remplacer le père de l'élément par son grand-père
                fathers.set( tags.indexOf( element ), tags.indexOf( find( tags.elementAt( fathers.elementAt( idx ) ) ) ) );
            }
            // retourner le père de l'élément, qui est forcément la racine
             return tags.elementAt( fathers.elementAt( idx ) );
        }
        catch( ArrayIndexOutOfBoundsException ex )
        {
            System.err.println( ex );
            return element;
        }
    }

    /**
     * Join the classes in which arg1 and arg2 belong to
     * @param arg1 an element of the first class to join
     * @param arg2 an element of the second class to join
     */
    public void union( T arg1, T arg2 )
    {
        T representative1 = find( arg1 );
        T representative2 = find( arg2 );
        try
        {
            fathers.setElementAt( tags.indexOf( representative1 ),
                                tags.indexOf( representative2 ) );
        }
        catch( ArrayIndexOutOfBoundsException ex)
        {
            System.err.println( ex );
        }
    }

    /**
     * Get a string representation of a class
     * @return String representation of the class
     */
    public String toString()
    {
        String ret = "";
        for ( int i = 0 ; i < fathers.size() ; i++ )
        {
            ret += "Chapitre n°" + fathers.get(i) + "\n";
            ret += i + " : [ " + tags.get(i) + " ]\n";
        }
        return ret;
    }
}
