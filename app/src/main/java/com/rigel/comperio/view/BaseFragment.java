package com.rigel.comperio.view;

import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.ActivityOptionsCompat;


import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;
import com.rigel.comperio.LoggingManager;
import com.rigel.comperio.NavigationManager;
import com.rigel.comperio.PersistenceManager;
import com.rigel.comperio.R;
import com.rigel.comperio.adapters.ScheduleAdapter;
import com.rigel.comperio.model.Schedule;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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
        initObserveInternetConnectivity();
    }

    private void initObserveInternetConnectivity() {
        ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override public void accept(Boolean isConnectedToInternet) {
                        updateConnectivityStatus(isConnectedToInternet);
                    }
                });
    }

    protected abstract void updateConnectivityStatus(Boolean isConnectedToInternet);

    protected ScheduleAdapter.ScheduleAdapterOnClickHandler buildOnClickHandler() {
        return new ScheduleAdapter.ScheduleAdapterOnClickHandler() {
            @Override
            public void onClick(Schedule schedule, ScheduleAdapter.ScheduleViewHolder vh) {
                ActivityOptionsCompat options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                                getActivity(),
                                vh.getItemScheduleBinding().avatar,
                                getContext().getString(R.string.teacherPicTransitionName));

                vh.getItemScheduleBinding().getItemScheduleViewModel().onItemTap(options);
            }
        };
    }

    public abstract String getFragmentTag();

}
