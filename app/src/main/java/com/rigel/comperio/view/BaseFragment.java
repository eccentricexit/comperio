package com.rigel.comperio.view;

import android.content.Context;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;

import com.rigel.comperio.LoggingManager;
import com.rigel.comperio.NavigationManager;
import com.rigel.comperio.PersistenceManager;
import com.rigel.comperio.R;
import com.rigel.comperio.adapters.ScheduleAdapter;
import com.rigel.comperio.model.Schedule;

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

}
