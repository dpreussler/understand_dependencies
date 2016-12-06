package de.jodamob.android.dependencies.simple_map;

import android.app.Activity;
import android.os.Bundle;

import de.jodamob.android.dependencies.R;
import de.jodamob.android.dependencies.components.Tracker;

public class SimpleActivity extends Activity {

    private final Tracker tracker = Dependencies.get(Tracker.class);

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_main);
        tracker.trackStarted();
    }
}
