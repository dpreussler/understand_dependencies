package de.jodamob.android.dependencies.simple_factory;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.jodamob.android.dependencies.components.Tracker;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SimpleActivityTest {

    @Mock
    Tracker tracker;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Dependencies.instance = mock(Dependencies.class);
        when(Dependencies.instance.getTracker()).thenReturn(tracker);
    }

    @Test
    public void should_track() {
        new SimpleActivity().onCreate(null);
        verify(tracker).trackStarted();
    }
}