/**
 * Classe qui représente un arbre binaire de recherche
 *@author Benjamin Sientzoff
 *@version 0.1 
 */

public class AVLTree<T extends Comparable> {

	private Node<T> root;
	
	public AVLTree(T root){
		this.root = new Node<T>(root);
	}
	
	public void add(T element){
		root.add(element);
	}
}

/**
 * 
 * @author Benjamin Sientzoff
 * Classe qui définit ce qu'est le Node d'une arbre binaire de recherche
 * @param <T extends {@link Comparable}> : Type des étiquettes du Node, doit implémenter l'interface Comparable !
 */
class Node<T extends Comparable> {
	
	private Node<T> leftSon, rightSon;
	private T tag;
	
	/**
	 * Constructeur simple, i.e. pour une feuille
	 * @param tag  Étiquette du Node
	 */
	public Node(T tag){
		this.tag = tag;
		this.leftSon = null;
		this.rightSon = null;
	}
	
	/**
	 * Constructeur de Node
	 * @param tag Étiquette de Node
	 * @param leftSon Son left du Node
	 * @param rightSon Son right du Node
	 */
	public Node(T tag, Node<T> leftSon, Node<T> rightSon){
		this(tag);
		this.leftSon = leftSon;
		this.rightSon = rightSon;
	}
	
	/**
	 * Quelle est l'étiquette du Node?
	 * @return Étiquette du Node
	 */
	public T getTag(){
		return tag;
	}
	
	/**
	 * Le Node est-il une feuille ?
	 * @return retourne vrai si pas de Son, sinon faux
	 */
	public boolean isLeaf(){
		return (null == leftSon) && (null == rightSon);
	}
	
	/**
	 * Mutteur du Son left
	 * @param nouveauleftSon Nouveau Son gacuhe du Node
	 */
	public void setLeftSon(Node<T> nouveauleftSon){
		this.leftSon = nouveauleftSon;
	}
	
	/**
	 * Mutteur du Son right
	 * @param nouveaurightSon Nouveau Son right du Node
	 */
	public void setRightSon(Node<T> nouveaurightSon){
		this.leftSon = nouveaurightSon;
	}
	
	/**
	 * Sélecteur du Son right d'un Node
	 * @return Son right du Node
	 * @throws NoSonException Exception si pas de Son right
	 */
	public Node<T> getRightSon() throws NoSonException{
		if(null == rightSon)
			throw new NoSonException("Le Node n'a pas de Son right!");
		return rightSon;
	}
	
	/**
	 * Sélecteur du Son right d'un Node
	 * @return Son left du Node
	 * @throws NoSonException Exception si pas de Son left
	 */
	public Node<T> getLeftSon() throws NoSonException{
		if(null == leftSon)
			throw new NoSonException("Le Node n'a pas de Son left!");
		return leftSon;
	}
	
	@Override
	/**
	 * Parcours infixé
	 */
	public String toString(){
		if(isLeaf()){
			return tag.toString();
		}
		else{
			/**
			 * TODO finish this shit ! And translate code
			 */
		}
	}
	
	/**
	 * add un élément sous un Node
	 * @param element Élement à add
	 */
	public void add(T element){
		// if element is 'less' than tag
		if(0 < tag.compareTo(element)){
			// then add element on the left
			// if it's a leaf
			if(isLeaf()) {
				// then add it
				leftSon = new Node<T>(element);
			} else {
				// else recursive call to the son
				leftSon.add(element);
			}
		} else {
			// else add element on the right
			// if it's a leaf
			if(isLeaf()) {
				// then add it
				rightSon = new Node<T>(element);
			} else {
				// else recursive call to the son
				rightSon.add(element);
			}
		}
	}
}