package com.rigel.comperio.view;

import android.databinding.DataBindingUtil;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.R;
import com.rigel.comperio.SettingsManager;
import com.rigel.comperio.databinding.ActivityMainBinding;
import com.rigel.comperio.viewmodel.MainViewModel;

public class MainActivity extends BottomNavigationActivity {

    private Navigator navigator;
    private SettingsManager settingsManager;
    private DevUtils.Logger logger;

    private MainViewModel mainViewModel;

    protected void initDataBinding() {
        ActivityMainBinding mainActivityBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainViewModel = new MainViewModel(getNavigator(), getSettingsManager(), getLogger());
        mainActivityBinding.setMainViewModel(mainViewModel);
    }

}

