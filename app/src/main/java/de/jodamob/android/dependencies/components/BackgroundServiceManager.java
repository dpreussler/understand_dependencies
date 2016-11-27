package de.jodamob.android.dependencies.components;

import javax.inject.Inject;

public class BackgroundServiceManager {

    private final Tracker tracker;

    @Inject
    public BackgroundServiceManager(Tracker tracker) {
        this.tracker = tracker;
    }
}
