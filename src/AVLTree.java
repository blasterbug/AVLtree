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
 * @param <T extends {@link Comparable}> : Type des étiquettes du Node, doit implémenter l'interface Comparable !
 */
class Node<T extends Comparable> {
	
	private Node<T> leftSon, rightSon;
	private T tag;
	private int balance;
	
	/**
	 * Constructeur simple, i.e. pour une feuille
	 * @param tag  Étiquette du Node
	 */
	public Node(T tag){
		this.balance = 0;
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
	 * What is a node height ?
	 */
	private int computeHeight(){
		if(null == leftSon){
			if(null == rightSon){
				// if node, height is 0
				return 0;
			}
			else{
				// height is right son height plus 1 (no left son)
				return 1 + rightSon.computeHeight();
			}
		}
		else {
			if(null == rightSon){
				// no right son, height is 1 plus 
				return 1 + leftSon.computeHeight();
			}
			else{
				// if two sons, height is sons maximum height
				return Math.max(leftSon.computeHeight(), rightSon.computeHeight());
			}
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
			
			toReturn += "," +tag.toString()+ "(" + computeHeight() + ")";
			
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
	
	/**
	 * Méthode qui équilibre l'arbre
	 * @param nodeA L'arbre sur lequel effectuer l'équlibrage
	 * @return La racine du nouvel arbre formé après équilibrage
	 */
	private Node<T> equilibrer(Node<T> nodeA) {
		// If the balance == 2, then the tree is unbalanced to the right
		if(nodeA.balance == 2) {
			if(nodeA.rightSon.balance >= 0) {
				// We perform a simple left rotation
				return this.leftRotation(nodeA);
			} else { // else, we perform a double left rotation
				return this.doubleLeftRotation(nodeA);
			}
		} else if(nodeA.balance == -2) { // else, if == -2, then the tree is unbalanced to the left
			if(nodeA.leftSon.balance <= 0) {
				// We perform a simple right rotation
				return this.rightRotation(nodeA);
			} else { // else, we perform a double right rotation
				return this.doubleRightRotation(nodeA);
			}
		} else { // else, the tree is balanced
			return nodeA;
		}
	}
	
	/**
	 * Méthode qui effectue une rotation gauche sur un arbre, puis retourne la racine du nouvel arbre
	 * @param nodeA L'arbre sur lequel effectuer la rotation
	 * @return La racine de l'arbre après avec la rotation gauche
	 */
	private Node<T> leftRotation(Node<T> nodeA) {
		int a, b;
		
		Node<T> nodeB = nodeA.rightSon;
		a = nodeA.balance;
		b = nodeB.balance;
		// Left rotation
		nodeA.rightSon = nodeB.leftSon;
		nodeB.leftSon = nodeA;
		// Update the balance of the new tree
		nodeA.balance = a - Math.max(b,0) - 1;
		nodeB.balance = Math.min(a - 2, Math.min(a + b - 2, b - 1));
		return nodeB;
		
	}
	
	/**
	 * Méthode qui effectue une double rotation gauche sur un arbre, puis retourne la racine du nouvel arbre
	 * @param nodeA L'arbre sur lequel effectuer la double rotation
	 * @return La racine de l'arbre formé après la double rotation gauche
	 */
	private Node<T> doubleLeftRotation(Node<T> nodeA) {
		nodeA.rightSon = this.leftRotation(nodeA.rightSon);
		return this.leftRotation(nodeA);
	}
	
	/**
	 * Méthode qui effectue une rotation droite sur un arbre, puis retourne la racine du nouvel arbre
	 * @param nodeA L'arbre sur lequel effectuer la rotation
	 * @return La racine de l'arbre formé après la rotation droite
	 */
	private Node<T> rightRotation(Node<T> nodeA) {
		int a, b;
		
		Node<T> nodeB = nodeA.leftSon;
		a = nodeA.balance;
		b = nodeB.balance;
		// Right rotation
		nodeA.leftSon = nodeB.rightSon;
		nodeB.rightSon = nodeA;
		// Update the balance of the new tree
		nodeA.balance = a - Math.max(b,0) - 1;
		nodeB.balance = Math.min(a - 2, Math.min(a + b - 2, b - 1));
		return nodeB;
		
	}
	
	/**
	 * Méthode qui effectue une double rotation droite sur un arbre, puis retourne la racine du nouvel arbre
	 * @param nodeA L'arbre sur lequel effectuer la double rotation
	 * @return La racine de l'arbre formé après la double rotation droite
	 */
	private Node<T> doubleRightRotation(Node<T> nodeA) {
		nodeA.leftSon = this.rightRotation(nodeA.leftSon);
		return this.rightRotation(nodeA);
	}

}