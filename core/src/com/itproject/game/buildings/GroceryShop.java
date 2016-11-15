package com.itproject.game.buildings;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Polygon;
import com.itproject.game.Assets;
import com.itproject.game.Citizen;

public class GroceryShop extends Building {

	public static final int TILE_HEIGHT = 32;
	public static final int TILE_WIDTH = 64;	
	public static final int GROCERY_SHOP_OK = 0;
	public static final int GROCERY_SHOP_ON_FIRE = 1;
	public static final int GROCERY_SHOP_SELECTED = 2;
	public static final int GROCERY_SHOP_UNSELECTED = 3;
	public static final int GROCERY_SHOP_DESTROYED = 4;
	public static final int GROCERY_SHOP_HEIGHT = 2;
	public static final int GROCERY_SHOP_WIDTH = 1;
	
	boolean isPowered;
	int state;
	private int col, row;
	private Polygon shape;
	int zIndex;
	List<Citizen> worker;
	TiledMapTileLayer layer;
	  
	public GroceryShop(int row, int col) {
		super(10000, 500);
		
		state = 0;
		zIndex = 100 - col + row;
		this.col = col;
		this.row = row;
		worker = new ArrayList<Citizen>(10); // default 10 firefighters at start
		layer = (TiledMapTileLayer)Assets.tiledMap.getLayers().get("mainLayer");
	}
	
	public void update() {
		updateSelected();
	}

	@Override
	public void setElectricityBill(short electricityBill) {
		this.electricityBill = electricityBill;
	}

	@Override
	public void setWaterBill(short waterBill) {
		this.waterBill = waterBill;
	}

	public void updateSelected() {
		if(state == GROCERY_SHOP_SELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.groceryShopSelectedCell1));
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.groceryShopSelectedCell2));
		} else if(state == GROCERY_SHOP_UNSELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.groceryShopCell1));
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.groceryShopCell2));
			state = GROCERY_SHOP_OK;
		}
	}
	
	public void createShape() {
		int screenx = (col + row + 1) * TILE_WIDTH / 2 - 32;
	    int screeny = (col - row + 1) * TILE_HEIGHT / 2;
	    float[] vertices = new float[14];
	    vertices[0] = screenx;   vertices[1] = screeny;
	    vertices[2] = screenx + 32; vertices[3] = screeny - 16;
	    vertices[4] = screenx + 64 + 32; vertices[5] = screeny + 16;
	    vertices[6] = screenx + 64 + 32 - 8; vertices[7] = screeny + 16 + 5;
	    vertices[8] = screenx + 64 + 32 - 8; vertices[9] = screeny + 64;
	    vertices[10] = screenx + 64 - 32; vertices[11] = screeny + 64;
	    vertices[12] = screenx; vertices[13] = screeny + 64;
		shape = new Polygon(vertices);
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	public Polygon getShape() {
		return shape;
	}
	
	public int getCol() {
		return col;
	}
	
	public int getRow() {
		return row;
	}
	
	public void showInfo(float screenX, float screenY) {
		// to implement
		System.out.println("It is a CityHall!!");
	}

	@Override
	public void createCollisionShape() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Polygon getCollisionShape() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getZIndex() {
		return zIndex;
	}

	@Override
	public void setZIndex(int zIndex) {
		this.zIndex = zIndex;
	}

	@Override
	public int getPeopleSize() {
		// TODO Auto-generated method stub
		return worker.size();
	}

	@Override
	public boolean isPowered() {
		return isPowered;
	}

	@Override
	public void setPowered(boolean isPowered) {
		this.isPowered = isPowered;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return GROCERY_SHOP_HEIGHT;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return GROCERY_SHOP_WIDTH;
	}
	
	
}