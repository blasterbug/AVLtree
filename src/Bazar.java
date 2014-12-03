import java.util.Vector;
import IOFile.TextFileReader;
import Tree.AVLTree;
import Union.UnionFind;


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
			
			// Variables
			int k = Integer.parseInt(args[0]); // nombre de mots en commun si deux pages font partie du même chapitre
			
			// dictionnaire
      AVLTree<String> dictionnaire = new AVLTree<String>();
      
      // lecteur de fichier
      TextFileReader reader = new TextFileReader();
      
      // collection des paires (id_page, nb mots du dictionnaire dans la page)
      // elle est représentée sous la forme paire(arbre, nb mots)
      Vector<Pair<String, Integer>> collection = new Vector<Pair<String, Integer>>();
      
      // On stocke le contenu du dictionnaire dans un AVL
      
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
      } else { // si le fichier du dictionnaire n'existe pas, erreur fatale
      	System.err.println("Erreur fatale : le fichier du dictionnaire n'existe pas");
      	System.exit(0); // arrêt du programme
      }
      
      // Création des classes
      
      // on parcours la liste des fichiers passés en arguments
      for(int ind = 2; ind < args.length; ++ind) {    	
      	
      	// si le fichier courant existe
      	if(reader.openTextFile(args[ind])) {
      		String mots[];
      		Pair<String, Integer> pair = new Pair<String, Integer>();
      		
      		// on récupère tous les mots de la ligne courante
      		mots = reader.readWordsPerLine();
      		
      		// on set l'id de la paire et le nombre de mots 
      		// de la page dans le dictionnaire à 0
    			pair.setLeft(mots[0]);
    			pair.setRight(0);
      		
      		// on parcours le reste du fichier ligne par ligne
      		while(reader.available()) {	
 
      			// on récupère tous les mots de la ligne courante
        		mots = reader.readWordsPerLine();
        		
        		// on parcours le tableau des mots
        		for(String mot: mots) {
        			// on vérifie si le mot courant est dans le dictionnaire
        			if(dictionnaire.contains(mot)) {
        				// on incrémente alors le nb dans la paire
        				pair.setRight(pair.getRight() + 1);    				
        			}
        		}   			
      		}
    			
    			// on ajoute la paire à la collection
    			collection.add(pair);
      		
      		// on ferme le fichier courant
      		reader.closeFile();
      		
      	} else { // si le fichier n'existe pas, on affiche une erreur
      		System.err.println("Erreur : fichier inexistant");
      	}
      }
      
      // on crée la classe-union à partir de la collection
      UnionFind<Pair<String, Integer>> unionFind = new UnionFind<Pair<String, Integer>>(collection);
      
      // on procède aux unions des classes pour créer les chapitres
      
      // on parcours les classes via la collection
      for(int ind = 0; ind < collection.size(); ind++) {
      	// on récupère la paire courante
      	Pair<String, Integer> pairA = collection.get(ind);
      	
      	// on parcours le reste des pairs
      	for(int j = 0; j < collection.size(); j++) {
      		// on récupère l'autre paire courante
      		Pair<String, Integer> pairB = collection.get(j);
      		
      		// si on peut unir les deux paires, on le fait
      		if( (pairA.getRight() == pairB.getRight()) && (pairA.getRight() >= k)) {
      			unionFind.union(pairA, pairB);
      		}
      	}
      }
      
      // on affiche le contenu de classes-union
      System.out.println(unionFind.toString());
      
		} else { // si on n'a pas le bon nombre d'arguments, on affiche une erreur
			System.err.println("Erreur fatale : le programme doit être lancé avec au moins 3 arguments (voir le README)");
		}	
	}

}
