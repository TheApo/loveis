package org.apogames.love.game.love;

import org.apogames.love.game.LoveEnumTiles;

import java.util.ArrayList;

public class LoveLevelLogic {

	private ArrayList<LoveLevelLogicHelp> help = new ArrayList<LoveLevelLogicHelp>();

	public LoveLevelLogic() {
		
	}

	public int getHelpSize() {
		return this.help.size();
	}

	public void reset() {
		this.help.clear();
	}

	public ArrayList<LoveLevelLogicHelp> getHelp() {
		return help;
	}

	public boolean isValidCommand(LoveLevelEntityString first,
								  LoveLevelEntityString second,
								  LoveLevelEntityString third) {
		if ((first.isSubjekt()) && (second.isPraedikat()) && (third.isObjekt())) {
			if ((first.isSubjekt()) && (!first.isObjekt()) && (third.isSubjekt())) {
				return false;
			}
			return true;
		}
		return false;
	}

	public boolean isConditionMet(LoveLevelEntityString first,
								  LoveLevelEntityString second,
								  LoveLevelEntityString third,
								  LoveLevelEntity[][] level) {
		LoveEnumTiles enumForFirst = LoveEnumTiles.getEnumForTextValue(first.getTextValue());
		LoveEnumTiles enumForSecond = LoveEnumTiles.getEnumForTextValue(second.getTextValue());
		LoveEnumTiles enumForThird = LoveEnumTiles.getEnumForTextValue(third.getTextValue());
		for (int y = 0; y < level.length; y++) {
			for (int x = 0; x < level[0].length; x++) {
				if ((enumForSecond != null) && (enumForSecond.getTextValue() != TextConstants.PRAEDIKAT_ISNOT)) {
					if (findCondition(enumForFirst, enumForSecond, enumForThird, level, x, y)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean findCondition(LoveEnumTiles enumForFirst, LoveEnumTiles enumForSecond, LoveEnumTiles enumForThird, LoveLevelEntity[][] level, int x, int y) {
		if (enumForSecond.getTextValue() == TextConstants.PRAEDIKAT_IS) {
			return findConditionForIs(enumForFirst, enumForThird, level, x, y);
		} else if (enumForSecond.getTextValue() == TextConstants.PRAEDIKAT_ISON) {
			return findConditionForIsOn(enumForFirst, enumForThird, level, x, y);
		}
		return false;
	}

	private boolean findConditionForIsOn(LoveEnumTiles enumForFirst, LoveEnumTiles enumForThird, LoveLevelEntity[][] level, int x, int y) {
		LoveLevelEntity loveLevelEntity = level[y][x];
		int result = loveLevelEntity.getLayerForText(enumForFirst);
		if (result < 0) {
			return false;
		}
		int thirdResult = loveLevelEntity.getLayerForText(enumForThird);
		return thirdResult >= 0;
	}

	private boolean findConditionForIs(LoveEnumTiles enumForFirst, LoveEnumTiles enumForThird, LoveLevelEntity[][] level, int x, int y) {
		LoveLevelEntity loveLevelEntity = level[y][x];
		int result = loveLevelEntity.getLayerForText(enumForFirst);
		if (result < 0) {
			return false;
		}

		switch (enumForThird.getTextValue()) {
			case TextConstants.VERB_YOU:
				return level[y][x].isYou();
			case TextConstants.VERB_GHOST:
				return level[y][x].isGhost();
			case TextConstants.VERB_ALL_GOAL:
				return level[y][x].isAllGoal();
			case TextConstants.VERB_GOAL:
				return level[y][x].isGoal();
			case TextConstants.VERB_HOT:
				return level[y][x].isHot();
			case TextConstants.VERB_KEY:
				return level[y][x].isKey();
			case TextConstants.VERB_KILL:
				return level[y][x].isKill();
			case TextConstants.VERB_LOCK:
				return level[y][x].isLock();
			case TextConstants.VERB_MELT:
				return level[y][x].isMelt();
			case TextConstants.VERB_PUSH:
				return level[y][x].isPushable();
			case TextConstants.VERB_SINK:
				return level[y][x].isSink();
			case TextConstants.VERB_SLIP:
				return level[y][x].isSlip();
			case TextConstants.VERB_SOLID:
				return level[y][x].isSolid();
			case TextConstants.VERB_STICKY:
				return level[y][x].isFixed();
			case TextConstants.VERB_WINABLE:
				return level[y][x].isWinable();
			default:
				return false;
		}
	}

	public int setCommand(LoveLevelEntityString first, 
			  LoveLevelEntityString second,
			  LoveLevelEntityString third) {
		if ((second.getTextValue() == TextConstants.PRAEDIKAT_IS) ||
            (second.getTextValue() == TextConstants.PRAEDIKAT_ISNOT)) {
			this.help.add(new LoveLevelLogicHelp(first, second, third));
		}
		return 0;
	}

	public int makeCommands(LoveLevelEntity[][] level) {
		return makeCommands(level, 0, new boolean[level.length][level[0].length]);
	}

	public int makeCommands(LoveLevelEntity[][] level, int start, boolean[][] check) {
		for (int i = start; i < help.size(); i++) {
			LoveLevelEntityString first = help.get(i).getFirst();
            LoveLevelEntityString second = help.get(i).getSecond();
			LoveLevelEntityString third = help.get(i).getThird();
			first.setValid(true);
			second.setValid(true);
			third.setValid(true);
			LoveEnumTiles enumForFirst = null;
			LoveEnumTiles enumForThird = null;
			for (int y = 0; y < level.length; y++) {
				for (int x = 0; x < level[0].length; x++) {
					if (check[y][x]) {
						if (enumForFirst == null) {
							enumForFirst = LoveEnumTiles.getEnumForTextValue(first.getTextValue());
						}
						if (enumForThird == null) {
							enumForThird = LoveEnumTiles.getEnumForTextValue(third.getTextValue());
						}
						if (second.getTextValue() == TextConstants.PRAEDIKAT_IS) {
							makeCommand(enumForFirst, enumForThird, level, x, y);
						}
						if (second.getTextValue() == TextConstants.PRAEDIKAT_ISNOT) {
							makeCommandNot(enumForFirst, enumForThird, level, x, y);
						}
					}
				}
			}
		}
		return 0;
	}

    public int makeCommand(LoveEnumTiles enumForFirst,
                           LoveEnumTiles enumForThird,
                           LoveLevelEntity[][] level, int x, int y) {
        LoveLevelEntity loveLevelEntity = level[y][x];
        int result = loveLevelEntity.getLayerForText(enumForFirst);
        if (result < 0) {
            return -1;
        }

        switch (enumForThird.getTextValue()) {
            case TextConstants.VERB_YOU:
                loveLevelEntity.setYou(result);
                return result;
            case TextConstants.VERB_SOLID:
                loveLevelEntity.setSolid(result);
                return result;
            case TextConstants.VERB_GOAL:
                loveLevelEntity.setGoal(result);
                return result;
            case TextConstants.VERB_ALL_GOAL:
                loveLevelEntity.setAllGoal(result);
                return result;
            case TextConstants.VERB_WINABLE:
                loveLevelEntity.setWinable(result);
                return result;
            case TextConstants.VERB_SINK:
                loveLevelEntity.setSink(result);
                return result;
            case TextConstants.VERB_PUSH:
            	if (enumForFirst.getLayer() < 99) {
					loveLevelEntity.setPushable(result);
				} else {
            		return -1;
				}
                return result;
            case TextConstants.VERB_GHOST:
                loveLevelEntity.setGhost(result);
                return result;
            case TextConstants.VERB_LOCK:
                loveLevelEntity.setLock(result);
                loveLevelEntity.setSolid(result);
                return result;
            case TextConstants.VERB_STICKY:
                loveLevelEntity.setFixed(result);
                loveLevelEntity.setSolid(result);
                return result;
            case TextConstants.VERB_KEY:
                loveLevelEntity.setKey(result);
                return result;
            case TextConstants.VERB_KILL:
                loveLevelEntity.setKill(result);
                return result;
            case TextConstants.VERB_SLIP:
                loveLevelEntity.setSlip(result);
                return result;
            case TextConstants.VERB_MELT:
                loveLevelEntity.setMelt(result);
                return result;
            case TextConstants.VERB_HOT:
                loveLevelEntity.setHot(result);
                return result;
            case TextConstants.EMPTY:
                loveLevelEntity.getLayer().clear();
                loveLevelEntity.getLayer().add(LoveLevelEntity.LAYER_EMPTY);
                return result;
            case TextConstants.ICE:
            case TextConstants.WATER:
            case TextConstants.LAVA:
            case TextConstants.GRAS:
                loveLevelEntity.setBackground(enumForThird.getLayer());
                return result;
            default:
                loveLevelEntity.clearLayerFrom(LoveLevelEntity.LAYER_EMPTY);
                loveLevelEntity.clearLayerFrom(result);
                loveLevelEntity.getLayer().add(enumForThird.getLayer());
                return result;
        }
    }

	public int makeCommandNot(LoveEnumTiles enumForFirst,
						   LoveEnumTiles enumForThird,
						   LoveLevelEntity[][] level, int x, int y) {
		LoveLevelEntity loveLevelEntity = level[y][x];
		int result = loveLevelEntity.getLayerForText(enumForFirst);
		if (result < 0) {
			return -1;
		}

		switch (enumForThird.getTextValue()) {
			case TextConstants.VERB_YOU:
				loveLevelEntity.removeYou();
				return result;
			case TextConstants.VERB_SOLID:
				loveLevelEntity.removeSolid();
				return result;
			case TextConstants.VERB_GOAL:
				loveLevelEntity.removeGoal();
				return result;
			case TextConstants.VERB_ALL_GOAL:
				loveLevelEntity.removeAllGoal();
				return result;
			case TextConstants.VERB_WINABLE:
				loveLevelEntity.removeWinable();
				return result;
			case TextConstants.VERB_SINK:
				loveLevelEntity.removeSink();
				return result;
			case TextConstants.VERB_GHOST:
				loveLevelEntity.removeGhost();
				return result;
			case TextConstants.VERB_PUSH:
				loveLevelEntity.removePushable();
				return result;
			case TextConstants.VERB_LOCK:
				loveLevelEntity.removeLock();
				loveLevelEntity.removeSolid();
				return result;
			case TextConstants.VERB_STICKY:
				loveLevelEntity.removeFixed();
				loveLevelEntity.removeSolid();
				return result;
			case TextConstants.VERB_KEY:
				loveLevelEntity.removeKey();
				return result;
			case TextConstants.VERB_KILL:
				loveLevelEntity.removeKill();
				return result;
			case TextConstants.VERB_SLIP:
				loveLevelEntity.removeSlip();
				return result;
			case TextConstants.VERB_MELT:
				loveLevelEntity.removeMelt();
				return result;
			case TextConstants.VERB_HOT:
				loveLevelEntity.removeHot();
				return result;
		}
		return -1;
	}
}
