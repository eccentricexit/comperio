package com.rigel.comperio.view;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.rigel.comperio.LoggingManager;
import com.rigel.comperio.NavigationManager;
import com.rigel.comperio.PersistenceManager;

public abstract class BaseFragment extends Fragment {

    NavigationManager navigator;
    LoggingManager logger;
    PersistenceManager persistenceManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        BaseActivity baseActivity = (BaseActivity) getActivity();
        navigator = baseActivity.getNavigationManager();
        logger = baseActivity.getLogger();
        persistenceManager = baseActivity.getPersistanceManager();
    }

}
