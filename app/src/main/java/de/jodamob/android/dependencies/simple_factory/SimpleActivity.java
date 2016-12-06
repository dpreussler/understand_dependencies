package de.jodamob.android.dependencies.simple_factory;

import android.app.Activity;
import android.os.Bundle;

import de.jodamob.android.dependencies.R;
import de.jodamob.android.dependencies.components.Tracker;

import static de.jodamob.android.dependencies.simple_factory.Dependencies.getInstance;

public class SimpleActivity extends Activity {

    private final Tracker tracker = getInstance().getTracker();

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_main);
        tracker.trackStarted();
    }
}
