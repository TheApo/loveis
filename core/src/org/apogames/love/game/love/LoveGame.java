package org.apogames.love.game.love;

import org.apogames.love.Constants;
import org.apogames.love.asset.AssetLoader;
import org.apogames.love.backend.DrawString;
import org.apogames.love.backend.GameScreen;
import org.apogames.love.backend.SequentiallyThinkingScreenModel;
import org.apogames.love.common.Localization;
import org.apogames.love.entity.ApoButton;
import org.apogames.love.entity.ApoButtonColor;
import org.apogames.love.entity.ApoButtonColorWithoutBorder;
import org.apogames.love.game.MainPanel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class LoveGame extends SequentiallyThinkingScreenModel {

	private final int MAX_LEVELS_SHOW = 16;
	
    private boolean[] keys = new boolean[256];
    private boolean[] keysNeedRefresh = new boolean[256];
    
    public static final int WIDTH = 480;
    public static final int HEIGHT = 600;

    public static final int TILE_SIZE = 24;
    public static final int SCALE = 2;

    public static final float[] COLOR_BUTTON = new float[]{116f / 255f, 35f / 255f, 35f / 255f, 1f};
	public static final float[] COLOR_BUTTON_SOLVED = new float[]{166f / 255f, 50f / 255f, 50f / 255f, 1f};
    public static final float[] COLOR_BUTTON_BRIGHT = new float[]{240f / 255f, 72f / 255f, 72f / 255f, 1f};
    
    public static final float[] COLOR_BACKGROUND = new float[]{72f / 255f, 152f / 255f, 72f / 255f, 1f};
    public static final float[] COLOR_DARK = new float[]{116f / 255f, 35f / 255f, 35f / 255f, 1f};
    public static final float[] COLOR_DARK_BRIGHT = new float[]{216f / 255f, 135f / 255f, 135f / 255f, 1f};
    public static final float[] COLOR_BLUE_DARK = new float[]{0f / 255f, 45f / 255f, 90f / 255f, 1f};
    public static final float[] COLOR_RED_DARK = new float[]{128f / 255f, 45f / 255f, 45f / 255f, 1f};
    public static final float[] COLOR_YELLOW_DARK = new float[]{92f / 255f, 75f / 255f, 0f / 255f, 1f};
    public static final float[] COLOR_GREEN_DARK = new float[]{19f / 255f, 51f / 255f, 28f / 255f, 1f};
    public static final float[] COLOR_GRAY_DARK = new float[]{32f / 255f, 32f / 255f, 32f / 255f, 1f};
    public static final float[] COLOR_GRAY_BRIGHT = new float[]{200f / 255f, 200f / 255f, 200f / 255f, 1f};
    public static final float[] COLOR_BLUE_BRIGHT = new float[]{10f / 255f, 145f / 255f, 190f / 255f, 1f};
    public static final float[] COLOR_RED_BRIGHT = new float[]{228f / 255f, 145f / 255f, 145f / 255f, 1f};
    public static final float[] COLOR_YELLOW_BRIGHT = new float[]{192f / 255f, 175f / 255f, 0f / 255f, 1f};
    public static final float[] COLOR_GREEN_BRIGHT = new float[]{219f / 255f, 251f / 255f, 228f / 255f, 1f};

	private static final String FUNCTION_RIGHT = "function_right";
	private static final String FUNCTION_RIGHT_LEVEL = "function_right_level";
	private static final String FUNCTION_LEFT = "function_left";
	private static final String FUNCTION_LEFT_LEVEL = "function_left_level";
    
    private int startButtonId = 0;
    
    private LoveLevel level;
    
    private boolean bEditor;
    private String levelString;
    
    private boolean bFirst = true;

    private boolean isLevelSolved = false;
    
	public LoveGame(MainPanel mainPanel) {
		super(mainPanel);
        
        setMenuButtons();
    }

	private void setMenuButtons() {
    	if (getModelButtons().size() <= 0) {
    		int startX = 42 * SCALE;
    		int x = startX;
    		int startY = HEIGHT * SCALE - 8 * TILE_SIZE * SCALE;
    		int y = startY;
    		BitmapFont font = AssetLoader.font40;
			String function = "";
    		for (int i = 0; i < LoveLevels.LEVELS.length; i++) {
    			
    			String text = ""+(i+1);
    			function = text;
    			int width = 45 * SCALE;
    			int height = 45 * SCALE;
				ApoButtonColor button = new ApoButtonColor(x, y, width, height, function, text, COLOR_BUTTON_BRIGHT, COLOR_BUTTON);
				button.setColorBorderSolved(COLOR_BUTTON_SOLVED);
    			button.setSolvedImage(AssetLoader.solvedImage);
    			button.setStroke(3);
    			button.setFont(font);
    			getModelButtons().add(button);
    			
    			x += width + 5 * SCALE;
    			if (x + 10 + width > WIDTH * SCALE) {
    				if (((i+1) % (MAX_LEVELS_SHOW) == 0) && (i > 0)) {
    					x = startX;
    					y = startY;
    				} else {
    					x = startX;
    					y += height + 5 * SCALE;
    				}
    			}
    		}
    		
    		String text = "X";
    		function = SequentiallyThinkingScreenModel.FUNCTION_EXIT;
			int width = 42 * LoveGame.SCALE;
			int height = 42 * LoveGame.SCALE;
			x = LoveGame.WIDTH * LoveGame.SCALE - width - 3 * LoveGame.SCALE;
			y = LoveGame.HEIGHT * LoveGame.SCALE - height - 3 * LoveGame.SCALE;
			ApoButton button = new ApoButtonColor(x, y, width, height, function, text, COLOR_BUTTON_BRIGHT, COLOR_BUTTON);
			button.setStroke(1);
			button.setFont(font);
			getModelButtons().add(button);
			
			width = 35 * LoveGame.SCALE;
			height = 35 * LoveGame.SCALE;
			text = ">";
    		function = FUNCTION_RIGHT;
			x = LoveGame.WIDTH * LoveGame.SCALE - width - 3 * LoveGame.SCALE;
			y = startY + 47 * LoveGame.SCALE - height/2;
			button = new ApoButtonColor(x, y, width, height, function, text, LoveGame.COLOR_BUTTON_BRIGHT, LoveGame.COLOR_BUTTON);
			button.setStroke(1);
			button.setFont(font);
			getModelButtons().add(button);
			
			text = "<";
    		function = FUNCTION_LEFT;
			x = 3 * LoveGame.SCALE;
			y = startY + 47 * LoveGame.SCALE - height/2;
			button = new ApoButtonColor(x, y, width, height, function, text, LoveGame.COLOR_BUTTON_BRIGHT, LoveGame.COLOR_BUTTON);
			button.setStroke(1);
			button.setFont(font);
			getModelButtons().add(button);

			float[] color = new float[] {0f, 0f, 0f, 0f};
			text = ">";
			function = FUNCTION_RIGHT_LEVEL;
			x = LoveGame.WIDTH * 3 * LoveGame.SCALE / 4;
			y = (int)(LoveGame.TILE_SIZE * LoveGame.SCALE / 2 + 8.5f * LoveGame.SCALE);
			button = new ApoButtonColorWithoutBorder(x, y, width, height, function, text, color, LoveGame.COLOR_BUTTON);
			button.setFont(AssetLoader.font40);
			getModelButtons().add(button);

			text = "<";
			function = FUNCTION_LEFT_LEVEL;
			x = LoveGame.WIDTH * LoveGame.SCALE / 4 - width;
			y = (int)(LoveGame.TILE_SIZE * LoveGame.SCALE / 2 + 8.5f * LoveGame.SCALE);
			button = new ApoButtonColorWithoutBorder(x, y, width, height, function, text, color, LoveGame.COLOR_BUTTON);
			button.setFont(AssetLoader.font40);
			getModelButtons().add(button);
    	}
    }

	private void showChangeButtons() {
		if ((state == STATE_MENU) && (!bEditor)) {
			ApoButton apoButtonForFunction = getApoButtonForFunction(FUNCTION_LEFT);
			if (this.startButtonId > 0) {
				apoButtonForFunction.setVisible(true);
			} else {
				apoButtonForFunction.setVisible(false);
			}
			
			apoButtonForFunction = getApoButtonForFunction(FUNCTION_RIGHT);
			if (this.startButtonId + MAX_LEVELS_SHOW < LoveLevels.LEVELS.length) {
				apoButtonForFunction.setVisible(true);
			} else {
				apoButtonForFunction.setVisible(false);
			}
			for (int i = 0; i < LoveLevels.LEVELS.length; i++) {
				if ((i < startButtonId) || (i >= startButtonId + MAX_LEVELS_SHOW)) {
					getModelButtons().get(i).setVisible(false);
				} else {
					getModelButtons().get(i).setVisible(true);
				}
			}

			getApoButtonForFunction(FUNCTION_LEFT_LEVEL).setVisible(false);
			getApoButtonForFunction(FUNCTION_RIGHT_LEVEL).setVisible(false);
		} else {
			getApoButtonForFunction(FUNCTION_LEFT_LEVEL).setVisible(true);
			getApoButtonForFunction(FUNCTION_RIGHT_LEVEL).setVisible(true);
			getApoButtonForFunction(FUNCTION_RIGHT).setVisible(false);
			getApoButtonForFunction(FUNCTION_LEFT).setVisible(false);
		}
	}	
	
	public void setMenuButtonVisible(final boolean visible) {
		super.setMenuButtonVisible(visible);
		showChangeButtons();
	}
	
	@Override
	public void init() {
		super.levels = LoveLevels.LEVELS;
		
    	setMyMenu();
    	
    	if (bFirst) {
    		bFirst = false;
    		if (startButtonId + MAX_LEVELS_SHOW < getMinUnsolvedLevel()) {
    			startButtonId = (getMinUnsolvedLevel() / MAX_LEVELS_SHOW) * MAX_LEVELS_SHOW;
    			showChangeButtons();
    		}
    	}
	}
	
	public void readAndCreateNewLevel(boolean bMenu) {
		if (bEditor) {
			this.level = new LoveLevel(levelString);
			setButtonVisibleForGame(true);
		} else {
	        if (curLevel < 0) {
	            curLevel = levels.length - 1;
	        } else if (curLevel >= levels.length) {
	            curLevel = 0;
	        }
	
	        String curString = levels[curLevel];
	        if (bMenu) {
	        	curString = LoveLevels.LEVELCHOOSER;
	        	setButtonVisibleForGame(false);
	        } else {
	        	setButtonVisibleForGame(true);
	        }
	        
	        this.level = new LoveLevel(curString);

			isLevelSolved = isLevelSolved(curLevel);
		}
        
        keys = new boolean[256];
        keysNeedRefresh = new boolean[256];
        
        super.setLevelWinButtonVisible(false);
        setNeededButtonsVisible();
        
        state = 1;
	}
	
	private void setButtonVisibleForGame(boolean isVisible) {
		getMainPanel().getButtonByFunction(FUNCTION_EXIT).setVisible(isVisible);
		getMainPanel().getButtonByFunction(FUNCTION_RESTART).setVisible(isVisible);
		getMainPanel().getButtonByFunction(FUNCTION_UNDO).setVisible(isVisible);
	}
	
	protected void quitMenu() {
		setLevelWinButtonVisible(false);
		initAnalyseButtons();
		this.getMainPanel().changeToMenu();
	}
	
	@Override
	protected int getHeight() {
		return HEIGHT * SCALE;
	}
	
	@Override
    protected float getScale() {
    	return SCALE * 2.6f;
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
        if (function.equals(SequentiallyThinkingScreenModel.FUNCTION_PLAY)) {
        	if (bEditor) {
				quit();
			} else {
				setNextLevel();
			}
        } else if ((function.equals(SequentiallyThinkingScreenModel.FUNCTION_RELOAD)) ||
        		   (function.equals(SequentiallyThinkingScreenModel.FUNCTION_RESTART))) {
        	if (bEditor) {
				state = 0;
		    	setMenuButtonVisible(false);
        	} else {
        		setLevel(curLevel);
        	}
        } else if (function.equals(SequentiallyThinkingScreenModel.FUNCTION_MENU)) {
            quit();
        } else if (function.equals(SequentiallyThinkingScreenModel.FUNCTION_EXIT)) {
        	quit();
        } else if (function.equals(FUNCTION_LEFT)) {
        	if (startButtonId > 0) {
        		startButtonId -= MAX_LEVELS_SHOW;
        		showChangeButtons();
        	}
        } else if (function.equals(FUNCTION_RIGHT)) {
        	if (startButtonId + MAX_LEVELS_SHOW < LoveLevels.LEVELS.length) {
        		startButtonId += MAX_LEVELS_SHOW;
        		showChangeButtons();
        	}
        } else if (function.equals(SequentiallyThinkingScreenModel.FUNCTION_UNDO)) {
        	level.undoLastStep();
        } else if (function.equals(FUNCTION_LEFT_LEVEL)) {
			setLevel(curLevel - 1);
		} else if (function.equals(FUNCTION_RIGHT_LEVEL)) {
			setLevel(curLevel + 1);
		} else {
        	checkIfLevelButtonIsReleased(function);
        }
    }
    
    public void quit() {
    	if (bEditor) {
    		bEditor = false;
    		boolean isLevelSolved = false;
    		if (state == 2) {
    			isLevelSolved = true;
    		}
    		getMainPanel().changeToEditor(isLevelSolved);
    	} else {
			super.quit();
    	}
    }
    
	private void setNextLevel() {
		setLevel(curLevel + 1);
	}
    
    @Override
    public void mouseButtonReleased(int x, int y, boolean isRightButton) {
        super.mouseButtonReleased(x, y, isRightButton);
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
	
	private void readAndCreateNewLevel() {
    	readAndCreateNewLevel(false);
    }
	
	public void readAndCreateNewLevel(String curLevel, boolean isEditor) {
		bEditor = isEditor;
		this.levelString = curLevel;
    	readAndCreateNewLevel(false);
    }

	@Override
	protected void doThink(float delta) {
		if (state == 0) {
            readAndCreateNewLevel();
		}
		
		int addX = 0;
        if ((keys[Input.Keys.LEFT]) || (keys[Input.Keys.A]) || (isbLeft())) {
            addX = -1;
        } else if ((keys[Input.Keys.RIGHT]) || (keys[Input.Keys.D]) || (isbRight())) {
            addX = 1;
        }
        int addY = 0;
        if (addX == 0) {
            if ((keys[Input.Keys.UP]) || (keys[Input.Keys.W]) || (isbUp())) {
                addY = -1;
            } else if ((keys[Input.Keys.DOWN]) || (keys[Input.Keys.S]) || (isbDown())) {
                addY = 1;
            }
        }
		
        if ((Constants.IS_ANDROID) && (state == STATE_MENU)) {
        	addX = 0;
        	addY = 0;
        }
        this.level.doThink(delta, addX, addY);
		
		if (state == STATE_MENU) {
			if (this.level.getWinState() != LoveWinState.RUNNING) {
				setMyMenu();
			}
			if (keys[Input.Keys.R]) {
				setMyMenu();
	        }
		} else {
			if (state == 1) {
				if (this.level.getWinState() == LoveWinState.WON) {
					state = 2;
		        	super.setLevelWinButtonVisible(true);
		        	setButtonsVisible(false);
		        	if (!bEditor) {
	        			addSolvedLevel(level.getLevelString());
		        		getMainPanel().saveLevelCount(level.getLevelString());
		        	}
				} else if (this.level.getWinState() == LoveWinState.LOST) {
					state = 3;
		        	super.setLevelLostButtonVisible(true);
		        	setButtonsVisible(false);
				} else {
					if ((keys[Input.Keys.U]) && (!keysNeedRefresh[Input.Keys.U])) {
						keysNeedRefresh[Input.Keys.U] = true;
						level.undoLastStep();
			        }
				}
			} else if (state == 2) {
				if ((keys[Input.Keys.SPACE]) || (keys[Input.Keys.ENTER])) {
					if (bEditor) {
						quit();
					} else {
						setNextLevel();
					}
		        }
			} else if (state == 3) {
				if ((keys[Input.Keys.SPACE]) || (keys[Input.Keys.ENTER])) {
					if (bEditor) {
						state = 0;
				    	setMenuButtonVisible(false);
					} else {
						setLevel(curLevel);
					}
		        }
			}
			
			if (bEditor) {
				if (keys[Input.Keys.R]) {
					state = 0;
			    	setMenuButtonVisible(false);
		        }				
			} else {
				if (keys[Input.Keys.N]) {
		            setNextLevel();
		        }
				if (keys[Input.Keys.R]) {
					setLevel(curLevel);
		        }
				if (keys[Input.Keys.P]) {
					setLevel(curLevel - 1);
		        }
			}
		}
	}
	
    @Override
    public void render() {
    	this.level.render(this.getMainPanel(), 0, 3 * TILE_SIZE * SCALE);
    	
    	if (state == STATE_MENU) {
    		renderMenu();
    	} else {
    		renderTutorial(this.getMainPanel());
    		renderDPad();
    	}

    	int start = LoveLevels.LEVELS.length;
    	if (state == STATE_MENU) {
			start = 0;
		}
		for (int i = start; i < getModelButtons().size(); i++) {
			getModelButtons().get(i).render(this.getMainPanel(), 0, 0);
		}
    	for (ApoButton button : this.getMainPanel().getButtons()) {
            button.render(this.getMainPanel(), 0, 0);
        }
    }

    
    private void renderTutorial(GameScreen screen) {
		renderMenuTitle();
    	if (!this.bEditor) {
	    	if (curLevel == 0) {
	    		float x = (WIDTH / 4f * SCALE) / 2f;
	    		float y = TILE_SIZE * 19.8f * SCALE;
	    		
	    		renderBackground(x, y + 2 * SCALE, WIDTH * 3 / 4f * SCALE, 3 * SCALE * TILE_SIZE - 7 * SCALE, 0.75f);
	
	    		screen.spriteBatch.begin();
	    		String[] moveText = Localization.getInstance().getLines("tutorial_0_move_text");
	    		for (int i = 0; i < moveText.length; i++) {
	            	this.getMainPanel().drawString(moveText[i], (WIDTH * SCALE) / 2f, y + 5 * SCALE + i * TILE_SIZE * SCALE, COLOR_DARK, AssetLoader.font25, DrawString.MIDDLE, false, false);
	    		}
	    		screen.spriteBatch.end();
	    	}
			if ((!level.isNoYou()) && (curLevel >= 0) && (curLevel < 20)) {
			String[] tutorialText = Localization.getInstance().getLines("tutorial_text_" + curLevel);
			if (tutorialText[0].length() > 0) {
	    		int size = tutorialText.length;
	    		float x = (WIDTH / 3f * SCALE) / 2f;
	    		float y = HEIGHT * SCALE - TILE_SIZE * size * SCALE + (size) * SCALE;
	    		renderBackground(x, y, WIDTH * 2 / 3f * SCALE,  (size) * SCALE * TILE_SIZE - 3 * size * SCALE, 0.75f);

	    		screen.spriteBatch.begin();
	    		for (int i = 0; i < tutorialText.length; i++) {
	            	this.getMainPanel().drawString(tutorialText[i], (WIDTH * SCALE) / 2f, y + 5 * SCALE + 2 * SCALE + (i * 0.8f) * TILE_SIZE * SCALE, COLOR_DARK, AssetLoader.font25, DrawString.MIDDLE, false, false);
	    		}
	    		screen.spriteBatch.end();
	    	}
	    	}
    	}
		renderNoYou(screen);
    }

	private void renderNoYou(GameScreen screen) {
		if (level.isNoYou()) {
			int size = 2;
			float x = (LoveGame.WIDTH / 4f * LoveGame.SCALE) / 2f;
			float y = LoveGame.HEIGHT * LoveGame.SCALE - LoveGame.TILE_SIZE * size * LoveGame.SCALE + (size) * LoveGame.SCALE;
			renderBackground(x, y, LoveGame.WIDTH * 3 / 4f * LoveGame.SCALE, (size) * LoveGame.SCALE * LoveGame.TILE_SIZE - 3 * size * LoveGame.SCALE, 0.75f);

			screen.spriteBatch.begin();
			String s = Localization.getInstance().get("undo_text");
			this.getMainPanel().drawString(s, (LoveGame.WIDTH * LoveGame.SCALE) / 2f, y + 5 * LoveGame.SCALE + 2 * LoveGame.SCALE + (0.4f) * LoveGame.TILE_SIZE * LoveGame.SCALE, LoveGame.COLOR_DARK, AssetLoader.font25, DrawString.MIDDLE, false, false);
			screen.spriteBatch.end();
		}
	}

	public void renderMenu() {
    	renderMenuTitle();
    	
    	String[] menuText = {Localization.getInstance().get("menu_text_0"), Localization.getInstance().get("menu_text_1")};
    	int size = menuText.length;
		float x = (WIDTH / 3f * SCALE) / 2f;
		float y = HEIGHT * SCALE - TILE_SIZE * size * SCALE + (size) * SCALE;
		renderBackground(x, y, WIDTH * 2 / 3f * SCALE,  (size) * SCALE * TILE_SIZE - 3 * size * SCALE, 0.75f);

		this.getMainPanel().spriteBatch.begin();
		for (int i = 0; i < menuText.length; i++) {
    		String s = menuText[i];
        	this.getMainPanel().drawString(s, (WIDTH * SCALE) / 2f, y + 5 * SCALE + 2 * SCALE + (i * 0.8f) * TILE_SIZE * SCALE, COLOR_DARK, AssetLoader.font25, DrawString.MIDDLE, false, false);
		}
    	this.getMainPanel().spriteBatch.end();
	}
    
    private void renderMenuTitle() {
    	int startY = 5 * SCALE;
    	renderBackground((WIDTH / 3f * SCALE) / 2, startY, WIDTH * 2 / 3f * SCALE, SCALE * TILE_SIZE * 2, 0.75f);

		if ((!bEditor) && (state != STATE_MENU) && (isLevelSolved)) {
			this.getMainPanel().spriteBatch.begin();
			this.getMainPanel().spriteBatch.enableBlending();
			int size = (int)(TILE_SIZE * 1.1f * SCALE / 2);
			this.getMainPanel().spriteBatch.draw(AssetLoader.solvedImage, (WIDTH * SCALE) / 2f + (TILE_SIZE * SCALE * 2.5f), startY * 2 + (TILE_SIZE * 1.02f) * SCALE, size, size);
			this.getMainPanel().spriteBatch.end();
		}

    	this.getMainPanel().spriteBatch.begin();
    	String s = Constants.PROGRAM_NAME;
    	this.getMainPanel().drawString(s, (WIDTH * SCALE) / 2f, startY * 2, COLOR_DARK, AssetLoader.font30, DrawString.MIDDLE, false, false);

    	s = "Level: " + (this.curLevel + 1) + " / " + LoveLevels.LEVELS.length;
    	if ((bEditor) || (state == STATE_MENU)) {
			s = Localization.getInstance().get("love_text");
			this.getMainPanel().drawString(s, (WIDTH * SCALE) / 2f, startY * 2 + (TILE_SIZE * 1.05f) * SCALE, COLOR_DARK, AssetLoader.font25, DrawString.MIDDLE, false, false);
		} else {
			this.getMainPanel().drawString(s, (WIDTH * SCALE) / 2f, startY * 2 + (TILE_SIZE * 1.05f) * SCALE, COLOR_DARK, AssetLoader.font30, DrawString.MIDDLE, false, false);
		}
    	this.getMainPanel().spriteBatch.end();    	
    }
    
    private void renderBackground(float x, float y, float width, float height, float alpha) {
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
		this.getMainPanel().getRenderer().setColor(COLOR_BUTTON[0], COLOR_BUTTON[1], COLOR_BUTTON[2], 1f);
		this.getMainPanel().getRenderer().roundedRectLine(x + rem, y + rem, width, height, 3);
		this.getMainPanel().getRenderer().end();
		Gdx.gl20.glLineWidth(1f);
    }
}
