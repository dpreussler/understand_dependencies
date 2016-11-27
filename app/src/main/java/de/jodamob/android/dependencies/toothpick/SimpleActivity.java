package de.jodamob.android.dependencies.toothpick;

import android.app.Activity;
import android.os.Bundle;

import javax.inject.Inject;

import de.jodamob.android.dependencies.R;
import de.jodamob.android.dependencies.components.BackgroundServiceManager;
import de.jodamob.android.dependencies.components.Tracker;

public class SimpleActivity extends Activity {

    @Inject BackgroundServiceManager serviceManager;
    @Inject Tracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Dependencies.getInstance(getApplication()).inject(this);
        tracker.trackStarted();
        // TODO do something with the serviceManager...
    }
}
