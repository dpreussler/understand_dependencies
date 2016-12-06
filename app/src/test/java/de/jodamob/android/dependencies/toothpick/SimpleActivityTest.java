package de.jodamob.android.dependencies.toothpick;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.jodamob.android.dependencies.components.Tracker;
import toothpick.testing.ToothPickRule;

import static org.mockito.Mockito.verify;

public class SimpleActivityTest {

    @Mock
    Tracker tracker;

    @Rule
    public ToothPickRule toothPickRule = new ToothPickRule(this, "DEFAULT_SCOPE");

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
//        Dependencies.set(toothPickRule.getScope());
    }

    @Test
    public void should_track() {
        new SimpleActivity().onCreate(null);
        verify(tracker).trackStarted();
    }

}