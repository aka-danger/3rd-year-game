package Trees;

public interface BinaryTree<T> {
	/**
	 * @return the left node at node p
	 * */
	Position<T> left(Position<T> p) throws IllegalArgumentException;
	
	/**
	 * @return right node ate node p
	 * */
	Position<T> right (Position<T> p);
	
	/**
	 * @return the position of the sibling at p or null if p has no sibling
	 * */
	Position<T> sibling(Position<T> p);
	
	
	
}
