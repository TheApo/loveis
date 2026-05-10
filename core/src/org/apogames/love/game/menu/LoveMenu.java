package org.apogames.love.game.menu;

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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class LoveMenu extends SequentiallyThinkingScreenModel {

    private boolean[] keys = new boolean[256];
    private boolean[] keysNeedRefresh = new boolean[256];
    
    public static final String FUNCTION_GAME = "love_menu_game";
    public static final String FUNCTION_USERLEVELS = "love_menu_userlevels";
    public static final String FUNCTION_EDITOR = "love_menu_editor";
    public static final String FUNCTION_OPTIONS = "love_menu_options";
    
    private LoveLevel level;
    
    private boolean isUserlevel = false;
    
	public LoveMenu(MainPanel mainPanel) {
		super(mainPanel);
		
		setMenuButtons();
    }
	
    public void setMenuButtons() {
    	if (getModelButtons().size() <= 0) {
    		BitmapFont font = AssetLoader.font40;
			int width = 180 * LoveGame.SCALE;
			int height = 45 * LoveGame.SCALE;
    		
    		String text = "GAME";
			String function = FUNCTION_GAME;
			int x = LoveGame.WIDTH * LoveGame.SCALE / 2 - width / 2;
			int y = LoveGame.TILE_SIZE * LoveGame.SCALE * 7; 
			ApoButton button = new ApoButtonColor(x, y, width, height, function, text, LoveGame.COLOR_BUTTON_BRIGHT, LoveGame.COLOR_BUTTON);
			button.setStroke(3);
			button.setFont(font);
			getModelButtons().add(button);
			
			text = "USERLEVELS";
			function = FUNCTION_USERLEVELS;
			x = LoveGame.WIDTH * LoveGame.SCALE / 2 - width / 2;
			y = (int)(LoveGame.TILE_SIZE * LoveGame.SCALE * 9.5f); 
			button = new ApoButtonColor(x, y, width, height, function, text, LoveGame.COLOR_BUTTON_BRIGHT, LoveGame.COLOR_BUTTON);
			button.setStroke(3);
			button.setFont(font);
			getModelButtons().add(button);
			
			text = "EDITOR";
			function = FUNCTION_EDITOR;
			x = LoveGame.WIDTH * LoveGame.SCALE / 2 - width / 2;
			y = (int)(LoveGame.TILE_SIZE * LoveGame.SCALE * 12f); 
			button = new ApoButtonColor(x, y, width, height, function, text, LoveGame.COLOR_BUTTON_BRIGHT, LoveGame.COLOR_BUTTON);
			button.setStroke(3);
			button.setFont(font);
			getModelButtons().add(button);

			text = "OPTIONS";
			function = FUNCTION_OPTIONS;
			x = LoveGame.WIDTH * LoveGame.SCALE / 2 -  width / 2;
			y = (int)(LoveGame.TILE_SIZE * LoveGame.SCALE * 14.5f); 
			button = new ApoButtonColor(x, y, width, height, function, text, LoveGame.COLOR_BUTTON_BRIGHT, LoveGame.COLOR_BUTTON);
			button.setStroke(3);
			button.setFont(font);
			getModelButtons().add(button);

			text = "X";
			function = FUNCTION_EXIT;
			width = 42 * LoveGame.SCALE;
			height = 42 * LoveGame.SCALE;
			x = LoveGame.WIDTH * LoveGame.SCALE - width - 3 * LoveGame.SCALE;
			y = LoveGame.HEIGHT * LoveGame.SCALE - height - 3 * LoveGame.SCALE;
			button = new ApoButtonColor(x, y, width, height, function, text, LoveGame.COLOR_BUTTON_BRIGHT, LoveGame.COLOR_BUTTON);
			button.setStroke(1);
			button.setFont(font);
			getModelButtons().add(button);

    	}
    }
    
    public void setMenuButtonVisible(final boolean visible) {
    	super.setMenuButtonVisible(visible);
    	
    	checkIfUserlevelsButtonShouldBeVisible();
    	if (Constants.IS_HTML) {
    		getApoButtonForFunction(FUNCTION_EXIT).setVisible(false);
    	}
    }

	private void checkIfUserlevelsButtonShouldBeVisible() {
		if (this.isUserlevel) {
			return;
		}
		ApoButton apoButtonForFunction = getApoButtonForFunction(FUNCTION_USERLEVELS);
    	if (this.getMainPanel().getUserlevels().size() <= 0) {
    		apoButtonForFunction.setVisible(false);
    	} else {
    		isUserlevel = true;
    		apoButtonForFunction.setVisible(true);
    	}
	}
    
	@Override
	public void init() {
		Constants.COLOR_CLEAR = LoveGame.COLOR_BACKGROUND;
        this.getMainPanel().resetSize(LoveGame.WIDTH * LoveGame.SCALE, LoveGame.HEIGHT * LoveGame.SCALE);
        
        setMyMenu();
	}
	
	public void readAndCreateNewLevel(boolean bMenu) {
		String curString = LoveLevels.MENU;
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
        } else if (function.equals(FUNCTION_GAME)) {
            this.getMainPanel().changeToLoveGame();
        } else if (function.equals(FUNCTION_USERLEVELS)) {
            this.getMainPanel().changeToLoveUserlevels();
        } else if (function.equals(FUNCTION_OPTIONS)) {
            this.getMainPanel().changeToLoveOptions();
        } else if (function.equals(FUNCTION_EDITOR)) {
            this.getMainPanel().changeToEditor(false);
        }
    }
    
    @Override
    public void mouseButtonReleased(int x, int y, boolean isRightButton) {
        super.mouseButtonReleased(x, y, isRightButton);
        if (state == STATE_MENU) {
			return;
		}
    }
    
    protected void quitMenu() {
    	if (!Constants.IS_HTML) {
    		super.quitMenu();
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
		checkIfUserlevelsButtonShouldBeVisible();
		
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
    	renderBackground((LoveGame.WIDTH / 3 * LoveGame.SCALE) / 2, 6.5f * LoveGame.TILE_SIZE * LoveGame.SCALE - 5 * LoveGame.SCALE, LoveGame.WIDTH * 2 / 3 * LoveGame.SCALE, LoveGame.HEIGHT * LoveGame.SCALE - LoveGame.SCALE * LoveGame.TILE_SIZE * 14.2f, 0.75f);
    	
    	String s = Constants.PROGRAM_NAME;
    	this.getMainPanel().drawString(s, (LoveGame.WIDTH * LoveGame.SCALE) / 2, startY * 2, LoveGame.COLOR_DARK, AssetLoader.font30, true);
    	
    	s = Localization.getInstance().get("love_text");
    	this.getMainPanel().drawString(s, (LoveGame.WIDTH * LoveGame.SCALE) / 2, startY * 2 + (LoveGame.TILE_SIZE * 1.05f) * LoveGame.SCALE, LoveGame.COLOR_DARK, AssetLoader.font25, true);
    	
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
