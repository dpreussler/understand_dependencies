package de.jodamob.android.dependencies.simple_factory;

import org.junit.Before;
import org.junit.Test;

import de.jodamob.android.dependencies.components.BackgroundServiceManager;
import de.jodamob.android.dependencies.components.Tracker;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SimpleActivityTest {

    Tracker tracker;

    @Before
    public void setup() {
        Dependencies.instance = mock(Dependencies.class);
        tracker = mock(Tracker.class);
        when(Dependencies.instance.getTracker()).thenReturn(tracker);
        when(Dependencies.instance.getBackgroundServiceManager()).thenReturn(mock(BackgroundServiceManager.class));

    }

    @Test
    public void should_track() {
        new SimpleActivity().onCreate(null);
        verify(tracker).trackStarted();
    }
}