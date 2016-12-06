package de.jodamob.android.dependencies.dagger;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;
import de.jodamob.android.dependencies.components.GoogleAnalyticsTracker;
import de.jodamob.android.dependencies.components.Tracker;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

public class Dependencies {

    @Module
    static class BaseModule {
        private final Context applicationContext;

        BaseModule(Application application) {
            this.applicationContext = application;
        }

        @Provides
        public Tracker provideTracker() {
            return new GoogleAnalyticsTracker();
        }
    }

//    @Component(modules = {BaseModule.class})
//    interface AppComponent {
//        void inject(SimpleActivity activity);
//    }

    @VisibleForTesting
    public static void init(AppComponent component) {
        Dependencies.component = component;
    }

    public static void init(Application application) {
        init(DaggerDependencies_AppComponent.builder().baseModule(new BaseModule(application)).build());
    }

    private static AppComponent component;

    public static AppComponent getInjector() {
        return component;
    }


    //////////////////
    // scopes
    //////////////////

    @Component(modules = {BaseModule.class})
    interface AppComponent {
        void inject(SimpleActivity activity);
        ScopeComponent plus(ScopeModule module);
    }

    @ActivityScope
    public static ScopeComponent createScope(Activity activity) {
        return component.plus(new ScopeModule(activity));
    }

    @Module
    static class ScopeModule {

        private Activity activity;

        public ScopeModule(Activity activity) {
            this.activity = activity;
        }

        @Provides @ActivityScope
        public Activity provideActivity() {
            return activity;
        }
    }

    @Scope
    @Retention(RUNTIME)
    public @interface ActivityScope {}

    @ActivityScope
    @Subcomponent(modules = {ScopeModule.class})
    interface ScopeComponent {
        void inject(ScopeActivity activity);
    }
}