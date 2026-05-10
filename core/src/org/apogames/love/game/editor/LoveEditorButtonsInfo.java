package org.apogames.love.game.editor;

import org.apogames.love.Constants;
import org.apogames.love.asset.AssetLoader;
import org.apogames.love.backend.DrawString;
import org.apogames.love.backend.GameScreen;
import org.apogames.love.game.love.LoveGame;

public class LoveEditorButtonsInfo extends LoveEditorButtons {

	public LoveEditorButtonsInfo(int x, int y, int width, int height, String function, String text) {
		super(x, y, width, height, function, text, -1, -1, LoveEditorEnum.INFO);
	}
	
	public void render(GameScreen screen, int changeX, int changeY, boolean bShowTextOnly ) {
		if ( this.isVisible() ) {
			screen.drawString(getText(), getX() + getWidth()/2, getY() + 4 * LoveGame.SCALE, Constants.COLOR_WHITE, AssetLoader.font40, DrawString.MIDDLE, false, false);
		}
	}

}
