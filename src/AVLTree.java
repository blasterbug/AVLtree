/**
 * AVL tree (Georgy Adelson-Velsky and Landis' tree, 
 * named after the inventors) is a self-balancing 
 * binary search tree.
 * 
 *@author Benjamin Sientzoff, Thomas Minier
 *@version 0.1 
 */

public class AVLTree<T extends Comparable> {

	private Node<T> root;
	
	public AVLTree(T root){
		this.root = new Node<T>(root);
	}
	
	public AVLTree(){
		this.root = null;
	}
	
	public void add(T element){
		if(null == root){
			root = new Node<T>(element);
		} else {
			root.add(element);
		}
	}
	
	public String toString(){
		return root.toString();
	}
}

/**
 * Node in a aVL tree
 * @author Benjamin Sientzoff, Thomas Minier
 * Classe qui définit ce qu'est le Node d'une arbre binaire de recherche
 * @param <T extends {@link Comparable}> : Type des étiquettes du Node, doit implémenter l'interface Comparable !
 */
class Node<T extends Comparable> {
	
	private Node<T> leftSon, rightSon;
	private T tag;
	private int height;
	
	/**
	 * Constructeur simple, i.e. pour une feuille
	 * @param tag  Étiquette du Node
	 */
	public Node(T tag){
		this.height = 0;
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
		updateHeight();
	}
	
	/**
	 * Update node height
	 */
	private void updateHeight(){
		if( this.leftSon.height < this.rightSon.height ){
			this.height = this.rightSon.height;
		} else {
			this.height = this.leftSon.height;
		}
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
		return ( null == leftSon ) && ( null == rightSon );
	}
	
	/**
	 * Mutteur du Son left
	 * @param nouveauleftSon Nouveau Son gacuhe du Node
	 */
	public void setLeftSon(Node<T> nouveauleftSon){
		this.leftSon = nouveauleftSon;
		updateHeight();
	}
	
	/**
	 * Mutteur du Son right
	 * @param nouveaurightSon Nouveau Son right du Node
	 */
	public void setRightSon(Node<T> nouveaurightSon){
		this.leftSon = nouveaurightSon;
		updateHeight();
	}
	
	/**
	 * Sélecteur du Son right d'un Node
	 * @return Son right du Node
	 * @throws NoSonException Exception si pas de Son right
	 */
	public Node<T> getRightSon() throws NoSonException{
		if( null == rightSon )
			throw new NoSonException("Le Node n'a pas de Son right!");
		return rightSon;
	}
	
	/**
	 * Sélecteur du Son right d'un Node
	 * @return Son left du Node
	 * @throws NoSonException Exception si pas de Son left
	 */
	public Node<T> getLeftSon() throws NoSonException{
		if( null == leftSon )
			throw new NoSonException("Le Node n'a pas de Son left!");
		return leftSon;
	}
	
	@Override
	/**
	 * Parcours infixé
	 */
	public String toString(){
		
			String toReturn = "";
			
			if( null != leftSon ){
				toReturn += leftSon.toString();
			}
			
			toReturn += "," +tag.toString()+ "(" + height + ")";
			
			if( null != rightSon ){
				toReturn += rightSon.toString();
			}
			return toReturn;
	}
	
	/**
	 * add un élément sous un Node
	 * @param element Élement à add
	 */
	public void add(T element){
		// update node height
		++height;
		// if element is 'less' than tag
		if( 0 < tag.compareTo(element) ){
			// then add element on the left
			// if there is not left son
			if( null == leftSon ) {
				// then create it
				leftSon = new Node<T>(element);
			} else {
				// else recursive call to the son
				leftSon.add(element);
			}
		} else {
			// else add element on the right
			// if no right son
			if( null == rightSon ) {
				// then create it
				rightSon = new Node<T>(element);
			} else {
				// else recursive call to the son
				rightSon.add(element);
			}
		}
	}

}