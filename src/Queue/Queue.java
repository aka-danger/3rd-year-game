package Queue;

import java.util.Iterator;

public class Queue<T> implements QueueADT<T>{

	//-------------nested node class-------
	private static class Node<T> {
		private Node<T> next;
		private T element;
		
		public Node(T element, Node<T> next){
			this.element = element;
			this.next = next;
		}

		public Node<T> getNext() {
			return next;
		}

		public void setNext(Node<T> next) {
			this.next = next;
		}

		public T getElement() {
			return element;
		}

		public void setElement(T element) {
			this.element = element;
		}
		
		
	}
	//-------------end nested node class---
	
	private Node<T> head = null;
	private Node<T> tail = null;
	private int size = 0;
	
	public Queue() {
	}
	
	public Queue(Queue<T> copy){
		
	}
	//--------------------------utility----------------
		
	public Queue<T> copy() throws IllegalArgumentException{
		if(this.isEmpty() || this == null){
			throw new IllegalArgumentException("List is empty or null");
		}
		Queue<T> temp = new Queue<T>();
		for(T i: this){
			temp.enqueue(i);
		}
		return temp;
	}

	//-------------------------methods------------------
	//addlast
	public void enqueue(T element) {
		Node<T> newNode = new Node<T>(element, null);
		if(isEmpty()){
			head = newNode;
		}else
			tail.setNext(newNode);
		tail = newNode;
		size++;
	}

	//remove first
	public T dequeue() {
		if(isEmpty()) return null ;
		T answer = head.getElement();
		head = head.next;
		size--;
		if(isEmpty())
			tail = null;
		return answer;
	}

	//first item
	public T first() {
		return head.getElement();
	}

	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}
	
	
	public String toString(){
		Node<T> i = head;
		String result = "";
		while(i != null){
			if(this.size ==3)
				result+= ""+i.getElement()+"";
			else 
				result+= ""+i.getElement()+"+";
			i = i.next;
		}
		return result;
	}
	//-----------------------------Iterator--------------------
	public class ElementIterator implements Iterator<T>{
		Node<T> cursor = head;
		@Override
		public boolean hasNext() {
			return (cursor != null);
		}

		@Override
		public T next() {
			T temp = cursor.getElement();
			cursor = cursor.getNext();
			return temp ;
		}
		
	
		
	}
	//----------------------end-----------------------------------
	@Override
	public Iterator<T> iterator() {
	
		return new ElementIterator();
	}
	
	public static void main(String [] args){
		Queue<Integer> numbers = new Queue<Integer>();
		numbers.enqueue(1);
		numbers.enqueue(2);
		numbers.enqueue(3);
		numbers.enqueue(4);
		
		for(Integer i : numbers){
			if(i.equals(3))
				System.out.println(i.toString());
		}
		
		
	}
	

}
