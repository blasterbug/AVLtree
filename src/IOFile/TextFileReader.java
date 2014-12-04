package IOFile;/// a text file as a buffer input

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Open a text file and read words from it
 * @author Benjamin Sientzoff
 * @author Thomas Minier
 * @version 0.1 6 nov. 2014
 */

public class TextFileReader
{
    private BufferedReader readFile;

    public TextFileReader()
    {
    }

    /**
     * Open a text file
     *
     * @param fileName Name of the file to open
     * @return True if file opened, else false
     */
    public boolean openTextFile( String fileName )
    {
        if(null != readFile )
        {
            try
            {
                readFile.close();
            }
            catch (IOException ex)
            {
                System.err.println( ex.getMessage() );
            }
        }
        try
        {
            readFile = new BufferedReader( new FileReader(fileName) );
            return true;
        }
        catch (IOException ex)
        {
            System.err.println( ex.getMessage() );
            return false;
        }
    }

    /**
     * Close a file opened previously
     */
    public void closeFile()
    {
        if (null != readFile)
        {
            try
            {
                readFile.close();
            }
            catch (IOException ex)
            {
                System.err.println(ex.getMessage());
            }
        }
        else
        {
            System.err.println( "Pas de fichier ouvert" );
        }
    }

    /**
     * Are there words to read?
     * @return True if file is not ended, else false is returned
     */
    public boolean available()
    {
        try
        {
            return readFile.ready();
        }
        catch ( IOException ex )
        {
            System.err.println( ex.getMessage() );
            return false;
        }
    }

    /**
     * Read words per line in the file
     * @return Array of String where each string is a word
     */
    public String[] readWordsPerLine()
    {

        try
        {
            String line = "";
            while ( 0 == line.length() && readFile.ready() )
            {
                line = readFile.readLine();
            }
            String mots[] = line.split( "[.;,?!:'\"\\s]" );
            for( String mot : mots )
            {
                System.out.println( mot );
            }
            return mots;
        }
        catch ( IOException ex )
        {
            System.err.println( ex.getMessage() );
            return null;
        }
    }
}
