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

import static toothpick.Toothpick.closeScope;
import static toothpick.Toothpick.openScope;
import static toothpick.Toothpick.openScopes;

public class Dependencies {
    private static final String DEFAULT_SCOPE = "DEFAULT_SCOPE";

    public static void init(Application application) {
        setupBaseScope(application);
    }

    private static Scope setupBaseScope(Application application) {
        setupReflectionFreeConfiguration();
        Scope scope = openScope(DEFAULT_SCOPE);
        scope.installModules(getBaseModules(application));
        return scope;
    }

    protected static Module[] getBaseModules(Application application) {
        return new Module[]{new BaseModule(application), new SmoothieApplicationModule(application)};
    }

    private static void setupReflectionFreeConfiguration() {
        Toothpick.setConfiguration(Configuration.forProduction().disableReflection());
        FactoryRegistryLocator.setRootRegistry(new de.jodamob.android.dependencies.FactoryRegistry());
        MemberInjectorRegistryLocator.setRootRegistry(new de.jodamob.android.dependencies.MemberInjectorRegistry());
    }

    public static void inject(Activity activity) {
        Toothpick.inject(activity, openScope(DEFAULT_SCOPE));
    }

    private static class BaseModule extends Module {

        public BaseModule(Application application) {
            bind(Tracker.class).to(GoogleAnalyticsTracker.class);
        }
    }

    //////////////
    // scope
    //////////////
    private static class ScopeModule extends Module {

        public ScopeModule(Activity activity) {
            bind(Activity.class).toInstance(activity);
        }
    }

    public static void createScopeFor(Activity activity) {
        Scope scope = openScopes(DEFAULT_SCOPE, activity.getClass().getName());
        scope.installModules(new ScopeModule(activity));
    }

    public static void closeScopeFor(Activity activity) {
        closeScope(activity.getClass().getName());
    }

    public static void injectScoped(Activity activity) {
        Toothpick.inject(activity, openScope(activity.getClass().getName()));
    }

}
