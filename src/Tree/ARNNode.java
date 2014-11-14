package Tree;

import java.util.HashMap;
import java.util.Vector;

/**
 * Node in a Black & Red Tree (data structure created by Rudolf Bayer - 1972)
 * @author Benjamin Sientzoff, Thomas Minier
 * @param <T extends {@link Comparable}> : Type des étiquettes du Node, doit implémenter l'interface Comparable !
 */
public class ARNNode<T extends Comparable> extends ABRNode<T>{
	
	/**
	 * Constructeur simple, i.e. pour une feuille
	 * @param tag  Étiquette du Node
	 */
	public ARNNode(T tag){
		super(tag);
		estNoir = false;
	}
	
	/**
	 * Constructeur de Node
	 * @param tag Étiquette de Node
	 * @param leftSon Son left du Node
	 * @param rightSon Son right du Node
	 */
	public ARNNode(T tag, ARNNode<T> leftSon, ARNNode<T> rightSon){
		super(tag, leftSon, rightSon);
		estNoir = false;
	}
	
	/**
	 * Méthode qui calcule la hauteur noir d'un noeud
	 * @param node Noeud depuis lequel on calcule la hauteur noire
	 * @return La hauteur noire du noeud
	 */
	public int computeBlackHeight(ABRNode<T> node) {
		int h;
		if(this == null) {
			h = Math.max(computeBlackHeight(node.leftSon), computeBlackHeight(node.rightSon));
			if(node.estNoir) {
				return h + 1;
			} else {
				return h;
			}
		} else {
			return 1;
		}
	}
	
	/**
	 * add un élément sous un Node
	 * @param element Élement à add
	 */
	public void add(T element){
		super.add(element); //ajout de l'élément dans l'arbre
		
		//comportement propre à l'ARN
		
	}

}
