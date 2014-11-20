import java.util.Vector;

import IOFile.TextFileReader;
import Tree.AVLTree;

/**
 * Programme principal
 * Merci de le lancer en ligne de commande (voir le README pour plus d'informations)
 * @author Benjamin Sientzoff
 * @author Thomas Minier
 * @version 0.1 9 oct. 2014
 */

public class Bazar {

	public static void main(String[] args) {
				int k = Integer.parseInt(args[0]); // nombre de mots en commun si deux pages font partie du même chapitre
        String dictionnaire = args[1]; // dictionnaire 
        TextFileReader reader = new TextFileReader();
        Vector<AVLTree<String>> collection = new Vector<AVLTree<String>>(); //TODO temporaire, à remplacer
        
        // Création des arbres & des classes 
        // on parcours la liste des fichiers passés en arguments
        for(int ind = 2; ind < args.length; ++ind) {
        	// si le fichier courant existe
        	if(reader.openTextFile(args[ind])) {
        		String mots[];
        		AVLTree<String> arbre = new AVLTree<String>();
        		// on parcours le fichier ligne par ligne
        		while(reader.available()) {
        			// on récupère tous les mots de la ligne courante
        			mots = reader.readWordsPerLine();
        			// on parcours le tableau des mots et on les ajoute dans l'arbre
        			for(String mot: mots) {
        				arbre.add(mot);
        			}
        			
        		}
        		// on ajoute l'arbre à la collection (TODO temporaire, utiliser les classes unions ici)
        		collection.add(arbre);
        		// on ferme le fichier courant
        		reader.closeFile();
        	} else {
        		System.out.println("Erreur : fichier inexistant");
        	}
        }
	}

}
