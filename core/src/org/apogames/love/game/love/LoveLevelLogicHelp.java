package org.apogames.love.game.love;

public class LoveLevelLogicHelp {

    private final LoveLevelEntityString first;
    private final LoveLevelEntityString second;
    private final LoveLevelEntityString third;

    public LoveLevelLogicHelp(final LoveLevelEntityString first, final LoveLevelEntityString second, LoveLevelEntityString third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public LoveLevelEntityString getFirst() {
        return first;
    }

    public LoveLevelEntityString getSecond() {
        return second;
    }

    public LoveLevelEntityString getThird() {
        return third;
    }
}
