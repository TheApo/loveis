package org.apogames.love.entity;

import org.apogames.love.backend.GameScreen;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ApoButtonSwitch extends ApoButton {

	private TextureRegion switchImage;
	
	public ApoButtonSwitch(int x, int y, int width, int height, String function, String text, TextureRegion image, TextureRegion switchImage) {
		super(x, y, width, height, function, text);
		this.setImage(image);
		this.switchImage = switchImage;
	}

	public void renderImage(GameScreen screen, int changeX, int changeY) {
		if (this.isSelect()) {
			screen.spriteBatch.draw(this.switchImage, this.getX() + changeX + this.getWidth()/2 - this.switchImage.getRegionWidth()/2, this.getY() + changeY + this.getHeight()/2 - this.switchImage.getRegionHeight()/2);
		} else {
			screen.spriteBatch.draw(this.getImage(), this.getX() + changeX + this.getWidth()/2 - this.getImage().getRegionWidth()/2, this.getY() + changeY + this.getHeight()/2 - this.getImage().getRegionHeight()/2);	
		}
	}
	
}
