package de.jodamob.android.dependencies.components;

import android.app.Activity;

import javax.inject.Inject;

public class Presenter {

    private final Activity activity;

    @Inject
    public Presenter(Activity activity) {
        this.activity = activity;
    }
}
