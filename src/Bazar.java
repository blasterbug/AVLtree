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
		
		//on teste si le programme a le bon nombre d'arguments (au moins 3)
		if(args.length >= 3) {
			
			int k = Integer.parseInt(args[0]); // nombre de mots en commun si deux pages font partie du même chapitre
      Vector<String> dictionnaire = new Vector<String>(); // dictionnaire 
      TextFileReader reader = new TextFileReader();
      Vector<AVLTree<String>> collection = new Vector<AVLTree<String>>(); //TODO temporaire, à remplacer par des classes-unions
      
      // On stocke le contenu du dictionnaire dans une liste
      
      // si le fichier du dictionnaire existe
      if(reader.openTextFile(args[1])) {
      	String cles[];
      	
      	// on parcours le fichier
      	while(reader.available()) {
      		
	      	// on récupère tous les mots de la ligne courante
	    		cles = reader.readWordsPerLine();
	    		
	    		// on parcours la liste des mots de cette ligne
	    		for(String c: cles) {
	    			// on ajoute la clé courante au dictionnaire
	    			dictionnaire.add(c);
	    		}
	    		
      	}
      } else { //si le fichier du dictionnaire n'existe pas, erreur fatale
      	System.err.println("Erreur fatale : le fichier du dictionnaire n'existe pas");
      	System.exit(0); // arrêt du programme
      }
      
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
      		
      		// on ajoute l'arbre à la collection (TODO temporaire, utiliser les classes-unions ici)
      		collection.add(arbre);
      		
      		System.out.println(arbre.toString()); //affichage TODO à virer, ici pour des tests
      		
      		// on ferme le fichier courant
      		reader.closeFile();
      		
      	} else { // si le fichier n'existe pas, on affiche une erreur
      		System.err.println("Erreur : fichier inexistant");
      	}
      }
      
      // on procède aux unions des classes pour créer les chapitres
      
      // on parcours le dictionnaire
      for(String key: dictionnaire) {
      	
      	// on compte les occurences de la clé dans chacune des pages
      	
      		// on teste si l'arbre courant contient la clé
      		// si oui, on incrémente le nb d'occu de 1
      	
      	// on regarde chaque nb d'occurence associé à un arbre
      		// si deux arbres ont le même nb d'occurneces et que nb >= k, alors on fait l'union des deux arbres/classes     	     	
      	
      }
      
      // Affichage des différents chapitre
      
      // on parcours nos classes-unions
      	
      	// on affiche le numéro du chapitre et la liste des id des pages du chapitre
      
      
		} else { // si on n'a pas le bon nombre d'arguments, on affiche une erreur
			System.err.println("Erreur fatale : le programme doit être lancé avec au moins 3 arguments (voir le README)");
		}	
	}

}
