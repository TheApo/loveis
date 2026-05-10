/*
 * Copyright (c) 2005-2017 Dirk Aporius <dirk.aporius@gmail.com>
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

package org.apogames.love.asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

    private static Texture mirrorTexture;
    public static TextureRegion[][] mirrorTextureRegion;
    public static TextureRegion dpad;
    public static TextureRegion solvedImage;
    public static TextureRegion[][] buttonTextureRegion;

	public static BitmapFont font40;
	public static BitmapFont font20;
	public static BitmapFont font15;
	public static BitmapFont font25;
	public static BitmapFont font30;

    public static void load() {
    	mirrorTexture = new Texture(Gdx.files.internal("mirror_26.png"));
    	mirrorTexture.setFilter(TextureFilter.Linear, TextureFilter.Nearest);
    	
    	mirrorTextureRegion = new TextureRegion[10][16];
    	for (int y = 0; y < mirrorTextureRegion.length; y++) {
    		for (int x = 0; x < mirrorTextureRegion[0].length; x++) {
        		mirrorTextureRegion[y][x] = new TextureRegion(mirrorTexture, x * 26 + 1, y * 26 + 1, 24, 24);
        		mirrorTextureRegion[y][x].flip(false, true);
        	}
    	}
    	
    	dpad = new TextureRegion(mirrorTexture, 0, 261, 74, 73);
    	dpad.flip(false, true);
 
    	solvedImage = new TextureRegion(mirrorTexture, 79, 263, 46, 42);
    	solvedImage.flip(false, true);
 
    	buttonTextureRegion = new TextureRegion[1][4];
		for (int y = 0; y < buttonTextureRegion.length; y++) {
			for (int x = 0; x < buttonTextureRegion[0].length; x++) {
				buttonTextureRegion[y][x] = new TextureRegion(mirrorTexture, x * 60, 354 + y * 65, 60, 65);
				buttonTextureRegion[y][x].flip(false, true);
			}
    	}
		
		font15 = new BitmapFont(Gdx.files.internal("fonts/frutiger15.fnt"), Gdx.files.internal("fonts/frutiger15.png"), true);
		font20 = new BitmapFont(Gdx.files.internal("fonts/frutiger20.fnt"), Gdx.files.internal("fonts/frutiger20.png"), true);
		font25 = new BitmapFont(Gdx.files.internal("fonts/frutiger25.fnt"), Gdx.files.internal("fonts/frutiger25.png"), true);
		font30 = new BitmapFont(Gdx.files.internal("fonts/frutiger30.fnt"), Gdx.files.internal("fonts/frutiger30.png"), true);
		font40 = new BitmapFont(Gdx.files.internal("fonts/frutiger40.fnt"), Gdx.files.internal("fonts/frutiger40.png"), true);
    }

    public static void dispose() {
    	mirrorTexture.dispose();
    	
    	font15.dispose();
    	font20.dispose();
    	font25.dispose();
    	font30.dispose();
    	font40.dispose();
    }

}

