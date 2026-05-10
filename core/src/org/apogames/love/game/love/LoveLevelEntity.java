package org.apogames.love.game.love;

import java.util.ArrayList;

import org.apogames.love.Constants;
import org.apogames.love.asset.AssetLoader;
import org.apogames.love.backend.DrawString;
import org.apogames.love.backend.GameScreen;
import org.apogames.love.game.LoveEnumTiles;

public class LoveLevelEntity {

	private final int RUN_TIME = 300; 
	private final int WATER_TIME = 600; 
	private final float CHANGE_VALUE = 1.3f; 
	
	public static final int BACKGROUND_EMPTY = 99;
	public static final int BACKGROUND_GRAS = 100;
	public static final int BACKGROUND_WATER = 101;
	public static final int BACKGROUND_LAVA = 102;
	public static final int BACKGROUND_ICE = 103;
	
	public static final int LAYER_EMPTY = 0;
	public static final int LAYER_WALL = 1;
	public static final int LAYER_ROCK = 2;
	public static final int LAYER_KEY = 3;
	public static final int LAYER_LOCK = 4;
	public static final int LAYER_GOAL = 5;
	public static final int LAYER_KNIGHT = 6;
	public static final int LAYER_TREE = 7;
	public static final int LAYER_TEXT = 8;
	public static final int LAYER_HEART = 9;
	public static final int LAYER_FIRE = 10;
	public static final int LAYER_SNOWBALL = 11;
	public static final int LAYER_WALL_BLUE = 12;
	public static final int LAYER_WALL_RED = 13;
	public static final int LAYER_WALL_GRAY = 14;
	public static final int LAYER_STAR = 15;
	
	private int background = BACKGROUND_EMPTY;
	private ArrayList<Integer> layer = new ArrayList<Integer>();
	
	private boolean isSolid;
	private ArrayList<Integer> layersSolid = new ArrayList<Integer>();
	private boolean isHot;
	private ArrayList<Integer> layersHot = new ArrayList<Integer>();
	private boolean isMelt;
	private ArrayList<Integer> layersMelt = new ArrayList<Integer>();
	private boolean isKey;
	private ArrayList<Integer> layersKey = new ArrayList<Integer>();
	private boolean isLock;
	private ArrayList<Integer> layersLock = new ArrayList<Integer>();
	private boolean isSink;
	private ArrayList<Integer> layersSink = new ArrayList<Integer>();
	private boolean isYou;
	private ArrayList<Integer> layersYou = new ArrayList<Integer>();
	private boolean isPushable;
	private ArrayList<Integer> layersPushable = new ArrayList<Integer>();
	private boolean isSlip;
	private ArrayList<Integer> layersSlip = new ArrayList<Integer>();
	private boolean isKill;
	private ArrayList<Integer> layersKill = new ArrayList<Integer>();
	private boolean isGhost;
	private ArrayList<Integer> layersGhost = new ArrayList<Integer>();
	private boolean isGoal;
	private ArrayList<Integer> layersGoal = new ArrayList<Integer>();
	private boolean isAllGoal;
	private ArrayList<Integer> layersAllGoal = new ArrayList<Integer>();
	private boolean isCold;
	private ArrayList<Integer> layersCold = new ArrayList<Integer>();
	private boolean isFixed;
	private ArrayList<Integer> layersFixed = new ArrayList<Integer>();
	private boolean isWinable;
	private ArrayList<Integer> layersWinable = new ArrayList<Integer>();
	
	private float change = 0;
	private int addX = 0;
	private int addY = 0;
	
	private int addGras = 0;
	private int addIce = 0;
	private int addWater = 0;
	private int waterTimer = 0;
	private int addRun = 0;
	private int runTimer = 0;
	
	private boolean bLeft = false;
	
	private LoveDetailEnum details = LoveDetailEnum.EMPTY;
	private LoveDetailEnum detailsIce = LoveDetailEnum.EMPTY;
	private LoveDetailEnum detailsWall = LoveDetailEnum.EMPTY;
	
	private boolean hasExtraForrest = false;
	
	private LoveLevelEntityString text = null;
	
	private int layerMoving = -1;
	
	public LoveLevelEntity(final int background, final int layer) {
		this.background = background;
		this.layer.add(layer);
		
		this.addGras = (int)(Math.random() * 4);
		this.addIce = (int)(Math.random() * 3);
		
		init();
	}
	
	private void init() {
		addX = 0;
		addY = 0;
		change = 0;
		layerMoving = -1;
	}
	
	public LoveLevelEntity getClone() {
		LoveLevelEntity clone = new LoveLevelEntity(this.background, LAYER_EMPTY);
		clone.getLayer().clear();
		for (Integer curLayer : layer) {
			clone.addLayer(curLayer);
		}
		clone.setAddGras(addGras);
		clone.setAddIce(addIce);
		clone.setAddWater(addWater);
		clone.setText(getTextClone());
		clone.setLeft(isLeft());

		return clone;
	}
	
	public int getAddGras() {
		return addGras;
	}

	public void setAddGras(int addGras) {
		this.addGras = addGras;
	}

	public int getAddIce() {
		return addIce;
	}

	public void setAddIce(int addIce) {
		this.addIce = addIce;
	}

	public int getAddWater() {
		return addWater;
	}

	public void setAddWater(int addWater) {
		this.addWater = addWater;
	}

	public LoveLevelEntityString getTextClone() {
		return getTextClone(this.text);
	}
	
	public LoveLevelEntityString getTextClone(LoveLevelEntityString text) {
		if (text == null) {
			return null;
		}
		return text.getClone();
	}
	
	public int getRunTimer() {
		return runTimer;
	}

	public void setRunTimer(int runTimer) {
		this.runTimer = runTimer;
	}

	public boolean isFixed() {
		return isFixed;
	}

	public void setFixed(int layer) {
		if (!this.layersFixed.contains(layer)) {
			this.layersFixed.add(layer);
		}
		this.isFixed = true;
	}

	public LoveLevelEntityString getText() {
		return text;
	}

	public void setText(LoveLevelEntityString text) {
		this.text = getTextClone(text);
	}

	public boolean hasExtraForrest() {
		return hasExtraForrest;
	}

	public void setHasExtraForrest(boolean hasExtraForrest) {
		this.hasExtraForrest = hasExtraForrest;
	}

	public LoveDetailEnum getDetails() {
		return details;
	}

	public void setDetails(LoveDetailEnum details) {
		this.details = details;
	}
	
	public void setDetails(LoveDetailEnum details, boolean bIce) {
		if (bIce) {
			this.detailsIce = details;
		} else {
			this.details = details;
		}
	}

	public LoveDetailEnum getDetailsIce() {
		return detailsIce;
	}

	public void setDetailsIce(LoveDetailEnum detailsIce) {
		this.detailsIce = detailsIce;
	}

	public LoveDetailEnum getDetailsWall() {
		return detailsWall;
	}

	public void setDetailsWall(LoveDetailEnum detailsWall) {
		this.detailsWall = detailsWall;
	}

	public boolean isLayerBackground(int layer) {
		return this.background == layer;
	}

	public boolean hasLayer(int layer) {
		if (isLayerBackground(layer)) {
			return true;
		}
		if (this.layer.contains(layer)) {
			return true;
		}
		return false;
	}

	public int getBackground() {
		return background;
	}

	public void setBackground(int background) {
		this.background = background;
	}

	public ArrayList<Integer> getLayer() {
		return layer;
	}
	
	public void clearLayerFrom(int layer) {
		while (this.layer.contains(layer)) {
			this.layer.remove(Integer.valueOf(layer));
		}
	}
	
	public void addLayer(int layer) {
		if (!this.layer.contains(layer)) {
			this.layer.add(layer);
		}
	}

	public boolean isCold() {
		return isCold;
	}

	public void setCold(int layer) {
		if (!this.layersCold.contains(layer)) {
			layersCold.add(layer);
		}
		this.isCold = true;
	}

	public boolean isSolid() {
		return isSolid;
	}
	public void setSolid(int layer) {
		if (!this.layersSolid.contains(layer)) {
			this.layersSolid.add(layer);
		}
		this.isSolid = true;
	}
	public void removeSolid() {
		this.layersSolid.clear();
		this.isSolid = false;
	}
	public boolean isText() {
		return this.text != null;
	}
	public boolean isHot() {
		return isHot;
	}
	public void setHot(int layer) {
		if (!this.layersHot.contains(layer)) {
			this.layersHot.add(layer);
		}
		this.isHot = true;
	}
	public void removeHot() {
		this.layersHot.clear();
		this.isHot = false;
	}
	public boolean isMelt() {
		return isMelt;
	}
	public void setMelt(int layer) {
		if (!this.layersMelt.contains(layer)) {
			this.layersMelt.add(layer);
		}
		this.isMelt = true;
	}
	public void removeMelt() {
		this.layersMelt.clear();
		this.isMelt = false;
	}
	public boolean isKey() {
		return isKey;
	}
	public void setKey(int layer) {
		if (!this.layersKey.contains(layer)) {
			this.layersKey.add(layer);
		}
		this.isKey = true;
	}
	public void removeKey() {
		this.layersKey.clear();
		this.isKey = false;
	}
	public boolean isLock() {
		return isLock;
	}
	public void setLock(int layer) {
		if (!this.layersLock.contains(layer)) {
			this.layersLock.add(layer);
		}
		this.isLock = true;
	}
	public void removeLock() {
		this.layersLock.clear();
		this.isLock = false;
	}
	public boolean isYou() {
		return isYou;
	}
	public void setYou(int layer)
	{
		if (!this.layersYou.contains(layer)) {
			this.layersYou.add(layer);
		}
		this.isYou = true;
	}
	public void removeYou() {
		this.layersYou.clear();
		this.isYou = false;
	}
	public boolean isPushable() {
		return isPushable;
	}
	public void setPushable(int layer) {
		if (!this.layersPushable.contains(layer)) {
			this.layersPushable.add(layer);
		}
		this.isPushable = true;
	}
	public void removePushable() {
		this.layersPushable.clear();
		this.isPushable = false;
	}
	
	public boolean isAllGoal() {
		return isAllGoal;
	}

	public void setAllGoal(int layer) {
		if (!this.layersAllGoal.contains(layer)) {
			this.layersAllGoal.add(layer);
		}
		this.isAllGoal = true;
	}
	public void removeAllGoal() {
		this.layersAllGoal.clear();
		this.isAllGoal = false;
	}

	public boolean isWinable() {
		return isWinable;
	}

	public void setWinable(int layer) {
		if (!this.layersWinable.contains(layer)) {
			this.layersWinable.add(layer);
		}
		this.isWinable = true;
	}
	public void removeWinable() {
		this.layersWinable.clear();
		this.isWinable = false;
	}

	public boolean isGoal() {
		return isGoal;
	}

	public void setGoal(int layer) {
		if (!this.layersGoal.contains(layer)) {
			this.layersGoal.add(layer);
		}
		this.isGoal = true;
	}
	public void removeGoal() {
		this.layersGoal.clear();
		this.isGoal = false;
	}

	public boolean isSink() {
		return isSink;
	}

	public void setSink(int layer) {
		if (!this.layersSink.contains(layer)) {
			this.layersSink.add(layer);
		}
		this.isSink = true;
	}
	public void removeSink() {
		this.layersSink.clear();
		this.isSink = false;
	}

	public boolean isSlip() {
		return isSlip;
	}

	public void setSlip(int layer) {
		if (!this.layersSlip.contains(layer)) {
			this.layersSlip.add(layer);
		}
		this.isSlip = true;
	}
	public void removeSlip() {
		this.layersSlip.clear();
		this.isSlip = false;
	}

	public boolean isKill() {
		return isKill;
	}

	public void setKill(int layer) {
		if (!this.layersKill.contains(layer)) {
			this.layersKill.add(layer);
		}
		this.isKill = true;
	}
	public void removeKill() {
		this.layersKill.clear();
		this.isKill = false;
	}

	public float getChange() {
		return change;
	}

	public boolean isLeft() {
		return bLeft;
	}

	public void setLeft(boolean bLeft) {
		this.bLeft = bLeft;
	}

	public boolean isGhost() {
		return isGhost;
	}

	public void setGhost(int layer) {
		this.layersGhost.add(layer);
		isGhost = true;
	}
	public void removeGhost() {
		this.layersGhost.clear();
		this.isGhost = false;
	}

	public boolean hasOtherGhosts(int checkLayer) {
		int size = 1;
		if (layersGhost.contains(checkLayer)) {
			size = 2;
		}
		if (layersGhost.size() >= size) {
			return true;
		}
		return false;
	}

	public ArrayList<Integer> getFixed() {
		return layersFixed;
	}

	public void setFixedComplete(ArrayList<Integer> fixed) {
		layersFixed.clear();
		for (int fix : fixed) {
			layersFixed.add(fix);
		}
		isFixed = true;
	}
	public void removeFixed() {
		this.layersFixed.clear();
		this.isFixed = false;
	}

	public ArrayList<Integer> getLayersSolid() {
		return layersSolid;
	}

	public ArrayList<Integer> getLayersHot() {
		return layersHot;
	}

	public ArrayList<Integer> getLayersMelt() {
		return layersMelt;
	}

	public ArrayList<Integer> getLayersKey() {
		return layersKey;
	}

	public ArrayList<Integer> getLayersLock() {
		return layersLock;
	}

	public ArrayList<Integer> getLayersSink() {
		return layersSink;
	}

	public ArrayList<Integer> getLayersYou() {
		return layersYou;
	}

	public ArrayList<Integer> getLayersPushable() {
		return layersPushable;
	}

	public ArrayList<Integer> getLayersSlip() {
		return layersSlip;
	}

	public ArrayList<Integer> getLayersKill() {
		return layersKill;
	}

	public ArrayList<Integer> getLayersGhost() {
		return layersGhost;
	}

	public ArrayList<Integer> getLayersGoal() {
		return layersGoal;
	}

	public ArrayList<Integer> getLayersAllGoal() {
		return layersAllGoal;
	}

	public ArrayList<Integer> getLayersCold() {
		return layersCold;
	}

	public ArrayList<Integer> getLayersFixed() {
		return layersFixed;
	}

	public ArrayList<Integer> getLayersWinable() {
		return layersWinable;
	}

	public void resetValues() {
		this.isSlip = false;
		this.isKey = false;
		this.isLock = false;
		this.isMelt = false;
		this.isPushable = false;
		this.isSink = false;
		this.isSlip = false;
		this.isSolid = false;
		this.isYou = false;
		this.isKill = false;
		this.isGoal = false;
		this.isAllGoal = false;
		this.isCold = false;
		this.isHot = false;
		this.isFixed = false;
		this.isWinable = false;
		this.isGhost = false;

		layersGhost.clear();
		layersSlip.clear();
		layersKey.clear();
		layersLock.clear();
		layersMelt.clear();
		layersPushable.clear();
		layersSolid.clear();
		layersKill.clear();
		layersGoal.clear();
		layersAllGoal.clear();
		layersCold.clear();
		layersHot.clear();
		layersFixed.clear();
		layersWinable.clear();
		layersYou.clear();

//		if (this.text != null) {
//			this.text.reset();
//		}
	}

	public boolean isNotOnlyEmpty() {
		if (this.text != null) {
			return true;
		}
		if (this.layer.size() == 0) {
			return false;
		} else if ((this.layer.size() == 1) && (layer.get(0) == LAYER_EMPTY)) {
			return false;
		}
		return true;
	}
	
	public void setChange(float change, int addX, int addY, int layerMoving) {
		this.change = change;
		this.addX = addX;
		this.addY = addY;
		this.layerMoving = layerMoving;
		if ((addX < 0) && (!bLeft)) {
			bLeft = true;
		} else if ((addX > 0) && (bLeft)) {
			bLeft = false;
		}
	}
	
	public void doThink(float delta) {
		waterTimer += delta;
		if (waterTimer > WATER_TIME) {
			waterTimer -= WATER_TIME;
			addWater += 1;
			if (addWater > 1) {
				addWater = 0;
			}
		}
		runTimer += delta;
		if (runTimer > RUN_TIME) {
			runTimer -= RUN_TIME;
			addRun += 1;
			if (addRun > 1) {
				addRun = 0;
			}
		}
		
		if (change != 0) {
			float oldChange = change;
			change += (addX + addY) * CHANGE_VALUE * LoveGame.SCALE;
			if (((change >= 0) && (oldChange < 0)) ||
				((change <= 0) && (oldChange > 0))) {
				change = 0;
				addY = 0;
			}
		}
	}
	
	int getLayerForText(LoveEnumTiles enumForText) {
		if (background == BACKGROUND_EMPTY) {
			return -1;
		}
		if (enumForText == null) {
			return -1;
		}

		if ((layer.contains(enumForText.getLayer())) &&
				( (enumForText.getTextureX() > 0) || (enumForText.getTextureY() > 0) )) {
			return enumForText.getLayer();
		}
		if ((layer.contains(LAYER_EMPTY)) && (layer.contains(enumForText.getLayer())) &&
				( (enumForText.getTextureX() == 0) && (enumForText.getTextureY() == 0) )) {
			return LoveEnumTiles.EMPTY.getLayer();
		}
		if ((background == enumForText.getLayer()) &&
				( (enumForText.getTextureX() == 0) && (enumForText.getTextureY() == 0) )) {
			return enumForText.getLayer();
		}

		return -1;
	}
    
    public void render(GameScreen screen, int changeX, int changeY) {
		renderBackground(screen, changeX, changeY);
        renderDetails(screen, changeX, changeY);
        renderLayer(screen, changeX, changeY, false);
        renderDetailsWall(screen, changeX, changeY, false);
    }
    
    void renderLayer(GameScreen screen, int changeX, int changeY) {
        renderLayer(screen, changeX, changeY, true);
        renderDetailsWall(screen, changeX, changeY, true);
    }
    
    public void renderLayerTextBackground(GameScreen screen, int changeX, int changeY) {
        renderText(screen, changeX, changeY, true);
    }
    
    public void renderLayerText(GameScreen screen, int changeX, int changeY) {
    	renderText(screen, changeX, changeY, false);
    }
    
    public void renderDebug(GameScreen screen, int changeX, int changeY) {
//		if (isPushable) {
//			screen.getRenderer().setColor(Constants.COLOR_RED[0], Constants.COLOR_RED[1], Constants.COLOR_RED[2], 1f);
//            screen.getRenderer().roundedRectLine(addX * change + changeX, addY * change + changeY, LoveGame.TILE_SIZE * LoveGame.SCALE, LoveGame.TILE_SIZE * LoveGame.SCALE, 5);
//		}
//		
//		for (int i = 0; i < layer.size(); i++) {
//			screen.getRenderer().setColor(Constants.COLOR_RED[0], Constants.COLOR_RED[1], Constants.COLOR_RED[2], 1f);
//            screen.getRenderer().line(addX * change + 3 * i * LoveGame.SCALE + changeX, addY * change + changeY + LoveGame.TILE_SIZE * LoveGame.SCALE*1/4, addX * change + 3 * i * LoveGame.SCALE + changeX, LoveGame.TILE_SIZE * LoveGame.SCALE*3/4 + addY * change + changeY);
//		}
    }

	public void renderBorderValid(GameScreen screen, int changeX, int changeY) {
		if ((this.text != null) && (this.text.isValid())) {
			for (LoveOrientation orientation : this.text.getOrientation()) {
				if ((orientation != null) && (!orientation.isHasBefore())) {
					float change = Math.abs(this.change);
					if ((change != 0) && (orientation.getOrientation() == EnumOrientation.HORIZONTAL) && (addY != 0)) {
						continue;
					}
					if ((change != 0) && (orientation.getOrientation() == EnumOrientation.VERTICAL) && (addX != 0)) {
						continue;
					}
					int tile = LoveGame.TILE_SIZE * LoveGame.SCALE;
					screen.getRenderer().setColor(Constants.COLOR_GRAY_BLUE[0], Constants.COLOR_GRAY_BLUE[1], Constants.COLOR_GRAY_BLUE[2], 1f);
					if (orientation.getOrientation() == EnumOrientation.HORIZONTAL) {
						screen.getRenderer().rect(addX * change + changeX, addY * change + changeY, tile * orientation.getMax(), tile);
					} else {
						screen.getRenderer().rect(addX * change + changeX, addY * change + changeY, tile, tile * orientation.getMax());
					}
				}
			}

		}
	}

	public void renderBorderValidButError(GameScreen screen, int changeX, int changeY) {
		if ((this.text != null) && (this.text.isValidButError()) && (change == 0)) {
			float change = Math.abs(this.change);
			int tile = LoveGame.TILE_SIZE * LoveGame.SCALE;
			screen.getRenderer().setColor(Constants.COLOR_RED[0], Constants.COLOR_RED[1], Constants.COLOR_RED[2], 0.6f);
			screen.getRenderer().line(addX * change + changeX, addY * change + changeY, addX * change + changeX + tile, addY * change + changeY + tile);
			screen.getRenderer().line(addX * change + changeX + tile, addY * change + changeY, addX * change + changeX, addY * change + changeY + tile);
		}
	}
    
    private void renderDetails(GameScreen screen, int changeX, int changeY) {
    	if ((this.background != BACKGROUND_ICE) && (this.background != BACKGROUND_GRAS) && (this.detailsIce != LoveDetailEnum.EMPTY)) {
			renderDetailsForLayer(screen, changeX, changeY, this.detailsIce, 5);
    	}
    	
    	if ((this.background != BACKGROUND_GRAS) && (this.details != LoveDetailEnum.EMPTY)) {
    		renderDetailsForLayer(screen, changeX, changeY, this.details, 0);
    	}
    }

	private void renderDetailsForLayer(GameScreen screen, int changeX, int changeY, LoveDetailEnum details, int addY) {
		if (details == LoveDetailEnum.ALL) {
			drawSprite(screen, 5, addY, changeX, changeY);
		} else if (details == LoveDetailEnum.DOWN_LEFT_UP) {
			drawSprite(screen, 4, addY, changeX, changeY);
		} else if (details == LoveDetailEnum.LEFT_UP_RIGHT) {
			drawSprite(screen, 1, addY, changeX, changeY);
		} else if (details == LoveDetailEnum.UP_RIGHT_DOWN) {
			drawSprite(screen, 2, addY, changeX, changeY);
		} else if (details == LoveDetailEnum.RIGHT_DOWN_LEFT) {
			drawSprite(screen, 3, addY, changeX, changeY);
		} else if (details == LoveDetailEnum.LEFT) {
			drawSprite(screen, 9, addY, changeX, changeY);
		} else if (details == LoveDetailEnum.LEFT_RIGHT) {
			drawSprite(screen, 9, addY, changeX, changeY);
			drawSprite(screen, 7, addY, changeX, changeY);
		} else if (details == LoveDetailEnum.LEFT_UP) {
			drawSprite(screen, 9, addY, changeX, changeY);
			drawSprite(screen, 6, addY, changeX, changeY);
		} else if (details == LoveDetailEnum.RIGHT) {
			drawSprite(screen, 7, addY, changeX, changeY);
		} else if (details == LoveDetailEnum.RIGHT_DOWN) {
			drawSprite(screen, 7, addY, changeX, changeY);
			drawSprite(screen, 8, addY, changeX, changeY);
		} else if (details == LoveDetailEnum.UP) {
			drawSprite(screen, 6, addY, changeX, changeY);
		} else if (details == LoveDetailEnum.UP_DOWN) {
			drawSprite(screen, 6, addY, changeX, changeY);
			drawSprite(screen, 8, addY, changeX, changeY);
		} else if (details == LoveDetailEnum.UP_RIGHT) {
			drawSprite(screen, 6, addY, changeX, changeY);
			drawSprite(screen, 7, addY, changeX, changeY);
		} else if (details == LoveDetailEnum.DOWN) {
			drawSprite(screen, 8, addY, changeX, changeY);
		} else if (details == LoveDetailEnum.DOWN_LEFT) {
			drawSprite(screen, 8, addY, changeX, changeY);
			drawSprite(screen, 9, addY, changeX, changeY);
		}
	}
    
    private void renderDetailsWall(GameScreen screen, int changeX, int changeY, boolean bMove) {
    	int layer = -1;
    	int addLayerY = 0;
    	if (this.layer.contains(LAYER_WALL)) {
    		layer = LAYER_WALL;
    	}
    	if (this.layer.contains(LAYER_WALL_BLUE)) {
    		layer = LAYER_WALL_BLUE;
    		addLayerY = 1;
    	}
    	if (this.layer.contains(LAYER_WALL_RED)) {
    		layer = LAYER_WALL_RED;
    		addLayerY = 2;
    	}
    	if (this.layer.contains(LAYER_WALL_GRAY)) {
    		layer = LAYER_WALL_GRAY;
    		addLayerY = 3;
    	}
    	if (layer >= 0) {
    		float myChange = Math.abs(this.change);
        	if (layerMoving != layer) {
        		if (bMove) {
        			return;
        		}
        		myChange = 0;
        	} else {
        		if (!bMove) {
        			return;
        		}
        	}
        	if ((isFixed) && ((isSink) || (this.background != BACKGROUND_GRAS))) {
        		screen.spriteBatch.setColor(1f, 1f, 1f, 0.6f);
        	}
        	float addX = this.addX * myChange;
        	float addY = this.addY * myChange;	
    		
    		if (detailsWall == LoveDetailEnum.ALL) {
    			drawSprite(screen, 11, 6 +addLayerY, changeX, changeY, addX, addY);
    		} else if (detailsWall == LoveDetailEnum.DOWN_LEFT_UP) {
    			drawSprite(screen, 13, 6 +addLayerY, changeX, changeY, addX, addY);
    		} else if (detailsWall == LoveDetailEnum.LEFT_UP_RIGHT) {
    			drawSprite(screen, 15, 6 +addLayerY, changeX, changeY, addX, addY);
    		} else if (detailsWall == LoveDetailEnum.UP_RIGHT_DOWN) {
    			drawSprite(screen, 14, 6 +addLayerY, changeX, changeY, addX, addY);
    		} else if (detailsWall == LoveDetailEnum.RIGHT_DOWN_LEFT) {
    			drawSprite(screen, 12, 6 +addLayerY, changeX, changeY, addX, addY);
    		} else if (detailsWall == LoveDetailEnum.LEFT) {
    			drawSprite(screen, 3, 6 +addLayerY, changeX, changeY, addX, addY);
    		} else if (detailsWall == LoveDetailEnum.LEFT_RIGHT) {
    			drawSprite(screen, 2, 6 +addLayerY, changeX, changeY, addX, addY);
    		} else if (detailsWall == LoveDetailEnum.LEFT_UP) {
    			drawSprite(screen, 10, 6 +addLayerY, changeX, changeY, addX, addY);
    		} else if (detailsWall == LoveDetailEnum.RIGHT) {
    			drawSprite(screen, 1, 6 +addLayerY, changeX, changeY, addX, addY);
    		} else if (detailsWall == LoveDetailEnum.RIGHT_DOWN) {
    			drawSprite(screen, 7, 6 +addLayerY, changeX, changeY, addX, addY);
    		} else if (detailsWall == LoveDetailEnum.UP) {
    			drawSprite(screen, 6, 6 +addLayerY, changeX, changeY, addX, addY);
    		} else if (detailsWall == LoveDetailEnum.UP_DOWN) {
    			drawSprite(screen, 5, 6 +addLayerY, changeX, changeY, addX, addY);
    		} else if (detailsWall == LoveDetailEnum.UP_RIGHT) {
    			drawSprite(screen, 9, 6 +addLayerY, changeX, changeY, addX, addY);
    		} else if (detailsWall == LoveDetailEnum.DOWN) {
    			drawSprite(screen, 4, 6 +addLayerY, changeX, changeY, addX, addY);
    		} else if (detailsWall == LoveDetailEnum.DOWN_LEFT) {
    			drawSprite(screen, 8, 6 +addLayerY, changeX, changeY, addX, addY);
    		} else if (detailsWall == LoveDetailEnum.EMPTY) {
    			drawSprite(screen, 0, 6 +addLayerY, changeX, changeY, addX, addY);
    		}
			if ((isFixed) && ((isSink) || (this.background != BACKGROUND_GRAS))) {
    			screen.spriteBatch.setColor(1f, 1f, 1f, 1f);
        	}
    	}
    }
    
    void renderBackgroundEmpty(GameScreen screen, int changeX, int changeY) {
    	if (this.background == BACKGROUND_EMPTY) {
            screen.getRenderer().setColor(Constants.COLOR_BLACK[0], Constants.COLOR_BLACK[1], Constants.COLOR_BLACK[2], 1f);
            screen.getRenderer().rect(changeX, changeY, LoveGame.TILE_SIZE * LoveGame.SCALE, LoveGame.TILE_SIZE * LoveGame.SCALE);
    	}
    }
    
    void renderBackground(GameScreen screen, int changeX, int changeY) {
    	if (this.background != BACKGROUND_EMPTY) {
    		if (this.background == BACKGROUND_GRAS) {
    			drawSprite(screen, 3 + addGras, 2, changeX, changeY);
    		} else if (this.background == BACKGROUND_WATER) {
    			drawSprite(screen, 7 + addWater, 2, changeX, changeY);
    		} else if (this.background == BACKGROUND_LAVA) {
    			drawSprite(screen, 7 + addWater, 3, changeX, changeY);
    		} else if (this.background == BACKGROUND_ICE) {
    			drawSprite(screen, 7 + addIce, 1, changeX, changeY);
    		}
    	}
    }
    
    private void renderLayer(GameScreen screen, int changeX, int changeY, boolean bMove) {
    	for (int i = 0; i < layer.size(); i++) {
    		int layer = this.layer.get(i);
        	float change = Math.abs(this.change);
        	if ((layer == LAYER_EMPTY) || (layer == LAYER_WALL_GRAY) || (layer == LAYER_WALL_BLUE) || (layer == LAYER_WALL_RED) || (layer == LAYER_WALL)) {
        		continue;
        	}
        	if (layerMoving != layer) {
        		if (bMove) {
        			continue;
        		}
        		change = 0;
        	} else if (!bMove) {
        		continue;
        	}
			if (layer == LAYER_GOAL) {
				if (bLeft) {
					screen.spriteBatch.draw(AssetLoader.mirrorTextureRegion[1][5 + addWater], (changeX + addX * change + LoveGame.TILE_SIZE * LoveGame.SCALE), (changeY + addY * change), -LoveGame.TILE_SIZE * LoveGame.SCALE, LoveGame.TILE_SIZE * LoveGame.SCALE);
				} else {
					drawSprite(screen, 5 + addWater, 1, changeX, changeY, addX * change, addY * change);
				}
			} else if (layer == LAYER_TREE) {
				if (hasExtraForrest) {
					drawSprite(screen, 3, 1, changeX, changeY, addX * change + LoveGame.TILE_SIZE * LoveGame.SCALE / 2f, addY * change - LoveGame.TILE_SIZE * LoveGame.SCALE / 2f);
				}
				drawSprite(screen, 3, 1, changeX, changeY, addX * change, addY * change);
			} else if (layer == LAYER_FIRE) {
				drawSprite(screen, addWater, 3, changeX, changeY, addX * change, addY * change);
			} else if (layer == LAYER_KNIGHT) {
				int addSprite = addWater * 2;
				if (change != 0) {
					addSprite = addRun;
				}
				if (!bLeft) {
					drawSprite(screen, addSprite, 1, changeX, changeY, addX * change, addY * change);
				} else {
					drawSprite(screen, addSprite, 2, changeX, changeY, addX * change, addY * change);
				}
			} else if (layer == LAYER_HEART) {
				drawSprite(screen, 7, 4, changeX, changeY, addX * change, addY * change);
			} else if (layer == LAYER_LOCK) {
				drawSprite(screen, 6, 4, changeX, changeY, addX * change, addY * change);
			} else if (layer == LAYER_ROCK) {
				drawSprite(screen, 5, 4, changeX, changeY, addX * change, addY * change);
			} else if (layer == LAYER_STAR) {
				drawSprite(screen, 5, 3, changeX, changeY, addX * change, addY * change);
			} else if (layer == LAYER_SNOWBALL) {
				drawSprite(screen, 2, 3, changeX, changeY, addX * change, addY * change);
			}
    	}
    }
    
    private void drawSprite(GameScreen screen, int subX, int subY, int x, int y) {
        drawSprite(screen, subX, subY, x, y, 0, 0);
    }

    private void drawSprite(GameScreen screen, int subX, int subY, int x, int y, float addX, float addY) {
        screen.spriteBatch.draw(AssetLoader.mirrorTextureRegion[subY][subX], (x + addX), (y + addY), LoveGame.TILE_SIZE * LoveGame.SCALE, LoveGame.TILE_SIZE * LoveGame.SCALE);
    }

    private void renderText(GameScreen screen, int changeX, int changeY, boolean onlyBackground) {
    	if (this.text != null) {
    		float change = Math.abs(this.change);
    		String myText = this.text.getText();
    		float[] color = this.text.getColor();
			int tile = LoveGame.TILE_SIZE * LoveGame.SCALE;
			if (onlyBackground) {
				if (this.text.isValid()) {
					screen.getRenderer().setColor(Constants.COLOR_WHITE[0], Constants.COLOR_WHITE[1], Constants.COLOR_WHITE[2], 0.15f);
					screen.getRenderer().rect(addX * change + changeX, addY * change + changeY, tile, tile);
				}
	    		if ((this.text.isObjekt()) && (!this.text.isPraedikat()) && (!this.text.isSubjekt())) {
	                screen.getRenderer().setColor(color[0], color[1], color[2], 0.5f);
	                screen.getRenderer().roundedRect(addX * change + changeX, addY * change + changeY, tile, tile, 5);
	    		}
    		} else {
    			if ((this.text.isObjekt()) && (!this.text.isPraedikat()) && (!this.text.isSubjekt())) {
    				color = Constants.COLOR_BLACK;
    			}
	    		if (myText.length() == 2) {
	    			screen.drawString(myText, addX * change + changeX + tile /2f, addY * change + changeY + 4f * LoveGame.SCALE, color, AssetLoader.font40, DrawString.MIDDLE, false, false);
	    		} else if (myText.length() == 3) {
	    			screen.drawString(myText, addX * change + changeX + tile /2f, addY * change + changeY + 7 * LoveGame.SCALE, color, AssetLoader.font20, DrawString.MIDDLE, false, false);
	    		} else if (myText.length() == 4) {
	    			String first = myText.substring(0, 2);
	    			screen.drawString(first, addX * change + changeX + tile /2f, addY * change + changeY + 2 * LoveGame.SCALE, color, AssetLoader.font20, DrawString.MIDDLE, false, false);
	    			
	    			String second = myText.substring(2, 4);
	    			screen.drawString(second, addX * change + changeX + tile /2f, addY * change + changeY + tile / 2f + 2 * LoveGame.SCALE, color, AssetLoader.font20, DrawString.MIDDLE, false, false);
	    		} else if (myText.length() == 5) {
	    			String first = myText.substring(0, 2);
	    			screen.drawString(first, addX * change + changeX + tile /2f, addY * change + changeY + 2 * LoveGame.SCALE, color, AssetLoader.font20, DrawString.MIDDLE, false, false);
	    			
	    			String second = myText.substring(2, 5);
	    			screen.drawString(second, addX * change + changeX + tile /2f, addY * change + changeY + tile / 2f + 2 * LoveGame.SCALE, color, AssetLoader.font20, DrawString.MIDDLE, false, false);
	    		} else if (myText.length() < 8) {
	    			String first = myText.substring(0, 3);
	    			screen.drawString(first, addX * change + changeX + tile /2f, addY * change + changeY + 2 * LoveGame.SCALE, color, AssetLoader.font20, DrawString.MIDDLE, false, false);
	    			
	    			String second = myText.substring(3, myText.length());
	    			screen.drawString(second, addX * change + changeX + tile /2f, addY * change + changeY + tile / 2f + 2 * LoveGame.SCALE, color, AssetLoader.font15, DrawString.MIDDLE, false, false);
	    		} else {
	    			int half = myText.length() / 2;
	    			String first = myText.substring(0, half);
	    			screen.drawString(first, addX * change + changeX + tile /2f, addY * change + changeY + 2 * LoveGame.SCALE, color, AssetLoader.font15, DrawString.MIDDLE, false, false);
	    			
	    			String second = myText.substring(half, myText.length());
	    			screen.drawString(second, addX * change + changeX + tile /2f, addY * change + changeY + tile / 2f + 2 * LoveGame.SCALE, color, AssetLoader.font15, DrawString.MIDDLE, false, false);
	    		}
    		}
    	}
    }

}
