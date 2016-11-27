package de.jodamob.android.dependencies.reflection;

import org.junit.Before;
import org.junit.Test;

import de.jodamob.android.dependencies.components.BackgroundServiceManager;
import de.jodamob.android.dependencies.components.Tracker;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SimpleActivityTest {

    Tracker tracker;

    @Before
    public void setup() {
        tracker = mock(Tracker.class);
        Dependencies.set(Tracker.class, tracker);
        Dependencies.set(BackgroundServiceManager.class, mock(BackgroundServiceManager.class));
    }

    @Test
    public void should_track() {
        new SimpleActivity().onCreate(null);
        verify(tracker).trackStarted();
    }
}