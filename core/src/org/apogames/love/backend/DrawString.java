package org.apogames.love.backend;

public enum DrawString {

	BEGIN(0),
	MIDDLE(0.5f),
	END(1f);
	
	private float difference;
	
	private DrawString(float difference) {
		this.difference = difference;
	}
	
	public float getDifference() {
		return difference;
	}
}
