package Trees;

public abstract class AbstractrTree<T> implements Tree<T> {
	
	public boolean isLeaf(Position<T> p){
		return (numChildren(p) == 0);
	}
	
	public boolean isRoot(Position<T> p) throws IllegalArgumentException {
		return (p == root());
	}

	public boolean isEmpty() {
		return (size() == 0);
	}
	
	public int depth(Position<T> p){
		if(isRoot(p))
			return 0;
		else 
			return 1 + depth(parent(p));
	}
	
	public int height( Position<T> p){
		int h = 0 ;
		for(Position<T> items : children(p))
			h = Math.max(h, 1+height(items));
		return h;
	}
	
}
