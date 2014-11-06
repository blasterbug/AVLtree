/**
 * A generic class for a Binary search tree
 * 
 *@author Benjamin Sientzoff, Thomas Minier
 *@version 0.1 
 */
public class ABRTree<T extends Comparable> {
	
	protected ABRNode<T> root;
	
	public ABRTree(T root){
		this.root = new ABRNode<T>(root);
	}
	
	public ABRTree(){
		this.root = null;
	}
	
	public void add(T element){
		if(null == root){
			root = new ABRNode<T>(element);
		} else {
			root.add(element);
		}
	}
	
	public String toString(){
		return root.toString();
	}
}
