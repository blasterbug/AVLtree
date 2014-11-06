/**
 * Run the current project
 * @author Benjamin Sientzoff
 * @version 0.1 9 oct. 2014
 */

public class Bazar {

	public static void main(String[] args) {
        String file = "germinal.txt";
        AVLTree<String> arbre = new AVLTree<String>();
        TextFileReader germinal = new TextFileReader();

        if(germinal.openTextFile(file)){
            String words[];
            while(germinal.available()){
                words = germinal.readWordsPerLine();
                for(String str : words){
                    System.out.println(str);
                    arbre.add(str);
                }
            }
            germinal.closeFile();
        }
        else {
            System.out.println( file + "not found.");
        }

        System.out.println(arbre.toString());

	}

}
