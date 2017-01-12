package Trees;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractBinaryTree<T> extends AbstractrTree<T> implements BinaryTree<T> {
	
	//check if there are siblings at position p 
	//siblings share the same parent
	public Position<T> sibling(Position<T> p){
		Position<T> parent = parent(p);
		if(parent == null ) return null ; //it is a root 
		if(p == left(parent))
			return right(parent);
		else 
			return left(parent);
	}
	
	public int numChildren (Position<T> p){
		int count =0;
		if(left(p) != null)
			count ++;
		if(right(p) != null)
			count ++;
		return count;
	}
	
	public Iterable<Position<T>> children (Position<T> p)throws IllegalArgumentException{
		List<Position<T>> snapshot = new ArrayList<>(2);
		if(left(p) != null)
			snapshot.add(left(p));
		if(right(p) != null)
			snapshot.add(right(p));
		return snapshot;
	}
}
