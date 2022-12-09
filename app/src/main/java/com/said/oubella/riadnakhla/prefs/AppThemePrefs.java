package com.said.oubella.riadnakhla.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

public final class AppThemePrefs {

    private volatile static AppThemePrefs INSTANCE = null;

    private static final String THEME_KEY = "isDark";
    private static final String PREF_NAME = "theme";

    private final SharedPreferences prefs;

    private AppThemePrefs(final Context context) {
        this.prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void toggleTheme() {
        prefs.edit().putBoolean(THEME_KEY, !isDark()).apply();
    }

    public boolean isDark() {
        return prefs.getBoolean(THEME_KEY, false);
    }

    private int nightMode() {
        return isDark() ?
                AppCompatDelegate.MODE_NIGHT_YES :
                AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
    }

    public static AppThemePrefs getInstance(Context context) {
        if (INSTANCE != null) {
            return INSTANCE;
        } else {
            synchronized (AppThemePrefs.class) {
                if (INSTANCE == null)
                    INSTANCE = new AppThemePrefs(context);
                return INSTANCE;
            }
        }
    }

    public static void applyTheme(Context context) {
        AppCompatDelegate.setDefaultNightMode(AppThemePrefs.getInstance(context).nightMode());
    }
}
