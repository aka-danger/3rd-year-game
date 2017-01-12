package Items;

import java.awt.Color;

import Game_Objects.Item;
import Map.TileMap;

public class Fruit extends Item {

	public Fruit(TileMap map) {
		super(map);
		type = "Fruit";
		color = Color.YELLOW;
		
	}

}
