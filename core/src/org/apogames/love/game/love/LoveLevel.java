package org.apogames.love.game.love;

import java.util.ArrayList;

import org.apogames.love.Constants;
import org.apogames.love.asset.AssetLoader;
import org.apogames.love.backend.GameScreen;
import org.apogames.love.common.Localization;
import org.apogames.love.game.LoveEnumTiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class LoveLevel {
	public static final int LEVEL_WIDTH = 20;
	public static final int LEVEL_HEIGHT = 20;
	
	private LoveLevelEntity[][] hud;
	
	private final String levelString;
	private LoveLevelEntity[][] level;
	private LoveLevelEntity[][] lastLevel;
	private LoveLevelLogic logic;
	
	private int lastAddX = 0;
	private int lastAddY = 0;
	
	private LoveWinState winState = LoveWinState.RUNNING;
	private ArrayList<LoveLevelEntity[][]> lastSteps;

	private ArrayList<String> lastLogic;
	private ArrayList<String> lastNextLogic;

	private boolean isTextMoved;
	private boolean isIfIn;
    private boolean[][] isMovedAndLogicTrue;
    private boolean[][] isMovedAndLogic;
    private boolean[][] isMoved;
	private boolean[][] isFixedMoved;
	private boolean[][] shouldMove;
	private boolean[][] canMove;
	private boolean[][] partOfIfStatement;
	
	private boolean isUserlevel = false;
	private boolean isVoted = false;
	
	private boolean canSink = false;

	private boolean isNoYou = false;

	private boolean curIsGhost = false;
	private int curGhostLayer = -1;
	private ArrayList<Integer> curGhostLayerList = new ArrayList<Integer>();


	public LoveLevel(final String levelString) {
		this.levelString = levelString;
//		System.out.println(levelString);

		this.logic = new LoveLevelLogic();
		
		this.lastSteps = new ArrayList<LoveLevelEntity[][]>();
		
		this.init();
	}

	public boolean isNoYou() {
		return isNoYou;
	}

	public void setUserlevel(boolean isUserlevel) {
		this.isUserlevel = isUserlevel;
	}

	public String getLevelString() {
		return levelString;
	}

	public boolean isVoted() {
		return isVoted;
	}

	public void setVoted(boolean isVoted) {
		this.isVoted = isVoted;
	}

	public LoveWinState getWinState() {
		return winState;
	}

	public void init() {
		this.level = new LoveLevelEntity[LEVEL_HEIGHT][LEVEL_WIDTH];

		partOfIfStatement = new boolean[level.length][level[0].length];
		isMoved = new boolean[level.length][level[0].length];
		isMovedAndLogic = new boolean[level.length][level[0].length];
		isMovedAndLogicTrue = new boolean[level.length][level[0].length];
		isIfIn = false;
		this.lastLogic = new ArrayList<>();
		this.lastNextLogic = new ArrayList<>();
		isTextMoved = false;
		isFixedMoved = new boolean[level.length][level[0].length];
		String[] split = this.levelString.split("#");
		for (int y = 0; y < level.length; y++) {
			for (int x = 0; x < level[0].length; x++) {
                isMovedAndLogicTrue[y][x] = true;
				String curSubstring = split[0].substring(y * level[0].length + x, y * level[0].length + x + 1);
				
				boolean bFoundOne = false;
				for (int i = 0; i < LoveEnumTiles.values().length; i++) {
					LoveEnumTiles enumTile = LoveEnumTiles.values()[i];
					if ((enumTile.getTextureX() > 0) || (enumTile.getTextureY() > 0)) {
						if (curSubstring.equals(enumTile.getLevel())) {
							this.level[y][x] = new LoveLevelEntity(LoveLevelEntity.BACKGROUND_GRAS, enumTile.getLayer());
							bFoundOne = true;
						}
					} else if ((enumTile.getTextureX() == 0) && (enumTile.getTextureY() == 0)) {
						if (curSubstring.equals(enumTile.getLevel())) {
							this.level[y][x] = new LoveLevelEntity(enumTile.getLayer(), LoveLevelEntity.LAYER_EMPTY);
							bFoundOne = true;
						}
					}
				}
				
				if (!bFoundOne) {
					if (curSubstring.equals("T")) {
						this.level[y][x] = new LoveLevelEntity(LoveLevelEntity.BACKGROUND_GRAS, LoveLevelEntity.LAYER_EMPTY);
					} else {
						this.level[y][x] = new LoveLevelEntity(LoveLevelEntity.BACKGROUND_EMPTY, LoveLevelEntity.LAYER_EMPTY);
					}
				}
				this.level[y][x].resetValues();
			}
		}
        isMovedAndLogic = isMovedAndLogicTrue;
		
		if (split.length > 1) {
			String[] textSplit = split[1].split(",");
			for (int i = 0; i < textSplit.length; i += 3) {
				int x = Integer.valueOf(textSplit[i+1]);
				int y = Integer.valueOf(textSplit[i+2]);
				String curSubstring = textSplit[i].toUpperCase();
				boolean bFoundOne = false;
				for (int j = 0; j < LoveEnumTiles.values().length; j++) {
					LoveEnumTiles enumTile = LoveEnumTiles.values()[j];
					if ((enumTile.getTextValue() < LoveEnumTiles.PRAEDIKAT_IS.getTextValue()) && (curSubstring.equals(enumTile.getText()))) {
						if (curSubstring.equals("TEXT")) {
							this.level[y][x].setText(new LoveLevelEntityString(curSubstring, true, false, false, enumTile.getColor(), 39));
						} else {
							boolean isSubjekt = false;
							if (enumTile.getTextureX() >= 0) {
								isSubjekt = true;
							}
							this.level[y][x].setText(new LoveLevelEntityString(curSubstring, isSubjekt, false, true, enumTile.getColor(), enumTile.getTextValue()));
						}
						bFoundOne = true;
					}
				}
				if (!bFoundOne) {
					if (textSplit[i].toUpperCase().equals("IS")) {
						this.level[y][x].setText(new LoveLevelEntityString(textSplit[i], false, true, false, LoveEnumTiles.PRAEDIKAT_IS.getColor(), LoveEnumTiles.PRAEDIKAT_IS.getTextValue()));
					} else if (textSplit[i].toUpperCase().equals("ISNOT")) {
						this.level[y][x].setText(new LoveLevelEntityString(textSplit[i], false, true, false, LoveEnumTiles.PRAEDIKAT_ISNOT.getColor(), LoveEnumTiles.PRAEDIKAT_ISNOT.getTextValue()));
					} else if (textSplit[i].toUpperCase().equals("ISON")) {
						this.level[y][x].setText(new LoveLevelEntityString(textSplit[i], false, true, false, LoveEnumTiles.PRAEDIKAT_ISON.getColor(), LoveEnumTiles.PRAEDIKAT_ISON.getTextValue()));
					} else if (textSplit[i].toUpperCase().equals("IF")) {
						isIfIn = true;
						this.level[y][x].setText(new LoveLevelEntityString(textSplit[i], false, false, false, LoveEnumTiles.CONDITION_IF.getColor(), LoveEnumTiles.CONDITION_IF.getTextValue()));
					} else if (textSplit[i].toUpperCase().equals("THEN")) {
						this.level[y][x].setText(new LoveLevelEntityString(textSplit[i], false, false, false, LoveEnumTiles.CONDITION_THEN.getColor(), LoveEnumTiles.CONDITION_THEN.getTextValue()));
					} else if (textSplit[i].toUpperCase().equals("AND")) {
						this.level[y][x].setText(new LoveLevelEntityString(textSplit[i], false, false, false, LoveEnumTiles.PRAEDIKAT_AND.getColor(), LoveEnumTiles.PRAEDIKAT_AND.getTextValue()));
						this.level[y][x].getText().setAnd(true);
					}
				}
			}
		}
		
		if (split.length > 2) {
			for (int y = 0; y < level.length; y++) {
				for (int x = 0; x < level[0].length; x++) {
					if ((!this.level[y][x].getLayer().contains(LoveLevelEntity.LAYER_EMPTY)) || (this.level[y][x].getText() != null)) {
						String curSubstring = split[2].substring(y * level[0].length + x, y * level[0].length + x + 1);
						for (int i = 0; i < LoveEnumTiles.values().length; i++) {
							LoveEnumTiles enumTile = LoveEnumTiles.values()[i];
							if ((enumTile.getTextureX() == 0) && (enumTile.getTextureY() == 0) && (curSubstring.equals(enumTile.getLevel()))) {
								this.level[y][x].setBackground(enumTile.getLayer());
							}
						}
					}
					this.level[y][x].resetValues();
				}
			}
		}
		
		createHud();
		
		setDetailsForUnderground();
		
		checkLogic(true);
		
		this.lastSteps.clear();
		
		winState = LoveWinState.RUNNING;
	}

	public String getStringForLevel() {
		String result = "";
		boolean bWithExtra = false;
		
		for (int y = 0; y < level.length; y++) {
			for (int x = 0; x < level[0].length; x++) {
				if ((level[y][x].getLayer().contains(LoveLevelEntity.LAYER_EMPTY)) || (level[y][x].getText() != null)) {
					result = addAndGetResultBackground(result, y, x);
				} else {
					boolean isFound = false;
					for (int i = 0; i < LoveEnumTiles.values().length; i++) {
						LoveEnumTiles enumTile = LoveEnumTiles.values()[i];
						if (!enumTile.isBackgroundOrEmpty()) {
							if (level[y][x].getLayer().contains(enumTile.getLayer())) {
								isFound = true;
								result += enumTile.getLevel();
								if (level[y][x].getBackground() != LoveLevelEntity.BACKGROUND_GRAS) {
									bWithExtra = true;
								}
							}
						}
					}
					if (!isFound) {
						result += "T";
						if (level[y][x].getBackground() != LoveLevelEntity.BACKGROUND_GRAS) {
							bWithExtra = true;
						}
					}
				}
			}
		}
		result += "#";
		for (int y = 0; y < level.length; y++) {
			for (int x = 0; x < level[0].length; x++) {
				if (level[y][x].getText() != null) {
					result += level[y][x].getText().getText()+","+x+","+y+",";
				}
			}
		}
		if (bWithExtra) {
			result += "#";
			for (int y = 0; y < level.length; y++) {
				for (int x = 0; x < level[0].length; x++) {
					result = addAndGetResultBackground(result, y, x);
				}
			}
		}
		result += "#";
		System.out.println(result);
		
		return result;
	}

	private String addAndGetResultBackground(String result, int y, int x) {
		for (int i = 0; i < LoveEnumTiles.values().length; i++) {
			LoveEnumTiles enumTile = LoveEnumTiles.values()[i];
			if ((enumTile.isBackgroundOrEmpty()) && (level[y][x].getBackground() == enumTile.getLayer())) {
				result += enumTile.getLevel();
			}
		}
		return result;
	}

	public LoveLevelEntity[][] getLevel() {
		return level;
	}

	private void createHud() {
		this.hud = new LoveLevelEntity[5][LEVEL_WIDTH];
		for (int y = 0; y < hud.length; y++) {
			for (int x = 0; x < hud[0].length; x++) {
				this.hud[y][x] = new LoveLevelEntity(LoveLevelEntity.BACKGROUND_GRAS, LoveLevelEntity.LAYER_EMPTY);
			}
		}
	}
	
	public void setDetailsForUnderground() {
		for (int y = 0; y < level.length; y++) {
			for (int x = 0; x < level[0].length; x++) {
				if ((isTreeLayer(x + 1, y - 1)) && (isTreeLayer(x, y - 1)) &&
					(isTreeLayer(x + 1, y))) {
					level[y][x].setHasExtraForrest(true);
				} else {
					level[y][x].setHasExtraForrest(false);
				}
				
				if (level[y][x].getBackground() != LoveLevelEntity.BACKGROUND_GRAS) {
					setDetailsForGras(y, x);
				}
				
				if ((level[y][x].getBackground() != LoveLevelEntity.BACKGROUND_ICE) &&
					(level[y][x].getBackground() != LoveLevelEntity.BACKGROUND_GRAS)) {
					setDetailsForIce(y, x);
				}
				
				setDetailsForWall(y, x, LoveLevelEntity.LAYER_WALL);
				setDetailsForWall(y, x, LoveLevelEntity.LAYER_WALL_BLUE);
				setDetailsForWall(y, x, LoveLevelEntity.LAYER_WALL_GRAY);
				setDetailsForWall(y, x, LoveLevelEntity.LAYER_WALL_RED);
			}
		}
	}

	private void setDetailsForGras(int y, int x) {
		boolean isGrasLeft = isGrasUnderground(x - 1, y);
		boolean isGrasRight = isGrasUnderground(x + 1, y);
		boolean isGrasUp = isGrasUnderground(x, y - 1);
		boolean isGrasDown = isGrasUnderground(x, y + 1);
		
		setDetailsForUnderground(y, x, isGrasLeft, isGrasRight, isGrasUp, isGrasDown, false);
	}
	
	private void setDetailsForIce(int y, int x) {
		boolean isGrasLeft = isIceUnderground(x - 1, y);
		boolean isGrasRight = isIceUnderground(x + 1, y);
		boolean isGrasUp = isIceUnderground(x, y - 1);
		boolean isGrasDown = isIceUnderground(x, y + 1);
		
		setDetailsForUnderground(y, x, isGrasLeft, isGrasRight, isGrasUp, isGrasDown, true);
	}
	
	private void setDetailsForUnderground(int y, int x, boolean isGrasLeft, boolean isGrasRight, boolean isGrasUp, boolean isGrasDown, boolean bIce) {
		if ((isGrasLeft) && (isGrasDown) && (isGrasRight) && (isGrasUp)) {
			level[y][x].setDetails(LoveDetailEnum.ALL, bIce);
		} else if ((isGrasLeft) && (!isGrasDown) && (!isGrasRight) && (!isGrasUp)) {
			level[y][x].setDetails(LoveDetailEnum.LEFT, bIce);
		} else if ((isGrasLeft) && (!isGrasDown) && (!isGrasRight) && (isGrasUp)) {
			level[y][x].setDetails(LoveDetailEnum.LEFT_UP, bIce);
		} else if ((isGrasLeft) && (!isGrasDown) && (isGrasRight) && (!isGrasUp)) {
			level[y][x].setDetails(LoveDetailEnum.LEFT_RIGHT, bIce);
		} else if ((isGrasLeft) && (isGrasDown) && (!isGrasRight) && (!isGrasUp)) {
			level[y][x].setDetails(LoveDetailEnum.DOWN_LEFT, bIce);
		} else if ((isGrasLeft) && (isGrasDown) && (isGrasRight) && (!isGrasUp)) {
			level[y][x].setDetails(LoveDetailEnum.RIGHT_DOWN_LEFT, bIce);
		} else if ((isGrasLeft) && (isGrasDown) && (!isGrasRight) && (isGrasUp)) {
			level[y][x].setDetails(LoveDetailEnum.DOWN_LEFT_UP, bIce);
		} else if ((isGrasLeft) && (!isGrasDown) && (isGrasRight) && (isGrasUp)) {
			level[y][x].setDetails(LoveDetailEnum.LEFT_UP_RIGHT, bIce);
		} else if ((!isGrasLeft) && (!isGrasDown) && (isGrasRight) && (!isGrasUp)) {
			level[y][x].setDetails(LoveDetailEnum.RIGHT, bIce);
		} else if ((!isGrasLeft) && (isGrasDown) && (isGrasRight) && (!isGrasUp)) {
			level[y][x].setDetails(LoveDetailEnum.RIGHT_DOWN, bIce);
		} else if ((!isGrasLeft) && (!isGrasDown) && (isGrasRight) && (isGrasUp)) {
			level[y][x].setDetails(LoveDetailEnum.UP_RIGHT, bIce);
		} else if ((!isGrasLeft) && (isGrasDown) && (isGrasRight) && (isGrasUp)) {
			level[y][x].setDetails(LoveDetailEnum.UP_RIGHT_DOWN, bIce);
		} else if ((!isGrasLeft) && (!isGrasDown) && (!isGrasRight) && (isGrasUp)) {
			level[y][x].setDetails(LoveDetailEnum.UP, bIce);
		} else if ((!isGrasLeft) && (isGrasDown) && (!isGrasRight) && (isGrasUp)) {
			level[y][x].setDetails(LoveDetailEnum.UP_DOWN, bIce);
		} else if ((!isGrasLeft) && (isGrasDown) && (!isGrasRight) && (!isGrasUp)) {
			level[y][x].setDetails(LoveDetailEnum.DOWN, bIce);
		} else {
			level[y][x].setDetails(LoveDetailEnum.EMPTY, bIce);
		}
	}

	private void setDetailsForWall(int y, int x, int layer) {
		if (isWallLayer(x, y, layer)) {
			boolean isWallLeft = isWallLayer(x - 1, y, layer);
			boolean isWallRight = isWallLayer(x + 1, y, layer);
			boolean isWallUp = isWallLayer(x, y - 1, layer);
			boolean isWallDown = isWallLayer(x, y + 1, layer);
			if ((isWallLeft) && (isWallDown) && (isWallRight) && (isWallUp)) {
				level[y][x].setDetailsWall(LoveDetailEnum.ALL);
			} else if ((isWallLeft) && (!isWallDown) && (!isWallRight) && (!isWallUp)) {
				level[y][x].setDetailsWall(LoveDetailEnum.LEFT);
			} else if ((isWallLeft) && (!isWallDown) && (!isWallRight) && (isWallUp)) {
				level[y][x].setDetailsWall(LoveDetailEnum.LEFT_UP);
			} else if ((isWallLeft) && (!isWallDown) && (isWallRight) && (!isWallUp)) {
				level[y][x].setDetailsWall(LoveDetailEnum.LEFT_RIGHT);
			} else if ((isWallLeft) && (isWallDown) && (!isWallRight) && (!isWallUp)) {
				level[y][x].setDetailsWall(LoveDetailEnum.DOWN_LEFT);
			} else if ((isWallLeft) && (isWallDown) && (isWallRight) && (!isWallUp)) {
				level[y][x].setDetailsWall(LoveDetailEnum.RIGHT_DOWN_LEFT);
			} else if ((isWallLeft) && (isWallDown) && (!isWallRight) && (isWallUp)) {
				level[y][x].setDetailsWall(LoveDetailEnum.DOWN_LEFT_UP);
			} else if ((isWallLeft) && (!isWallDown) && (isWallRight) && (isWallUp)) {
				level[y][x].setDetailsWall(LoveDetailEnum.LEFT_UP_RIGHT);
			} else if ((!isWallLeft) && (!isWallDown) && (isWallRight) && (!isWallUp)) {
				level[y][x].setDetailsWall(LoveDetailEnum.RIGHT);
			} else if ((!isWallLeft) && (isWallDown) && (isWallRight) && (!isWallUp)) {
				level[y][x].setDetailsWall(LoveDetailEnum.RIGHT_DOWN);
			} else if ((!isWallLeft) && (!isWallDown) && (isWallRight) && (isWallUp)) {
				level[y][x].setDetailsWall(LoveDetailEnum.UP_RIGHT);
			} else if ((!isWallLeft) && (isWallDown) && (isWallRight) && (isWallUp)) {
				level[y][x].setDetailsWall(LoveDetailEnum.UP_RIGHT_DOWN);
			} else if ((!isWallLeft) && (!isWallDown) && (!isWallRight) && (isWallUp)) {
				level[y][x].setDetailsWall(LoveDetailEnum.UP);
			} else if ((!isWallLeft) && (isWallDown) && (!isWallRight) && (isWallUp)) {
				level[y][x].setDetailsWall(LoveDetailEnum.UP_DOWN);
			} else if ((!isWallLeft) && (isWallDown) && (!isWallRight) && (!isWallUp)) {
				level[y][x].setDetailsWall(LoveDetailEnum.DOWN);
			} else {
				level[y][x].setDetailsWall(LoveDetailEnum.EMPTY);
			}
		}
	}
	
	private boolean isGrasUnderground(int x, int y) {
		if ((x >= 0) && (x < level[0].length) && (y >= 0) && (y < level.length)) {
			if (level[y][x].getBackground() == LoveLevelEntity.BACKGROUND_GRAS) {
				return true;
			}
		} else {
			return true;
		}
		return false;
	}
	
	private boolean isIceUnderground(int x, int y) {
		if ((x >= 0) && (x < level[0].length) && (y >= 0) && (y < level.length)) {
			if (level[y][x].getBackground() == LoveLevelEntity.BACKGROUND_ICE) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isWallLayer(int x, int y, int layer) {
		if ((x >= 0) && (x < level[0].length) && (y >= 0) && (y < level.length)) {
			if (level[y][x].getLayer().contains(layer)) {
				return true;
			}
		}
		return false;
	}
	
	
	private boolean isTreeLayer(int x, int y) {
		if ((x >= 0) && (x < level[0].length) && (y >= 0) && (y < level.length)) {
			if (level[y][x].getLayer().contains(LoveLevelEntity.LAYER_TREE)) {
				return true;
			}
		}
		return false;
	}
	
	public void doThink(float delta, int addX, int addY) {
		if (winState == LoveWinState.RUNNING) {
			boolean bReady = false;
			boolean isMoving = false;
			for (int y = 0; y < level.length; y++) {
				for (int x = 0; x < level[0].length; x++) {
					boolean bMove = false;
					if (level[y][x].getChange() != 0) {
						bMove = true;
						isMoving = true;
					}
					level[y][x].doThink(delta);
					if ((bMove) && (level[y][x].getChange() == 0)) {
						bReady = true;
						isMoving = false;
					}
				}
			}
	
			if (bReady) {
				checkAfterMovement(true);
				if (!checkSlipEntities()) {
					checkAfterMovement(false);
					setDetailsForUnderground();
				} else {
					isMoving = true;
				}
			}
			
		    if (((addX != 0) || (addY != 0)) && (!isMoving) && (winState == LoveWinState.RUNNING)) {
	            moveEntities(addX, addY, false);
			}
		} else {
			for (int y = 0; y < level.length; y++) {
				for (int x = 0; x < level[0].length; x++) {
					level[y][x].doThink(delta);
				}
			}
		}
	}

	private void checkAfterMovement(boolean first) {
		checkLogic(first);
		if (winState == LoveWinState.RUNNING) {
			checkWon();
			checkKill();
		}
	}

	private void moveEntities(int addX, int addY, boolean bSlip) {
		boolean bMove = false;
		boolean[][] oldMoved = getClone(isMoved);
		isMoved = new boolean[level.length][level[0].length];
		isFixedMoved = new boolean[level.length][level[0].length];
		
		if (!bSlip) {
			this.lastLevel = getClone(this.level);
		}
		if (addX != 0) {
			int startX = 0;
			if (addX < 0) {
				startX = level[0].length - 1;
			}
			for (int x = startX; x >= 0 && x < level[0].length; x += addX) {
			    for (int y = 0; y < level.length; y++) {
			    	if (interMove(addX, addY, bSlip, oldMoved, x, y)) {
			    		bMove = true;
			    	}
			    }
			}
		} else {
			int startY = 0;
			if (addY < 0) {
				startY = level.length - 1;
			}
			for (int x = 0; x < level[0].length; x++) {
				for (int y = startY; y >= 0 && y < level.length; y += addY) {
			    	if (interMove(addX, addY, bSlip, oldMoved, x, y)) {
			    		bMove = true;
			    	}
			    }
			}
		}
		if (bMove) {
			setDetailsForUnderground();
			if (!bSlip) {
				saveLastStep();
			}
		}
	}

	private boolean interMove(int addX, int addY, boolean bSlip, boolean[][] oldMoved, int x, int y) {
		if ((x + addX < 0) || (x + addX >= level[0].length)  || 
			(y + addY < 0) || (y + addY >= level.length)) {
			return false;
		}
		if (winState != LoveWinState.RUNNING) {
			return false;
		}
		boolean bMove = false;
		if (!bSlip) {
		    if ((level[y][x].isYou()) && (!isFixedMoved[y][x])) {
		    	setForMoving(x, y);
		        if (checkAndMoveObjects(x, y, addX, addY)) {
		            bMove = true;
					lastAddX = addX;
					lastAddY = addY;	                                
		        }
		    }
		} else {
			if (oldMoved[y][x]) {
				setForMoving(x, y);
				if ((level[y+addY][x+addX].isSlip()) && (!isMoved[y+addY][x+addX]) && (checkAndMoveObjects(x + addX, y + addY, addX, addY))) {
		            bMove = true;
					lastAddX = addX;
					lastAddY = addY;	                                
		        }
			}
		}
		return bMove;
	}

	private void setForMoving(int x, int y) {
		curIsGhost = level[y][x].isGhost();
		curGhostLayer = -1;
		if (curIsGhost) {
			for (int ghostLayer : level[y][x].getLayersGhost()) {
				for (int curYou : level[y][x].getLayersYou()) {
					if (curYou == ghostLayer) {
						if (level[y][x].getBackground() == ghostLayer) {
							curGhostLayer = ghostLayer;
						} else if (level[y][x].getLayer().contains(ghostLayer)) {
							curGhostLayer = ghostLayer;
						}
					}
				}
			}
		}
		if (curGhostLayer < 0) {
			curIsGhost = false;
		} else {
			curGhostLayerList.clear();
			curGhostLayerList.add(curGhostLayer);
		}
		shouldMove = new boolean[level.length][level[0].length];
		canMove = new boolean[level.length][level[0].length];
	}
	
	private LoveLevelEntity[][] getClone(LoveLevelEntity[][] original) {
		LoveLevelEntity[][] clone = new LoveLevelEntity[original.length][original[0].length];
		for (int x = 0; x < clone[0].length; x++) {
		    for (int y = 0; y < clone.length; y++) {
		    	clone[y][x] = original[y][x].getClone();
		    }
		}
		return clone;
	}

	private boolean[][] getClone(boolean[][] original) {
		boolean[][] clone = new boolean[original.length][original[0].length];
		for (int x = 0; x < clone[0].length; x++) {
		    for (int y = 0; y < clone.length; y++) {
		    	clone[y][x] = original[y][x];
		    }
		}
		return clone;
	}

	private boolean checkSlipEntities() {
		boolean bMoveSlip = false;

		for (int y = 0; y < level.length; y++) {
			for (int x = 0; x < level[0].length; x++) {
				if ((x + lastAddX < 0) || (x + lastAddX >= level[0].length) ||
			        (y + lastAddY < 0) || (y + lastAddY >= level.length)) {
			            
			    } else if ((level[y+lastAddY][x+lastAddX].isSlip()) && (isMoved[y][x]) && (level[y+lastAddY][x+lastAddX].isNotOnlyEmpty())) {
					if (canMoveObject(x + lastAddX, y + lastAddY, lastAddX, lastAddY)) {
						bMoveSlip = true;
					}
				}
			}
		}
		if (bMoveSlip) {
			moveEntities(lastAddX, lastAddY, true);
		}
		return bMoveSlip;
	}

	private boolean checkIfSameGhost(ArrayList<Integer> first, ArrayList<Integer> second, int x, int y) {
		return checkIfSameGhost(first, second, x, y, false);
	}

	private boolean checkIfSameGhost(ArrayList<Integer> first, ArrayList<Integer> second, int x, int y, boolean bText) {
		boolean isFirst = false;
		int firstCount = 0;
		boolean isSecond = false;
		int secondCount = 0;
		for (int layer : level[y][x].getLayersGhost()) {
			if (first.contains(layer)) {
				isFirst = true;
				firstCount += 1;
			}
			if (second.contains(layer)) {
				isSecond = true;
				secondCount += 1;
			}
		}
		if ((level[y][x].getLayersGhost().size() == 0) && (curIsGhost) && (!bText)) {
			isFirst = true;
		}
		if (isFirst != isSecond) {
			if ((((secondCount > 0) && (secondCount == second.size())) || (secondCount == 0)) &&
				(((firstCount > 0) && (firstCount == first.size())) || (firstCount == 0))) {
				return false;
			}

		}
		return true;
	}

	private void checkWon() {
		int allWin = 0;
		int allWinGoal = 0;
		for (int y = 0; y < level.length; y++) {
			for (int x = 0; x < level[0].length; x++) {
				if (((level[y][x].isYou()) || (level[y][x].isWinable())) && (level[y][x].isGoal())) {
					if (((checkIfSameGhost(level[y][x].getLayersYou(), level[y][x].getLayersGoal(), x, y)) && (level[y][x].isYou())) ||
						((checkIfSameGhost(level[y][x].getLayersWinable(), level[y][x].getLayersGoal(), x, y)) && (level[y][x].isWinable()))) {
						winState = LoveWinState.WON;
					}
				} else if (level[y][x].isAllGoal()) {
					allWin += 1;
					if ((level[y][x].isYou()) || (level[y][x].isWinable())) {
						if (((checkIfSameGhost(level[y][x].getLayersYou(), level[y][x].getLayersAllGoal(), x, y)) && (level[y][x].isYou())) ||
							((checkIfSameGhost(level[y][x].getLayersWinable(), level[y][x].getLayersAllGoal(), x, y)) && (level[y][x].isWinable()))) {
							allWinGoal += 1;
						}
					}
				}
			}
		}
		if ((allWin > 0) && (allWin == allWinGoal)) {
			winState = LoveWinState.WON;
		}
	}
	
	private void checkKill() {
		isNoYou = false;
		int countYous = getCountObjektAvailable();
		for (int y = 0; y < level.length; y++) {
			for (int x = 0; x < level[0].length; x++) {
				boolean bChanged = false;
				if ((level[y][x].isHot()) && (level[y][x].getBackground() == LoveLevelEntity.BACKGROUND_ICE)) {
					level[y][x].setBackground(LoveLevelEntity.BACKGROUND_WATER);
					bChanged = true;
				} else if ((level[y][x].isSink()) && (level[y][x].isPushable())) {
					if ((!level[y][x].getLayer().contains(LoveLevelEntity.LAYER_EMPTY)) && (level[y][x].getLayer().size() > 0)) {
						if ((isFixedAndCanSink(x, y)) && (checkIfSameGhost(level[y][x].getLayersSink(), level[y][x].getLayersPushable(), x, y))) {
							sinkObject(level[y][x], level[y][x].getLayersPushable());
						}
					}
				}
				if ((level[y][x].isHot()) && (level[y][x].isMelt()) && (checkIfSameGhost(level[y][x].getLayersHot(), level[y][x].getLayersMelt(), x, y))) {
					for (Integer integer : level[y][x].getLayersMelt()) {
						if (level[y][x].isYou()) {
							if (level[y][x].getLayersYou().contains(integer)) {
								countYous -= 1;
							}
						}
						if (level[y][x].isLayerBackground(integer)) {
							if (level[y][x].getBackground() != LoveLevelEntity.BACKGROUND_GRAS) {
								level[y][x].setBackground(LoveLevelEntity.BACKGROUND_GRAS);
							}
						} else {
							level[y][x].clearLayerFrom(integer);
							if (level[y][x].getLayer().size() <= 0) {
								level[y][x].getLayer().add(LoveLevelEntity.LAYER_EMPTY);
							}
						}
					}
				}

				if ((level[y][x].isKill()) && (!bChanged) && ((level[y][x].getBackground() == LoveLevelEntity.BACKGROUND_WATER) || (level[y][x].getBackground() == LoveLevelEntity.BACKGROUND_LAVA))) {
					if ((!level[y][x].getLayer().contains(LoveLevelEntity.LAYER_EMPTY)) && (level[y][x].getLayer().size() > 0)) {
						level[y][x].getLayer().clear();
						level[y][x].getLayer().add(LoveLevelEntity.LAYER_EMPTY);
					}
				}
				
				if (level[y][x].isYou()) {
					if ((level[y][x].isKill()) || ((level[y][x].isSink()) && (isFixedAndCanSink(x, y)))) {
							if (((checkIfSameGhost(level[y][x].getLayersYou(), level[y][x].getLayersKill(), x, y)) && (level[y][x].isKill())) ||
								((checkIfSameGhost(level[y][x].getLayersYou(), level[y][x].getLayersSink(), x, y)) && (level[y][x].isSink()))) {
								for (Integer integer : level[y][x].getLayersYou()) {
									level[y][x].clearLayerFrom(integer);
								}
								countYous -= 1;
								if ((level[y][x].isSink()) && (level[y][x].getBackground() != LoveLevelEntity.BACKGROUND_GRAS)) {
									sinkObject(level[y][x], level[y][x].getLayersYou());
								}
							}

					}
				}
			}
		}
		if (countYous <= 0) {
			isNoYou = true;
		}
	}

	private void sinkObject(LoveLevelEntity loveLevelEntity, ArrayList<Integer> checkArray) {
		for (int i = 0; i < loveLevelEntity.getLayersSink().size(); i++) {
			int curLayer = loveLevelEntity.getLayersSink().get(i);
			if (loveLevelEntity.hasLayer(curLayer)) {
				for (int j = 0; j < checkArray.size(); j++) {
					int curPush = checkArray.get(j);
					loveLevelEntity.clearLayerFrom(curPush);
				}
				if (loveLevelEntity.isLayerBackground(curLayer)) {
					if ((loveLevelEntity.getLayer().size() == 0) || ((loveLevelEntity.getLayer().size() == 1) && (loveLevelEntity.getLayer().get(0) == LoveLevelEntity.LAYER_EMPTY))) {
						loveLevelEntity.getLayer().add(LoveLevelEntity.LAYER_EMPTY);
						loveLevelEntity.setBackground(LoveLevelEntity.BACKGROUND_GRAS);
					}
				} else {
					loveLevelEntity.clearLayerFrom(curLayer);
					if (loveLevelEntity.getLayer().size() == 0) {
						loveLevelEntity.getLayer().add(LoveLevelEntity.LAYER_EMPTY);
					}
				}
			}
		}
	}

	private boolean isFixedAndCanSink(int x, int y) {
		if (!level[y][x].isFixed()) {
			return true;
		}
		boolean[][] checked = new boolean[level.length][level[0].length];
		canSink = true;
		for (int layer : level[y][x].getLayersFixed()) {
			if (level[y][x].getLayer().contains(layer)) {
				checkNeighboursIsFixedAndCanSink(x, y, layer, checked);
			}
		}
		return canSink;
	}

	private void checkNeighboursIsFixedAndCanSink(int x, int y, int layer, boolean[][] checked) {
		checked[y][x] = true;
		if (!level[y][x].isSink()) {
			canSink = false;
			return;
		}
		if ((x - 1 >= 0) && (!checked[y][x-1]) && (level[y][x-1].getLayer().contains(layer))) {
			checkNeighboursIsFixedAndCanSink(x - 1, y, layer, checked);
		}
		if ((x + 1 < level[0].length) && (!checked[y][x+1]) && (level[y][x+1].getLayer().contains(layer))) {
			checkNeighboursIsFixedAndCanSink(x + 1, y, layer, checked);
		}
		if ((y - 1 >= 0) && (!checked[y-1][x]) && (level[y-1][x].getLayer().contains(layer))) {
			checkNeighboursIsFixedAndCanSink(x, y - 1, layer, checked);
		}
		if ((y + 1 < level.length) && (!checked[y+1][x]) && (level[y+1][x].getLayer().contains(layer))) {
			checkNeighboursIsFixedAndCanSink(x, y + 1, layer, checked);
		}
	}

	private boolean checkAndMoveObjects(int x, int y, int addX, int addY) {
		if (canMoveObject(x, y, addX, addY)) {
			checkAndResetCanGo();
			if (addX != 0) {
				int startX = 0;
				int endX = level[0].length - 1;
				if (addX > 0) {
					startX = endX;
				}
				for (int myY = 0; myY < level.length; myY += 1) {
					for (int i = startX; i >= 0 && i < level[0].length; i -= addX) {
						if (canMove[myY][i]) {
							setNewPosition(i, myY, addX, addY);
						}
					}
				}
			} else if (addY != 0) {
				int startY = 0;
				int endY = level.length - 1;
				if (addY > 0) {
					startY = endY;
				}
				for (int myX = 0; myX < level[0].length; myX += 1) {
					for (int i = startY; i >= 0 && i < level.length; i -= addY) {
						if (canMove[i][myX]) {
							setNewPosition(myX, i, addX, addY);
						}
					}
				}
			}
//			setDetailsForUnderground();
			return true;
        }
		return false;
	 }
	
	private void checkAndResetCanGo() {
		boolean bReset = false;
		for (int y = 0; y < level.length; y++) {
			for (int x = 0; x < level[0].length; x++) {
				if ((shouldMove[y][x]) && (!canMove[y][x])) {
					if (x >= 10){
						bReset = true;
					}
				}
			}
		}
		if (bReset) {
			for (int y = 0; y < level.length; y++) {
				for (int x = 0; x < level[0].length; x++) {
					if (shouldMove[y][x]) {
						canMove[y][x] = false;
						shouldMove[y][x] = false;
					}
				}
			}
		}
	}
	
    private boolean canMoveObject(int x, int y, int addX, int addY) {
        if ((x + addX < -1) || (x + addX > level[0].length) ||
            (y + addY < -1) || (y + addY > level.length)) {
            return false;
        }
        if (level[y][x].getBackground() == LoveLevelEntity.BACKGROUND_EMPTY) {
            return false;
        }
        if ((level[y][x].getBackground() == LoveLevelEntity.BACKGROUND_WATER) && (level[y][x].isSink()) && (isSameGhost(x, y)) && ((level[y][x].getText() == null) || (level[y][x].isYou()))) {
            if (!level[y][x].isFixed()) {
            	isTextMoved = true;
            	return true;
            }
        }
        if ((level[y][x].isYou()) || ((level[y][x].isPushable()) && (isSameGhost(x, y))) || (level[y][x].getText() != null)) {
    		if ((x + addX >= 0) && (x + addX < level[0].length) &&
    	        (y + addY >= 0) && (y + addY < level.length) &&
    	        (level[y+addY][x+addX].isLock()) && (!level[y][x].isKey()) && (checkIfSameGhost(curGhostLayerList, level[y+addY][x+addX].getLayersLock(), x, y))) {
     	       return false;
    	    }
    		if (level[y][x].isFixed()) {
    			shouldMove[y][x] = true;
    			boolean bRight = true;
    			if ((addX != 1) && (x + 1 < level[0].length) && (!shouldMove[y][x+1]) && (!isFixedMoved[y][x+1]) && (isSameFixed(level[y][x], level[y][x+1]))) {
    				bRight = moveObject(x, y, addX, addY, 1, 0);
    			}
    			boolean bLeft = true;
    			if ((addX != -1) && (x - 1 >= 0) && (!shouldMove[y][x-1]) && (!isFixedMoved[y][x-1]) && (isSameFixed(level[y][x], level[y][x-1]))) {
    				bLeft = moveObject(x, y, addX, addY, -1, 0);
    			}
    			boolean bUp = true;
    			if ((addY != -1) && (y - 1 >= 0) && (!shouldMove[y-1][x]) && (!isFixedMoved[y-1][x]) && (isSameFixed(level[y][x], level[y-1][x]))) {
    				bUp = moveObject(x, y, addX, addY, 0, -1);
    			}
    			boolean bDown = true;
    			if ((addY != 1) && (y + 1 < level.length) && (!shouldMove[y+1][x]) && (!isFixedMoved[y+1][x]) && (isSameFixed(level[y][x], level[y+1][x]))) {
    				bDown = moveObject(x, y, addX, addY, 0, 1);
    			}
				if (level[y][x].getText()!= null) {
					isTextMoved = true;
				}
    			return moveObject(x, y, addX, addY, bLeft && bRight && bUp && bDown);
    		} else {
    			if (level[y][x].getText()!= null) {
					isTextMoved = true;
				}
    			return moveObject(x, y, addX, addY, true);
    		}
        }
		if ((level[y][x].isLock()) && (level[y-addY][x-addX].isKey()) && (checkIfSameGhost(level[y-addY][x-addX].getLayersKey(), level[y][x].getLayersLock(), x, y))) {

		} else if (level[y][x].isSolid()) {
			if (level[y][x].getText()!= null) {
				isTextMoved = true;
			}
			boolean bText = false;
			ArrayList<Integer> list = level[y][x].getLayersSolid();
			if (level[y][x].isLock()) {
				list = level[y][x].getLayersLock();
			}
			ArrayList<Integer> curGhost = curGhostLayerList;
			if (level[y-addY][x-addX].getText() != null) {
				bText = true;
				curGhost = level[y-addY][x-addX].getLayersGhost();
			}
			if (checkIfSameGhost(curGhost, list, x - addX, y - addY, bText)) {
				return false;
			}
        }
        return true;
    }

    private boolean isSameGhost(int x, int y) {
		if (curIsGhost != level[y][x].isGhost()) {
			return false;
		}
		if ((curIsGhost) && (curGhostLayer >= 0)) {
			if (level[y][x].hasOtherGhosts(curGhostLayer)) {
				return true;
			}
			return false;
		}
		return true;
	}

    private boolean moveObject(int x, int y, int addX, int addY, int addRealX, int addRealY) {
		shouldMove[y+addRealY][x+addRealX] = true;
		boolean canMoveOb = canMoveObject(x + addRealX, y + addRealY, addX, addY);
		canMove[y][x] = canMoveOb;
		return canMoveOb;
	}
    
	private boolean moveObject(int x, int y, int addX, int addY, boolean bCanGo) {
		boolean canMoveOb = canMoveObject(x + addX, y + addY, addX, addY) && bCanGo;
		shouldMove[y][x] = true;
		canMove[y][x] = canMoveOb;
		return canMoveOb;
	}
	
    private boolean isSameFixed(LoveLevelEntity loveLevelEntity, LoveLevelEntity loveLevelEntityCheck) {
    	if (!loveLevelEntityCheck.isFixed()) {
    		return false;
    	}
    	for (int i = 0; i < loveLevelEntity.getLayersFixed().size(); i++) {
    		if (loveLevelEntityCheck.getLayer().contains(loveLevelEntity.getLayersFixed().get(i))) {
    			return true;
    		}
    	}
		return false;
	}

	private void saveLastStep() {
		this.lastSteps.add(this.lastLevel);
	}
    
    public void undoLastStep() {
    	if (this.lastSteps.size() > 0) {
    		this.level = getClone(this.lastSteps.remove(this.lastSteps.size() - 1));
			this.isMovedAndLogic = this.isMovedAndLogicTrue;
    		checkAfterMovement(true);
			checkAfterMovement(true);
			setDetailsForUnderground();
    	}
    }

	protected void setNewPosition(int x, int y, int addX, int addY) {
        isMovedAndLogic[y][x] = true;
        isMovedAndLogic[y+addY][x+addX] = true;
		isMoved[y][x] = true;
		isFixedMoved[y+addY][x+addX] = true;
        LoveLevelEntity newEntity = level[y + addY][x + addX];
        LoveLevelEntity oldEntity = level[y][x];
        if (oldEntity.isYou()) {
        	int curYouLayer = getCurYouLayerForTile(x, y);
        	while (oldEntity.getLayer().contains(curYouLayer)) {
        		oldEntity.getLayer().remove(Integer.valueOf(curYouLayer));
        	}
        	if (oldEntity.getLayer().size() <= 0) {
        		oldEntity.getLayer().add(LoveLevelEntity.LAYER_EMPTY);
        	}
        	if (newEntity.getLayer().size() > 1) {
				while (newEntity.getLayer().contains(LoveLevelEntity.LAYER_EMPTY)) {
					newEntity.getLayer().remove(Integer.valueOf(LoveLevelEntity.LAYER_EMPTY));
				}
			}
        	newEntity.getLayer().add(curYouLayer);
        	if (oldEntity.isFixed()) {
				newEntity.setFixedComplete(oldEntity.getFixed());
			}
        	newEntity.setLeft(oldEntity.isLeft());
        	newEntity.setRunTimer(oldEntity.getRunTimer());
        	newEntity.setChange(LoveGame.TILE_SIZE * LoveGame.SCALE * (addX + addY), -addX, -addY, curYouLayer);
        	oldEntity.resetValues();
        } else if (oldEntity.isPushable()) {
        	ArrayList<Integer> layersForPushable = oldEntity.getLayersPushable();
        	for (Integer layerPush : layersForPushable) {
        		if (oldEntity.getLayer().contains(layerPush)) {
        			oldEntity.getLayer().remove(Integer.valueOf(layerPush));
                	if (oldEntity.getLayer().size() <= 0) {
                		oldEntity.getLayer().add(LoveLevelEntity.LAYER_EMPTY);
                	}
            		newEntity.getLayer().add(layerPush);
            		newEntity.getLayer().remove(Integer.valueOf(LoveLevelEntity.LAYER_EMPTY));
            		newEntity.setChange(LoveGame.TILE_SIZE * LoveGame.SCALE * (addX + addY), -addX, -addY, layerPush);
        		}
			}
        }
        if ((oldEntity.getText() != null) && (!oldEntity.isYou())) {
        	newEntity.setText(oldEntity.getTextClone());
        	oldEntity.setText(null);
        	newEntity.setChange(LoveGame.TILE_SIZE * LoveGame.SCALE * (addX + addY), -addX, -addY, -1);
        }
    }
	
	private int getCurYouLayerForTile(int x, int y) {
		ArrayList<Integer> layersForYou = this.level[y][x].getLayersYou();
		for (int i = 0; i< layersForYou.size(); i++) {
			if (level[y][x].getLayer().contains(layersForYou.get(i))) {
				return layersForYou.get(i);
			}
		}
		return -1;
	}
	
	private int getCountObjektAvailable() {
		int count = 0;
		for (int y = 0; y < level.length; y++) {
			for (int x = 0; x < level[0].length; x++) {
				if (level[y][x].isYou()) {
					count += 1;
				}
			}
		}
		return count;
	}
	
	public void checkLogic(boolean first) {
		boolean[][] valid = new boolean[level.length][level[0].length];
		boolean[][] validButError = new boolean[level.length][level[0].length];
		if (!first) {
			for (int y = 0; y < level.length; y++) {
				for (int x = 0; x < level[0].length; x++) {
					if (level[y][x].getText() != null) {
						valid[y][x] = level[y][x].getText().isValid();
						validButError[y][x] = level[y][x].getText().isValidButError();
					}
				}
			}
		}

		if (first) {
			setLastLogic(this.lastLogic);

			resetText();

			this.logic = new LoveLevelLogic();
			partOfIfStatement = new boolean[level.length][level[0].length];
			for (int y = 0; y < level.length; y++) {
				for (int x = 0; x < level[0].length; x++) {
					checkLogicOnIfPosition(x, y, 1, 0, first, false);
					checkLogicOnIfPosition(x, y, 0, 1, first, false);
				}
			}

			for (int y = 0; y < level.length; y++) {
				for (int x = 0; x < level[0].length; x++) {
					checkLogicOnPosition(x, y, 1, 0, false, true, first);
					checkLogicOnPosition(x, y, 0, 1, false, true, first);
				}
			}
			setLastLogic(this.lastNextLogic);

			partOfIfStatement = new boolean[level.length][level[0].length];
			for (int y = 0; y < level.length; y++) {
				for (int x = 0; x < level[0].length; x++) {
					checkLogicOnIfPosition(x, y, 1, 0, first, true);
					checkLogicOnIfPosition(x, y, 0, 1, first, true);
				}
			}

			setLastLogic(this.lastNextLogic);
		}

		if (this.checkAllTiles()) {
			isMovedAndLogic = isMovedAndLogicTrue;
		}

        resetAllValues(this.isMovedAndLogic);
		curIsGhost = false;

		if (first) {
			logic.makeCommands(level, 0, this.isMovedAndLogic);
		} else {
			for (int y = 0; y < level.length; y++) {
				for (int x = 0; x < level[0].length; x++) {
					if (level[y][x].getText() != null) {
						level[y][x].getText().setValid(valid[y][x]);
						level[y][x].getText().setValidButError(validButError[y][x]);
					}
				}
			}
			logic.makeCommands(level, 0, this.isMovedAndLogic);

			this.isTextMoved = false;
			this.isMovedAndLogic = new boolean[level.length][level[0].length];
		}
	}

	private boolean checkAllTiles() {
		if (this.lastLogic.size() == 0) {
			return true;
		}
		if (this.lastLogic.size() != this.lastNextLogic.size()) {
			return true;
		}
		for (int i = 0; i < this.lastLogic.size(); i++) {
			if (!this.lastLogic.get(i).equals(this.lastNextLogic.get(i))) {
				return true;
			}
		}
		return false;
	}

	private void setLastLogic(ArrayList<String> lastLogic) {
		lastLogic.clear();
		for (int i = 0; i < this.logic.getHelpSize(); i++) {
			LoveLevelLogicHelp help = this.logic.getHelp().get(i);
			lastLogic.add(help.getFirst().getText()+" "+help.getSecond().getText()+" "+help.getThird().getText());
		}
	}

	private void checkLogicOnIfPosition(int x, int y, int addX, int addY, boolean isFirst, boolean isRealConditionCheck) {
		int add = 1;
		LoveLevelEntityString first = level[y][x].getText();
		if ((first != null) && (first.getTextValue() == TextConstants.CONDITION_IF)) {
			ArrayList<LoveLevelEntityString> allCondition = checkLogicOnPosition(x + add * addX, y + add * addY, addX, addY, true, false, isFirst);
			if (allCondition == null) {
				return;
			}

			add += allCondition.size();
			LoveLevelEntityString then = level[y+add * addY][x + add * addX].getText();
			if ((then != null) && (then.getTextValue() == TextConstants.CONDITION_THEN)) {
				add += 1;
				boolean conditionMet = true;
				if (isRealConditionCheck) {
					conditionMet = isConditionMet(allCondition);
				}
				ArrayList<LoveLevelEntityString> all = checkLogicOnPosition(x + add * addX, y + add * addY, addX, addY, conditionMet, true, isFirst);
				if (all != null) {
					add += all.size();
					EnumOrientation orientation = addY == 0 ? EnumOrientation.HORIZONTAL : EnumOrientation.VERTICAL;
					boolean beforeThen = true;
					for (int i = 0; i < add; i++) {
						partOfIfStatement[y + i * addY][x + i * addX] = true;
						LoveLevelEntityString text = level[y + i * addY][x + i * addX].getText();
						text.setValid(true);
						if ((beforeThen) && (text.getText().equals("THEN"))) {
							beforeThen = false;
						}
						if ((isRealConditionCheck) && (!text.getText().equals("IF")) && (beforeThen)) {
							text.setValidButError(conditionMet);
						}
						int index = 0;
						if (text.getOrientation()[index] != null) {
							index += 1;
						}
						text.getOrientation()[index] = new LoveOrientation(orientation, i != 0, i + 1 != add, add);
					}
				}
			}
		}
	}

	private boolean isConditionMet(ArrayList<LoveLevelEntityString> allCondition) {
		ArrayList<LoveLevelEntityString> subjekte = new ArrayList<LoveLevelEntityString>();
		LoveLevelEntityString praedikat = null;
		ArrayList<LoveLevelEntityString> objekte = new ArrayList<LoveLevelEntityString>();
		boolean isPraedikat = false;
		for (LoveLevelEntityString entity : allCondition) {
			if (!entity.isAnd()) {
				if ((entity.isSubjekt()) && (!isPraedikat)) {
					subjekte.add(entity);
				}
				if (entity.isPraedikat()) {
					praedikat = entity;
					isPraedikat = true;
				}
				if ((entity.isObjekt()) && (isPraedikat)) {
					objekte.add(entity);
				}
			}
		}

		for (LoveLevelEntityString subjekt : subjekte) {
			for (LoveLevelEntityString objekt : objekte) {
				String task = subjekt.getText()+" "+praedikat.getText()+" "+objekt.getText();
				if (praedikat.getText().equals(LoveEnumTiles.PRAEDIKAT_ISON.getText())) {
					if (!logic.isConditionMet(subjekt, praedikat, objekt, level)) {
						return true;
					}
				} else{
					if (!this.lastNextLogic.contains(task)) {
						return true;
					}
				}

			}
		}

		return false;
	}

	private ArrayList<LoveLevelEntityString> checkLogicOnPosition(int x, int y, int addX, int addY, boolean isCondition, boolean onlyIs, boolean bFirst) {
		int add = 0;
		int inIfCondition = 0;
		if ((x >= level[0].length) || (y >= level.length)) {
			return null;
		}

		LoveLevelEntityString first = level[y][x].getText();
		if ((first != null) && (first.isSubjekt())) {
			ArrayList<LoveLevelEntityString> all = new ArrayList<LoveLevelEntityString>();
			ArrayList<LoveLevelEntityString> subjekte = new ArrayList<LoveLevelEntityString>();
			LoveLevelEntityString praedikat = null;
			ArrayList<LoveLevelEntityString> objekte = new ArrayList<LoveLevelEntityString>();
			int stage = 1;

			while ((x + add * addX < level[0].length) && (y + add * addY < level.length)) {
				LoveLevelEntityString next = level[y + add * addY][x + add * addX].getText();
				if (stage == 0) {
					if ((next != null) && (next.isAnd())) {
						all.add(next.getClone());
						stage = 1;
					} else if ((onlyIs) && (next != null) && ((next.isPraedikat()) && (next.getTextValue() == TextConstants.PRAEDIKAT_IS) || (next.getTextValue() == TextConstants.PRAEDIKAT_ISNOT))) {
						all.add(next.getClone());
						praedikat = next.getClone();
						stage = 4;
					} else if ((!onlyIs) && (next != null) && (next.isPraedikat())) {
						all.add(next.getClone());
						praedikat = next.getClone();
						stage = 4;
					} else {
						break;
					}
				} else if (stage == 1) {
					if ((next != null) && (next.isSubjekt())) {
						subjekte.add(next.getClone());
						all.add(next.getClone());
						stage = 0;
					} else {
						break;
					}
				} else if (stage == 3) {
					if ((next != null) && (next.isAnd())) {
						stage = 4;
					} else {
						break;
					}
				} else if (stage == 4) {
					if ((next != null) && (next.isObjekt())) {
						if (objekte.size() > 0) {
							LoveLevelEntityString prev = level[y + (add - 1) * addY][x + (add - 1) * addX].getText();
							all.add(prev.getClone());
						}
						objekte.add(next.getClone());
						all.add(next.getClone());
						stage = 3;
					} else {
						break;
					}
				}
				add += 1;
				if (partOfIfStatement[y][x]) {
					inIfCondition += 1;
				}
			}

			if ((!isCondition) && (inIfCondition != all.size()) && (praedikat != null) && (subjekte.size() > 0) && (objekte.size() > 0)) {
				if (bFirst) {
					for (LoveLevelEntityString subjekt : subjekte) {
						for (LoveLevelEntityString objekt : objekte) {
							checkAndSetText(subjekt, praedikat, objekt);
						}
					}
				}
				for (int i = 0; i < all.size(); i++) {
					LoveLevelEntityString text = level[y + i * addY][x + i * addX].getText();
					text.setValid(true);
					int index = text.getOrientation()[0] == null ? 0 : 1;
					EnumOrientation orientation = addX != 0 ? EnumOrientation.HORIZONTAL : EnumOrientation.VERTICAL;
					text.getOrientation()[index] = new LoveOrientation(orientation, i != 0, i + 1 != all.size(), all.size());
				}
				return all;
			} else if ((isCondition) && (praedikat != null) && (subjekte.size() > 0) && (objekte.size() > 0)) {
				return all;
			}
		}
		return null;
	}

	private boolean checkAndSetText(LoveLevelEntityString first, LoveLevelEntityString second, LoveLevelEntityString third) {
		return checkAndSetCommand(first, second, third) >= 0;
	}

	private int checkAndSetCommand(LoveLevelEntityString first, LoveLevelEntityString second,
			LoveLevelEntityString third) {
		int command = -1;
		if (logic.isValidCommand(first, second, third)) {
			command = logic.setCommand(first, second, third);
			if (command < 0) {
				command = 0;
			}
		}
		return command;
	}
	
	public boolean isLevelValid() {
		checkLogic(true);
		boolean isYou = false;
		boolean isGoal = false;
		int countIs = 0;
		for (int y = 0; y < level.length; y++) {
			for (int x = 0; x < level[0].length; x++) {
				if (level[y][x].isYou()) {
					isYou = true;
				}
				if ((level[y][x].getText() != null) && ((level[y][x].getText().getTextValue() == TextConstants.VERB_GOAL) || (level[y][x].getText().getTextValue() == TextConstants.VERB_ALL_GOAL))) {
					isGoal = true;
				}
				if ((level[y][x].getText() != null) && (level[y][x].getText().getTextValue() == TextConstants.PRAEDIKAT_IS)) {
					countIs += 1;
				}
			}
		}
		if (countIs < 2) {
			return false;
		}
		return isYou && isGoal;
	}

    private void resetAllValues() {
	    resetAllValues(this.isMovedAndLogic);
    }

	private void resetAllValues(boolean[][] check) {
		for (int y = 0; y < level.length; y++) {
			for (int x = 0; x < level[0].length; x++) {
			    if (check[y][x]) {
                    level[y][x].resetValues();
                }

			}
		}
	}

	private void resetText() {
		for (LoveLevelEntity[] loveLevelEntities : level) {
			for (int x = 0; x < level[0].length; x++) {
				if (loveLevelEntities[x].getText() != null) {
					loveLevelEntities[x].getText().reset();
				}
			}
		}
	}
	
    public void render(GameScreen screen) {
        this.render(screen, 0, 3 * LoveGame.SCALE * LoveGame.TILE_SIZE);
    }
    
    public void render(GameScreen screen, int changeX, int changeY) {
    	screen.getRenderer().begin(ShapeType.Filled);
    	for (int y = 0; y < level.length; y++) {
			for (int x = 0; x < level[0].length; x++) {
				level[y][x].renderBackgroundEmpty(screen, changeX + LoveGame.TILE_SIZE * LoveGame.SCALE * x, changeY + LoveGame.TILE_SIZE * LoveGame.SCALE * y);
			}
		}
    	for (int y = 0; y < hud.length; y++) {
			for (int x = 0; x < hud[0].length; x++) {
				if (y < 3) {
					hud[y][x].renderBackgroundEmpty(screen, LoveGame.TILE_SIZE * LoveGame.SCALE * x, LoveGame.TILE_SIZE * LoveGame.SCALE * y);
				} else {
					hud[y][x].renderBackgroundEmpty(screen, LoveGame.TILE_SIZE * LoveGame.SCALE * x, LoveGame.TILE_SIZE * LoveGame.SCALE * (y - 3 + level.length) + changeY);
				}
			}
		}
    	screen.getRenderer().end();

    	screen.spriteBatch.begin();
    	for (int y = 0; y < hud.length; y++) {
			for (int x = 0; x < hud[0].length; x++) {
				if (y < 3) {
					hud[y][x].renderBackground(screen, LoveGame.TILE_SIZE * LoveGame.SCALE * x, LoveGame.TILE_SIZE * LoveGame.SCALE * y);
				} else {
					hud[y][x].renderBackground(screen, LoveGame.TILE_SIZE * LoveGame.SCALE * x, LoveGame.TILE_SIZE * LoveGame.SCALE * (y - 3 + level.length) + changeY);
				}
			}
		}
    	for (int y = 0; y < level.length; y++) {
			for (int x = 0; x < level[0].length; x++) {
				level[y][x].render(screen, changeX + LoveGame.TILE_SIZE * LoveGame.SCALE * x, changeY + LoveGame.TILE_SIZE * LoveGame.SCALE * y);
			}
		}
		for (int y = 0; y < level.length; y++) {
			for (int x = 0; x < level[0].length; x++) {
				level[y][x].renderLayer(screen, changeX + LoveGame.TILE_SIZE * LoveGame.SCALE * x, changeY + LoveGame.TILE_SIZE * LoveGame.SCALE * y);
			}
		}
		screen.spriteBatch.end();
		
		Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
		screen.getRenderer().begin(ShapeType.Filled);
		screen.getRenderer().setColor(LoveGame.COLOR_DARK[0], LoveGame.COLOR_DARK[1], LoveGame.COLOR_DARK[2], 1f);
		screen.getRenderer().rect(0, 3 * LoveGame.TILE_SIZE * LoveGame.SCALE - 2 * LoveGame.SCALE, LoveGame.WIDTH * LoveGame.SCALE, 3 * LoveGame.SCALE);
		screen.getRenderer().rect(0, changeY + level.length * LoveGame.TILE_SIZE * LoveGame.SCALE - 2 * LoveGame.SCALE, LoveGame.WIDTH * LoveGame.SCALE, 3 * LoveGame.SCALE);

		for (int y = 0; y < level.length; y++) {
			for (int x = 0; x < level[0].length; x++) {
				level[y][x].renderLayerTextBackground(screen, changeX + LoveGame.TILE_SIZE * LoveGame.SCALE * x, changeY + LoveGame.TILE_SIZE * LoveGame.SCALE * y);
			}
		}
		screen.getRenderer().end();
        Gdx.graphics.getGL20().glDisable(GL20.GL_BLEND);

		screen.getRenderer().begin(ShapeType.Line);
		for (int y = 0; y < level.length; y++) {
			for (int x = 0; x < level[0].length; x++) {
				level[y][x].renderBorderValid(screen, changeX + LoveGame.TILE_SIZE * LoveGame.SCALE * x, changeY + LoveGame.TILE_SIZE * LoveGame.SCALE * y);
				level[y][x].renderBorderValidButError(screen, changeX + LoveGame.TILE_SIZE * LoveGame.SCALE * x, changeY + LoveGame.TILE_SIZE * LoveGame.SCALE * y);
			}
		}
		screen.getRenderer().end();
		
		screen.spriteBatch.begin();
		for (int y = 0; y < level.length; y++) {
			for (int x = 0; x < level[0].length; x++) {
				level[y][x].renderLayerText(screen, changeX + LoveGame.TILE_SIZE * LoveGame.SCALE * x, changeY + LoveGame.TILE_SIZE * LoveGame.SCALE * y);
			}
		}
		screen.spriteBatch.end();
		
		if (winState != LoveWinState.RUNNING) {
			float addYWon = 0;
			if ((winState == LoveWinState.WON) && (isUserlevel) && (!isVoted)) {
				addYWon = 5;
				renderBackground(screen, (LoveGame.WIDTH / 3 * LoveGame.SCALE) / 2, LoveGame.HEIGHT / 2f * LoveGame.SCALE - (1.5f + addYWon) * LoveGame.TILE_SIZE * LoveGame.SCALE, LoveGame.WIDTH * 2 / 3 * LoveGame.SCALE, LoveGame.SCALE * LoveGame.TILE_SIZE * 8, 2f, 0.75f);
			} else {
				renderBackground(screen, (LoveGame.WIDTH / 3 * LoveGame.SCALE) / 2, LoveGame.HEIGHT / 2f * LoveGame.SCALE - 1.5f * LoveGame.TILE_SIZE * LoveGame.SCALE, LoveGame.WIDTH * 2 / 3 * LoveGame.SCALE, LoveGame.SCALE * LoveGame.TILE_SIZE * 3, 2f, 0.75f);
			}

			String key = (winState == LoveWinState.WON) ? "analysis_text_0" : "analysis_text_1";
			String s = Localization.getInstance().get(key);
			screen.drawString(s, LoveGame.WIDTH / 2 * LoveGame.SCALE, 9 * LoveGame.SCALE + LoveGame.HEIGHT / 2f * LoveGame.SCALE - (1.5f + addYWon) * LoveGame.TILE_SIZE * LoveGame.SCALE, LoveGame.COLOR_BUTTON, AssetLoader.font40, true);

			key = (winState == LoveWinState.WON) ? "analysis_next_text_0" : "analysis_next_text_1";
			s = Localization.getInstance().get(key);
			screen.drawString(s, LoveGame.WIDTH / 2 * LoveGame.SCALE, 14 * LoveGame.SCALE + LoveGame.HEIGHT / 2f * LoveGame.SCALE - 0.2f * LoveGame.TILE_SIZE * LoveGame.SCALE, LoveGame.COLOR_BUTTON, AssetLoader.font30, true);
			
		}
    }
    
    protected void renderBackground(GameScreen screen, float x, float y, float width, float height, float stroke, float alpha) {
    	float rem = stroke/2f;
    	
    	Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
    	screen.getRenderer().begin(ShapeType.Filled);
    	screen.getRenderer().setColor(Constants.COLOR_WHITE[0], Constants.COLOR_WHITE[1], Constants.COLOR_WHITE[2], alpha);
    	screen.getRenderer().roundedRect(x + rem, y + rem, width, height, 3);
    	screen.getRenderer().end();
		Gdx.graphics.getGL20().glDisable(GL20.GL_BLEND);
		
		Gdx.gl20.glLineWidth(stroke);
		screen.getRenderer().begin(ShapeType.Line);
		screen.getRenderer().setColor(LoveGame.COLOR_BUTTON[0], LoveGame.COLOR_BUTTON[1], LoveGame.COLOR_BUTTON[2], 1f);
		screen.getRenderer().roundedRectLine(x + rem, y + rem, width, height, 3);
		screen.getRenderer().end();
		Gdx.gl20.glLineWidth(1f);
    }
    
}
