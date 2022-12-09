package com.said.oubella.riadnakhla;

import android.app.Application;
import android.content.Context;

import com.said.oubella.riadnakhla.prefs.AppThemePrefs;

public final class App extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        AppThemePrefs.applyTheme(this);
    }
}
