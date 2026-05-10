package org.apogames.love.entity;

import org.apogames.love.backend.GameScreen;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

/**
 * Klasse von der Button und Player erben und einige grundlegene Sachen zur
 * Verfügung stellt
 *
 * @author Dirk Aporius
 */
public class ApoEntity {
    private float x, y, startX, startY, vecX, vecY, lastX;

    private float width, height;

    private boolean bSelect, visible, bClose, bUse, bOpaque, bMoveable, bLastOnGround, bInFunction;

    public ApoEntity(float x, float y, float width, float height) {
        this.startX = x;
        this.startY = y;
        this.width = width;
        this.height = height;
        this.bOpaque = true;
        this.init();
    }

    public void init() {
        this.x = this.startX;
        this.lastX = this.x;
        this.y = this.startY;
        this.bSelect = false;
        this.visible = true;
        this.bLastOnGround = false;
        this.bInFunction = false;
        this.vecX = 0.0F;
        this.vecY = 0.0F;
        this.setBUse(false);
    }

    public boolean isbInFunction() {
        return bInFunction;
    }

    public void setbInFunction(boolean bInFunction) {
        this.bInFunction = bInFunction;
    }

    public boolean isbLastOnGround() {
        return bLastOnGround;
    }

    public void setbLastOnGround(boolean bLastOnGround) {
        this.bLastOnGround = bLastOnGround;
    }

    public boolean isMoveable() {
        return bMoveable;
    }

    public void setMoveable(boolean bMoveable) {
        this.bMoveable = bMoveable;
    }

    public float getStartX() {
        return this.startX;
    }

    public void setStartX(float startX) {
        this.startX = startX;
    }

    public float getStartY() {
        return this.startY;
    }

    public void setStartY(float startY) {
        this.startY = startY;
    }

    public boolean isBOpaque() {
        return this.bOpaque;
    }

    public void setBOpaque(boolean bOpaque) {
        this.bOpaque = bOpaque;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isSelect() {
        return this.bSelect;
    }

    public void setSelect(boolean bSelect) {
        this.bSelect = bSelect;
    }

    public boolean isBClose() {
        return this.bClose;
    }

    public void setBClose(boolean bClose) {
        this.bClose = bClose;
    }

    public boolean isBUse() {
        return this.bUse;
    }

    public void setBUse(boolean bUse) {
        this.bUse = bUse;
    }

    public float getVecY() {
        return this.vecY;
    }

    public void setVecY(float vecY) {
        this.vecY = vecY;
    }

    public float getVecX() {
        return this.vecX;
    }

    public void setVecX(float vecX) {
        this.vecX = vecX;
    }

    public float getWidth() {
        return this.width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return this.height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getX() {
        return this.x;
    }

    public float getXMiddle() {
        return this.x + this.width / 2;
    }

    public void setX(float x) {
        this.lastX = this.x;
        this.x = x;
    }

    public float getLastX() {
        return this.lastX;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public boolean intersects(float x, float y) {
        return this.intersects(x, y, 1, 1);
    }

    public boolean intersects(float x, float y, float width, float height) {
        if (this.getRec().overlaps(new Rectangle(x, y, width, height))) {
            return true;
        }
        return false;
    }

    public boolean intersects(ApoEntity entity) {
        if (this.getRec().overlaps(entity.getRec())) {
            return true;
        }
        return false;
    }

    public boolean contains(float x, float y, float width, float height) {
        return this.getRec().contains(new Rectangle(x, y, width, height));
    }

    public boolean contains(ApoEntity entity) {
        return this.getRec().contains(entity.getRec());
    }

    public Rectangle getRec() {
        return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    public Rectangle getSubRec(Rectangle source, Rectangle part) {
        Rectangle sub = new Rectangle();

        if (source.x > part.x) {
            sub.x = 0;
        } else {
            sub.x = part.x - source.x;
        }

        if (source.y > part.y) {
            sub.y = 0;
        } else {
            sub.y = part.y - source.y;
        }

        sub.width = part.width;
        sub.height = part.height;

        return sub;
    }

    public void think(int delta) {
    }

    public void render(GameScreen screen, int x, int y) {
        if (this.isVisible()) {
            if ((this.isSelect()) || (this.bInFunction)) {
                screen.getRenderer().begin(ShapeType.Filled);
                screen.getRenderer().setColor(255f / 255.0f, 0f / 255.0f, 0f / 255.0f, 1f);
                screen.getRenderer().rect(this.getX() + x, this.getY() + y, this.getWidth() - 1, this.getHeight() - 1);
                screen.getRenderer().end();
            }
        }
    }

    public void render(GameScreen screen) {
        this.render(screen, 0, 0);
    }

}
