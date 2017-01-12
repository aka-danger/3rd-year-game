package Items;

import java.awt.Color;

import Game_Objects.Item;
import Map.TileMap;

public class Hammer extends Item {
	
	public Hammer(TileMap map) {
		super(map);
		type = "hammer";
		itemType = BATTERY;
		color = Color.GRAY;
	}
}
