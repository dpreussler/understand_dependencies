package de.jodamob.android.dependencies;

import android.app.Application;


public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initToothpick();
        initDagger();
    }

    private void initDagger() {
        de.jodamob.android.dependencies.dagger.Dependencies.init(this);
    }

    private void initToothpick() {
        de.jodamob.android.dependencies.toothpick.Dependencies.init(this);
    }
}
