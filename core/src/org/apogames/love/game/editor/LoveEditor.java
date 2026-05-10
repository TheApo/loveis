package org.apogames.love.game.editor;

import org.apogames.love.Constants;
import org.apogames.love.asset.AssetLoader;
import org.apogames.love.backend.DrawString;
import org.apogames.love.backend.SequentiallyThinkingScreenModel;
import org.apogames.love.common.Localization;
import org.apogames.love.entity.ApoButton;
import org.apogames.love.entity.ApoButtonColor;
import org.apogames.love.game.LoveEnumTiles;
import org.apogames.love.game.MainPanel;
import org.apogames.love.game.love.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class LoveEditor extends SequentiallyThinkingScreenModel {

	private final int START_EXTRA_BUTTONS = 4;
	
    private boolean[] keys = new boolean[256];
    private boolean[] keysNeedRefresh = new boolean[256];
    
    public static final float[] COLOR_BUTTON = new float[]{116f / 255f, 35f / 255f, 35f / 255f, 1f};
    public static final float[] COLOR_BUTTON_BRIGHT = new float[]{240f / 255f, 72f / 255f, 72f / 255f, 1f};
    
    public static final float[] COLOR_BACKGROUND = new float[]{56f / 255f, 96f / 255f, 168f / 255f, 1f};
    public static final float[] COLOR_DARK = new float[]{116f / 255f, 35f / 255f, 35f / 255f, 1f};
    public static final float[] COLOR_BLUE_DARK = new float[]{0f / 255f, 45f / 255f, 90f / 255f, 1f};
    public static final float[] COLOR_RED_DARK = new float[]{128f / 255f, 45f / 255f, 45f / 255f, 1f};
    public static final float[] COLOR_YELLOW_DARK = new float[]{92f / 255f, 75f / 255f, 0f / 255f, 1f};
    public static final float[] COLOR_GREEN_DARK = new float[]{19f / 255f, 51f / 255f, 28f / 255f, 1f};
    
    private final String FUNCTION_NEW = "editor_new";
    private final String FUNCTION_TEST = "editor_test";
    private final String FUNCTION_UPLOAD = "editor_upload";
    
    private final String FUNCTION_BACKGROUND_WATER = "background_water";
    private final String FUNCTION_BACKGROUND_GRAS = "background_gras";
    private final String FUNCTION_BACKGROUND_EMPTY = "background_empty";
    private final String FUNCTION_BACKGROUND_LAVA = "background_lava";
    private final String FUNCTION_BACKGROUND_ICE = "background_ice";

    private final String FUNCTION_LAYER_EMPTY = "layer_empty";

    private final String FUNCTION_INFO = "function_info";
    private final String FUNCTION_TEXT_PLUS = "text_plus";
    
    private final String FUNCTION_TEXT_IS = "text_is";
	private final String FUNCTION_TEXT_AND = "text_and";
	private final String FUNCTION_TEXT_ISON = "text_ison";
    private final String FUNCTION_TEXT_ISNOT = "text_isnot";
	private final String FUNCTION_TEXT_IF = "text_IF";
	private final String FUNCTION_TEXT_THEN = "text_THEN";
    
    private int heightLengthEnum = 10;
    
    private LoveEditorEnum valueType = LoveEditorEnum.PLUS;
    private int valueX = -1;
    private int valueY = -1;
    private int valueId = -1;
    private LoveLevelEntity valueText = null;
    
    private int mouseX = -1;
    private int mouseY = -1;
    
    private int textChangeX = 0;
    
    private LoveLevel level;
    
    private String curString;
    
    private boolean isInfoShowing = true;
    private LoveEditorButtonsSwitch switchButton = null;

    private boolean isLevelSolved = false;
    private boolean bFirst = true;
    
	public LoveEditor(MainPanel mainPanel) {
		super(mainPanel);
		setMenuButtons();
		
		int level = (int)(Math.random() * LoveLevels.LEVELS.length);
		this.curString = LoveLevels.LEVELS[level];
//		this.curString = "tttttttttt..........tt......ttt.........tt.y....tttt........tt......ttttt.......tt......t...t.......tt.....r~.h.t.......tt......t...t.......tt.p....ttttt.......tt......ttttt.......tttttttttttt........ttttttttttt.........ttt...tttt...........tttttttt.............t...tt..............ttttt.................................................................................................................#HERO,5,2,IS,5,3,YOU,5,4,WOMAN,5,7,WATER,3,11,IS,4,11,SINK,5,11,IF,19,12,TREE,3,13,IS,4,13,SOLID,5,13,WOMAN,19,13,IS,19,14,LOVE,3,15,IS,4,15,GOAL,5,15,YOU,19,15,THEN,19,16,ROCK,19,17,IS,19,18,PUSH,19,19,#";
		
		this.heightLengthEnum = 0;
		for (int i = 0; i < LoveEnumTiles.values().length; i++) {
			LoveEnumTiles enumTile = LoveEnumTiles.values()[i];
			if (((enumTile.getTextureX() >= 0) || (enumTile.getTextureY() >= 0)) && (enumTile.getText().length() > 0)) {
				heightLengthEnum += 1;
			}
		}
    }
	
    public void setMenuButtons() {
    	if (getModelButtons().size() <= 0) {
    		BitmapFont font = AssetLoader.font40;

			String text = "X";
    		String function = SequentiallyThinkingScreenModel.FUNCTION_EXIT;
			int width = 42 * LoveGame.SCALE;
			int height = 42 * LoveGame.SCALE;
			int x = LoveGame.WIDTH * LoveGame.SCALE - width - 3 * LoveGame.SCALE;
			int y = LoveGame.HEIGHT * LoveGame.SCALE - height - 3 * LoveGame.SCALE;
			ApoButton button = new ApoButtonColor(x, y, width, height, function, text, COLOR_BUTTON_BRIGHT, COLOR_BUTTON);
			button.setStroke(1);
			button.setFont(font);
			getModelButtons().add(button);
			
			font = AssetLoader.font30;
			text = "reset";
    		function = FUNCTION_NEW;
			width = 3 * LoveGame.TILE_SIZE * LoveGame.SCALE;
			height = 1 * LoveGame.SCALE * LoveGame.TILE_SIZE;
			x = (int)(3 * LoveGame.SCALE);
			y = (int)(LoveGame.HEIGHT * LoveGame.SCALE - 1.5f * LoveGame.TILE_SIZE * LoveGame.SCALE);
			button = new ApoButtonColor(x, y, width, height, function, text, COLOR_BUTTON_BRIGHT, COLOR_BUTTON);
			button.setStroke(1);
			button.setFont(font);
			getModelButtons().add(button);
			
			text = "test";
    		function = FUNCTION_TEST;
			width = 3 * LoveGame.TILE_SIZE * LoveGame.SCALE;
			height = 1 * LoveGame.SCALE * LoveGame.TILE_SIZE;
			x = (int)(3 * LoveGame.SCALE + width + 0.5f * LoveGame.SCALE * LoveGame.TILE_SIZE);
			y = (int)(LoveGame.HEIGHT * LoveGame.SCALE - 1.5f * LoveGame.TILE_SIZE * LoveGame.SCALE);
			button = new ApoButtonColor(x, y, width, height, function, text, COLOR_BUTTON_BRIGHT, COLOR_BUTTON);
			button.setStroke(1);
			button.setFont(font);
			getModelButtons().add(button);
			
			text = "upload";
    		function = FUNCTION_UPLOAD;
			width = 3 * LoveGame.TILE_SIZE * LoveGame.SCALE;
			height = 1 * LoveGame.SCALE * LoveGame.TILE_SIZE;
			x = (int)(3 * LoveGame.SCALE + 2 * width + 2 * 0.5f * LoveGame.SCALE * LoveGame.TILE_SIZE);
			y = (int)(LoveGame.HEIGHT * LoveGame.SCALE - 1.5f * LoveGame.TILE_SIZE * LoveGame.SCALE);
			button = new ApoButtonColor(x, y, width, height, function, text, COLOR_BUTTON_BRIGHT, COLOR_BUTTON);
			button.setStroke(1);
			button.setFont(font);
			getModelButtons().add(button);
			
			width = 1 * LoveGame.TILE_SIZE * LoveGame.SCALE;
			height = 1 * LoveGame.SCALE * LoveGame.TILE_SIZE;
			x = (int)(- 3 * LoveGame.SCALE + LoveGame.WIDTH * LoveGame.SCALE - width);
			y = (int)(2 * LoveGame.TILE_SIZE * LoveGame.SCALE - 3 * LoveGame.SCALE - 1 * height - 1 * 3 * LoveGame.SCALE);
			button = new LoveEditorButtonsInfo(x, y, width, height, FUNCTION_INFO, "i");
			button.setStroke(1);
			button.setSelect(true);
			getModelButtons().add(button);
			
			
			x = (int)(3 * LoveGame.SCALE + 0 * width + 0 * 5 * LoveGame.SCALE);
			y = (int)(2 * LoveGame.TILE_SIZE * LoveGame.SCALE - 3 * LoveGame.SCALE - 0 * height - 0 * 3 * LoveGame.SCALE);
			addEditorButton(FUNCTION_BACKGROUND_WATER, x, y, 7, 2, LoveEditorEnum.BACKGROUND, LoveLevelEntity.BACKGROUND_WATER);

			x = (int)(3 * LoveGame.SCALE + 0 * width + 0 * 5 * LoveGame.SCALE);
			y = (int)(2 * LoveGame.TILE_SIZE * LoveGame.SCALE - 3 * LoveGame.SCALE - 1 * height - 1 * 3 * LoveGame.SCALE);
			addEditorButton(FUNCTION_BACKGROUND_GRAS, x, y, 4, 2, LoveEditorEnum.BACKGROUND, LoveLevelEntity.BACKGROUND_GRAS);
			
			x = (int)(3 * LoveGame.SCALE + 1 * width + 1 * 5 * LoveGame.SCALE);
			y = (int)(2 * LoveGame.TILE_SIZE * LoveGame.SCALE - 3 * LoveGame.SCALE - 1 * height - 1 * 3 * LoveGame.SCALE);
			addEditorButton(FUNCTION_BACKGROUND_ICE, x, y, 7, 1, LoveEditorEnum.BACKGROUND, LoveLevelEntity.BACKGROUND_ICE);
			
			x = (int)(3 * LoveGame.SCALE + 1 * width + 1 * 5 * LoveGame.SCALE);
			y = (int)(2 * LoveGame.TILE_SIZE * LoveGame.SCALE - 3 * LoveGame.SCALE - 0 * height - 0 * 3 * LoveGame.SCALE);
			addEditorButton(FUNCTION_BACKGROUND_LAVA, x, y, 7, 3, LoveEditorEnum.BACKGROUND, LoveLevelEntity.BACKGROUND_LAVA);
			
			x = (int)(3 * LoveGame.SCALE + 2 * width + 2 * 5 * LoveGame.SCALE);
			y = (int)(2 * LoveGame.TILE_SIZE * LoveGame.SCALE - 3 * LoveGame.SCALE - 1 * height - 1 * 3 * LoveGame.SCALE);
			addEditorButton(FUNCTION_BACKGROUND_EMPTY, x, y, 3, 3, LoveEditorEnum.BACKGROUND, LoveLevelEntity.BACKGROUND_EMPTY);
			
			int addX = 0;
			int addY = 1;
			for (int i = 0; i < LoveEnumTiles.values().length; i++) {
				x = (int)(4 * LoveGame.TILE_SIZE * LoveGame.SCALE + addX * width + addX * 5 * LoveGame.SCALE);
				y = (int)(2 * LoveGame.TILE_SIZE * LoveGame.SCALE - 3 * LoveGame.SCALE - addY * height - addY * 3 * LoveGame.SCALE);
				LoveEnumTiles enumTile = LoveEnumTiles.values()[i];
				if ((enumTile.getTextureX() > 0) || (enumTile.getTextureY() > 0)) {
					addEditorButton("LAYER"+enumTile.getText(), x, y, enumTile.getTextureX(), enumTile.getTextureY(), LoveEditorEnum.LAYER, enumTile.getLayer());

					addY -= 1;
					if (addY < 0) {
						addX += 1;
						addY = 1;
					}
				}
			}

			x = (int)(4 * LoveGame.TILE_SIZE * LoveGame.SCALE + addX * width + addX * 5 * LoveGame.SCALE);
			y = (int)(2 * LoveGame.TILE_SIZE * LoveGame.SCALE - 3 * LoveGame.SCALE - addY * height - addY * 3 * LoveGame.SCALE);
			addEditorButton(FUNCTION_LAYER_EMPTY, x, y, 4, 3, LoveEditorEnum.LAYER, LoveLevelEntity.LAYER_EMPTY);

			float startX = 12.9f;
			x = (int)(startX * LoveGame.TILE_SIZE * LoveGame.SCALE + 0 * width + 0 * 5 * LoveGame.SCALE);
			y = (int)(2 * LoveGame.TILE_SIZE * LoveGame.SCALE - 3 * LoveGame.SCALE - 1 * height - 1 * 3 * LoveGame.SCALE);
			addEditorButtonSwitch(FUNCTION_TEXT_PLUS, x, y, -1, -1, LoveEditorEnum.PLUS);
			switchButton = (LoveEditorButtonsSwitch)(this.getModelButtons().get(getModelButtons().size() - 1));
			
			x = (int)(startX * LoveGame.TILE_SIZE * LoveGame.SCALE + 2 * width + 2 * 5 * LoveGame.SCALE);
			y = (int)(2 * LoveGame.TILE_SIZE * LoveGame.SCALE - 3 * LoveGame.SCALE - 1 * height - 1 * 3 * LoveGame.SCALE);
			LoveLevelEntityString textEntity = new LoveLevelEntityString("IS", false, true, false, LoveEnumTiles.PRAEDIKAT_IS.getColor(), LoveEnumTiles.PRAEDIKAT_IS.getTextValue());
			addEditorButtonText(FUNCTION_TEXT_IS, x, y, -1, -1, LoveEditorEnum.TEXT, textEntity);

			x = (int)(startX * LoveGame.TILE_SIZE * LoveGame.SCALE + 2 * width + 2 * 5 * LoveGame.SCALE);
			y = (int)(2 * LoveGame.TILE_SIZE * LoveGame.SCALE) - 3 * LoveGame.SCALE;
			textEntity = new LoveLevelEntityString("AND", false, false, false, LoveEnumTiles.PRAEDIKAT_AND.getColor(), LoveEnumTiles.PRAEDIKAT_AND.getTextValue());
			addEditorButtonText(FUNCTION_TEXT_AND, x, y, -1, -1, LoveEditorEnum.TEXT, textEntity);

			x = (int)(startX * LoveGame.TILE_SIZE * LoveGame.SCALE + 2 * width + 2 * 5 * LoveGame.SCALE);
			y = (int)(2 * LoveGame.TILE_SIZE * LoveGame.SCALE) - 3 * LoveGame.SCALE + 1 * height + 1 * 3 * LoveGame.SCALE;
			textEntity = new LoveLevelEntityString("ISON", false, false, false, LoveEnumTiles.PRAEDIKAT_ISON.getColor(), LoveEnumTiles.PRAEDIKAT_ISON.getTextValue());
			addEditorButtonText(FUNCTION_TEXT_ISON, x, y, -1, -1, LoveEditorEnum.TEXT, textEntity);

            x = (int)(startX * LoveGame.TILE_SIZE * LoveGame.SCALE + 2 * width + 2 * 5 * LoveGame.SCALE);
            y = (int)(2 * LoveGame.TILE_SIZE * LoveGame.SCALE) - 3 * LoveGame.SCALE + 2 * height + 2 * 3 * LoveGame.SCALE;
            textEntity = new LoveLevelEntityString(LoveEnumTiles.PRAEDIKAT_ISNOT.getText(), false, false, false, LoveEnumTiles.PRAEDIKAT_ISNOT.getColor(), LoveEnumTiles.PRAEDIKAT_ISNOT.getTextValue());
            addEditorButtonText(FUNCTION_TEXT_ISNOT, x, y, -1, -1, LoveEditorEnum.TEXT, textEntity);

			x = (int)(startX * LoveGame.TILE_SIZE * LoveGame.SCALE + 2 * width + 2 * 5 * LoveGame.SCALE);
			y = (int)(2 * LoveGame.TILE_SIZE * LoveGame.SCALE) - 3 * LoveGame.SCALE + 3 * height + 3 * 3 * LoveGame.SCALE;
			textEntity = new LoveLevelEntityString("IF", false, false, false, LoveEnumTiles.CONDITION_IF.getColor(), LoveEnumTiles.CONDITION_IF.getTextValue());
			addEditorButtonText(FUNCTION_TEXT_IF, x, y, -1, -1, LoveEditorEnum.TEXT, textEntity);

			x = (int)(startX * LoveGame.TILE_SIZE * LoveGame.SCALE + 2 * width + 2 * 5 * LoveGame.SCALE);
			y = (int)(2 * LoveGame.TILE_SIZE * LoveGame.SCALE) - 3 * LoveGame.SCALE + 4 * height + 4 * 3 * LoveGame.SCALE;
			textEntity = new LoveLevelEntityString("THEN", false, false, false, LoveEnumTiles.CONDITION_THEN.getColor(), LoveEnumTiles.CONDITION_THEN.getTextValue());
			addEditorButtonText(FUNCTION_TEXT_THEN, x, y, -1, -1, LoveEditorEnum.TEXT, textEntity);
			
			int count = 0;
			for (int i = 0; i < LoveEnumTiles.values().length; i++) {
				LoveEnumTiles enumTile = LoveEnumTiles.values()[i];
				if (((enumTile.getTextureX() >= 0) || (enumTile.getTextureY() >= 0)) && (enumTile.getText().length() > 0)) {
					x = (int)(startX * LoveGame.TILE_SIZE * LoveGame.SCALE + 1 * width + 1 * 5 * LoveGame.SCALE);
					int add = 1 - count;
					y = (int)(2 * LoveGame.TILE_SIZE * LoveGame.SCALE - 3 * LoveGame.SCALE - (add) * height - (-count + 1) * 3 * LoveGame.SCALE);
					textEntity = new LoveLevelEntityString(enumTile.getText(), true, false, true, enumTile.getColor(), enumTile.getTextValue());
					addEditorButtonText(enumTile.getText(), x, y, -1, -1, LoveEditorEnum.TEXT, textEntity);
					count += 1;
				}
			}
			
			count = 0;
			for (int i = 0; i < LoveEnumTiles.values().length; i++) {
				LoveEnumTiles enumTile = LoveEnumTiles.values()[i];
				if ((enumTile.getTextureX() < 0) && (enumTile.getTextureY() < 0) && (enumTile.getTextValue() < TextConstants.PRAEDIKAT_IS)) {
					x = (int)(startX * LoveGame.TILE_SIZE * LoveGame.SCALE + 3 * width + 3 * 5 * LoveGame.SCALE);
					int add = 1 - count;
					y = (int)(2 * LoveGame.TILE_SIZE * LoveGame.SCALE - 3 * LoveGame.SCALE - (add) * height - (-count + 1) * 3 * LoveGame.SCALE);
					textEntity = new LoveLevelEntityString(enumTile.getText(), false, false, true, enumTile.getColor(), enumTile.getTextValue());
					addEditorButtonText(enumTile.getText(), x, y, -1, -1, LoveEditorEnum.TEXT, textEntity);
					count += 1;
				}
			}
    	}
    }
    
    private void addEditorButton(String function, int x, int y, int imageX, int imageY, LoveEditorEnum type, int id) {
		int width = 1 * LoveGame.TILE_SIZE * LoveGame.SCALE;
		int height = 1 * LoveGame.SCALE * LoveGame.TILE_SIZE;

		ApoButton button = new LoveEditorButtons(x, y, width, height, function, "", imageX, imageY, type, id);
		button.setStroke(1);
		getModelButtons().add(button);
    }
    
    private void addEditorButtonSwitch(String function, int x, int y, int imageX, int imageY, LoveEditorEnum type) {
		int width = (int)(1.0f * LoveGame.TILE_SIZE * LoveGame.SCALE);
		int height = (int)(1.0f * LoveGame.SCALE * LoveGame.TILE_SIZE);

		ApoButton button = new LoveEditorButtonsSwitch(x, y, width, height, function, "", imageX, imageY, type);
		button.setStroke(1);
		getModelButtons().add(button);
    }
    
    private void addEditorButtonText(String function, int x, int y, int imageX, int imageY, LoveEditorEnum type, LoveLevelEntityString text) {
		int width = 1 * LoveGame.TILE_SIZE * LoveGame.SCALE;
		int height = 1 * LoveGame.SCALE * LoveGame.TILE_SIZE;

		ApoButton button = new LoveEditorButtonsText(x, y, width, height, function, "", imageX, imageY, type, text);
		button.setStroke(1);
		getModelButtons().add(button);
    }
    
	@Override
	public void init() {
        setMyMenu();
	}
	
	public void setMenuButtonVisible(final boolean visible) {
		super.setMenuButtonVisible(visible);
		
		getApoButtonForFunction(FUNCTION_TEST).setVisible(isLevelValid());
		getApoButtonForFunction(FUNCTION_UPLOAD).setVisible(isLevelSolved);
		
		mouseButtonFunction(FUNCTION_TEXT_PLUS);
		mouseButtonFunction(FUNCTION_TEXT_PLUS);
		
		if ((bFirst) || (isInfoShowing)) {
			isInfoShowing = true;
			getButtonForFunction(FUNCTION_INFO).setSelect(isInfoShowing);
		}
	}
	 
	private boolean isLevelValid() {
		return level.isLevelValid();
	}
	
	public boolean isLevelSolved() {
		return isLevelSolved;
	}

	public void setLevelSolved(boolean isLevelSolved) {
		this.isLevelSolved = isLevelSolved;
	}

	protected void quitMenu() {
		setLevelWinButtonVisible(false);
		initAnalyseButtons();
		this.getMainPanel().changeToMenu();
	}
	
	public void readAndCreateNewLevel(boolean bMenu) {
		if (this.curString == null) {
			curString = LoveLevels.EDITOR;
		}
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
        if (function.equals(SequentiallyThinkingScreenModel.FUNCTION_EXIT)) {
            quit();
        } else if (function.equals(FUNCTION_NEW)) {
        	this.curString = null;
            setMyMenu();
			makeInfoShowingInvisible();
    	} else if (function.equals(FUNCTION_TEST)) {
        	deselectAllButtons();
    		this.curString = level.getStringForLevel();
    		getMainPanel().changeToLoveGame(this.curString, true);
    	} else if (function.equals(FUNCTION_UPLOAD)) {
    		if (this.isLevelSolved) {
    			this.getMainPanel().saveLevel(this.curString);
    			this.isLevelSolved = false;
        		getApoButtonForFunction(FUNCTION_UPLOAD).setVisible(isLevelSolved);
        		getMainPanel().loadLevel();
    		}
    	} else {
        	LoveEditorButtons buttonForFunction = getButtonForFunction(function);
        	if (function.equals(FUNCTION_TEXT_PLUS)) {
        		buttonForFunction.setSwitched(!buttonForFunction.isSwitched());
        		setVisibileTextButtons(buttonForFunction.isSwitched());
    			makeInfoShowingInvisible();
        	} else {
        		boolean wasSelected = buttonForFunction.isSelect();
	        	for (int i = START_EXTRA_BUTTONS; i < getModelButtons().size(); i++) {
	        		getModelButtons().get(i).setSelect(false);
	        	}
	        	if ((buttonForFunction != null) && (!wasSelected)) {
	        		buttonForFunction.setSelect(true);
	        	}
        		valueType = buttonForFunction.getType();
	        	if ((buttonForFunction.getType() == LoveEditorEnum.BACKGROUND) ||
	        		(buttonForFunction.getType() == LoveEditorEnum.LAYER)) {
	        		valueX = buttonForFunction.getTextureX();
	        		valueY = buttonForFunction.getTextureY();
	        		valueId = buttonForFunction.getId();
	        		valueText = null;
	        	} else if (buttonForFunction.getType() == LoveEditorEnum.TEXT) {
	        		valueX = buttonForFunction.getTextureX();
	        		LoveLevelEntityString clone = ((LoveEditorButtonsText)(buttonForFunction)).getTextEntity().getClone();
	        		valueText = new LoveLevelEntity(0, 0);
	        		valueText.setText(clone);
	        	} else {
	        		valueText = null;
	        		valueX = -1;
	        		valueId = -1;
	        		if (function.equals(FUNCTION_INFO)) {
	        			LoveEditorButtons buttonForPlus = getButtonForFunction(FUNCTION_TEXT_PLUS);
	        			if (wasSelected) {
	        				makeInfoShowingInvisible();
	        			} else {
	        				if (buttonForPlus.isSwitched()) {
	        					mouseButtonFunction(FUNCTION_TEXT_PLUS);
	        				}
	        				isInfoShowing = true;
	        			}
	        		}
	        	}
	        	if (!function.equals(FUNCTION_INFO)) {
	        		makeInfoShowingInvisible();
	        	}
        	}
        }
    }

    private void deselectAllButtons() {
		for (int i = START_EXTRA_BUTTONS; i < getModelButtons().size(); i++) {
			getModelButtons().get(i).setSelect(false);
		}

		valueType = LoveEditorEnum.NOTHING;
		valueText = null;
		valueX = -1;
		valueId = -1;
	}

	private void makeInfoShowingInvisible() {
		isInfoShowing = false;
		bFirst = false;
		getButtonForFunction(FUNCTION_INFO).setSelect(false);
	}
    
    private void setVisibileTextButtons(boolean bVisible) {
    	for (int i = START_EXTRA_BUTTONS; i < getModelButtons().size(); i++) {
    		LoveEditorButtons button = (LoveEditorButtons)(getModelButtons().get(i));
    		if (button.getType() == LoveEditorEnum.TEXT) {
    			button.setVisible(bVisible);
    		}
    	}
    }
    
    private LoveEditorButtons getButtonForFunction(String function) {
    	for (int i = START_EXTRA_BUTTONS; i < getModelButtons().size(); i++) {
			if (getModelButtons().get(i).getFunction().equals(function)) {
				return (LoveEditorButtons)(getModelButtons().get(i));
			}
		}
    	return null;
    }
    
    private boolean containsButton(int x, int y) {
    	for (int i = START_EXTRA_BUTTONS; i < getModelButtons().size(); i++) {
    		LoveEditorButtons button = (LoveEditorButtons)(getModelButtons().get(i));
			if ((button.isVisible()) && (button.getType() == LoveEditorEnum.TEXT) && (button.intersects(x, y))) {
				return true;
			}
		}
    	return false;
    }
    
    @Override
    public void mouseButtonReleased(int x, int y, boolean isRightButton) {
        super.mouseButtonReleased(x, y, isRightButton);
    }
    
    @Override
    public void mouseDragged(int x, int y, boolean isRightButton) {
        super.mouseDragged(x, y, isRightButton);
        
        mousePressed(x, y, isRightButton);
    }
    
    @Override
    public void mousePressed(int x, int y, boolean isRightButton) {
        super.mousePressed(x, y, isRightButton);
        
        checkAndSetMouseXAndY(x, y);
        
        if ((mouseX >= 0) && (!containsButton(x, y))) {
        	if (isRightButton) {
        		if ((!level.getLevel()[mouseY][mouseX].getLayer().contains(LoveLevelEntity.LAYER_EMPTY)) || (level.getLevel()[mouseY][mouseX].getText() != null)) {
        			isLevelSolved = false;
            		level.getLevel()[mouseY][mouseX].getLayer().clear();
            		level.getLevel()[mouseY][mouseX].addLayer(LoveLevelEntity.LAYER_EMPTY);
            		level.getLevel()[mouseY][mouseX].setText(null);
            		level.setDetailsForUnderground();
        		}
    		} else if (valueType == LoveEditorEnum.BACKGROUND) {
    			if (level.getLevel()[mouseY][mouseX].getBackground() != valueId) {
        			isLevelSolved = false;
            		level.getLevel()[mouseY][mouseX].setBackground(valueId);
            		level.setDetailsForUnderground();
        		}
        	} else if (valueType == LoveEditorEnum.LAYER) {
        		if ((!level.getLevel()[mouseY][mouseX].getLayer().contains(valueId)) || (level.getLevel()[mouseY][mouseX].getText() != null)) {
        			isLevelSolved = false;
           			level.getLevel()[mouseY][mouseX].getLayer().clear();
           			level.getLevel()[mouseY][mouseX].setText(null);
           			level.getLevel()[mouseY][mouseX].addLayer(valueId);
            		level.setDetailsForUnderground();
        		}
        	} else if (valueType == LoveEditorEnum.TEXT) {
        		if ((level.getLevel()[mouseY][mouseX].getText() == null) || (!level.getLevel()[mouseY][mouseX].getText().getText().equals(valueText.getText()))) {
        			isLevelSolved = false;
        			level.getLevel()[mouseY][mouseX].getLayer().clear();
        			level.getLevel()[mouseY][mouseX].setText(valueText.getText().getClone());
        			level.checkLogic(true);
        		}
        	}
        	getApoButtonForFunction(FUNCTION_TEST).setVisible(isLevelValid());
    		getApoButtonForFunction(FUNCTION_UPLOAD).setVisible(isLevelSolved);
        }
    }
    
    @Override
    public void mouseMoved(int x, int y) {
        super.mouseMoved(x, y);
         
        checkAndSetMouseXAndY(x, y);
    }

	private void checkAndSetMouseXAndY(int x, int y) {
		if ((x >= 0) && (x < LoveGame.WIDTH * LoveGame.SCALE) &&
        	(y >= 3 * LoveGame.TILE_SIZE * LoveGame.SCALE) && (y < (3 + LoveLevel.LEVEL_HEIGHT) * LoveGame.TILE_SIZE * LoveGame.SCALE)) {
        	mouseX = (x) / (LoveGame.TILE_SIZE * LoveGame.SCALE);
        	mouseY = (y - 3 * LoveGame.TILE_SIZE * LoveGame.SCALE) / (LoveGame.TILE_SIZE * LoveGame.SCALE);
        } else {
        	mouseX = -1;
        	mouseY = -1;
        }
	}
    
	@Override
	public void dispose() {
		
	}

	@Override
	protected void doThink(float delta) {
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
    	renderMenuTitle();
    	
    	for (int i = 0; i < START_EXTRA_BUTTONS; i++) {
    		ApoButton button = this.getModelButtons().get(i);
            button.render(this.getMainPanel(), 0, 0);
        }
    	
    	Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
    	getMainPanel().getRenderer().begin(ShapeType.Filled);
    	for (int i = START_EXTRA_BUTTONS; i < getModelButtons().size(); i++) {
    		ApoButton button = this.getModelButtons().get(i);
            button.renderBefore(this.getMainPanel(), 0, 0);
        }
    	if ((this.valueText != null) && (mouseX >= 0)) {
    		valueText.renderLayerTextBackground(getMainPanel(),  (mouseX * LoveGame.TILE_SIZE * LoveGame.SCALE),  ((mouseY + 3) * LoveGame.TILE_SIZE * LoveGame.SCALE));
    	}
    	getMainPanel().getRenderer().end();
    	Gdx.graphics.getGL20().glDisable(GL20.GL_BLEND);
    	
    	getMainPanel().spriteBatch.begin();
    	getMainPanel().spriteBatch.enableBlending();
    	
    	if ((valueX >= 0) && (mouseX >= 0)) {
    		getMainPanel().spriteBatch.setColor(Constants.COLOR_WHITE[0], Constants.COLOR_WHITE[1], Constants.COLOR_WHITE[2], 0.5f);
    		getMainPanel().spriteBatch.draw(AssetLoader.mirrorTextureRegion[valueY][valueX], (mouseX * LoveGame.TILE_SIZE * LoveGame.SCALE), ((mouseY + 3) * LoveGame.TILE_SIZE * LoveGame.SCALE), LoveGame.TILE_SIZE * LoveGame.SCALE, LoveGame.TILE_SIZE * LoveGame.SCALE);
    		getMainPanel().spriteBatch.setColor(Constants.COLOR_WHITE[0], Constants.COLOR_WHITE[1], Constants.COLOR_WHITE[2], 1f);
    	}
    	
    	for (int i = START_EXTRA_BUTTONS; i < getModelButtons().size(); i++) {
    		ApoButton button = this.getModelButtons().get(i);
            button.render(this.getMainPanel(), 0, 0);
        }
    	if ((this.valueText != null) && (mouseX >= 0)) {
    		valueText.renderLayerText(getMainPanel(),  (mouseX * LoveGame.TILE_SIZE * LoveGame.SCALE),  ((mouseY + 3) * LoveGame.TILE_SIZE * LoveGame.SCALE));
    	}
    	getMainPanel().spriteBatch.end();
    	
    	getMainPanel().getRenderer().begin(ShapeType.Line);
    	for (int i = START_EXTRA_BUTTONS; i < getModelButtons().size(); i++) {
    		ApoButton button = this.getModelButtons().get(i);
            button.renderOutline(this.getMainPanel(), 0, 0, false);
        }
    	getMainPanel().getRenderer().end();
	}
    
    public void renderMenuTitle() {
    	renderBackground(0 * LoveGame.SCALE, 1 * LoveGame.SCALE, 3.8f * LoveGame.SCALE * LoveGame.TILE_SIZE - 2 * LoveGame.SCALE, 3 * LoveGame.SCALE * LoveGame.TILE_SIZE - 2 * LoveGame.SCALE, 1f, 0.5f);
    	renderBackground(1f * LoveGame.SCALE + 3.8f * LoveGame.SCALE * LoveGame.TILE_SIZE, 1 * LoveGame.SCALE, 8.7f * LoveGame.SCALE * LoveGame.TILE_SIZE - 2 * LoveGame.SCALE, 3 * LoveGame.SCALE * LoveGame.TILE_SIZE - 2 * LoveGame.SCALE, 1f, 0.5f);
    	if (switchButton.isSwitched()) {
    		renderBackground(1f * LoveGame.SCALE + 12.7f * LoveGame.SCALE * LoveGame.TILE_SIZE, 1 * LoveGame.SCALE, 5f * LoveGame.SCALE * LoveGame.TILE_SIZE - 2 * LoveGame.SCALE, (heightLengthEnum + 3.3f) * LoveGame.SCALE * LoveGame.TILE_SIZE - 2 * LoveGame.SCALE, 1f, 0.5f);
    	} else {
    		renderBackground(1f * LoveGame.SCALE + 12.7f * LoveGame.SCALE * LoveGame.TILE_SIZE, 1 * LoveGame.SCALE, 1.5f * LoveGame.SCALE * LoveGame.TILE_SIZE - 2 * LoveGame.SCALE, 2f * LoveGame.SCALE * LoveGame.TILE_SIZE - 2 * LoveGame.SCALE, 1f, 0.5f);	
    	}
    	if (isInfoShowing) {
    		renderBackground(LoveGame.WIDTH * LoveGame.SCALE * 0.15f, 4 * LoveGame.TILE_SIZE * LoveGame.SCALE, LoveGame.WIDTH * LoveGame.SCALE * 0.7f, 13f * LoveGame.SCALE * LoveGame.TILE_SIZE, 1f, 0.75f);
    	}
    	
    	this.getMainPanel().spriteBatch.begin();
    	String s = "Background";
    	this.getMainPanel().drawString(s, 3 * LoveGame.SCALE, 2 * LoveGame.SCALE, COLOR_DARK, AssetLoader.font25, DrawString.BEGIN, false, false);
    	
    	s = "Layer";
    	this.getMainPanel().drawString(s, (LoveGame.TILE_SIZE * 4 * LoveGame.SCALE), 2 * LoveGame.SCALE, COLOR_DARK, AssetLoader.font25, DrawString.BEGIN, false, false);
    	
    	s = "Text";
    	this.getMainPanel().drawString(s, (LoveGame.TILE_SIZE * 12.9f * LoveGame.SCALE) + textChangeX, 2 * LoveGame.SCALE, COLOR_DARK, AssetLoader.font25, DrawString.BEGIN, false, false);
    	
    	if (isInfoShowing) {
    		s = "Editor";
    		this.getMainPanel().drawString(s, (LoveGame.WIDTH  * LoveGame.SCALE) / 2, 4.4f * LoveGame.TILE_SIZE * LoveGame.SCALE, COLOR_DARK, AssetLoader.font40, DrawString.MIDDLE, false, false);
    		
    		String[] editorText = Localization.getInstance().getLines("editor_text");
    		for (int i = 0; i < editorText.length; i++) {
    			s = editorText[i];
        		this.getMainPanel().drawString(s, (LoveGame.WIDTH  * LoveGame.SCALE) / 2, (6.2f + 0.8f * i) * LoveGame.TILE_SIZE * LoveGame.SCALE, Constants.COLOR_BLACK, AssetLoader.font25, DrawString.MIDDLE, false, false);
    		}
    	}
    	this.getMainPanel().spriteBatch.end();    	
    }
    
    protected void renderBackground(float x, float y, float width, float height, float stroke, float alpha) {
    	float rem = stroke/2f;
    	
    	Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
    	this.getMainPanel().getRenderer().begin(ShapeType.Filled);
    	this.getMainPanel().getRenderer().setColor(Constants.COLOR_WHITE[0], Constants.COLOR_WHITE[1], Constants.COLOR_WHITE[2], alpha);
    	this.getMainPanel().getRenderer().roundedRect(x + rem, y + rem, width, height, 3);
    	this.getMainPanel().getRenderer().end();
		Gdx.graphics.getGL20().glDisable(GL20.GL_BLEND);
		
		Gdx.gl20.glLineWidth(stroke);
		this.getMainPanel().getRenderer().begin(ShapeType.Line);
		this.getMainPanel().getRenderer().setColor(COLOR_BUTTON[0], COLOR_BUTTON[1], COLOR_BUTTON[2], 1f);
		this.getMainPanel().getRenderer().roundedRectLine(x + rem, y + rem, width, height, 3);
		this.getMainPanel().getRenderer().end();
		Gdx.gl20.glLineWidth(1f);
    }
}
