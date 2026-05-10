/*
 * Copyright (c) 2005-2017 Dirk Aporius <dirk.aporius@gmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.apogames.love;

import java.util.Locale;

import org.apogames.love.common.Localization;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class Constants {
    public static String REGION = "en";

    public final static GlyphLayout glyphLayout = new GlyphLayout();

    public final static String USERLEVELS_GETPHP = "https://www.apo-games.de/love/get_level.php";
    public final static String USERLEVELS_SAVEPHP = "https://www.apo-games.de/love/save_level.php";
    public final static String USERLEVELS_SAVEPHP_RATING = "https://www.apo-games.de/love/save_rating.php";
    public final static String USERLEVELS_SAVEPHP_COUNT = "https://www.apo-games.de/love/save_levelcount.php";

    public final static String PROPERTY_NAME = "Love is ...";
    public static final String PROGRAM_NAME = "=== " + PROPERTY_NAME + " ===";
    public static final double VERSION = 0.01;
    public static boolean FPS = false;
    public static final float MAX_SCALE = 0.5f;
    public static final int GAME_WIDTH = (int)(960 * MAX_SCALE);
    public static final int GAME_HEIGHT = (int)(1200 * MAX_SCALE);

    public static float[] COLOR_CLEAR = new float[]{72f / 255f, 152f / 255f, 72f / 255f, 1f};
    public static final float[] COLOR_WHITE = new float[]{255f / 255f, 255f / 255f, 255f / 255f, 1f};
    public static final float[] COLOR_BLUE_BRIGHT = new float[]{128f / 255f, 128f / 255f, 255f / 255f, 1f};
    public static final float[] COLOR_BLUE = new float[]{0f / 255f, 0f / 255f, 255f / 255f, 1f};
    public static final float[] COLOR_GREEN_BRIGHT = new float[]{128f / 255f, 255f / 255f, 128f / 255f, 1f};
    public static final float[] COLOR_GREEN = new float[]{0f / 255f, 255f / 255f, 0f / 255f, 1f};
    public static final float[] COLOR_RED_LIGHT = new float[]{255f / 255f, 128f / 255f, 128f / 255f, 1f};
    public static final float[] COLOR_RED = new float[]{255f / 255f, 0f / 255f, 0f / 255f, 1f};
    public static final float[] COLOR_YELLOW = new float[]{255f / 255f, 255f / 255f, 0f / 255f, 1f};
    public static final float[] COLOR_BLACK = new float[]{0f / 255f, 0f / 255f, 0f / 255f, 1f};
    public static final float[] COLOR_GRAY = new float[]{99f / 255f, 99f / 255f, 99f / 255f, 1f};
    public static final float[] COLOR_GRAY_BRIGHT = new float[]{199f / 255f, 199f / 255f, 199f / 255f, 1f};
    public static final float[] COLOR_GRAY_BLUE = new float[]{30f / 255f, 84f / 255f, 139f / 255f, 1f};
    public static final float[] COLOR_BACKGROUND_BRIGHT = new float[]{126f / 255f, 126f / 255f, 146f / 255f, 1f};
    public static final float[] COLOR_BACKGROUND = new float[]{26f / 255f, 26f / 255f, 46f / 255f, 1f};
    public static final float[] COLOR_BUTTONS = new float[]{55f / 255f, 44f / 255f, 72f / 255f, 1f};

    public static boolean HELP_TIMER = false;

    public static boolean IS_ANDROID = false;
    public static boolean IS_HTML = false;
    public static boolean IS_MOBILE = false;

    public static String round(double zahl, int stellen) {
        double d = (double) ((int) zahl + (Math.round(Math.pow(10, stellen) * (zahl - (int) zahl))) / (Math.pow(10, stellen)));
        String result = String.valueOf(d);
        if (result.indexOf(".") < result.length() - stellen) {
            result = result.substring(0, result.indexOf(".") + stellen + 1);
        } else if (result.indexOf(".") >= result.length() - stellen) {
            result = result + "0";
        }
        return result;
    }

    /**
     * Switches the active language. Accepts a 2-letter language tag (e.g. "de", "en").
     */
    public static void setLanguage(final String region) {
        REGION = (region != null) ? region : "en";
        Locale locale = "de".equals(REGION) ? Locale.GERMAN : Locale.ENGLISH;
        Localization.getInstance().setLocale(locale);
    }
}
