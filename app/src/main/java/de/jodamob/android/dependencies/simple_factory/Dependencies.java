package de.jodamob.android.dependencies.simple_factory;

import android.support.annotation.VisibleForTesting;

import de.jodamob.android.dependencies.components.BackgroundServiceManager;
import de.jodamob.android.dependencies.components.GoogleAnalyticsTracker;
import de.jodamob.android.dependencies.components.Tracker;

/**
 * simple factory version
 */
public class Dependencies {

    @VisibleForTesting
    static Dependencies instance = new Dependencies();

    // singleton
    private final BackgroundServiceManager serviceManager = new BackgroundServiceManager(getTracker());

    public Tracker getTracker() {
        return new GoogleAnalyticsTracker();
    }

    public BackgroundServiceManager getBackgroundServiceManager() {
        return serviceManager;
    }

    public static Dependencies getInstance() {
        return instance;
    }
}
