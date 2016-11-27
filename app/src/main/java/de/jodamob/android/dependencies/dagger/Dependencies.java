package de.jodamob.android.dependencies.dagger;

import android.app.Application;
import android.content.Context;

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

    public static void init(Application application) {
        component = DaggerApplicationComponent.builder().baseModule(new BaseModule(application)).build();
    }
}
