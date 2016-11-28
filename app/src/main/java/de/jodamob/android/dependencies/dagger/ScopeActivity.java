package de.jodamob.android.dependencies.dagger;

import android.app.Activity;
import android.os.Bundle;

import javax.inject.Inject;

import de.jodamob.android.dependencies.R;
import de.jodamob.android.dependencies.components.BackgroundServiceManager;
import de.jodamob.android.dependencies.components.Presenter;
import de.jodamob.android.dependencies.components.Tracker;

public class ScopeActivity extends Activity {

    @Inject BackgroundServiceManager serviceManager;
    @Inject Tracker tracker;
    @Inject Presenter presenter;
    private Dependencies.ScopeComponent scope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scope = Dependencies.createScope(this);
        scope.inject(this);
        tracker.trackStarted();
    }

    // call from fragments...
    public Dependencies.ScopeComponent getScope() {
        return scope;
    }

    @Override
    protected void onDestroy() {
        scope = null;
        super.onDestroy();
    }
}
