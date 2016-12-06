package de.jodamob.android.dependencies;

import android.app.Activity;
import android.os.Bundle;

import de.jodamob.android.dependencies.components.GoogleAnalyticsTracker;
import de.jodamob.android.dependencies.components.Tracker;


public class LonelyActivity extends Activity {

    /**
     * no testable!
     */
    private final Tracker tracker = new GoogleAnalyticsTracker();

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_main);
        tracker.trackStarted();
    }
}
