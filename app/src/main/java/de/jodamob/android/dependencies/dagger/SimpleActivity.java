package de.jodamob.android.dependencies.dagger;

import android.app.Activity;
import android.os.Bundle;

import javax.inject.Inject;

import de.jodamob.android.dependencies.R;
import de.jodamob.android.dependencies.components.Tracker;

public class SimpleActivity extends Activity {

    @Inject Tracker tracker;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_main);
        Dependencies.getInjector().inject(this);
        tracker.trackStarted();
    }
}
