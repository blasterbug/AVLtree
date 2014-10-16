/**
 * Classe qui représente un arbre binaire de recherche
 *@author Benjamin Sientzoff
 *@author Thomas Minier
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
 * @author Thomas Minier
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
		if ( !( isLeaf() ) ) {
			
			return leftSon.toString() + ',' + tag.toString() +',' + rightSon.toString();
			
		}
		else {
			
			String toReturn = "";
			
			if( null != leftSon ){
				toReturn += leftSon.toString();
			}
			toReturn += tag.toString();
			
			if( null != rightSon ){
				toReturn += rightSon.toString();
			}
			
			return toReturn;
		}
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
			// if it's a leaf
			if( isLeaf() ) {
				// then add it
				leftSon = new Node<T>(element);
				// update node height
			} else {
				// else recursive call to the son
				leftSon.add(element);
			}
		} else {
			// else add element on the right
			// if it's a leaf
			if( isLeaf() ) {
				// then add it
				rightSon = new Node<T>(element);
			} else {
				// else recursive call to the son
				rightSon.add(element);
			}
		}
	}
	
	/**
	 * Méthode qui effectue une rotation gauche sur un arbre, puis retourne la racine du nouvel arbre
	 * @param nodeA Arbre sur lequel effectuer la rotation
	 * @return La racine de l'arbre après avec la rotation gauche
	 */
	private Node<T> leftRotation(Node<T> nodeA) {
		int a, b;
		
		Node<T> nodeB = nodeA.rightSon;
		a = nodeA.height;
		b = nodeB.height;
		// Left rotation
		nodeA.rightSon = nodeB.leftSon;
		nodeB.leftSon = nodeA;
		// Update the height of the new tree
		nodeA.height = a - Math.max(b,0) - 1;
		nodeB.height = Math.min(a - 2, Math.min(a + b - 2, b - 1));
		return nodeB;
		
	}
	
	/**
	 * Méthode qui effectue une double rotation gauche sur un arbre, puis retourne la racine du nouvel arbre
	 * @param nodeA arbre sur lequel effectuer la double rotation
	 * @param La racine de l'arbre formé après la double rotation gauche
	 */
	private Node<T> doubleLeftRotation(Node<T> nodeA) {
		nodeA = this.leftRotation(nodeA.rightSon);
		return this.leftRotation(nodeA);
	}
	
	/**
	 * Méthode qui effectue une rotation droite sur un arbre, puis retourne la racine du nouvel arbre
	 * @param nodeA Arbre sur lequel effectuer la rotation
	 * @return La racine de l'arbre formé après la rotation droite
	 */
	private Node<T> rightRotation(Node<T> nodeA) {
		int a, b;
		
		Node<T> nodeB = nodeA.leftSon;
		a = nodeA.height;
		b = nodeB.height;
		// Left rotation
		nodeA.leftSon = nodeB.rightSon;
		nodeB.rightSon = nodeA;
		// Update the height of the new tree
		nodeA.height = a - Math.max(b,0) - 1;
		nodeB.height = Math.min(a - 2, Math.min(a + b - 2, b - 1));
		return nodeB;
		
	}
	
	/**
	 * Méthode qui effectue une double rotation droite sur un arbre, puis retourne la racine du nouvel arbre
	 * @param nodeA arbre sur lequel effectuer la double rotation
	 * @param La racine de l'arbre formé après la double rotation droite
	 */
	private Node<T> doubleRightRotation(Node<T> nodeA) {
		nodeA = this.rightRotation(nodeA.leftSon);
		return this.rightRotation(nodeA);
	}

}