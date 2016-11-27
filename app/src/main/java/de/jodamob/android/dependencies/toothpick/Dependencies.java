package de.jodamob.android.dependencies.toothpick;

import android.app.Activity;
import android.app.Application;

import de.jodamob.android.dependencies.components.GoogleAnalyticsTracker;
import de.jodamob.android.dependencies.components.Tracker;
import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;
import toothpick.configuration.Configuration;
import toothpick.registries.FactoryRegistryLocator;
import toothpick.registries.MemberInjectorRegistryLocator;
import toothpick.smoothie.module.SmoothieApplicationModule;

import static toothpick.Toothpick.openScope;

public class Dependencies {
    private static final String DEFAULT_SCOPE = "DEFAULT_SCOPE";

    public static void init(Application application) {
        new Dependencies().setupBaseScope(application);
    }

    private Scope setupBaseScope(Application application) {
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

    public static void inject(Activity activity) {
        Toothpick.inject(activity, openScope(DEFAULT_SCOPE));
    }

    private static class BaseModule extends Module {

        public BaseModule(Application application) {
            bind(Tracker.class).to(GoogleAnalyticsTracker.class);
        }
    }
}
