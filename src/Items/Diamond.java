package Items;

import java.awt.Color;

import Game_Objects.Item;
import Map.TileMap;

public class Diamond extends Item{

	public Diamond(TileMap map) {
		super(map);
		type = "diamond";
		itemType = DIAMOND;
		color = Color.blue;
	}

}
