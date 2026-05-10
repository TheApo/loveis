package org.apogames.love.game.userlevels;

import org.apogames.love.Constants;
import org.apogames.love.asset.AssetLoader;
import org.apogames.love.backend.DrawString;
import org.apogames.love.backend.SequentiallyThinkingScreenModel;
import org.apogames.love.common.Localization;
import org.apogames.love.entity.ApoButton;
import org.apogames.love.entity.ApoButtonColor;
import org.apogames.love.entity.ApoButtonColorWithoutBorder;
import org.apogames.love.game.MainPanel;
import org.apogames.love.game.love.LoveGame;
import org.apogames.love.game.love.LoveLevel;
import org.apogames.love.game.love.LoveWinState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class LoveUserlevels extends SequentiallyThinkingScreenModel {

	private final int MAX_LEVELS_SHOW = 32;
	
	private static final String FUNCTION_RIGHT = "function_right";
	private static final String FUNCTION_RIGHT_LEVEL = "function_right_level";
	private static final String FUNCTION_LEFT = "function_left";
	private static final String FUNCTION_LEFT_LEVEL = "function_left_level";
	private static final String FUNCTION_FUN = "function_fun";
	private static final String FUNCTION_EASY = "function_easy";
	private static final String FUNCTION_HARD = "function_hard";
	private static final String FUNCTION_CREATIVE = "function_creative";
	private static final String FUNCTION_STAR = "function_star";
	private static final String FUNCTION_STAR_1 = "function_star_1";
	private static final String FUNCTION_STAR_2 = "function_star_2";
	private static final String FUNCTION_STAR_3 = "function_star_3";
	private static final String FUNCTION_STAR_4 = "function_star_4";
	private static final String FUNCTION_STAR_5 = "function_star_5";
	
    private boolean[] keys = new boolean[256];
    private boolean[] keysNeedRefresh = new boolean[256];
    
    private UserlevelsHelp currentUserlevel;
    private LoveLevel level;
    
    private int random = -1;
    
    private int startButtonId = 0;
    
    private String levelIsString = "";

    private boolean isLevelSolved = false;
    
	public LoveUserlevels(MainPanel mainPanel) {
		super(mainPanel);
		
        setMenuButtons();
    }

	public void setMenuButtons() {
    	if (getModelButtons().size() <= 0) {
    		random = (int)(Math.random() * getMainPanel().getUserlevels().size());
    		
    		int startX = 40 * LoveGame.SCALE;
    		int x = startX;
    		int startY = LoveGame.HEIGHT * LoveGame.SCALE - 12 * LoveGame.TILE_SIZE * LoveGame.SCALE;
    		int y = startY;
    		BitmapFont font = AssetLoader.font40;
    		for (int i = 0; i < getMainPanel().getUserlevels().size(); i++) {
    			
    			String text = ""+(i+1);
    			String function = text;
    			int width = 45 * LoveGame.SCALE;
    			int height = 45 * LoveGame.SCALE;
				ApoButtonColor button = new ApoButtonColor(x, y, width, height, function, text, LoveGame.COLOR_BUTTON_BRIGHT, LoveGame.COLOR_BUTTON);
				button.setColorBorderSolved(LoveGame.COLOR_BUTTON_SOLVED);
    			button.setSolvedImage(AssetLoader.solvedImage);
    			button.setStroke(3);
    			button.setFont(font);
    			getModelButtons().add(button);
    			
    			x += width + 5 * LoveGame.SCALE;
    			if (x + 10 + width > LoveGame.WIDTH * LoveGame.SCALE) {
					if (((i+1) % (MAX_LEVELS_SHOW) == 0) && (i > 0)) {
    					x = startX;
    					y = startY;
    				} else {
    					x = startX;
    					y += height + 5 * LoveGame.SCALE;
    				}
    			}
    		}
    		
    		String text = "X";
    		String function = SequentiallyThinkingScreenModel.FUNCTION_EXIT;
			int width = 42 * LoveGame.SCALE;
			int height = 42 * LoveGame.SCALE;
			x = LoveGame.WIDTH * LoveGame.SCALE - width - 3 * LoveGame.SCALE;
			y = LoveGame.HEIGHT * LoveGame.SCALE - height - 3 * LoveGame.SCALE;
			ApoButton button = new ApoButtonColor(x, y, width, height, function, text, LoveGame.COLOR_BUTTON_BRIGHT, LoveGame.COLOR_BUTTON);
			button.setStroke(1);
			button.setFont(font);
			getModelButtons().add(button);
			
			width = 35 * LoveGame.SCALE;
			height = 35 * LoveGame.SCALE;
			text = ">";
    		function = FUNCTION_RIGHT;
			x = LoveGame.WIDTH * LoveGame.SCALE - width - 3 * LoveGame.SCALE;
			y = startY + 97 * LoveGame.SCALE - height/2;
			button = new ApoButtonColor(x, y, width, height, function, text, LoveGame.COLOR_BUTTON_BRIGHT, LoveGame.COLOR_BUTTON);
			button.setStroke(1);
			button.setFont(font);
			getModelButtons().add(button);
			
			text = "<";
    		function = FUNCTION_LEFT;
			x = 3 * LoveGame.SCALE;
			y = startY + 97 * LoveGame.SCALE - height/2;
			button = new ApoButtonColor(x, y, width, height, function, text, LoveGame.COLOR_BUTTON_BRIGHT, LoveGame.COLOR_BUTTON);
			button.setStroke(1);
			button.setFont(font);
			getModelButtons().add(button);


			float[] color = new float[] {0f, 0f, 0f, 0f};
			text = ">";
			function = FUNCTION_RIGHT_LEVEL;
			x = LoveGame.WIDTH * 3 * LoveGame.SCALE / 4;
			y = (int)(-1.5f * LoveGame.SCALE);
			button = new ApoButtonColorWithoutBorder(x, y, width, height, function, text, color, LoveGame.COLOR_BUTTON);
			button.setFont(AssetLoader.font40);
			getModelButtons().add(button);

			text = "<";
			function = FUNCTION_LEFT_LEVEL;
			x = LoveGame.WIDTH * LoveGame.SCALE / 4 - width;
			y = (int)(-1.5f * LoveGame.SCALE);
			button = new ApoButtonColorWithoutBorder(x, y, width, height, function, text, color, LoveGame.COLOR_BUTTON);
			button.setFont(AssetLoader.font40);
			getModelButtons().add(button);
			
			font = AssetLoader.font30;
			
			text = "fun";
    		function = FUNCTION_FUN;
    		width = (int)(2.5f * LoveGame.TILE_SIZE * LoveGame.SCALE);
    		height = (int)(0.8f * LoveGame.TILE_SIZE * LoveGame.SCALE);
			y = (int)(LoveGame.HEIGHT / 2 * LoveGame.SCALE - (1.2f) * LoveGame.TILE_SIZE * LoveGame.SCALE);
			
    		x = (int)((LoveGame.WIDTH / 2 * LoveGame.SCALE) - 2 * width - 2.5f * LoveGame.SCALE - 1 * 5f * LoveGame.SCALE);
			button = new LoveUserlevelsButtons(x, y, width, height, function, text, Constants.COLOR_WHITE, LoveGame.COLOR_BUTTON);
			button.setStroke(1);
			button.setFont(font);
			getModelButtons().add(button);
			
			text = "easy";
    		function = FUNCTION_EASY;
			x = (int)((LoveGame.WIDTH / 2 * LoveGame.SCALE) - width - 2.5f * LoveGame.SCALE);
			button = new LoveUserlevelsButtons(x, y, width, height, function, text, Constants.COLOR_WHITE, LoveGame.COLOR_BUTTON);
			button.setStroke(1);
			button.setFont(font);
			getModelButtons().add(button);
			
			text = "hard";
    		function = FUNCTION_HARD;
			x = (int)((LoveGame.WIDTH / 2 * LoveGame.SCALE) + 0 * width + 2.5f * LoveGame.SCALE);
			button = new LoveUserlevelsButtons(x, y, width, height, function, text, Constants.COLOR_WHITE, LoveGame.COLOR_BUTTON);
			button.setStroke(1);
			button.setFont(font);
			getModelButtons().add(button);
			
			text = "creative";
    		function = FUNCTION_CREATIVE;
			x = (int)((LoveGame.WIDTH / 2 * LoveGame.SCALE) + 1 * width + 7.5f * LoveGame.SCALE);
			button = new LoveUserlevelsButtons(x, y, width, height, function, text, Constants.COLOR_WHITE, LoveGame.COLOR_BUTTON);
			button.setStroke(1);
			button.setFont(font);
			getModelButtons().add(button);
			
			y = (int)(LoveGame.HEIGHT / 2 * LoveGame.SCALE - (3.7f) * LoveGame.TILE_SIZE * LoveGame.SCALE);
			width = (int)(1.5f * LoveGame.TILE_SIZE * LoveGame.SCALE);
			height = width;

			function = FUNCTION_STAR_1;
			x = (int)((LoveGame.WIDTH / 2 * LoveGame.SCALE) - 2 * width - 1 * width/2 - 2 * 5f * LoveGame.SCALE);
			button = new LoveUserlevelsButtonsImage(x, y, width, height, function, text, AssetLoader.solvedImage);
			getModelButtons().add(button);
			
			function = FUNCTION_STAR_2;
			x = (int)((LoveGame.WIDTH / 2 * LoveGame.SCALE) - 1 * width - 1 * width/2 - 1 * 5f * LoveGame.SCALE);
			button = new LoveUserlevelsButtonsImage(x, y, width, height, function, text, AssetLoader.solvedImage);
			getModelButtons().add(button);
			
			function = FUNCTION_STAR_3;
			x = (int)((LoveGame.WIDTH / 2 * LoveGame.SCALE) - 1 * width/2 + 0 * 5f * LoveGame.SCALE);
			button = new LoveUserlevelsButtonsImage(x, y, width, height, function, text, AssetLoader.solvedImage);
			getModelButtons().add(button);
			
			function = FUNCTION_STAR_4;
			x = (int)((LoveGame.WIDTH / 2 * LoveGame.SCALE) + 1 * width - 1 * width/2 + 1 * 5f * LoveGame.SCALE);
			button = new LoveUserlevelsButtonsImage(x, y, width, height, function, text, AssetLoader.solvedImage);
			getModelButtons().add(button);
			
			function = FUNCTION_STAR_5;
			x = (int)((LoveGame.WIDTH / 2 * LoveGame.SCALE) + 2 * width - 1 * width/2 + 2 * 5f * LoveGame.SCALE);
			button = new LoveUserlevelsButtonsImage(x, y, width, height, function, text, AssetLoader.solvedImage);
			getModelButtons().add(button);
    	}
    }

	@Override
	public void init() {
		String[] level = new String[getMainPanel().getUserlevels().size()];
		for (int i = 0; i < getMainPanel().getUserlevels().size(); i++) {
			level[i] = getMainPanel().getUserlevels().get(i).getLevel();
		}
		super.levels = level;

       	setMyMenu();
	}
	
	public void readAndCreateNewLevel(boolean bMenu) {
		if (curLevel < 0) {
            curLevel = this.getMainPanel().getUserlevels().size() - 1;
        } else if (curLevel >= this.getMainPanel().getUserlevels().size()) {
            curLevel = 0;
        }

        String curString = "";
        
        if (bMenu) {
        	this.currentUserlevel = this.getMainPanel().getUserlevels().get(random);
        	setButtonVisibleForGame(false);
        } else {
        	this.currentUserlevel = this.getMainPanel().getUserlevels().get(curLevel);
        	setButtonVisibleForGame(true);
        }
    	curString = this.currentUserlevel.getLevel();
        
        this.level = new LoveLevel(curString);
        this.level.setUserlevel(true);
        this.level.setVoted(this.getVotedLevels().contains(curString));

		isLevelSolved = isLevelSolved(curLevel);

        setLevelIsString(curString);
        
        keys = new boolean[256];
        keysNeedRefresh = new boolean[256];
        
        super.setLevelWinButtonVisible(false);
        setNeededButtonsVisible();
        
        state = 1;
	}
	
	private void setLevelIsString(String level) {
		levelIsString = "";
		for (int i = 0; i < this.getMainPanel().getUserlevels().size(); i++) {
			UserlevelsHelp userlevelsHelp = getMainPanel().getUserlevels().get(i);
			if (userlevelsHelp.getLevel().equals(level)) {
				if (userlevelsHelp.getCount() <= 0) {
					return;
				}
				int fun = userlevelsHelp.getFun();
				int easy = userlevelsHelp.getEasy();
				int hard = userlevelsHelp.getHard();
				int creative = userlevelsHelp.getCreative();
				
				if ((easy > hard) && (easy > 0)) {
					levelIsString = "The level is easy";
				} else if ((easy < hard) && (hard > 0)) {
					levelIsString = "The level is hard";
				}
				if ((levelIsString.length() > 0) && ((fun > 0) || (creative > 0))) {
					levelIsString += " and ";
				} else if (levelIsString.length() <= 0) {
					levelIsString = "The level is ";
				}
				if ((fun > creative) && (fun > 0)) {
					levelIsString += "fun";
				} else if ((fun <= creative) && (creative > 0)) {
					levelIsString += "creative";
				}
				return;
			}
		}
	}
	
	 public void setMenuButtonVisible(final boolean visible) {
		 super.setMenuButtonVisible(visible);
		 showChangeButtons();
		 setUserlevelsButtonsVisible(false);
	 }

	private void setUserlevelsButtonsVisible(boolean visible) {
		if (level.isVoted()) {
			visible = false;
		}
		for (int i = getModelButtons().size() - 9; i < getModelButtons().size(); i++) {
			getModelButtons().get(i).setSelect(false);
			getModelButtons().get(i).setVisible(visible);
		}
	}
	
	private void showChangeButtons() {
		if (state == STATE_MENU) {
			ApoButton apoButtonForFunction = getApoButtonForFunction(FUNCTION_LEFT);
			if (this.startButtonId > 0) {
				apoButtonForFunction.setVisible(true);
			} else {
				apoButtonForFunction.setVisible(false);
			}

			apoButtonForFunction = getApoButtonForFunction(FUNCTION_RIGHT);
			if (this.startButtonId + MAX_LEVELS_SHOW < getMainPanel().getUserlevels().size()) {
				apoButtonForFunction.setVisible(true);
			} else {
				apoButtonForFunction.setVisible(false);
			}
		}
		
		if (state == STATE_MENU) {
			for (int i = 0; i < getMainPanel().getUserlevels().size(); i++) {
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
	
	protected void setLevelWinButtonVisible(boolean visible) {
		super.setLevelWinButtonVisible(visible);
		setUserlevelsButtonsVisible(visible);
	}
	
	public void setButtonVisibleForGame(boolean isVisible) {
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
		return LoveGame.HEIGHT * LoveGame.SCALE;
	}
	
	@Override
    protected float getScale() {
    	return LoveGame.SCALE * 2.6f;
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
        	checkVote();
			setNextLevel();
        } else if ((function.equals(SequentiallyThinkingScreenModel.FUNCTION_RELOAD)) ||
        		   (function.equals(SequentiallyThinkingScreenModel.FUNCTION_RESTART))) {
        	checkVote();
    		setLevel(curLevel);
        } else if (function.equals(SequentiallyThinkingScreenModel.FUNCTION_MENU)) {
            quit();
        } else if (function.equals(SequentiallyThinkingScreenModel.FUNCTION_EXIT)) {
        	quit();
        } else if (function.equals(SequentiallyThinkingScreenModel.FUNCTION_UNDO)) {
        	level.undoLastStep();
        } else if (function.equals(FUNCTION_LEFT)) {
        	if (startButtonId > 0) {
        		startButtonId -= MAX_LEVELS_SHOW;
        		showChangeButtons();
        	}
        } else if (function.equals(FUNCTION_RIGHT)) {
        	if (startButtonId + MAX_LEVELS_SHOW < getMainPanel().getUserlevels().size()) {
        		startButtonId += MAX_LEVELS_SHOW;
        		showChangeButtons();
        	}
        } else if (function.equals(FUNCTION_LEFT_LEVEL)) {
			setLevel(curLevel - 1);
		} else if (function.equals(FUNCTION_RIGHT_LEVEL)) {
			setLevel(curLevel + 1);
		} else if ((function.equals(FUNCTION_CREATIVE)) || (function.equals(FUNCTION_HARD)) ||
        		   (function.equals(FUNCTION_EASY)) || (function.equals(FUNCTION_FUN))) {
        	ApoButton apoButtonForFunction = getApoButtonForFunction(function);
        	apoButtonForFunction.setSelect(!apoButtonForFunction.isSelect());
        } else if (function.startsWith(FUNCTION_STAR)) {
	     	ApoButton apoButtonForFunction = getApoButtonForFunction(function);
	     	apoButtonForFunction.setSelect(!apoButtonForFunction.isSelect());
	     	if (!apoButtonForFunction.isSelect()) {
		     	for (int i = getModelButtons().size() - 5; i < getModelButtons().size(); i++) {
		     		getModelButtons().get(i).setSelect(false);
		     	}
	     	} else {
	     		String func = apoButtonForFunction.getFunction();
	     		int star = Integer.valueOf(func.substring(func.length() - 1, func.length()));
	     		for (int i = getModelButtons().size() - 5; i < getModelButtons().size() && i < getModelButtons().size() - 5 + star; i++) {
		     		getModelButtons().get(i).setSelect(true);
		     	}
	     	}
	     } else if (state == STATE_MENU) {
        	checkIfLevelButtonIsReleased(function);
        }
    }
    
    private boolean hasVotedLevel() {
    	return getModelButtons().get(getModelButtons().size() - 5).isSelect();
    }
    
    private void voteLevel() {
    	String level = this.level.getLevelString();
    	addVotedLevel(level);
    	int stars = 0;
    	for (int i = getModelButtons().size() - 5; i < getModelButtons().size(); i++) {
     		if (getModelButtons().get(i).isSelect()) {
     			stars += 1;
     		}
     	}
    	int fun = 0;
    	if (getApoButtonForFunction(FUNCTION_FUN).isSelect()) {
    		fun = 1;
    	}
    	int easy = 0;
    	if (getApoButtonForFunction(FUNCTION_EASY).isSelect()) {
    		easy = 1;
    	}
    	int hard = 0;
    	if (getApoButtonForFunction(FUNCTION_HARD).isSelect()) {
    		hard = 1;
    	}
    	int creative = 0;
    	if (getApoButtonForFunction(FUNCTION_CREATIVE).isSelect()) {
    		creative = 1;
    	}
    	getMainPanel().saveLevelRating(level, stars, fun, easy, hard, creative);
    	setUserlevelsButtonsVisible(false);
    }
    
    public void quit() {
    	super.quit();
    }
    
	private void setNextLevel() {
		setLevel(curLevel + 1);
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
		        	setLevelWinButtonVisible(true);
		        	setButtonsVisible(false);
        			String levelString = level.getLevelString();
					addSolvedLevel(levelString);
	        		getMainPanel().saveLevelCount(levelString);
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
					setNextLevel();
		        }
			} else if (state == 3) {
				if ((keys[Input.Keys.SPACE]) || (keys[Input.Keys.ENTER])) {
					checkVote();
					setLevel(curLevel);
		        }
			}
			
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

	private void checkVote() {
		if (hasVotedLevel()) {
			voteLevel();
		}
	}
	
    @Override
    public void render() {
    	this.level.render(this.getMainPanel(), 0, 3 * LoveGame.TILE_SIZE * LoveGame.SCALE);
    	
    	if (state == STATE_MENU) {
    		renderMenu();
    	} else {
    		renderMenuTitle();
    		renderDPad();
    	}
    	
    	for (ApoButton button : this.getMainPanel().getButtons()) {
            button.render(this.getMainPanel(), 0, 0);
        }
    	for (int i = getMainPanel().getUserlevels().size(); i < getModelButtons().size(); i++) {
    		this.getModelButtons().get(i).render(this.getMainPanel(), 0, 0);
    	}
    }
    
    public void renderMenu() {    	
    	renderMenuTitle();
    	
    	renderBackground(30 * LoveGame.SCALE, LoveGame.HEIGHT * LoveGame.SCALE - 12 * LoveGame.TILE_SIZE * LoveGame.SCALE - 10 * LoveGame.SCALE, LoveGame.WIDTH * LoveGame.SCALE - 60 * LoveGame.SCALE,  210 * LoveGame.SCALE, 0.75f);
    	
    	String[] menuText = {Localization.getInstance().get("menu_text_0"), Localization.getInstance().get("menu_text_1")};
    	int size = menuText.length;
		float x = (LoveGame.WIDTH / 3 * LoveGame.SCALE) / 2;
		float y = LoveGame.HEIGHT * LoveGame.SCALE - LoveGame.TILE_SIZE * size * LoveGame.SCALE + (size) * LoveGame.SCALE;
		renderBackground(x, y, LoveGame.WIDTH * 2 / 3 * LoveGame.SCALE,  (size) * LoveGame.SCALE * LoveGame.TILE_SIZE - 3 * size * LoveGame.SCALE, 0.75f);

		this.getMainPanel().spriteBatch.begin();
		for (int i = 0; i < menuText.length; i++) {
    		String s = menuText[i];
        	this.getMainPanel().drawString(s, (LoveGame.WIDTH * LoveGame.SCALE) / 2, y + 7 * LoveGame.SCALE + (i * 0.8f) * LoveGame.TILE_SIZE * LoveGame.SCALE, LoveGame.COLOR_DARK, AssetLoader.font25, DrawString.MIDDLE, false, false);
		}
    	this.getMainPanel().spriteBatch.end();
		
    	for (int i = startButtonId; i < startButtonId + MAX_LEVELS_SHOW && i < getMainPanel().getUserlevels().size(); i++) {
    		this.getModelButtons().get(i).render(this.getMainPanel(), 0, 0);
    	}
    	
    	for (int i = getMainPanel().getUserlevels().size(); i < getModelButtons().size(); i++) {
    		this.getModelButtons().get(i).render(this.getMainPanel(), 0, 0);
    	}
	}
    
    public void renderMenuTitle() {
    	int startX = (LoveGame.WIDTH / 3 * LoveGame.SCALE) / 2;
    	int startY = 5 * LoveGame.SCALE;
    	renderBackground(startX, startY, LoveGame.WIDTH * 2 / 3 * LoveGame.SCALE, LoveGame.SCALE * LoveGame.TILE_SIZE * 2f, 0.75f);


        int size = 2;
        float x = (LoveGame.WIDTH / 4f * LoveGame.SCALE) / 2f;
        float y = LoveGame.HEIGHT * LoveGame.SCALE - LoveGame.TILE_SIZE * size * LoveGame.SCALE + (size) * LoveGame.SCALE;
        if (level.isNoYou()) {
            renderBackground(x, y, LoveGame.WIDTH * 3 / 4f * LoveGame.SCALE, (size) * LoveGame.SCALE * LoveGame.TILE_SIZE - 3 * size * LoveGame.SCALE, 0.75f);
        }

		float startLevelX = startX + 12.4f * LoveGame.SCALE * LoveGame.TILE_SIZE;
		float startLevelY = (LoveGame.TILE_SIZE * 1.7f) * LoveGame.SCALE;
		if ((state != STATE_MENU) && (isLevelSolved)) {
			this.getMainPanel().spriteBatch.begin();
			this.getMainPanel().spriteBatch.enableBlending();

			int solvedSize = (int)( LoveGame.TILE_SIZE * 1.1f *  LoveGame.SCALE / 2);
			this.getMainPanel().spriteBatch.draw(AssetLoader.solvedImage, startLevelX - solvedSize/2, startLevelY, solvedSize, solvedSize);
			this.getMainPanel().spriteBatch.end();
		}

    	this.getMainPanel().spriteBatch.begin();
    	String s = Constants.PROGRAM_NAME;
    	this.getMainPanel().drawString(s, (LoveGame.WIDTH * LoveGame.SCALE) / 2f, startY * 2, LoveGame.COLOR_DARK, AssetLoader.font30, DrawString.MIDDLE, false, false);

        if (level.isNoYou()) {

            s = Localization.getInstance().get("undo_text");
            this.getMainPanel().drawString(s, (LoveGame.WIDTH * LoveGame.SCALE) / 2f, y + 5 * LoveGame.SCALE + 2 * LoveGame.SCALE + (0.4f) * LoveGame.TILE_SIZE * LoveGame.SCALE, LoveGame.COLOR_DARK, AssetLoader.font25, DrawString.MIDDLE, false, false);
        }

    	if (state == STATE_MENU) {
    		s = Localization.getInstance().get("love_text");
        	this.getMainPanel().drawString(s, (LoveGame.WIDTH * LoveGame.SCALE) / 2f, startY * 2 + (LoveGame.TILE_SIZE * 1.05f) * LoveGame.SCALE, LoveGame.COLOR_DARK, AssetLoader.font25, DrawString.MIDDLE, false, false);
    	} else {
    		s = "ratings";
    		this.getMainPanel().drawString(s, startX + LoveGame.SCALE * LoveGame.TILE_SIZE, (LoveGame.TILE_SIZE * 1.15f) * LoveGame.SCALE, LoveGame.COLOR_DARK, AssetLoader.font25, DrawString.MIDDLE, false, false);
    	
    		s = this.currentUserlevel.getCount()+"";
    		this.getMainPanel().drawString(s, startX + LoveGame.SCALE * LoveGame.TILE_SIZE, (LoveGame.TILE_SIZE * 1.7f) * LoveGame.SCALE, LoveGame.COLOR_DARK, AssetLoader.font25, DrawString.MIDDLE, false, false);
    		
    		s = "stars";
    		this.getMainPanel().drawString(s, startX + LoveGame.SCALE * LoveGame.TILE_SIZE * 3, (LoveGame.TILE_SIZE * 1.15f) * LoveGame.SCALE, LoveGame.COLOR_DARK, AssetLoader.font25, DrawString.MIDDLE, false, false);
    		
    		float stars = 0f;
    		if (this.currentUserlevel.getCount() > 0) {
    			stars = (float)this.currentUserlevel.getAll() / (float)this.currentUserlevel.getCount();
    		}
    		s = roundTo2DecPlaces(stars) + "";
    		this.getMainPanel().drawString(s, startX + LoveGame.SCALE * LoveGame.TILE_SIZE * 3, (LoveGame.TILE_SIZE * 1.7f) * LoveGame.SCALE, LoveGame.COLOR_DARK, AssetLoader.font25, DrawString.MIDDLE, false, false);
    		
    		s = levelIsString;
    		this.getMainPanel().drawString(s, startX + LoveGame.SCALE * LoveGame.TILE_SIZE * 7.7f, (LoveGame.TILE_SIZE * 1.42f) * LoveGame.SCALE, LoveGame.COLOR_DARK, AssetLoader.font25, DrawString.MIDDLE, false, false);
    		
    		
    		s = "level";
    		this.getMainPanel().drawString(s, startLevelX, (LoveGame.TILE_SIZE * 1.15f) * LoveGame.SCALE, LoveGame.COLOR_DARK, AssetLoader.font25, DrawString.MIDDLE, false, false);
    	
    		s = (curLevel+1) + " / " +this.getMainPanel().getUserlevels().size();
    		this.getMainPanel().drawString(s, startLevelX, startLevelY, LoveGame.COLOR_DARK, AssetLoader.font25, DrawString.MIDDLE, false, false);
    		
    	}
    	this.getMainPanel().spriteBatch.end();
    }
    
    private float roundTo2DecPlaces(float val)
    {
        return (float)(Math.round(val * 10f) / 10f);
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
