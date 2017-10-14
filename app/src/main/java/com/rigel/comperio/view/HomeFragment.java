package com.rigel.comperio.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manaschaudhari.android_mvvm.utils.BindingUtils;
import com.rigel.comperio.R;
import com.rigel.comperio.adapters.ScheduleRecyclerViewAdapter;
import com.rigel.comperio.viewmodel.HomeViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    @BindView(R.id.recycler_home) RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_home)
    SwipeRefreshLayout swipeRefreshLayout;

    public HomeFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);
        ButterKnife.bind(this, view);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateItems();
            }
        });

        return view;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //TODO: check MvvmActivity to fix null defaultBinder warning
        recyclerView.setAdapter(new ScheduleRecyclerViewAdapter(
                ((HomeViewModel)viewModel).itemVms, ViewProviders.getItemListing(),
                BindingUtils.getDefaultBinder())
        );
    }

    @Override
    public void onResume() {
        super.onResume();
        getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }

    public SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(getContext());
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        refreshRecycler();
    }

    private void refreshRecycler() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                updateItems();
            }
        });
    }

    private void updateItems() {
        ((HomeViewModel) viewModel).updateItems();
    }
}
