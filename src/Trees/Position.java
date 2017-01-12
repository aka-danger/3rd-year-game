package Trees;

public interface Position<T> {
	/**
	 * How a position works
	 * - it refers to a particular node wherever it may be in memory
	 * - it enables u to get the element of a particular node whereever it is
	 * - it hides implementation of the node ADT
	 * @return the element of a node 
	 * */
	public T getElement ();
}
