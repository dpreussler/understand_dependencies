package de.jodamob.android.dependencies.dagger;

import android.app.Application;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;
import de.jodamob.android.dependencies.components.GoogleAnalyticsTracker;
import de.jodamob.android.dependencies.components.Tracker;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SimpleActivityTest {

    @Mock
    Tracker tracker;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Dependencies.init(DaggerDependencies_AppComponent.builder().baseModule(new TestModule()).build());
    }

    @Test
    public void should_track() {
        new SimpleActivity().onCreate(null);
        verify(tracker).trackStarted();
    }


    private class TestModule extends Dependencies.BaseModule {

        TestModule() {
            super(mock(Application.class));
        }

        @Provides
        public Tracker provideTracker() {
            return tracker;
        }
    }
}