package de.jodamob.android.dependencies;

import android.app.Application;

import de.jodamob.android.dependencies.toothpick.Dependencies;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initToothpick();
    }

    private void initToothpick() {
        Dependencies.init(this);
    }
}
