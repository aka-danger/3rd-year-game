package Stack;

public class Stack<T> implements StackADT<T> {

	//-------------------nested class---------------------
	private static class  Node<T> implements Position<T>{

		private T element;
		private Node<T> next;
		
		public Node(T element, Position<T> next){
			this.element = element ;
			this.next = (Node<T>)next;
		}
		
		@Override
		public T getElement() {
			// TODO Auto-generated method stub
			return element;
		}
		
		public Position<T> getNext() {
			return next;
		}

		public void setNext(Position<T> next) {
			this.next = (Node<T>)next;
		}

		public void setElement(T element) {
			this.element = element;
		}
		
	}
	//-------------------End of nested-------------------
	
	private Node<T> head = null;
	private int size = 0;
	
	public Stack(){}
	//--------------------utility--------------------------
	
	public T pop(){
		if(isEmpty()) return null;
		Node<T> node = head.next;
		T temp  = head.getElement();
		head.setElement(null);
		head.setNext(null);
		head = node ;
		size --;
		return temp;
	}

	@Override
	public void push(T element) {
		if(isEmpty()) head = new Node<T>(element, null);
		else{
			Node<T> newNode = new Node<T>(element, head);
			head = newNode;
		}
		size ++;
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	public T peek() {
		return head.getElement();
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size ;
	}
	
	public String toString(){
		Node<T> i = head;
		String result = "";
		while(i != null){
			result += String.format("<%s>",i.element.toString());
			i =i.next;
		}
		return result;
	}

}
