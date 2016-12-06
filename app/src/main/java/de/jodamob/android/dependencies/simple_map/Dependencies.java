package de.jodamob.android.dependencies.simple_map;

import android.support.annotation.VisibleForTesting;

import java.util.HashMap;
import java.util.Map;

import de.jodamob.android.dependencies.components.GoogleAnalyticsTracker;
import de.jodamob.android.dependencies.components.Tracker;

/**
 * simple factory version with predefined map
 */
public class Dependencies {

    private final static Map<Class, Object> modules = new HashMap<>();
    static {
        GoogleAnalyticsTracker tracker = new GoogleAnalyticsTracker();
        modules.put(Tracker.class, tracker);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(Class<T> clzz) {
        return (T) modules.get(clzz);
    }

    @VisibleForTesting
    static <T> void set(Class<T> clzz, T instance) {
        modules.put(clzz, instance);
    }
}
