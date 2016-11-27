package de.jodamob.android.dependencies.toothpick;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.VisibleForTesting;

import de.jodamob.android.dependencies.components.GoogleAnalyticsTracker;
import de.jodamob.android.dependencies.components.Tracker;
import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;
import toothpick.configuration.Configuration;
import toothpick.smoothie.module.SmoothieApplicationModule;

import static toothpick.Toothpick.openScope;

public class Dependencies {
    private static final String DEFAULT_SCOPE = "DEFAULT_SCOPE";

    private static Dependencies instance;
    private final Scope baseScope;

    protected Dependencies(Application application) {
        baseScope = setupBaseScope(application);
    }

    public static Dependencies getInstance(Application application) {
        if (instance == null) {
            instance = new Dependencies(application);
        }
        return instance;
    }

    protected Scope setupBaseScope(Application application) {
        setupReflectionFreeConfiguration();
        Scope scope = openScope(DEFAULT_SCOPE);
        scope.installModules(new BaseModule(application));
        scope.installModules(new SmoothieApplicationModule(application));
        return scope;
    }

    private void setupReflectionFreeConfiguration() {
        Toothpick.setConfiguration(Configuration.forProduction().disableReflection());
        // TODO
//        FactoryRegistryLocator.setRootRegistry(new de.jodamob.android.dependencies.FactoryRegistry());
//        MemberInjectorRegistryLocator.setRootRegistry(new de.jodamob.android.dependencies.MemberInjectorRegistry());
    }

    public void inject(Activity activity) {
        Toothpick.inject(activity, baseScope);
    }

    @VisibleForTesting
    static void set(Dependencies instance) {
        Dependencies.instance = instance;
    }

    private static class BaseModule extends Module {

        public BaseModule(Application application) {
            bind(Tracker.class).to(GoogleAnalyticsTracker.class);
        }
    }
}
