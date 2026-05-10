package org.apogames.love.game;

import java.util.ArrayList;

import org.apogames.love.Constants;
import org.apogames.love.backend.GameProperties;
import org.apogames.love.backend.GameScreen;
import org.apogames.love.backend.ScreenModel;
import org.apogames.love.backend.io.IOOnlineLibgdx;
import org.apogames.love.game.editor.LoveEditor;
import org.apogames.love.game.love.LoveGame;
import org.apogames.love.game.love.LoveProperties;
import org.apogames.love.game.menu.LoveMenu;
import org.apogames.love.game.options.LoveOptions;
import org.apogames.love.game.userlevels.LoveUserlevels;
import org.apogames.love.game.userlevels.UserlevelsHelp;

import com.badlogic.gdx.Gdx;

public class MainPanel extends GameScreen {

    private final static int WAIT_TIME_NEXT_RELOAD = 10000;
    private final static int MAX_TRIES_RELOAD = 3;

    private float curNextReload = 0;
    private int curReloadTried = 0;

    private LoveGame loveGame;
    private LoveEditor loveEditor;
    private LoveUserlevels loveUserlevels;
    private LoveOptions loveOptions;
    private LoveMenu loveMenu;

    private IOOnlineLibgdx online;

    private GameProperties gameProperties;

    private float scale = 1;

    private int minID = 0;

    private ArrayList<String> solvedLevels;
    private ArrayList<String> votedLevels;

    public MainPanel() {
        super();
        if ((this.getButtons() == null) || (this.getButtons().size() <= 0)) {
            ButtonProvider button = new ButtonProvider(this);
            button.init();
        }

        this.solvedLevels = new ArrayList<String>();
        this.votedLevels = new ArrayList<String>();
        
        if (this.online == null) {
            this.online = new IOOnlineLibgdx(this);
        }
        if (this.loveUserlevels == null) {
            this.loveUserlevels = new LoveUserlevels(this);
        }
        if (this.loveGame == null) {
            this.loveGame = new LoveGame(this);
        }
        
        if (this.loveEditor == null) {
            this.loveEditor = new LoveEditor(this);
        }
        if (this.loveOptions == null) {
            this.loveOptions = new LoveOptions(this);
        }
        if (this.loveMenu == null) {
            this.loveMenu = new LoveMenu(this);
        }

        this.gameProperties = new LoveProperties(this);

        this.loadProperties();
        this.loadLevel();
        this.changeToMenu();
    }
    
    public void loadProperties() {
    	gameProperties.readLevel();
        updateLevelChooser();
    }
    
    public ArrayList<String> getVotedLevels() {
		return votedLevels;
	}

	public ArrayList<String> getSolvedLevels() {
		return solvedLevels;
	}
    
    public void addVotedLevel(final String votedLevel) {
    	if (votedLevels.contains(votedLevel)) {
    		return;
    	}
    	this.votedLevels.add(votedLevel);
    	this.getGameProperties().writeLevel();
    }
	
    public void addSolvedLevel(final String solvedLevel) {
    	if (solvedLevels.contains(solvedLevel)) {
    		return;
    	}
    	this.solvedLevels.add(solvedLevel);
    	this.getGameProperties().writeLevel();
    }

    public int getMinID() {
        return minID;
    }

    public void setMinID(int minID) {
        this.minID = minID;
    }

    public GameProperties getGameProperties() {
		return gameProperties;
	}

	public void loadLevel() {
        try {
            this.online.loadLevel(minID);
        } catch (Exception ex) {
        }
    }
    
    public void updateLevelChooser() {
        this.setMinID(this.online.getMinID());
        if (this.loveUserlevels != null) {
        	this.loveUserlevels.getModelButtons().clear();
        	this.loveUserlevels.setMenuButtons();
        }
        this.gameProperties.writeLevel();
    }

    public void tryAgainLoadLevel() {
        if (this.curReloadTried < MAX_TRIES_RELOAD) {
            this.curNextReload = WAIT_TIME_NEXT_RELOAD;
        }
    }

    public void saveLevel(final String level) {
        this.online.saveLevel(level);
    }
    
    public void saveLevelCount(final String level) {
        this.online.saveLevelCount(level);
    }
    
    public void setLanguage(String language) {
    	Constants.REGION = language;
    	Constants.setLanguage(language);
    	this.loveOptions.setLanguage(language);
    }
    
    public boolean saveLevelRating(final String level, final int stars, final int fun, final int easy, final int hard, final int creative) {
    	return this.online.saveLevelRating(level, stars, fun, easy, hard, creative);
    }
    
    public ArrayList<UserlevelsHelp> getUserlevels() {
    	return this.online.getUserlevels();
    }
    
    public final void changeToLoveUserlevels() {
    	changeModel(loveUserlevels);
    }
    
    public final void changeToLoveOptions() {
    	changeModel(loveOptions);
    }
    
    public final void changeToLoveGame(String level, boolean isEditor) {
        changeModel(loveGame);
        
        loveGame.readAndCreateNewLevel(level, isEditor);
    }
    
    public final void changeToLoveGame() {
    	changeModel(loveGame);
    }
    
    public final void changeToEditor(boolean isLevelSolved) {
    	loveEditor.setLevelSolved(isLevelSolved);
        changeModel(loveEditor);
    }
    
    public final void changeToMenu() {
        changeModel(loveMenu);
    }
    
    public final void quitGame() {
        Gdx.app.exit();
    }

    private void changeModel(final ScreenModel model) {
        if (this.model != null) {
            this.model.dispose();
        }

        this.model = model;

        this.setButtonsInvisible();
        this.model.setNeededButtonsVisible();
        this.model.init();
    }
    
    public final void setButtonsInvisible() {
    	for (int i = 0; i < this.getButtons().size(); i++) {
            this.getButtons().get(i).setVisible(false);
        }
    }

    public float getScale() {
        return scale;
    }

    public void think(final float delta) {
        super.think(delta);
        if (model != null) {
        	model.think(delta);
        }
        if (curNextReload > 0) {
            curNextReload -= delta;
            if (curNextReload <= 0) {
                loadLevel();
                curReloadTried += 1;
            }
        }
    }

    public void render(float delta) {
        super.render(delta);

        if (model != null) {
            model.render();
            model.drawOverlay();
        }
    }

}
