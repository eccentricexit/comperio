package com.rigel.comperio.view;

import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.manaschaudhari.android_mvvm.ViewModel;
import com.rigel.comperio.R;
import com.rigel.comperio.viewmodel.MainViewModel;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    private static final int FAVORITES = 0;
    private static final int HOME = 1;
    private static final int FILTERS = 2;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @BindString(R.string.homeTabTitle) String homeTabTitle;
    @BindString(R.string.favoritesTabTitle) String favoritesTabTitle;
    @BindString(R.string.filtersTabTitle) String filtersTabTitle;

    @BindView(R.id.tabLayout_main) TabLayout tabLayout;
    @BindView(R.id.viewPager_main) ViewPager viewPager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        setupViewPager();
    }

    @NonNull
    @Override
    protected ViewModel createViewModel() {
        return new MainViewModel(getNavigator());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private void setupViewPager() {
        FragmentPagerAdapter viewPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                switch(position){
                    case(FAVORITES):{
                        return new FavoritesFragment();
                    }
                    case(HOME):{
                        return new HomeFragment();
                    }
                    case(FILTERS):{
                        return new FiltersFragment();
                    }
                    default:{
                        throw new UnsupportedOperationException();
                    }
                }

            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch(position){
                    case(FAVORITES):{
                        return favoritesTabTitle;
                    }
                    case(HOME):{
                        return homeTabTitle;
                    }
                    case(FILTERS):{
                        return filtersTabTitle;
                    }
                    default:{
                        throw new UnsupportedOperationException();
                    }
                }

            }
        };

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
