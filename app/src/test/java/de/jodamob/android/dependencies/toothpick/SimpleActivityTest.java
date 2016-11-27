package de.jodamob.android.dependencies.toothpick;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.jodamob.android.dependencies.components.Tracker;

public class SimpleActivityTest {

    @Mock
    Tracker tracker;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_track() {
        new SimpleActivity().onCreate(null);
        tracker.trackStarted();
    }
}