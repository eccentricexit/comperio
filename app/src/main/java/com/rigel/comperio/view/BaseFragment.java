package com.rigel.comperio.view;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.PersistenceManager;

public abstract class BaseFragment extends Fragment {

    Navigator navigator;
    DevUtils.Logger logger;
    PersistenceManager persistenceManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        BaseActivity baseActivity = (BaseActivity) getActivity();
        navigator = baseActivity.getNavigator();
        logger = baseActivity.getLogger();
        persistenceManager = baseActivity.getPersistenceManager();
    }

}
