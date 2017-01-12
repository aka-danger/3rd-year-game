package Items;

import java.awt.Color;

import Game_Objects.Item;
import Map.TileMap;

public class Battery extends Item{

	public Battery(TileMap map) {
		super(map);
		type = "battery";
		itemType = BATTERY;
		color = Color.GRAY;
	}

}
