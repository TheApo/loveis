package org.apogames.love.game.editor;

import org.apogames.love.Constants;
import org.apogames.love.backend.GameScreen;
import org.apogames.love.game.love.LoveGame;

public class LoveEditorButtonsSwitch extends LoveEditorButtons {

	public LoveEditorButtonsSwitch(int x, int y, int width, int height, String function, String text, int textureX, int textureY, LoveEditorEnum type) {
		super(x, y, width, height, function, text, textureX, textureY, type);
	}
	
	public void renderBefore(GameScreen screen, int changeX, int changeY) {
		if ( this.isVisible() ) {
			renderPlus(screen, getX(), getY());
		}
	}
	
	private void renderPlus(GameScreen screen, float changeX, float changeY) {
		screen.getRenderer().setColor(Constants.COLOR_WHITE[0], Constants.COLOR_WHITE[1], Constants.COLOR_WHITE[2], 1f);
		screen.getRenderer().rect(getX() + 3 * LoveGame.SCALE, getY() + getHeight()/2 - 3f * LoveGame.SCALE, getWidth() - 6 * LoveGame.SCALE, 6 * LoveGame.SCALE);
		if (!isSwitched()) {
			screen.getRenderer().rect(getX() + getWidth()/2 - 3 * LoveGame.SCALE, getY() + 3f * LoveGame.SCALE, 6 * LoveGame.SCALE, getHeight() - 6 * LoveGame.SCALE);	
		}
	}
	
	public void render(GameScreen screen, int changeX, int changeY, boolean bShowTextOnly ) {
		
	}

}
