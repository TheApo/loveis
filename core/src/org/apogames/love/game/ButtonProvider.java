/*
 * Copyright (c) 2005-2013 Dirk Aporius <dirk.aporius@gmail.com>
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.apogames.love.game;

import org.apogames.love.asset.AssetLoader;
import org.apogames.love.backend.SequentiallyThinkingScreenModel;
import org.apogames.love.entity.ApoButton;
import org.apogames.love.entity.ApoButtonColor;
import org.apogames.love.entity.ApoButtonImage;
import org.apogames.love.game.love.LoveGame;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class ButtonProvider {
	
	private final MainPanel game;
	
	public ButtonProvider(MainPanel game) {
		this.game = game;
	}

	public void init() {
		if ((this.game.getButtons() == null) || (this.game.getButtons().size() <= 0)) {
			this.game.getButtons().clear();
			
			String text = "";
			String function = SequentiallyThinkingScreenModel.FUNCTION_MENU;
			int width = 60 * LoveGame.SCALE;
			int height = 65 * LoveGame.SCALE;
			int x = LoveGame.WIDTH * LoveGame.SCALE/2 - width / 2 - width - 10;
			int y = LoveGame.HEIGHT * LoveGame.SCALE/2 + 30 * LoveGame.SCALE;
			ApoButton button = new ApoButtonImage(x, y, width, height, function, text, AssetLoader.buttonTextureRegion[0][1]);
			button.setStroke(5);
			this.game.getButtons().add(button);
			
			text = "";
			function = SequentiallyThinkingScreenModel.FUNCTION_RELOAD;
			width = 60 * LoveGame.SCALE;
			height = 65 * LoveGame.SCALE;
			x = LoveGame.WIDTH * LoveGame.SCALE/2 - width / 2;
			y = LoveGame.HEIGHT * LoveGame.SCALE/2 + 30 * LoveGame.SCALE;
			button = new ApoButtonImage(x, y, width, height, function, text, AssetLoader.buttonTextureRegion[0][2]);
			button.setStroke(5);
			this.game.getButtons().add(button);

			text = "";
			function = SequentiallyThinkingScreenModel.FUNCTION_PLAY;
			width = 60 * LoveGame.SCALE;
			height = 65 * LoveGame.SCALE;
			x = LoveGame.WIDTH * LoveGame.SCALE/2 - width / 2 + width + 10;
			y = LoveGame.HEIGHT * LoveGame.SCALE/2 + 30 * LoveGame.SCALE;
			button = new ApoButtonImage(x, y, width, height, function, text, AssetLoader.buttonTextureRegion[0][0]);
			button.setStroke(5);
			this.game.getButtons().add(button);
			
			BitmapFont font = AssetLoader.font40;
			
			float[] color = new float[4];
			for (int i = 0; i < color.length - 1; i++) {
				color[i] = LoveGame.COLOR_BUTTON_BRIGHT[i];
			}
			color[3] = 1f;
			
			text = "X";
    		function = SequentiallyThinkingScreenModel.FUNCTION_EXIT;
			width = 42 * LoveGame.SCALE;
			height = 42 * LoveGame.SCALE;
			x = LoveGame.WIDTH * LoveGame.SCALE - width - 3 * LoveGame.SCALE;
			y = LoveGame.HEIGHT * LoveGame.SCALE - height - 3 * LoveGame.SCALE;
			button = new ApoButtonColor(x, y, width, height, function, text, color, LoveGame.COLOR_BUTTON);
			button.setStroke(1);
			button.setFont(font);
			this.game.getButtons().add(button);
			
			text = "R";
    		function = SequentiallyThinkingScreenModel.FUNCTION_RESTART;
			width = 42 * LoveGame.SCALE;
			height = 42 * LoveGame.SCALE;
			x = LoveGame.WIDTH * LoveGame.SCALE - width - 3 * LoveGame.SCALE;
			y = (int)(1.5f * LoveGame.TILE_SIZE * LoveGame.SCALE - height / 2);
			button = new ApoButtonColor(x, y, width, height, function, text, color, LoveGame.COLOR_BUTTON);
			button.setStroke(1);
			button.setFont(font);
			this.game.getButtons().add(button);

			text = "U";
    		function = SequentiallyThinkingScreenModel.FUNCTION_UNDO;
			width = 42 * LoveGame.SCALE;
			height = 42 * LoveGame.SCALE;
			x = 3 * LoveGame.SCALE;
			y = (int)(1.5f * LoveGame.TILE_SIZE * LoveGame.SCALE - height / 2);
			button = new ApoButtonColor(x, y, width, height, function, text, color, LoveGame.COLOR_BUTTON);
			button.setStroke(1);
			button.setFont(font);
			this.game.getButtons().add(button);
			
			for (int i = 0; i < this.game.getButtons().size(); i++) {
				this.game.getButtons().get(i).setBOpaque(false);
			}
		}
	}
}
