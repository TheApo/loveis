package org.apogames.love.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;

import static com.badlogic.gdx.utils.I18NBundle.createBundle;

/**
 * Holds the active I18NBundle and manages the locale.
 */
public final class Localization {
    private static final Localization INSTANCE = new Localization();

    private static final String BUNDLE = "i18n/love";

    private Locale locale;
    private I18NBundle common;

    private Localization() {
        this.locale = Locale.ENGLISH;
    }

    private void initialize() {
        FileHandle internal = Gdx.files.internal(BUNDLE);
        common = createBundle(internal, locale);
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
        initialize();
    }

    public Locale getLocale() {
        return locale;
    }

    public I18NBundle getCommon() {
        if (common == null) {
            initialize();
        }
        return common;
    }

    public String get(String key) {
        return getCommon().get(key);
    }

    public String[] getLines(String key) {
        return get(key).split("\n", -1);
    }

    public static Localization getInstance() {
        return INSTANCE;
    }
}
