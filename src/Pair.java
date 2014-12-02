
/**
 * Classe représentant une paire d'éléments
 * @author Benjamin Sientzoff, Thomas Minier
 * @version 0.1 
 * @param <A> Type du premier élément de la paire
 * @param <B> Type du second élément de la paire
 */
public class Pair<A, B> {
	
	private A left;
	
	private B right;
	
	/**
	 * Constructeur avec les deux membres de la paire
	 * @param l Premier membre
	 * @param r Deuxième membre
	 */
	public Pair(A l, B r) {
		left = l;
		right = r;
	}
	
	/**
	 * Constructeur vide
	 */
	public Pair() {
		left = null;
		right = null;
	}
	
	/**
	 * Accesseur retournant le premier élément de la paire
	 * @return Le premier élément de la paire
	 */
	public A getLeft() {
		return left;
	}
	
	/**
	 * Mutateur définissant le premier élément de la paire
	 * @param l Le nouveau premier membre de la paire
	 */
	public void setLeft(A l) {
		left = l;
	}
	
	/**
	 * Accesseur retournant le second élément de la paire
	 * @return Le second élément de la paire
	 */
	public B getRight() {
		return right;
	}
	
	/**
	 * Mutateur définissant le second élément de la paire
	 * @param r Le nouveau second élément de la paire
	 */
	public void setRight(B r) {
		right = r;
	}
	
	/**
	 * Méthode retournant une représentation sous forme de chaîne de l'objet
	 * @return La représentation sous forme de chaîne de l'objet
	 */
	public String toString() {
		return left.toString();
	}
}
