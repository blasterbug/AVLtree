package utils.Tree;

/**
 * A generic class for a Binary search tree
 * 
 *@author Benjamin Sientzoff, Thomas Minier
 *@version 0.1 
 */
public class ABRTree<T extends Comparable> {
	
	protected ABRNode<T> root;
	
	/**
	 * Constructeur
	 * @param root L'étiquette du noeud de la racine
	 */
	public ABRTree(T root){
		this.root = new ABRNode<T>(root);
	}
	
	/**
	 * Constructeur vide
	 */
	public ABRTree(){
		this.root = null;
	}
	
	/**
	 * Méthode testant si un élément est dans l'arbre
	 * @param elt L'élément dont on veut tester l'existence
	 * @return True si l'élément est présent. Faux sinon
	 */
	public boolean contains(T elt) {
		return this.root.contains(elt);
	}
	
	/**
	 * Méthode ajoutant un élément à l'arbre
	 * @param element élément à ajouter dans l'arbre
	 */
	public void add(T element){
		if(null == root){
			root = new ABRNode<T>(element);
		} else {
			root.add(element);
		}
	}
	
	/**
	 * Méthode renvoyant une représentation sous forme de chaîne de caractères de l'arbre
	 * @return L'arbre sous la forme d'une chaîne de caractères
	 */
	public String toString(){
		if(root == null) {
			return "";
		} else {
			return root.toString();
		}
	}
	
	/**
	 * Méthode retournant le nombre d'éléments communs à deux arbres
	 * @param arbre L'autre arbre dont on veut comparer les éléments avec l'arbre courant
	 * @return Le nombre d'éléments communs entre les deux arbres
	 */
	public int nbCommuns(ABRTree<T> arbre) {
		if(root == null) {
			return 0;
		} else {
			return this.root.nbCommuns(arbre.root);
		}
	}

}
