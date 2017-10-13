package com.rigel.comperio.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manaschaudhari.android_mvvm.ViewModel;
import com.manaschaudhari.android_mvvm.adapters.RecyclerViewAdapter;
import com.manaschaudhari.android_mvvm.adapters.ViewModelBinder;
import com.manaschaudhari.android_mvvm.adapters.ViewProvider;
import com.manaschaudhari.android_mvvm.utils.BindingUtils;
import com.rigel.comperio.R;
import com.rigel.comperio.ViewProviders;
import com.rigel.comperio.adapters.ScheduleRecyclerViewAdapter;
import com.rigel.comperio.viewmodel.HomeViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import io.reactivex.Observable;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.recycler_home) RecyclerView recyclerView;

    public HomeFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new ScheduleRecyclerViewAdapter(
                ((HomeViewModel)viewModel).itemVms, ViewProviders.getItemListing(), BindingUtils.getDefaultBinder())
        );
    }
}
