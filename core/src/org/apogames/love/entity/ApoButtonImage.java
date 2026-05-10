package org.apogames.love.entity;

import org.apogames.love.backend.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class ApoButtonImage extends ApoButton {

	public ApoButtonImage(int x, int y, int width, int height, String function, String text, TextureRegion image) {
		super(x, y, width, height, function, text);
		
		super.setImage(image);
	}
	
	public void render(GameScreen screen, int changeX, int changeY, boolean bShowTextOnly ) {
		if ( this.isVisible() ) {
			screen.spriteBatch.begin();
			screen.spriteBatch.enableBlending();
			renderImage(screen, changeX, changeY);
			screen.spriteBatch.end();
			
			renderOutline(screen, changeX, changeY);
		}
	}

	public void renderOutline(GameScreen screen, int changeX, int changeY) {
		if ( this.isVisible() ) {
			screen.getRenderer().begin(ShapeType.Line);
			screen.getRenderer().setColor(0f, 44f/255f, 99f/255f, 1f);
			if (( this.isBPressed() )) {
				screen.getRenderer().setColor(255f/ 255.0f, 0f / 255.0f, 0f / 255.0f, 1f);
			} else if ( this.isBOver() ) {
				screen.getRenderer().setColor(255f/ 255.0f, 255f / 255.0f, 0f / 255.0f, 1f);
			}
			if (getStroke() > 1) {
				Gdx.gl20.glLineWidth(getStroke());
			}
			if (getWidth() > 70) {
				screen.getRenderer().roundedRectLine(this.getX() + changeX + 3, this.getY() + changeY, getWidth() - 5, getHeight() - 5, 25);
			} else {
				screen.getRenderer().roundedRectLine(this.getX() + changeX + 1, this.getY() + changeY, getWidth() - 3, getHeight() - 3, 10);
			}
			screen.getRenderer().end();
			
			Gdx.gl20.glLineWidth(1f);
		}
	}

	public void renderImage(GameScreen screen, int changeX, int changeY) {
		screen.spriteBatch.draw(this.getImage(), this.getX() + changeX, this.getY() + changeY, getWidth(), getHeight());
	}
}
