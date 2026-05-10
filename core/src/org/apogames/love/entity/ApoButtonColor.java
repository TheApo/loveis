package org.apogames.love.entity;

import org.apogames.love.backend.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class ApoButtonColor extends ApoButton {

	private float[] color;
	private float[] colorBorder;
	private float[] colorBorderSolved;
	
	public ApoButtonColor(int x, int y, int width, int height, String function, float[] color, float[] colorBorder) {
		this(x, y, width, height, function, "", color, colorBorder);
	}
	
	public ApoButtonColor(int x, int y, int width, int height, String function, String text, float[] color, float[] colorBorder) {
		super(x, y, width, height, function, text);
		this.color = color;
		this.colorBorder = colorBorder;
		this.colorBorderSolved = new float[] {colorBorder[0]+0.1f, colorBorder[1]+0.1f, colorBorder[2]+0.1f, 1f};
	}

	public float[] getColorBorderSolved() {
		return colorBorderSolved;
	}

	public void setColorBorderSolved(float[] colorBorderSolved) {
		this.colorBorderSolved = colorBorderSolved;
	}

	public float[] getColor() {
		return color;
	}

	public float[] getColorBorder() {
		return colorBorder;
	}

	/**
	 * malt den Button an die Stelle getX() + changeX und getY() + changeY hin
	 * @param changeX: Verschiebung in x-Richtung
	 * @param changeY: Verschiebung in y-Richtung
	 */
	public void render(GameScreen screen, int changeX, int changeY ) {
		if ( this.isVisible() ) {
			if (!this.isOnlyText()) {
				int rem = 0;
				if (getStroke() > 1) {
					rem = getStroke()/2;
				}
				Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
				screen.getRenderer().begin(ShapeType.Filled);
				screen.getRenderer().setColor(color[0], color[1], color[2], color[3]);
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
				
				if (this.isSolved()) {
					if (getSolvedImage() != null) {
						screen.spriteBatch.begin();
						screen.spriteBatch.enableBlending();
						renderSolvedImage(screen, changeX, changeY);
						screen.spriteBatch.end();
						changeY -= getHeight()/6;
					}
				}
				
				if (getImage() != null) {
					screen.spriteBatch.begin();
					screen.spriteBatch.enableBlending();
					renderImage(screen, changeX, changeY);
					screen.spriteBatch.end();
				} else {
					if (isSolved()) {
						drawString(screen, changeX, changeY, colorBorderSolved);
					} else {
						drawString(screen, changeX, changeY, colorBorder);
					}
				}
			}
		}
	}
	
	public void renderSolvedImage(GameScreen screen, int changeX, int changeY) {
		float width = getWidth()/3;
		float height = getHeight()/3;
		screen.spriteBatch.draw(this.getSolvedImage(), this.getX() + getWidth()/2 - width/2 + changeX, this.getY() + getHeight() - height - 2 + changeY, width, height);
	}
}
