package com.actionbarsherlock.app;

import android.preference.PreferenceFragment;
import android.view.View;

import com.actionbarsherlock.ActionBarSherlock;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class PrefFragment extends PreferenceFragment implements ActionBarSherlock.OnCreatePanelMenuListener, ActionBarSherlock.OnPreparePanelListener, ActionBarSherlock.OnMenuItemSelectedListener, ActionBarSherlock.OnActionModeStartedListener, ActionBarSherlock.OnActionModeFinishedListener {


    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        return false;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        return false;
    }

    @Override
    public boolean onPreparePanel(int featureId, View view, Menu menu) {
        return false;
    }

    @Override
    public void onActionModeFinished(ActionMode mode) {

    }

    @Override
    public void onActionModeStarted(ActionMode mode) {

    }
}
