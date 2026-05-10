package org.apogames.love.game.love;

public class LoveOrientation {

    private final EnumOrientation orientation;
    private final boolean hasBefore;
    private final boolean hasAfter;
    private final int max;

    public LoveOrientation(EnumOrientation orientation, boolean hasBefore, boolean hasAfter, int max) {
        this.orientation = orientation;
        this.hasBefore = hasBefore;
        this.hasAfter = hasAfter;
        this.max = max;
    }

    public EnumOrientation getOrientation() {
        return orientation;
    }

    public boolean isHasBefore() {
        return hasBefore;
    }

    public boolean isHasAfter() {
        return hasAfter;
    }

    public int getMax() {
        return max;
    }

    public LoveOrientation getClone() {
        return new LoveOrientation(orientation, hasBefore, hasAfter, max);
    }
}
