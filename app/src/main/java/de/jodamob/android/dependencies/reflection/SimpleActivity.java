package de.jodamob.android.dependencies.reflection;

import android.app.Activity;
import android.os.Bundle;

import javax.inject.Inject;

import de.jodamob.android.dependencies.R;
import de.jodamob.android.dependencies.components.Tracker;

public class SimpleActivity extends Activity {

    private final Tracker tracker = Dependencies.get(Tracker.class);

    @Inject Tracker trackerViaInject;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_main);
        Dependencies.inject(this);
        tracker.trackStarted();
    }
}
