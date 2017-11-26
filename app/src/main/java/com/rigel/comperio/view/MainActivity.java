package com.rigel.comperio.view;

import android.database.Cursor;
import android.databinding.DataBindingUtil;

import com.rigel.comperio.R;
import com.rigel.comperio.data.ComperioContract;
import com.rigel.comperio.databinding.ActivityMainBinding;
import com.rigel.comperio.sync.SyncAdapter;
import com.rigel.comperio.viewmodel.MainViewModel;

public class MainActivity extends BottomNavigationActivity {

    private MainViewModel mainViewModel;

    protected void initDataBinding() {
        ActivityMainBinding mainActivityBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainViewModel = new MainViewModel(getNavigator(), getPersistenceManager(),
                getLogger());

        mainActivityBinding.setMainViewModel(mainViewModel);

        Cursor cursor = getContentResolver().query(ComperioContract.ScheduleTable.CONTENT_URI,
                null,
                null,
                null,
                null);

        if(cursor.getCount()<=0){
            SyncAdapter.syncImmediately(this);
        }

        cursor.close();

    }

}

