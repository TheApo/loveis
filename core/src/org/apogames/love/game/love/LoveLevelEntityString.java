package org.apogames.love.game.love;

import org.apogames.love.game.LoveEnumTiles;

public class LoveLevelEntityString {

	private final String text;
	private final int textValue;
	private final boolean isPraedikat;
	private final boolean isSubjekt;
	private final boolean isObjekt;
	private final float[] color;

	private boolean isValid;
	private boolean isValidButError;
	private boolean isAnd;
	private LoveOrientation[] orientation = new LoveOrientation[2];
	
	public LoveLevelEntityString(final String text, final boolean isSubjekt, final boolean isPraedikat, final boolean isObjekt, final float[] color, int textValue) {
		this.text = text.toUpperCase();

		this.isSubjekt = isSubjekt;
		this.isPraedikat = isPraedikat;
		this.isObjekt = isObjekt;
		this.color = color;
		this.textValue = textValue;

		this.isValid = false;
		this.isValidButError = false;
		this.isAnd = false;
		if (this.textValue == LoveEnumTiles.PRAEDIKAT_AND.getTextValue()) {
			isAnd = true;
		}
	}

	public void reset() {
		isValid = false;
		isValidButError = false;
		orientation[0] = null;
		orientation[1] = null;

	}
	
	public LoveLevelEntityString getClone() {
		LoveLevelEntityString clone = new LoveLevelEntityString(text, isSubjekt, isPraedikat, isObjekt, color, textValue);
		clone.setValid(isValid);
		clone.setValidButError(isValidButError);
		clone.setAnd(isAnd);
		if (isValid) {
			if (this.orientation[0] != null) {
				clone.getOrientation()[0] = this.orientation[0].getClone();
			}
			if (this.orientation[1] != null) {
				clone.getOrientation()[1] = this.orientation[1].getClone();
			}
		}

		return clone;
	}
	
	public String getText() {
		return text;
	}
	
	public boolean isPraedikat() {
		return isPraedikat;
	}
	
	public boolean isSubjekt() {
		return isSubjekt;
	}
	
	public boolean isObjekt() {
		return isObjekt;
	}

	public float[] getColor() {
		return color;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean valid) {
		isValid = valid;
	}

	public boolean isAnd() {
		return isAnd;
	}

	public void setAnd(boolean and) {
		isAnd = and;
	}

	public int getTextValue() {
		return textValue;
	}

	public boolean isValidButError() {
		return isValidButError;
	}

	public void setValidButError(boolean validButError) {
		isValidButError = validButError;
	}

	public LoveOrientation[] getOrientation() {
		return orientation;
	}
}
