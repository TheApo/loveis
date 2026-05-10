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

package org.apogames.love.backend;

import org.apogames.love.common.KeyCodes;
import org.apogames.love.game.MainPanel;

import com.badlogic.gdx.utils.Disposable;

public abstract class ScreenModel implements Disposable {

    private MainPanel mainPanel;

    public ScreenModel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public abstract void init();

    public abstract void think(float delta);

    public abstract void render();

    /**
     * Quits the screen. Override this if quitting your screen requires different behaviour.
     */
    protected void quit() {
        getMainPanel().quitGame();
    }

    public void setNeededButtonsVisible() {
    	// Optional hook
    }

    public void keyPressed(int keyCode, char keyCharacter) {
        // Optional hook
    }

    public void keyButtonReleased(int button, char character) {
        for (int exitCode : KeyCodes.EXIT) {
        	if (button == exitCode) {
        		quit();
        		break;
        	}
        	
        }
        // Optional hook
    }

    public void mouseButtonFunction(String function) {
        // Optional hook
    }

    public void mousePressed(int x, int y, boolean isRightButton) {
        // Optional hook
    }

    public void mouseMoved(int x, int y) {
        // Optional hook
    }

    public void mouseDragged(int x, int y, boolean isRightButton) {
        // Optional hook
    }

    public void mouseButtonReleased(int x, int y, boolean isRightButton) {
        // Optional hook
    }

    public void mouseWheelChanged(int changed) {
        // Optional hook
    }

    public void drawOverlay() {
        // Optional hook
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

}
