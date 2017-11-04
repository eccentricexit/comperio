package com.rigel.comperio.view;

import android.databinding.DataBindingUtil;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.R;
import com.rigel.comperio.PersistenceManager;
import com.rigel.comperio.databinding.ActivityMainBinding;
import com.rigel.comperio.sync.SyncAdapter;
import com.rigel.comperio.viewmodel.MainViewModel;

public class MainActivity extends BottomNavigationActivity implements SyncAdapter.SyncManager{

    private Navigator navigator;
    private PersistenceManager persistenceManager;
    private DevUtils.Logger logger;

    private MainViewModel mainViewModel;

    protected void initDataBinding() {
        ActivityMainBinding mainActivityBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainViewModel = new MainViewModel(getNavigator(), getPersistenceManager(),
                getLogger(),this);

        mainActivityBinding.setMainViewModel(mainViewModel);
    }

    @Override
    public void initializeSyncAdapter() {
        SyncAdapter.initializeSyncAdapter(this);
    }
}

