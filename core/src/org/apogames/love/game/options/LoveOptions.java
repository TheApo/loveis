package org.apogames.love.game.options;

import org.apogames.love.Constants;
import org.apogames.love.asset.AssetLoader;
import org.apogames.love.backend.SequentiallyThinkingScreenModel;
import org.apogames.love.common.Localization;
import org.apogames.love.entity.ApoButton;
import org.apogames.love.entity.ApoButtonColor;
import org.apogames.love.game.MainPanel;
import org.apogames.love.game.love.LoveGame;
import org.apogames.love.game.love.LoveLevel;
import org.apogames.love.game.love.LoveLevels;
import org.apogames.love.game.userlevels.LoveUserlevelsButtons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class LoveOptions extends SequentiallyThinkingScreenModel {

    private boolean[] keys = new boolean[256];
    private boolean[] keysNeedRefresh = new boolean[256];
    
    private final String FUNCTION_LANGUAGE_ENG = "function_language_eng";
    private final String FUNCTION_LANGUAGE_DE = "function_language_de";
    
    private LoveLevel level;
    
	public LoveOptions(MainPanel mainPanel) {
		super(mainPanel);
		
		setMenuButtons();
    }
	
    public void setMenuButtons() {
    	if (getModelButtons().size() <= 0) {
    		BitmapFont font = AssetLoader.font40;
			int width = 180 * LoveGame.SCALE;
			int height = 45 * LoveGame.SCALE;

			String text = "X";
			String function = FUNCTION_EXIT;
			width = 42 * LoveGame.SCALE;
			height = 42 * LoveGame.SCALE;
			int x = LoveGame.WIDTH * LoveGame.SCALE - width - 3 * LoveGame.SCALE;
			int y = LoveGame.HEIGHT * LoveGame.SCALE - height - 3 * LoveGame.SCALE;
			ApoButton button = new ApoButtonColor(x, y, width, height, function, text, LoveGame.COLOR_BUTTON_BRIGHT, LoveGame.COLOR_BUTTON);
			button.setStroke(1);
			button.setFont(font);
			getModelButtons().add(button);

			font = AssetLoader.font30;
			text = "english";
    		function = FUNCTION_LANGUAGE_ENG;
    		width = (int)(3f * LoveGame.TILE_SIZE * LoveGame.SCALE);
    		height = (int)(1f * LoveGame.TILE_SIZE * LoveGame.SCALE);
			y = (int)(11.5f * LoveGame.TILE_SIZE * LoveGame.SCALE);
			
    		x = (int)((LoveGame.WIDTH / 2 * LoveGame.SCALE) + 5f * LoveGame.SCALE);
			button = new LoveUserlevelsButtons(x, y, width, height, function, text, Constants.COLOR_WHITE, LoveGame.COLOR_BUTTON);
			button.setStroke(1);
			button.setFont(font);
			getModelButtons().add(button);

			text = "deutsch";
    		function = FUNCTION_LANGUAGE_DE;
    		width = (int)(3f * LoveGame.TILE_SIZE * LoveGame.SCALE);
    		height = (int)(1f * LoveGame.TILE_SIZE * LoveGame.SCALE);
			y = (int)(12.8f * LoveGame.TILE_SIZE * LoveGame.SCALE);
			
    		x = (int)((LoveGame.WIDTH / 2 * LoveGame.SCALE) + 5f * LoveGame.SCALE);
			button = new LoveUserlevelsButtons(x, y, width, height, function, text, Constants.COLOR_WHITE, LoveGame.COLOR_BUTTON);
			button.setStroke(1);
			button.setFont(font);
			getModelButtons().add(button);
    	}
    }
    
    public void setLanguage(String language) {
    	if (language.equals("en")) {
    		getApoButtonForFunction(FUNCTION_LANGUAGE_ENG).setSelect(true);
    		getApoButtonForFunction(FUNCTION_LANGUAGE_DE).setSelect(false);
    	} else {
    		getApoButtonForFunction(FUNCTION_LANGUAGE_ENG).setSelect(false);
    		getApoButtonForFunction(FUNCTION_LANGUAGE_DE).setSelect(true);
    	}
    }
    
	protected void quitMenu() {
		setLevelWinButtonVisible(false);
		initAnalyseButtons();
		this.getMainPanel().changeToMenu();
	}
    
	@Override
	public void init() {
        setMyMenu();
	}
	
	public void readAndCreateNewLevel(boolean bMenu) {
		String curString = LoveLevels.OPTIONS;
        this.level = new LoveLevel(curString);
        
        keys = new boolean[256];
        keysNeedRefresh = new boolean[256];
        
        super.setLevelWinButtonVisible(false);
        setNeededButtonsVisible();
        
        state = 1;
	}
	
	@Override
	protected int getHeight() {
		return LoveGame.HEIGHT * LoveGame.SCALE;
	}
	
	@Override
    protected float getScale() {
    	return LoveGame.SCALE;
    }
	
    public void setNeededButtonsVisible() {
    	if (Constants.IS_ANDROID) {
    		setButtonsVisible(true);
    	}
    }
	
    public void keyPressed(int keyCode, char keyCharacter) {
        super.keyPressed(keyCode, keyCharacter);
        keys[keyCode] = true;
    }

    @Override
    public void keyButtonReleased(int keyCode, char character) {
        super.keyButtonReleased(keyCode, character);
        keys[keyCode] = false;
        keysNeedRefresh[keyCode] = false;
    }
	
    @Override
    public void mouseButtonFunction(String function) {
        super.mouseButtonFunction(function);
        if (function.equals(FUNCTION_EXIT)) {
            quit();
        } else if (function.equals(FUNCTION_LANGUAGE_ENG)) {
            getMainPanel().setLanguage("en");
            getMainPanel().getGameProperties().writeLevel();
        } else if (function.equals(FUNCTION_LANGUAGE_DE)) {
            getMainPanel().setLanguage("de");
			getMainPanel().getGameProperties().writeLevel();
        }
    }
    
    @Override
    public void mouseButtonReleased(int x, int y, boolean isRightButton) {
        super.mouseButtonReleased(x, y, isRightButton);
        if (state == STATE_MENU) {
			return;
		}
    }
    
    @Override
    public void mouseDragged(int x, int y, boolean isRightButton) {
        super.mouseDragged(x, y, isRightButton);
    }
    
    @Override
    public void mousePressed(int x, int y, boolean isRightButton) {
        super.mousePressed(x, y, isRightButton);
    }
    
	@Override
	public void dispose() {
		
	}
	
	public void readAndCreateNewLevel() {
    	readAndCreateNewLevel(false);
    }

	@Override
	protected void doThink(float delta) {
		if (state == 0) {
            readAndCreateNewLevel();
		}
		
		this.level.doThink(delta, 0, 0);
	}
	
    @Override
    public void render() {
    	this.level.render(this.getMainPanel());
    	
    	if (state == STATE_MENU) {
    		renderMenu();
    	}
    	
    	for (ApoButton button : this.getMainPanel().getButtons()) {
            button.render(this.getMainPanel(), 0, 0);
        }
    }
    
    public void renderMenu() {
    	int startY = 5 * LoveGame.SCALE;
    	renderBackground((LoveGame.WIDTH / 3 * LoveGame.SCALE) / 2, startY, LoveGame.WIDTH * 2 / 3 * LoveGame.SCALE, LoveGame.SCALE * LoveGame.TILE_SIZE * 2, 0.75f);
    	renderBackground((LoveGame.WIDTH / 3 * LoveGame.SCALE) / 2, 11.2f * LoveGame.TILE_SIZE * LoveGame.SCALE - 5 * LoveGame.SCALE, LoveGame.WIDTH * 2 / 3 * LoveGame.SCALE, LoveGame.HEIGHT * LoveGame.SCALE - LoveGame.SCALE * LoveGame.TILE_SIZE * 21.7f, 0.75f);
    	
    	String s = Constants.PROGRAM_NAME;
    	this.getMainPanel().drawString(s, (LoveGame.WIDTH * LoveGame.SCALE) / 2, startY * 2, LoveGame.COLOR_DARK, AssetLoader.font30, true);
    	
    	s = "Options";
    	this.getMainPanel().drawString(s, (LoveGame.WIDTH * LoveGame.SCALE) / 2, startY * 2 + (LoveGame.TILE_SIZE * 1.05f) * LoveGame.SCALE, LoveGame.COLOR_DARK, AssetLoader.font25, true);
    	
    	s = Localization.getInstance().get("options_language");
    	this.getMainPanel().drawString(s, (LoveGame.WIDTH / 3 * LoveGame.SCALE) / 2 + 10 * LoveGame.SCALE, 11.7f * LoveGame.TILE_SIZE * LoveGame.SCALE, LoveGame.COLOR_DARK, AssetLoader.font30, false);
    	
    	for (ApoButton button : this.getModelButtons()) {
            button.render(this.getMainPanel(), 0, 0);
        }
	}
    
    protected void renderBackground(float x, float y, float width, float height, float alpha) {
    	float stroke = 5f;
    	float rem = stroke/2f;
    	
    	Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
    	this.getMainPanel().getRenderer().begin(ShapeType.Filled);
    	this.getMainPanel().getRenderer().setColor(Constants.COLOR_WHITE[0], Constants.COLOR_WHITE[1], Constants.COLOR_WHITE[2], alpha);
    	this.getMainPanel().getRenderer().roundedRect(x + rem, y + rem, width, height, 3);
    	this.getMainPanel().getRenderer().end();
		Gdx.graphics.getGL20().glDisable(GL20.GL_BLEND);
		
		Gdx.gl20.glLineWidth(3f);
		this.getMainPanel().getRenderer().begin(ShapeType.Line);
		this.getMainPanel().getRenderer().setColor(LoveGame.COLOR_BUTTON[0], LoveGame.COLOR_BUTTON[1], LoveGame.COLOR_BUTTON[2], 1f);
		this.getMainPanel().getRenderer().roundedRectLine(x + rem, y + rem, width, height, 3);
		this.getMainPanel().getRenderer().end();
		Gdx.gl20.glLineWidth(1f);
    }
}