package com.rigel.comperio.adapters;

import android.content.Context;
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
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {

    private final Context context;

    private List<Schedule> schedules;
    private Navigator navigator;
    private DevUtils.Logger logger;

    public ScheduleAdapter(Context context, Navigator navigator, DevUtils.Logger logger) {
        this.schedules = Collections.emptyList();
        this.navigator = navigator;
        this.logger = logger;
        this.context = context;
    }

    @Override
    public ScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemScheduleBinding itemScheduleBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_schedule, parent, false);

        return new ScheduleViewHolder(itemScheduleBinding, navigator, logger);
    }

    @Override
    public void onBindViewHolder(ScheduleViewHolder holder, int position) {
        Schedule schedule = schedules.get(position);
        holder.bindSchedule(schedule);
        Picasso.with(context)
                .load(schedule.teacherPicUrl)
                .into(holder.itemScheduleBinding.avatar);
    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }

    public void setScheduleList(List<Schedule> schedules) {
        this.schedules = schedules;
        notifyDataSetChanged();
    }

    public List<Schedule> getScheduleList() {
        return schedules;
    }

    public static class ScheduleViewHolder extends RecyclerView.ViewHolder {
        ItemScheduleBinding itemScheduleBinding;
        Navigator navigator;
        DevUtils.Logger logger;

        public ScheduleViewHolder(ItemScheduleBinding itemScheduleBinding,
                                  Navigator navigator, DevUtils.Logger logger) {
            super(itemScheduleBinding.itemScheduleLayout);
            this.itemScheduleBinding = itemScheduleBinding;
            this.navigator = navigator;
            this.logger = logger;
        }

        void bindSchedule(Schedule schedule) {
            itemScheduleBinding.setItemScheduleViewModel(
                    new ItemScheduleViewModel(schedule, navigator, logger));
        }
        public ItemScheduleBinding getItemScheduleBinding(){
            return itemScheduleBinding;
        }
    }
}
