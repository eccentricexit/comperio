package com.rigel.comperio.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

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

    public ScheduleAdapter(Navigator navigator) {
        this.schedules = Collections.emptyList();
        this.navigator = navigator;
    }

    @Override public ScheduleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemScheduleBinding itemScheduleBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_schedule,
                        parent, false);
        return new ScheduleAdapterViewHolder(itemScheduleBinding);
    }

    @Override
    public void onBindViewHolder(ScheduleAdapterViewHolder holder, int position) {
        holder.bindSchedule(schedules.get(position));
        holder.setNavigator(navigator);
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
        private Navigator navigator;

        public ScheduleAdapterViewHolder(ItemScheduleBinding itemScheduleBinding) {
            super(itemScheduleBinding.itemSchedule);
            this.itemScheduleBinding = itemScheduleBinding;
        }

        void bindSchedule(Schedule people) {
            if (itemScheduleBinding.getItemScheduleViewModel() == null) {
                itemScheduleBinding.setItemScheduleViewModel(
                        new ItemScheduleViewModel(people,navigator));
            } else {
                itemScheduleBinding.getItemScheduleViewModel().setSchedule(people);
            }
        }

        public void setNavigator(Navigator navigator) {
            this.navigator = navigator;
        }
    }
}
