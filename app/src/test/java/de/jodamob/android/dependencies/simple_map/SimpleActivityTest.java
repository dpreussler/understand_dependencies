package de.jodamob.android.dependencies.simple_map;

import org.junit.Before;
import org.junit.Test;

import de.jodamob.android.dependencies.components.Tracker;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SimpleActivityTest {

    Tracker tracker;

    @Before
    public void setup() {
        tracker = mock(Tracker.class);
        Dependencies.set(Tracker.class, tracker);
    }

    @Test
    public void should_track() {
        new SimpleActivity().onCreate(null);
        verify(tracker).trackStarted();
    }
}