import java.util.NoSuchElementException;

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
		if(this.leftSon.height < this.rightSon.height){
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
		return (null == leftSon) && (null == rightSon);
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
		if (!(isLeaf())) {
			
			return leftSon.toString() + ',' + tag.toString() +',' + rightSon.toString();
			
		}
		else {
			
			String toReturn = "";
			
			if(null != leftSon){
				toReturn += leftSon.toString();
			}
			toReturn += tag.toString();
			
			if(null != rightSon){
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
		if(0 < tag.compareTo(element)){
			// then add element on the left
			// if it's a leaf
			if(isLeaf()) {
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
			if(isLeaf()) {
				// then add it
				rightSon = new Node<T>(element);
			} else {
				// else recursive call to the son
				rightSon.add(element);
			}
		}
	}
	
	/**
	 * méthode suppirmant l'élément passé en paramètre du noeud
	 * @throws NoSonException 
	 */
	public void delete(T element) throws NoSonException {
		//if the node is not a leaf
		if(! isLeaf()) {
			//if the element to delete is in the left son
			if(0 < tag.compareTo(element)) {
				//if the element to suppress is the left son of the node
				if(leftSon.getTag().compareTo(element) == 0) {
					//if the left son of the left son is not a leaf
					if(! leftSon.getLeftSon().isLeaf()) {
						//we replace the left son of the node by the left son of his left son
						leftSon = leftSon.getLeftSon();
					} else {
						//we replace the left son of the node by the right son of his left son
						leftSon = leftSon.getRightSon();
					}
				} else { //else, we call recursively the method in the left son
					leftSon.delete(element);
				}
			} else if(tag.compareTo(element) > 1) { //else, the element is in the right son
				//if the element to suppress is the left son of the node
				if(rightSon.getTag().compareTo(element) == 0) {
					//if the left son of the right son is not a leaf
					if(! rightSon.getLeftSon().isLeaf()) {
						//we replace the left son of the node by the left son of his right son
						rightSon = rightSon.getLeftSon();
					} else {
						//we replace the left son of the node by the right son of his right son
						rightSon = rightSon.getRightSon();
					}
				} else { //else, we call recursively the method in the right son
					rightSon.delete(element);
				}
			}
		}
	}

}