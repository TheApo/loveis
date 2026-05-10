package org.apogames.love.game;

import org.apogames.love.Constants;
import org.apogames.love.game.love.LoveGame;
import org.apogames.love.game.love.LoveLevelEntity;
import org.apogames.love.game.love.TextConstants;

public enum LoveEnumTiles {

	HERO("HERO", "y", LoveLevelEntity.LAYER_KNIGHT, 0, 1, LoveGame.COLOR_BLUE_DARK, TextConstants.HERO),
	GOAL("WOMAN", "p", LoveLevelEntity.LAYER_GOAL, 5, 1, LoveGame.COLOR_RED_DARK, TextConstants.GOAL),
	ROCK("ROCK", "r", LoveLevelEntity.LAYER_ROCK, 5, 4, LoveGame.COLOR_GRAY_DARK, TextConstants.ROCK),
	HEART("LOVE", "h", LoveLevelEntity.LAYER_HEART, 7, 4, Constants.COLOR_RED, TextConstants.HEART),
	TREE("TREE", "t", LoveLevelEntity.LAYER_TREE, 3, 1, LoveGame.COLOR_GREEN_DARK, TextConstants.TREE),
	FIRE("FIRE", "f", LoveLevelEntity.LAYER_FIRE, 0, 3, LoveGame.COLOR_RED_DARK, TextConstants.FIRE),
	SNOWBALL("SNOWBALL", "s", LoveLevelEntity.LAYER_SNOWBALL, 2, 3, LoveGame.COLOR_GRAY_DARK, TextConstants.SNOWBALL),
	LOCK("PADLOCK", "l", LoveLevelEntity.LAYER_LOCK, 6, 4, LoveGame.COLOR_YELLOW_DARK, TextConstants.LOCK),
	STAR("STAR", "u", LoveLevelEntity.LAYER_STAR, 5, 3, LoveGame.COLOR_YELLOW_DARK, TextConstants.STAR),
	WALL("WALL", "w", LoveLevelEntity.LAYER_WALL, 0, 6, LoveGame.COLOR_YELLOW_DARK, TextConstants.WALL),
	WALL_BLUE("BLUEWALL", "b", LoveLevelEntity.LAYER_WALL_BLUE, 0, 7, LoveGame.COLOR_BLUE_DARK, TextConstants.WALL_BLUE),
	WALL_RED("REDWALL", "a", LoveLevelEntity.LAYER_WALL_RED, 0, 8, LoveGame.COLOR_RED_DARK, TextConstants.WALL_RED),
	WALL_GRAY("GRAYWALL", "c", LoveLevelEntity.LAYER_WALL_GRAY, 0, 9, LoveGame.COLOR_GRAY_DARK, TextConstants.WALL_GRAY),
	EMPTY("EMPTY", "", LoveLevelEntity.LAYER_EMPTY, 0, 0, LoveGame.COLOR_DARK, TextConstants.EMPTY),
	EMPTY_BACKGROUND("", " ", LoveLevelEntity.BACKGROUND_EMPTY, 0, 0, LoveGame.COLOR_DARK, TextConstants.EMPTY_BACKGROUND),
	ICE("ICE", "i", LoveLevelEntity.BACKGROUND_ICE, 0, 0, LoveGame.COLOR_BLUE_DARK, TextConstants.ICE),
	WATER("WATER", "~", LoveLevelEntity.BACKGROUND_WATER, 0, 0, Constants.COLOR_BLUE, TextConstants.WATER),
	LAVA("LAVA", "*", LoveLevelEntity.BACKGROUND_LAVA, 0, 0, LoveGame.COLOR_RED_DARK, TextConstants.LAVA),
	GRAS("GRAS", ".", LoveLevelEntity.BACKGROUND_GRAS, 0, 0, LoveGame.COLOR_GREEN_DARK, TextConstants.GRAS),
	VERB_YOU("YOU", "", LoveLevelEntity.LAYER_EMPTY, -1, -1, LoveGame.COLOR_DARK_BRIGHT, TextConstants.VERB_YOU),
	VERB_GOAL("GOAL", "", LoveLevelEntity.LAYER_EMPTY, -1, -1, LoveGame.COLOR_DARK_BRIGHT, TextConstants.VERB_GOAL),
	VERB_ALL_GOAL("ALLGOAL", "", LoveLevelEntity.LAYER_EMPTY, -1, -1, LoveGame.COLOR_DARK_BRIGHT, TextConstants.VERB_ALL_GOAL),
	VERB_WINABLE("WINABLE", "", LoveLevelEntity.LAYER_EMPTY, -1, -1, LoveGame.COLOR_GREEN_BRIGHT, TextConstants.VERB_WINABLE),
	VERB_SOLID("SOLID", "", LoveLevelEntity.LAYER_EMPTY, -1, -1, LoveGame.COLOR_YELLOW_BRIGHT, TextConstants.VERB_SOLID),
	VERB_PUSH("PUSH", "", LoveLevelEntity.LAYER_EMPTY, -1, -1, LoveGame.COLOR_GRAY_BRIGHT, TextConstants.VERB_PUSH),
	VERB_KILL("KILL", "", LoveLevelEntity.LAYER_EMPTY, -1, -1, Constants.COLOR_RED_LIGHT, TextConstants.VERB_KILL),
	VERB_MELT("MELT", "", LoveLevelEntity.LAYER_EMPTY, -1, -1, Constants.COLOR_RED_LIGHT, TextConstants.VERB_MELT),
	VERB_HOT("HOT", "", LoveLevelEntity.LAYER_EMPTY, -1, -1, Constants.COLOR_RED_LIGHT, TextConstants.VERB_HOT),
	VERB_SLIP("SLIP", "", LoveLevelEntity.LAYER_EMPTY, -1, -1, LoveGame.COLOR_BLUE_BRIGHT, TextConstants.VERB_SLIP),
	VERB_SINK("SINK", "", LoveLevelEntity.LAYER_EMPTY, -1, -1, Constants.COLOR_BLUE_BRIGHT, TextConstants.VERB_SINK),
	VERB_KEY("KEY", "", LoveLevelEntity.LAYER_EMPTY, -1, -1, LoveGame.COLOR_YELLOW_BRIGHT, TextConstants.VERB_KEY),
	VERB_LOCK("LOCK", "", LoveLevelEntity.LAYER_EMPTY, -1, -1, LoveGame.COLOR_YELLOW_BRIGHT, TextConstants.VERB_LOCK),
	VERB_STICKY("STICKY", "", LoveLevelEntity.LAYER_EMPTY, -1, -1, LoveGame.COLOR_YELLOW_BRIGHT, TextConstants.VERB_STICKY),
	VERB_GHOST("GHOST", "", LoveLevelEntity.LAYER_EMPTY, -1, -1, LoveGame.COLOR_GRAY_BRIGHT, TextConstants.VERB_GHOST),
	PRAEDIKAT_IS("IS", "", LoveLevelEntity.LAYER_EMPTY, -1, -1, Constants.COLOR_WHITE, TextConstants.PRAEDIKAT_IS),
	PRAEDIKAT_ISNOT("ISNOT", "", LoveLevelEntity.LAYER_EMPTY, -1, -1, Constants.COLOR_WHITE, TextConstants.PRAEDIKAT_ISNOT),
	PRAEDIKAT_AND("AND", "", LoveLevelEntity.LAYER_EMPTY, -1, -1, Constants.COLOR_BLUE, TextConstants.PRAEDIKAT_AND),
	PRAEDIKAT_ISON("ISON", "", LoveLevelEntity.LAYER_EMPTY, -1, -1, Constants.COLOR_WHITE, TextConstants.PRAEDIKAT_ISON),
	CONDITION_IF("IF", "", LoveLevelEntity.LAYER_EMPTY, -1, -1, Constants.COLOR_BLACK, TextConstants.CONDITION_IF),
	CONDITION_THEN("THEN", "", LoveLevelEntity.LAYER_EMPTY, -1, -1, Constants.COLOR_BLACK, TextConstants.CONDITION_THEN);

	private final String text;
	private final String level;
	private final int layer;
	private final int textureX;
	private final int textureY;
	private final float[] color;
	private final int textValue;
	
	LoveEnumTiles(String text, String level, int layer, int textureX, int textureY, float[] color, int textValue) {
		this.text = text;
		this.level = level;
		this.layer = layer;
		this.textureX = textureX;
		this.textureY = textureY;
		this.color = color;
		this.textValue = textValue;
	}

	public final boolean isBackgroundOrEmpty() {
		return ((textureX == 0) && (textureY == 0));
	}
	
	public final String getText() {
		return text;
	}

	public final String getLevel() {
		return level;
	}

	public final int getLayer() {
		return layer;
	}

	public final int getTextureX() {
		return textureX;
	}

	public final int getTextureY() {
		return textureY;
	}
	
	public final float[] getColor() {
		return color;
	}

	public final int getTextValue() {
		return textValue;
	}

	public static LoveEnumTiles getEnumForTextValue(int textValue) {
		for (LoveEnumTiles enumTile : values()) {
			if (enumTile.getTextValue() == textValue) {
				return enumTile;
			}
		}
		return null;
	}
}
