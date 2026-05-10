package org.apogames.love.backend;

import org.apogames.love.Constants;
import org.apogames.love.game.MainPanel;

import com.badlogic.gdx.Preferences;
import org.apogames.love.game.userlevels.UserlevelsHelp;

public abstract class GameProperties {

	private final String LEVEL_SIZE = "size";
	private final String VOTED_SIZE = "votedSize";
	private final String USERLEVELS_SIZE = "userlevelsSize";
	private final String MAX_ID = "maxID";
	private final String LANGUAGE = "language";
	
	private Preferences pref;
	
	private final MainPanel mainPanel;
	
	public GameProperties(final MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		
		this.pref = getPreferences();
	}
	
	public MainPanel getMainPanel() {
		return mainPanel;
	}

	public Preferences getPref() {
		return pref;
	}

	public abstract Preferences getPreferences();
	
	public void writeLevel() {
		int size = getMainPanel().getSolvedLevels().size();
		getPref().putInteger(LEVEL_SIZE, size);
		for (int i = 0; i < size; i++) {
			getPref().putString(String.valueOf(i), getMainPanel().getSolvedLevels().get(i));
		}
		
		size = getMainPanel().getVotedLevels().size();
		getPref().putInteger(VOTED_SIZE, size);
		for (int i = 0; i < size; i++) {
			getPref().putString(VOTED_SIZE+i, getMainPanel().getVotedLevels().get(i));
		}
		
		getPref().putString(LANGUAGE, Constants.REGION);

		getPref().putInteger(MAX_ID, getMainPanel().getMinID());

		size = getMainPanel().getUserlevels().size();
		getPref().putInteger(USERLEVELS_SIZE, size);
		for (int i = 0; i < size; i++) {
			UserlevelsHelp help = getMainPanel().getUserlevels().get(i);

			getPref().putString(USERLEVELS_SIZE+"level"+i, help.getLevel());
			getPref().putString(USERLEVELS_SIZE+"user"+i, help.getUser());
			getPref().putInteger(USERLEVELS_SIZE+"all"+i, help.getAll());
			getPref().putInteger(USERLEVELS_SIZE+"count"+i, help.getCount());
			getPref().putInteger(USERLEVELS_SIZE+"creative"+i, help.getCreative());
			getPref().putInteger(USERLEVELS_SIZE+"easy"+i, help.getEasy());
			getPref().putInteger(USERLEVELS_SIZE+"fun"+i, help.getFun());
			getPref().putInteger(USERLEVELS_SIZE+"hard"+i, help.getHard());
			getPref().putInteger(USERLEVELS_SIZE+"id"+i, help.getId());
			getPref().putFloat(USERLEVELS_SIZE+"curTime"+i, (float)help.getCurTime());
			getPref().putFloat(USERLEVELS_SIZE+"time"+i, (float)help.getTime());

		}

		getPref().flush();
	}
	
	public void readLevel() {
		int size = getPref().getInteger(LEVEL_SIZE, 0);
		for (int i = 0; i < size; i++) {
			getMainPanel().getSolvedLevels().add(getPref().getString(String.valueOf(i)));
		}
		
		size = getPref().getInteger(VOTED_SIZE, 0);
		for (int i = 0; i < size; i++) {
			getMainPanel().getVotedLevels().add(getPref().getString(VOTED_SIZE+i));
		}
		
		getMainPanel().setLanguage(getPref().getString(LANGUAGE, "en"));

		getMainPanel().setMinID(getPref().getInteger(MAX_ID, 0));

		getMainPanel().getUserlevels().clear();
		size = getPref().getInteger(USERLEVELS_SIZE, 0);
		for (int i = 0; i < size; i++) {
			UserlevelsHelp help = new UserlevelsHelp();
			help.setLevel(getPref().getString(USERLEVELS_SIZE+"level"+i));
			help.setUser(getPref().getString(USERLEVELS_SIZE+"user"+i));
			help.setAll(getPref().getInteger(USERLEVELS_SIZE+"all"+i));
			help.setCount(getPref().getInteger(USERLEVELS_SIZE+"count"+i));
			help.setCreative(getPref().getInteger(USERLEVELS_SIZE+"creative"+i));
			help.setEasy(getPref().getInteger(USERLEVELS_SIZE+"easy"+i));
			help.setFun(getPref().getInteger(USERLEVELS_SIZE+"fun"+i));
			help.setHard(getPref().getInteger(USERLEVELS_SIZE+"hard"+i));
			help.setId(getPref().getInteger(USERLEVELS_SIZE+"id"+i));
			help.setCurTime(getPref().getFloat(USERLEVELS_SIZE+"curTime"+i));
			help.setTime(getPref().getFloat(USERLEVELS_SIZE+"time"+i));

			getMainPanel().getUserlevels().add(help);
		}
	}
}
