package de.jodamob.android.dependencies.dagger;

import android.app.Application;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import de.jodamob.android.dependencies.components.GoogleAnalyticsTracker;
import de.jodamob.android.dependencies.components.Tracker;

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

    @Component(modules = {BaseModule.class})
    interface AppComponent {
        void inject(SimpleActivity activity);
    }

    private static AppComponent component;

    public static AppComponent getInjector() {
        return component;
    }

    @VisibleForTesting
    public static void init(AppComponent component) {
        Dependencies.component = component;
    }

    public static void init(Application application) {
        init(DaggerDependencies_AppComponent.builder().baseModule(new BaseModule(application)).build());
    }
}
