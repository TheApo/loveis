package org.apogames.love.game.userlevels;

import org.apogames.love.Constants;
import org.apogames.love.backend.GameScreen;
import org.apogames.love.entity.ApoButtonColor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class LoveUserlevelsButtons extends ApoButtonColor {

	public LoveUserlevelsButtons(int x, int y, int width, int height, String function, String text, float[] color, float[] colorBorder) {
		super(x, y, width, height, function, text, color, colorBorder);
	}
	
	public void render(GameScreen screen, int changeX, int changeY ) {
		if ( this.isVisible() ) {
			if (!this.isOnlyText()) {
				float[] colorBorder = getColorBorder();
				if (!this.isSelect()) {
					colorBorder = Constants.COLOR_GRAY_BRIGHT;
				}
				
				int rem = 0;
				if (getStroke() > 1) {
					rem = getStroke()/2;
				}
				Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
				screen.getRenderer().begin(ShapeType.Filled);
				screen.getRenderer().setColor(getColor()[0], getColor()[1], getColor()[2], getColor()[3]);
				screen.getRenderer().roundedRect(this.getX() + rem + changeX, this.getY() + rem + changeY, getWidth(), getHeight(), 3);
				screen.getRenderer().end();
				Gdx.graphics.getGL20().glDisable(GL20.GL_BLEND);
				
				Gdx.gl20.glLineWidth(getStroke());
				screen.getRenderer().begin(ShapeType.Line);
				screen.getRenderer().setColor(colorBorder[0], colorBorder[1], colorBorder[2], 1f);
				if (( this.isBPressed() ) || (isSelect())) {
					screen.getRenderer().setColor(255f/ 255.0f, 0f / 255.0f, 0f / 255.0f, 1f);
				} else if ( this.isBOver() ) {
					screen.getRenderer().setColor(255f/ 255.0f, 255f / 255.0f, 0f / 255.0f, 1f);
				}
				screen.getRenderer().roundedRectLine(this.getX() + rem + changeX, this.getY() + rem + changeY, getWidth(), getHeight(), 3);
				screen.getRenderer().end();
				Gdx.gl20.glLineWidth(1f);
				
				drawString(screen, changeX, changeY, colorBorder);
			}
		}
	}

}
