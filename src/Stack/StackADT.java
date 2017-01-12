package Stack;

import java.util.Iterator;

public interface StackADT<T> {

	/**
	 * adds element to the to of the stack
	 * */
	public void push(T element);
	
	/**
	 * removes element from the top of the stack
	 * @return the element that was removed
	 * */
	public T pop();
	
	/**
	 * @return the element at the top of the stack but does not remove
	 * */
	public T peek();
	
	/**
	 * @return true if stack is empty and false if not empty
	 * */
	public boolean isEmpty();
	
	/**
	 * @return number of elements in the stack
	 * */
	public int size();

}
