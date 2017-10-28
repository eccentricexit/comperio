package com.rigel.comperio.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.R;
import com.rigel.comperio.databinding.ItemScheduleBinding;
import com.rigel.comperio.model.Schedule;
import com.rigel.comperio.viewmodel.ItemScheduleViewModel;

import java.util.Collections;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleAdapterViewHolder> {

    private List<Schedule> schedules;
    private Navigator navigator;
    private DevUtils.Logger logger;

    public ScheduleAdapter(Navigator navigator, DevUtils.Logger logger) {
        this.schedules = Collections.emptyList();
        this.navigator = navigator;
        this.logger = logger;
    }

    @Override public ScheduleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemScheduleBinding itemScheduleBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_schedule,
                        parent, false);
        return new ScheduleAdapterViewHolder(itemScheduleBinding,navigator,logger);
    }

    @Override
    public void onBindViewHolder(ScheduleAdapterViewHolder holder, int position) {
        holder.bindSchedule(schedules.get(position));
    }

    @Override public int getItemCount() {
        return schedules.size();
    }

    public void setScheduleList(List<Schedule> schedules) {
        this.schedules = schedules;
        notifyDataSetChanged();
    }

    public static class ScheduleAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemScheduleBinding itemScheduleBinding;
        Navigator navigator;
        DevUtils.Logger logger;

        public ScheduleAdapterViewHolder(ItemScheduleBinding itemScheduleBinding, Navigator navigator, DevUtils.Logger logger) {
            super(itemScheduleBinding.itemSchedule);
            this.itemScheduleBinding = itemScheduleBinding;
            this.navigator = navigator;
            this.logger =logger;
        }

        void bindSchedule(Schedule schedule) {
            if (itemScheduleBinding.getItemScheduleViewModel() == null) {
                itemScheduleBinding.setItemScheduleViewModel(
                        new ItemScheduleViewModel(schedule,navigator,logger));
            } else {
                itemScheduleBinding.getItemScheduleViewModel().schedule = schedule;
            }
        }
    }
}
