package de.jodamob.android.dependencies.toothpick;

import android.app.Activity;
import android.os.Bundle;

import javax.inject.Inject;

import de.jodamob.android.dependencies.R;
import de.jodamob.android.dependencies.components.BackgroundServiceManager;
import de.jodamob.android.dependencies.components.Presenter;
import de.jodamob.android.dependencies.components.Tracker;

public class ScopedActivity extends Activity {

    @Inject BackgroundServiceManager serviceManager;
    @Inject Tracker tracker;
    @Inject Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Dependencies.createScopeFor(this);
        tracker.trackStarted();
    }

    @Override
    protected void onDestroy() {
        Dependencies.closeScopeFor(this);
        super.onDestroy();
    }
}
