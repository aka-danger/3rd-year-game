package Trees;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.RootPaneContainer;

import Game_Objects.Item;
import Items.*;
import Queue.Queue;


public class LinkedBinaryTree<T> extends AbstractBinaryTree<T> {

	//----------------------------Nested Node class-------------------	
	/**
	 * we make this inner class static because it is not alterd by the outside class
	 *and doesnt relate to the outer class
	 * */
	protected static class Node<T> implements Position<T>{

		private T element ;
		private Node<T> left ;
		private Node<T> right;
		private Node<T> parent; // important
		
		public Node(T element, Node<T> left, Node<T> right, Node<T> parent){
			this.element = element ;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}
		
		public Node<T> getParent(){return parent;}
		
		public void setParent (Node<T> parent){
			this.parent = parent;
		}
		
		public Node<T> getLeft() {
			return left;
		}

		public void setLeft(Node<T> left) {
			this.left = left;
		}

		public Node<T> getRight() {
			return right;
		}

		public void setRight(Node<T> right) {
			this.right = right;
		}

		public void setElement(T element) {
			this.element = element;
		}

		@Override
		public T getElement() {
			return element;
		}
		
	}
	
	//----------------------------Utility functions--------------------
	private Node<T> validate(Position<T> p) throws IllegalArgumentException{
		if(!(p instanceof Node))
			throw new IllegalArgumentException("Position is not of type node");
		Node<T> node = (Node<T>) p ; //safe cast
		if(node.getParent() == node)
			throw new IllegalArgumentException("Node is no longer in the tree");
		return node;
			
	}
	
	private Node<T> createNode(T element, Node<T> left, Node<T> right, Node<T> parent){
		return new Node<T> (element, left, right, parent);
	}
	
	//----------------------------Variables----------------------------
	private Node<T> root = null ;
	private int size = 0; 
	
	public LinkedBinaryTree() {
		// TODO Auto-generated constructor stub
	}
	
	//---------------------------Methods------------------------------
	
	@Override
	public Position<T> left(Position<T> p) throws IllegalArgumentException {
		Node<T> node = validate(p);
		return node.getLeft();
	}

	@Override
	public Position<T> right(Position<T> p) {
		Node<T> node = validate(p);
		return node.getRight();
	}

	@Override
	public Position<T> root() {
		return root;
	}

	@Override
	public Position<T> parent(Position<T> p) throws IllegalArgumentException {
		Node<T> node = validate(p);
		return node.getParent();
	}

	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Adds a root node to an empty tree
	 * @return position to root node
	 * */
	public Position<T> addRoot(T element) throws IllegalStateException{
		if(!(isEmpty())) throw new IllegalStateException("Tree is not empty");
		root = createNode(element, null, null, null);
		size++;
		return root ;
	}
	
	/**
	 * Adds node to the left of the parent
	 * if parent has a left node an error is thrown
	 * @return position of child
	 * */
	public Position<T> addLeft(T element, Position<T> p) throws IllegalArgumentException{
		Node<T> parent = validate(p);
		if(parent.getLeft() != null) 
			throw new IllegalArgumentException("There is already a left node");
		//remeber for a link to have effect it must be linked both ways
		Node<T> child = createNode(element,null, null,parent); 
		parent.setLeft(child);
		size++;
		return child;
	}
	
	/**
	 * Adds node to the right of the parent
	 * if parent has a right node an error is thrown
	 * @return the position of the child
	 * */
	public Position<T> addRight(T element, Position<T> p) throws IllegalArgumentException{
		Node <T> parent = validate(p);
		if(parent.getRight() != null)
			throw new IllegalArgumentException("There is already a right node");
		Node<T> child = createNode(element, null, null,parent);
		parent.setRight(child);
		size++;
		return child;
	}
	
	/**
	 * Replaces an element in a tree and returns the repleced element
	 * @return element that is replaced
	 * */
	public T replace(T element, Position<T> p) {
		Node<T> node = validate(p);
		T temp = node.getElement();
		node.setElement(element);
		return temp ;
	}
	
	/**
	 * Attaches 2 subtrees to a leaf node
	 * */
	public void attach(LinkedBinaryTree<T> leftTree,
			LinkedBinaryTree<T> rightTree, Position<T> p)
			throws IllegalArgumentException {
		
		Node<T> parent =  validate(p);
		if(!(isLeaf(p))) throw new IllegalArgumentException("Node is not a leaf node");
		size += leftTree.size() + rightTree.size();
		if(!(leftTree.isEmpty())){
			leftTree.root.setParent(parent);
			parent.setLeft(leftTree.root);
			leftTree.root = null;
			leftTree.size = 0 ; 
		}
		if(!(rightTree.isEmpty())){
			rightTree.root.setParent(parent);
			parent.setRight(rightTree.root);
			rightTree.root = null ;
			rightTree.size = 0 ; 
		}
		
	}
	
	/**
	 * removes node and replaces it with its child
	 * NB learn this shit !!!!!!!
	 * 
	 * */
	public T remove(Position<T> p) throws IllegalArgumentException{
		Node<T> node = validate(p);
		if(numChildren(p) == 2)
			throw new IllegalArgumentException("p has 2 children ");
		Node<T> child = (node.getLeft() != null? node.getLeft():node.getRight());
		if(child != null)
			child.setParent(node.getParent());
		if(node == root)
			root = child;
		else {
			Node<T> parent = node.getParent();
			if(node == parent.getLeft())
				parent.setLeft(child);
			else
				parent.setRight(child);
		}
		size --;
		T temp = node.getElement();
		node.setElement(null);
		node.setLeft(null);
		node.setRight(null);
		node.setParent(node);
		return temp;
	}

	//-----------------------------element iterator-------------------------
	private class ElementIterator implements Iterator <T>{
		Iterator<Position<T>> i = positions().iterator();

		@Override
		public boolean hasNext() {
			return i.hasNext();
		}

		@Override
		public T next() {
			return i.next().getElement();
		}
		
		public void remove(){
			i.remove();
		}
	}
	//---------------------------end iterator class-------------------------
	
	
	@Override
	public Iterator<T> iterator() {
		return new ElementIterator();
	}

	//positions will give you all the elements in the tree
	//you can chop and change which way to iterator  through the elements
	public Iterable<Position<T>> positions() {
		return preOrder();
	}
	
	//---------------------preorder-----------------------------------------
	//start at root
	//end at leaf 
	private void preOrderSubtree(Position<T> p , List<Position<T>>snapshot ){
		snapshot.add(p);
		for(Position<T> c : children(p))
			preOrderSubtree(c, snapshot);
	}
	
	private Iterable<Position<T>> preOrder(){
		List<Position<T>> snapshot = new ArrayList<>();
		if(!isEmpty()){
			preOrderSubtree(root(), snapshot);
		}
		return snapshot;
	}
	//---------------------end----------------------------------------------
	
	
	//-------------------------postorder -----------------------------------
	//start at most right leaf
	//end at root
	private void postOrderSubtree (Position<T> p, List<Position<T>> snaphot){
		for(Position<T> c : children(p)){
			postOrderSubtree(c, snaphot);
		}
		snaphot.add(p);
	}
	
	private Iterable<Position<T>> postOrder(){
		List<Position<T>> snapshot = new ArrayList<>();
		if(!isEmpty())
			postOrderSubtree(root(), snapshot);
		return snapshot;
	}
	//----------------------------------------------------------------------
	
	public String toString(Position<T> root){
		StringBuilder result = new StringBuilder();
		Node<T> node = validate(root);
		if(root == null)
			return null;
		result.append(toString(node.left) + "/");
		result.append("'\'" + toString(node.right));
		result.append("\n");
		return result.append(node.getElement().toString()).toString();
				
	}
	
	
	public static void main(String [] args){
		
		
		
		
		
		

		
	}
	
	
}
