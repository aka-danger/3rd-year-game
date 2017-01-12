package Trees;

import Game_Objects.Item;
import Items.Battery;
import Items.Diamond;
import Items.Door;
import Items.Gem;
import Items.Gold;
import Items.Table;
import Map.TileMap;
import Queue.Queue;

//core game logic
public class GameLogic {

	private static TileMap m;

	/**
	 * @param que of times from the players bag
	 * complete game logic of the game
	 * */
	public static boolean isWinner(Queue<Item> items){
	    if (!(items.isEmpty())){
			m = items.first().getMap();
			LinkedBinaryTree<Item> tree = makeTree();
			
			Position<Item> parent = tree.root();
			
			while(!(parent.getElement() instanceof Door)){
				 
				Position<Item> leftChild = tree.left(parent);
				
				Position<Item> rightChild = tree.right(parent);
				
				//check parent
				Item root = items.dequeue();
				System.out.println(root.toString());
				
				if(parent.getElement().getClass().equals(root.getClass())){
					//check left and right
					//left
					Item child = null;
					if(!(items.isEmpty()))
						 child = items.first();
					else child = new Door(m);
					
					if(leftChild.getElement().getClass().equals(child.getClass()) || leftChild instanceof Door){
						parent = leftChild;
					}else if (rightChild.getElement().getClass().equals(child.getClass()) || rightChild instanceof Door){
						parent = rightChild;
					}else {System.out.println("child not found / door not found"); break ;};
					
				} else {
					System.out.println("root doesnt match");
					break ;
				}
			}
			if(parent.getElement() instanceof Door) return true;
			else return false;
	    }
		return false;
	
	}
	
	private static LinkedBinaryTree<Item> makeTree(){
		LinkedBinaryTree<Item> tree = new LinkedBinaryTree<Item>();
		Position<Item> one = tree.addRoot(new Battery(m));
		
		Position<Item> two = tree.addLeft(new Gem(m), one);
		Position<Item> three = tree.addRight(new Diamond(m), one);
		
		Position<Item> four = tree.addLeft(new Table(m), two);
		Position<Item> five = tree.addRight(new Gold(m), two);
		
		Position<Item> six = tree.addLeft(new Gem(m), three);
		Position<Item> seven = tree.addRight(new Item(m), three);
		
		Position<Item> eight = tree.addLeft(new Item(m), four);
		Position<Item> nine = tree.addRight(new Door(m), four);
		
		Position<Item> ten = tree.addLeft(new Item(m), five);
		Position<Item> elven = tree.addRight(new Item(m), five);
		
		Position <Item> twelve = tree.addLeft(new Door(m), six);
		Position <Item> thirteen = tree.addRight(new Item(m), six);
		
		
		Position<Item> fourteen = tree.addLeft(new Door(m), seven);
		Position<Item> fifteen = tree.addRight(new Door(m), seven);
		return tree;
	}
	
	public static void main(String [] args){
		//for(Integer i: tree){System.out.println(i);}
		TileMap map =new TileMap("data/level.txt", 32);
		Queue<Item> list = new Queue<Item>();
		
		list.enqueue(new Battery(map));
		list.enqueue(new Gem(map));
		list.enqueue(new Table(map));
		
		
		System.out.println(isWinner(list));
		
			
		}
		
	}


