package Items;

import java.awt.Color;

import Game_Objects.Item;
import Map.TileMap;

public class Gem extends Item {

	public Gem(TileMap map) {
		super(map);
		color = Color.CYAN;
		type ="gem";
		itemType = GEM;
	}
	
}
