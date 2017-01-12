package Items;

import java.awt.Color;

import Game_Objects.Item;
import Map.TileMap;

public class Gold extends Item {

	public Gold(TileMap map) {
		super(map);
		color = Color.orange;
		type = "Gold";
	}

}
