package Items;

import java.awt.Color;

import Game_Objects.Item;
import Map.TileMap;

public class Door extends Item{

	public Door(TileMap map) {
		super(map);
		type = "door";
		itemType = DOOR;
		color = Color.pink;
	}

}
