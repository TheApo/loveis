package org.apogames.love.game.editor;

import org.apogames.love.Constants;
import org.apogames.love.asset.AssetLoader;
import org.apogames.love.backend.DrawString;
import org.apogames.love.backend.GameScreen;
import org.apogames.love.game.love.LoveGame;
import org.apogames.love.game.love.LoveLevelEntityString;

public class LoveEditorButtonsText extends LoveEditorButtons {
	
	private final LoveLevelEntityString text;
	
	public LoveEditorButtonsText(int x, int y, int width, int height, String function, String text, int textureX, int textureY, LoveEditorEnum type, LoveLevelEntityString entityString) {
		super(x, y, width, height, function, text, textureX, textureY, type);
		
		this.text = entityString;
	}

	public LoveLevelEntityString getTextEntity() {
		return text;
	}
	
	public void renderBefore(GameScreen screen, int changeX, int changeY) {
		if ( this.isVisible() ) {
			renderText(screen, getX(), getY(), true);
		}
	}

	public void render(GameScreen screen, int changeX, int changeY, boolean bShowTextOnly ) {
		if ( this.isVisible() ) {
			renderText(screen, getX(), getY(), false);
		}
	}
	
    private void renderText(GameScreen screen, float changeX, float changeY, boolean onlyBackground) {
    	if (this.text != null) {
    		String myText = this.text.getText();
    		float[] color = this.text.getColor();
    		if (onlyBackground) {
	    		if ((this.text.isObjekt()) && (!this.text.isPraedikat()) && (!this.text.isSubjekt())) {
	                screen.getRenderer().setColor(color[0], color[1], color[2], 0.5f);
	                screen.getRenderer().roundedRect(changeX, changeY, LoveGame.TILE_SIZE * LoveGame.SCALE, LoveGame.TILE_SIZE * LoveGame.SCALE, 5);
	    		} else if (!this.text.getText().equals("IS")){
	    			screen.getRenderer().setColor(Constants.COLOR_WHITE[0], Constants.COLOR_WHITE[1], Constants.COLOR_WHITE[2], 0.4f);
	                screen.getRenderer().roundedRect(changeX, changeY, LoveGame.TILE_SIZE * LoveGame.SCALE, LoveGame.TILE_SIZE * LoveGame.SCALE, 5);
	    		}
    		} else {
    			if ((this.text.isObjekt()) && (!this.text.isPraedikat()) && (!this.text.isSubjekt())) {
    				color = Constants.COLOR_BLACK;
    			}
	    		if (myText.length() == 2) {
	    			screen.drawString(myText, changeX + LoveGame.TILE_SIZE * LoveGame.SCALE/2, changeY + 4 * LoveGame.SCALE, color, AssetLoader.font40, DrawString.MIDDLE, false, false);
	    		} else if (myText.length() == 3) {
	    			screen.drawString(myText, changeX + LoveGame.TILE_SIZE * LoveGame.SCALE/2, changeY + 7 * LoveGame.SCALE, color, AssetLoader.font20, DrawString.MIDDLE, false, false);
	    		} else if (myText.length() == 4) {
	    			String first = myText.substring(0, 2);
	    			screen.drawString(first, changeX + LoveGame.TILE_SIZE * LoveGame.SCALE/2, changeY + 2 * LoveGame.SCALE, color, AssetLoader.font20, DrawString.MIDDLE, false, false);
	    			
	    			String second = myText.substring(2, 4);
	    			screen.drawString(second, changeX + LoveGame.TILE_SIZE * LoveGame.SCALE/2, changeY + LoveGame.TILE_SIZE * LoveGame.SCALE / 2 + 2 * LoveGame.SCALE, color, AssetLoader.font20, DrawString.MIDDLE, false, false);
	    		} else if (myText.length() == 5) {
	    			String first = myText.substring(0, 2);
	    			screen.drawString(first, changeX + LoveGame.TILE_SIZE * LoveGame.SCALE/2, changeY + 2 * LoveGame.SCALE, color, AssetLoader.font20, DrawString.MIDDLE, false, false);
	    			
	    			String second = myText.substring(2, 5);
	    			screen.drawString(second, changeX + LoveGame.TILE_SIZE * LoveGame.SCALE/2, changeY + LoveGame.TILE_SIZE * LoveGame.SCALE / 2 + 2 * LoveGame.SCALE, color, AssetLoader.font20, DrawString.MIDDLE, false, false);
	    		} else if (myText.length() < 8) {
	    			String first = myText.substring(0, 3);
	    			screen.drawString(first, changeX + LoveGame.TILE_SIZE * LoveGame.SCALE/2, changeY + 2 * LoveGame.SCALE, color, AssetLoader.font20, DrawString.MIDDLE, false, false);
	    			
	    			String second = myText.substring(3, myText.length());
	    			screen.drawString(second, changeX + LoveGame.TILE_SIZE * LoveGame.SCALE/2, changeY + LoveGame.TILE_SIZE * LoveGame.SCALE / 2 + 2 * LoveGame.SCALE, color, AssetLoader.font15, DrawString.MIDDLE, false, false);
	    		} else {
	    			int half = myText.length() / 2;
	    			String first = myText.substring(0, half);
	    			screen.drawString(first, changeX + LoveGame.TILE_SIZE * LoveGame.SCALE/2, changeY + 2 * LoveGame.SCALE, color, AssetLoader.font15, DrawString.MIDDLE, false, false);
	    			
	    			String second = myText.substring(half, myText.length());
	    			screen.drawString(second, changeX + LoveGame.TILE_SIZE * LoveGame.SCALE/2, changeY + LoveGame.TILE_SIZE * LoveGame.SCALE / 2 + 2 * LoveGame.SCALE, color, AssetLoader.font15, DrawString.MIDDLE, false, false);
	    		}
    		}
    	}
    }
}
