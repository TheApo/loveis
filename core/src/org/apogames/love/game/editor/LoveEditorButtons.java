package org.apogames.love.game.editor;

import org.apogames.love.asset.AssetLoader;
import org.apogames.love.backend.GameScreen;
import org.apogames.love.entity.ApoButton;

public class LoveEditorButtons extends ApoButton {

	private int textureX;
	private int textureY;
	
	private LoveEditorEnum type;
	
	private int id;
	
	private boolean isSwitched = false;
	
	public LoveEditorButtons(int x, int y, int width, int height, String function, String text, int textureX, int textureY, LoveEditorEnum type) {
		this(x, y, width, height, function, text, textureX, textureY, type, -1);
	}
	
	public LoveEditorButtons(int x, int y, int width, int height, String function, String text, int textureX, int textureY, LoveEditorEnum type, int id) {
		super(x, y, width, height, function, text);
		
		this.textureX = textureX;
		this.textureY = textureY;
		
		this.id = id;
		
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public boolean isSwitched() {
		return isSwitched;
	}

	public void setSwitched(boolean bSwitch) {
		this.isSwitched = bSwitch;
	}

	public int getTextureX() {
		return textureX;
	}

	public int getTextureY() {
		return textureY;
	}

	public LoveEditorEnum getType() {
		return type;
	}
	
	public void render(GameScreen screen, int changeX, int changeY, boolean bShowTextOnly ) {
		if ( this.isVisible() ) {
			renderImage(screen, changeX, changeY);
		}
	}
	
	public void renderImage(GameScreen screen, int changeX, int changeY) {
		screen.spriteBatch.draw(AssetLoader.mirrorTextureRegion[textureY][textureX], this.getX() + changeX, this.getY() + changeY, getWidth(), getHeight());
	}
}
