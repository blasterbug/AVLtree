
/**
 * Node in an AVL tree
 * @author Benjamin Sientzoff, Thomas Minier
 * @param <T extends {@link Comparable}> : Type des étiquettes du Node, doit implémenter l'interface Comparable !
 */
class AVLNode<T extends Comparable> extends ABRNode<T> {
	
	private int balance;
	
	/**
	 * Constructeur simple, i.e. pour une feuille
	 * @param tag  Étiquette du Node
	 */
	public AVLNode(T tag){
		super(tag);
		this.balance = 0;
	}
	
	/**
	 * Constructeur de Node
	 * @param tag Étiquette de Node
	 * @param leftSon Son left du Node
	 * @param rightSon Son right du Node
	 */
	public AVLNode(T tag, AVLNode<T> leftSon, AVLNode<T> rightSon){
		super(tag, leftSon, rightSon);
	}

	/**
	 * add un élément sous un Node
	 * @param element Élement à add
	 */
	public void add(T element){
		super.add(element);
		this.balance += this.computeHeight();
		equilibrer(this);
	}
	
	/**
	 * Méthode qui équilibre l'arbre
	 * @param nodeA L'arbre sur lequel effectuer l'équlibrage
	 * @return La racine du nouvel arbre formé après équilibrage
	 */
	private ABRNode<T> equilibrer(AVLNode<T> nodeA) {
		// If the balance == 2, then the tree is unbalanced to the right
		if(nodeA.balance == 2) {
			AVLNode<T> right = (AVLNode<T>) nodeA.rightSon;
			if(right.balance >= 0) {
				// We perform a simple left rotation
				return this.leftRotation(nodeA);
			} else { // else, we perform a double left rotation
				return this.doubleLeftRotation(nodeA);
			}
		} else if(nodeA.balance == -2) { // else, if == -2, then the tree is unbalanced to the left
			AVLNode<T> left = (AVLNode<T>) nodeA.leftSon;
			if(left.balance <= 0) {
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
	private ABRNode<T> leftRotation(AVLNode<T> nodeA) {
		int a, b;
		
		AVLNode<T> nodeB = (AVLNode<T>) nodeA.rightSon;
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
	private ABRNode<T> doubleLeftRotation(AVLNode<T> nodeA) {
		nodeA.rightSon = this.leftRotation((AVLNode<T>) nodeA.rightSon);
		return this.leftRotation(nodeA);
	}
	
	/**
	 * Méthode qui effectue une rotation droite sur un arbre, puis retourne la racine du nouvel arbre
	 * @param nodeA L'arbre sur lequel effectuer la rotation
	 * @return La racine de l'arbre formé après la rotation droite
	 */
	private ABRNode<T> rightRotation(AVLNode<T> nodeA) {
		int a, b;
		
		AVLNode<T> nodeB = (AVLNode<T>) nodeA.leftSon;
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
	private ABRNode<T> doubleRightRotation(AVLNode<T> nodeA) {
		nodeA.leftSon = this.rightRotation((AVLNode<T>) nodeA.leftSon);
		return this.rightRotation(nodeA);
	}
}
