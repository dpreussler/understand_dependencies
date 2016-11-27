package de.jodamob.android.dependencies.reflection;

import android.app.Activity;
import android.os.Bundle;

import de.jodamob.android.dependencies.R;
import de.jodamob.android.dependencies.components.BackgroundServiceManager;
import de.jodamob.android.dependencies.components.Tracker;

public class SimpleActivity extends Activity {

    private final BackgroundServiceManager serviceManager = Dependencies.get(BackgroundServiceManager.class);
    private final Tracker tracker = Dependencies.get(Tracker.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tracker.trackStarted();

        // TODO do something with the serviceManager...
    }
}
