package org.apogames.love;

import java.util.Locale;

import org.apogames.love.asset.AssetLoader;
import org.apogames.love.backend.Game;
import org.apogames.love.common.Localization;
import org.apogames.love.game.MainPanel;

public class Love extends Game {

	@Override
	public void create () {
		Localization.getInstance().setLocale(Locale.getDefault());
		Constants.REGION = Localization.getInstance().getLocale().getLanguage();
		AssetLoader.load();
		setScreen(new MainPanel());
	}

	@Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }

}
