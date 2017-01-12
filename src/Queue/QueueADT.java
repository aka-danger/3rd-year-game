package Queue;

public interface QueueADT<T> extends Iterable<T>{
	
	/**
	 * Add element to queue
	 * */
	public void  enqueue(T element);
	
	/**
	 * remove element from queue
	 * @return element that has been removed
	 * */
	public T dequeue();
	
	/**
	 * @return first element in the queue
	 * */
	public T first();
	
	/**
	 * @return size of the queue
	 * */
	public int size();
	
	
	/**
	 * @return true if empty else false
	 * */
	public boolean isEmpty();
}
