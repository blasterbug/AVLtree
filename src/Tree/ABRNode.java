package Tree;

/**
 * Node in an ABR tree
 * @author Benjamin Sientzoff, Thomas Minier
 * @param <T extends {@link Comparable}> : Type des étiquettes du Node, doit implémenter l'interface Comparable !
 */
public class ABRNode<T extends Comparable> {

	protected ABRNode<T> leftSon, rightSon;
	protected T tag;
	protected int balance;
	protected boolean	estNoir;
	
	/**
	 * Constructeur simple, i.e. pour une feuille
	 * @param tag  Étiquette du Node
	 */
	public ABRNode(T tag){
		this.tag = tag;
		this.leftSon = null;
		this.rightSon = null;
		this.balance = 0;
		this.estNoir = false;
	}
	
	/**
	 * Constructeur de Node
	 * @param tag Étiquette de Node
	 * @param leftSon Son left du Node
	 * @param rightSon Son right du Node
	 */
	public ABRNode(T tag, ABRNode<T> leftSon, ABRNode<T> rightSon){
		this.tag = tag;
		this.leftSon = leftSon;
		this.rightSon = rightSon;
		this.estNoir = false;
	}
	
	/**
	 * Méthode calculant la hauteur du noeud
	 * @return La hauteur du noeud
	 */
	protected int computeHeight(){
		
		if(this.isLeaf()) {
			return 0;
		} else {
			if(this.rightSon == null) {
				return 1 + leftSon.computeHeight();
			} else if(this.leftSon == null) {
				return 1 + rightSon.computeHeight();
			} else {
				return 1 + Math.max(leftSon.computeHeight(), rightSon.computeHeight());
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
	 * Méthode testant si un élément est dans l'arbre
	 * @param elt L'élément dont on veut tester l'existence
	 * @return True si l'élément est présent. Faux sinon
	 */
	public boolean contains(T elt) {
		//Si le noeud n'est pas une feuille
		if( ! this.isLeaf()) {
			//si l'élément courant correspond à l'élément recherché
			if( this.tag == elt) {
				//on renvoie vrai
				return true;
			} else {
				//sinon, on teste si le fils droit ou le fils gauche du noeud contiennent l'élément
				if(this.rightSon == null) {
					return this.leftSon.contains(elt);
				} else if(this.leftSon == null) {
					return this.rightSon.contains(elt);
				} else {
					return this.leftSon.contains(elt) || this.rightSon.contains(elt);
				}
			}
		} else { //sinon, on renvoie faux
			return false;
		}
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
	public void setLeftSon(ABRNode<T> nouveauleftSon){
		this.leftSon = nouveauleftSon;
	}
	
	/**
	 * Mutteur du Son right
	 * @param nouveaurightSon Nouveau Son right du Node
	 */
	public void setRightSon(ABRNode<T> nouveaurightSon){
		this.leftSon = nouveaurightSon;
	}
	
	/**
	 * Sélecteur du Son right d'un Node
	 * @return Son right du Node
	 * @throws NoSonException Exception si pas de Son right
	 */
	public ABRNode<T> getRightSon() throws NoSonException {
		if( null == rightSon )
			throw new NoSonException("Le Node n'a pas de Son right!");
		return rightSon;
	}
	
	/**
	 * Sélecteur du Son right d'un Node
	 * @return Son left du Node
	 * @throws NoSonException Exception si pas de Son left
	 */
	public ABRNode<T> getLeftSon() throws NoSonException {
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
				leftSon = new ABRNode<T>(element);
			} else {
				// else recursive call to the son
				leftSon.add(element);
			}
		} else {
			// else add element on the right
			// if no right son
			if( null == rightSon ) {
				// then create it
				rightSon = new ABRNode<T>(element);
			} else {
				// else recursive call to the son
				rightSon.add(element);
			}
		}
	}
	
}
