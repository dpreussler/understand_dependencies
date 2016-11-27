package de.jodamob.android.dependencies.reflection;

import android.annotation.SuppressLint;
import android.support.annotation.VisibleForTesting;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import de.jodamob.android.dependencies.components.GoogleAnalyticsTracker;
import de.jodamob.android.dependencies.components.Tracker;

public class Dependencies {

    private final static Map<Class, Object> modules = new HashMap<>();
    static {
        modules.put(Tracker.class, GoogleAnalyticsTracker.class);
    }

    @SuppressWarnings({"Since15", "unchecked"})
    @SuppressLint("NewApi")
    static <T> T get(Class<T> clzz) {
        if (modules.get(clzz) instanceof Class) {
            // found remapping to other class
            return (T) get((Class<Object>) modules.get(clzz));
        } else if (modules.get(clzz) != null) {
            // found remapping to instance
            return (T) modules.get(clzz);
        }

        Constructor<?>[] constructors = clzz.getDeclaredConstructors();
        for(Constructor constructor: constructors) {
            if (constructor.getDeclaredAnnotation(Inject.class) != null) {
                Class[] types = constructor.getParameterTypes();
                Object[] instances = new Object[types.length];
                for (int i=1; i<instances.length; i++) {
                    instances[i] = get(types[i]);
                }
                try {
                    return (T) constructor.newInstance(instances);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @VisibleForTesting
    static <T> void set(Class<T> clzz, T instance) {
        modules.put(clzz, instance);
    }

    @VisibleForTesting
    static void set(Class mapFrom, Class mapTo) {
        modules.put(mapFrom, mapTo);
    }
}
