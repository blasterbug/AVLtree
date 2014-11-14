package Tree;

/**
 * Node in an AVL tree
 * @author Benjamin Sientzoff, Thomas Minier
 * @param <T extends {@link Comparable}> : Type des étiquettes du Node, doit implémenter l'interface Comparable !
 */
class AVLNode<T extends Comparable> extends ABRNode<T> {
	
	/**
	 * Constructeur simple, i.e. pour une feuille
	 * @param tag  Étiquette du Node
	 */
	public AVLNode(T tag){
		super(tag);
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
		super.add(element); //ajout de l'élément dans l'arbre
		
		if(this.leftSon == null) {
			this.balance = 1;
		} else if (this.rightSon == null) {
			this.balance = -1;
		} else {
			this.balance = this.rightSon.computeHeight() - this.leftSon.computeHeight();
		}
		equilibrer(this); //équilibrage de l'arbre
		
	}
	
	/**
	 * Méthode qui équilibre l'arbre
	 * @param nodeA L'arbre sur lequel effectuer l'équlibrage
	 */
	private void equilibrer(ABRNode<T> nodeA) {
		// If the balance == 2, then the tree is unbalanced to the right
		if(nodeA.balance == 2) {
			if(nodeA.rightSon.balance >= 0) {
				// We perform a simple left rotation
				nodeA = this.leftRotation(nodeA);
			} else { // else, we perform a double left rotation
				nodeA.rightSon = this.rightRotation(nodeA.rightSon);
				nodeA = this.leftRotation(nodeA);
			}
		} else if(nodeA.balance == -2) { // else, if == -2, then the tree is unbalanced to the left
			if(nodeA.leftSon.balance <= 0) {
				// We perform a simple right rotation
				nodeA = this.rightRotation(nodeA);
			} else { // else, we perform a double right rotation
				nodeA.leftSon = this.leftRotation(nodeA.leftSon);
				nodeA = this.rightRotation(nodeA);
			}
		}
	}
	
	/**
	 * Méthode qui effectue une rotation gauche sur un arbre, puis retourne la racine du nouvel arbre
	 * @param nodeA L'arbre sur lequel effectuer la rotation
	 * @return La racine de l'arbre après avec la rotation gauche
	 */
	private ABRNode<T> leftRotation(ABRNode<T> nodeA) {
		int a, b;
		ABRNode<T> nodeB = nodeA.rightSon;

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
	private ABRNode<T> doubleLeftRotation(ABRNode<T> nodeA) {
		nodeA.rightSon = this.leftRotation(nodeA.rightSon);
		return this.leftRotation(nodeA);
	}
	
	/**
	 * Méthode qui effectue une rotation droite sur un arbre, puis retourne la racine du nouvel arbre
	 * @param nodeA L'arbre sur lequel effectuer la rotation
	 * @return La racine de l'arbre formé après la rotation droite
	 */
	private ABRNode<T> rightRotation(ABRNode<T> nodeA) {
		int a, b;
		ABRNode<T> nodeB = nodeA.leftSon;
		
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
	private ABRNode<T> doubleRightRotation(ABRNode<T> nodeA) {
		nodeA.leftSon = this.rightRotation(nodeA.leftSon);
		return this.rightRotation(nodeA);
	}
}
