package de.jodamob.android.dependencies.simple_factory;

import android.support.annotation.VisibleForTesting;

import de.jodamob.android.dependencies.components.GoogleAnalyticsTracker;
import de.jodamob.android.dependencies.components.Tracker;

/**
 * simple factory version
 */
public class Dependencies {

    @VisibleForTesting
    static Dependencies instance = new Dependencies();

    public static Dependencies getInstance() {
        return instance;
    }

    public Tracker getTracker() {
        return new GoogleAnalyticsTracker();
    }

}
