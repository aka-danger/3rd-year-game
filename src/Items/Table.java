package Items;

import Game_Objects.Item;
import Map.TileMap;

public class Table extends Item{

	public Table(TileMap map) {
		super(map);
		type = "table";
		itemType = TABLE;
		color = java.awt.Color.GREEN;
	}

	
}
