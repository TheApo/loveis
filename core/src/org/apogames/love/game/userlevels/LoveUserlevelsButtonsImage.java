package org.apogames.love.game.userlevels;

import org.apogames.love.backend.GameScreen;
import org.apogames.love.entity.ApoButtonImage;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class LoveUserlevelsButtonsImage extends ApoButtonImage {

	public LoveUserlevelsButtonsImage(int x, int y, int width, int height, String function, String text,
			TextureRegion image) {
		super(x, y, width, height, function, text, image);
	}
	
	public void renderOutline(GameScreen screen, int changeX, int changeY) {
	}

	public void renderImage(GameScreen screen, int changeX, int changeY) {
		if (!isSelect()) {
			screen.spriteBatch.setColor(1f, 1f, 1f, 0.4f);
		}
		screen.spriteBatch.draw(this.getImage(), this.getX() + changeX, this.getY() + changeY, getWidth(), getHeight());
		screen.spriteBatch.setColor(1f, 1f, 1f, 1f);
	}

}
