package com.rigel.comperio.view;

import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.rigel.comperio.viewmodel.FavoritesViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritesFragment extends BaseFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    @BindView(R.id.recycler_favorites) RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_favorites)
    SwipeRefreshLayout swipeRefreshLayout;

    public FavoritesFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_favorites;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //TODO: check MvvmActivity to fix null defaultBinder warning
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new ScheduleRecyclerViewAdapter(
                ((FavoritesViewModel)viewModel).itemVms, ViewProviders.getItemListing(),
                BindingUtils.getDefaultBinder())
        );
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
            }
        });
    }
}
