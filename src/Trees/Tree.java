package Trees;
import java.util.Iterator;

public interface Tree<T> extends Iterable<T> {

	/**
	 * The first node in the tree
	 * @return position of the root
	 * */
	public Position<T> root();
	
	/**
	 * parent node at a specific position
	 * @return position of the parent node 
	 * */
	public Position<T> parent(Position<T> p) throws IllegalArgumentException;
	
	/**
	 * All children of a node
	 * @return an Iterable position of the children (gives the ability to loop through these children)
	 * */
	public Iterable<Position<T>> children (Position<T> p)throws IllegalArgumentException;
	
	/**
	 * number of children at a node
	 * @return number of children
	 * */
	public int numChildren(Position<T> p)throws IllegalArgumentException;
	
	/**
	 * is the node is a leaf or not
	 * @return true if node is a leaf
	 * */
	public boolean isLeaf(Position<T> p)throws IllegalArgumentException;
	
	/**
	 * is the root node
	 * @return true if position is the root
	 * */
	public boolean isRoot(Position<T> p)throws IllegalArgumentException;
	
	/**
	 * number of nodes in the tree 
	 * @return size of the tree
	 * */
	public int size();
	
	/**
	 * @return is tree empty
	 * */
	public boolean isEmpty();
	
	/**
	 * the number of ancestors to get to the root excluding the node itself
	 * or its the number of edges to the root node
	 * 						o
	 * 					   / \
	 * 					  0   %
	 *                   / \ / \
	 *                  #  0 0  0
	 * # -> depth = 2      
	 * % -> depth = 1
	 * 
	 * @return depth of node
	 * */
	public int depth(Position<T> p );
	
	/**
	 * the path with the largest number of edges
	 * so from root/subtree ,take the longest path and add the edges
	 * @return height of tree or subtree
	 * */
	public int height(Position<T> p); 
	
	@Override
	public Iterator<T> iterator();
	
	/**
	 * @return an iteratable representation of all nodes in a tree
	 * */
	public Iterable<Position<T>> positions();
}
