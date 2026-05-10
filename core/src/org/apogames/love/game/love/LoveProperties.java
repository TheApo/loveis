package org.apogames.love.game.love;

import org.apogames.love.backend.GameProperties;
import org.apogames.love.game.MainPanel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class LoveProperties extends GameProperties {
	
	public LoveProperties(MainPanel mainPanel) {
		super(mainPanel);
	}

	@Override
	public Preferences getPreferences() {
		return Gdx.app.getPreferences("LoveProperties");
	}

}
