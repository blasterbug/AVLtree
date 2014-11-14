package Tree;

/**
 * A generic class for a Black & Red Tree
 * 
 *@author Benjamin Sientzoff, Thomas Minier
 *@version 0.1 
 */
public class ARNTree<T extends Comparable> extends ABRTree<T> {
	
	public ARNTree(T root){
		this.root = new AVLNode<T>(root);
	}
	
	public ARNTree(){
		this.root = null;
	}
	
}
