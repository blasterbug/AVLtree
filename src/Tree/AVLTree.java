package Tree;

/**
 * AVL tree (Georgy Adelson-Velsky and Landis' tree, 
 * named after the inventors) is a self-balancing 
 * binary search tree.
 * 
 *@author Benjamin Sientzoff, Thomas Minier
 *@version 0.1 
 */

public class AVLTree<T extends Comparable> extends ABRTree<T>{
	
	public AVLTree(T root){
		this.root = new AVLNode<T>(root);
	}
	
	public AVLTree(){
		this.root = null;
	}

}