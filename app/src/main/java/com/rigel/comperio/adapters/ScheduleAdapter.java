package com.rigel.comperio.adapters;

import android.content.Context;
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

    private List<Schedule> peopleList;

    public ScheduleAdapter() {
        this.peopleList = Collections.emptyList();
    }

    @Override public ScheduleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemScheduleBinding itemScheduleBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_schedule,
                        parent, false);
        return new ScheduleAdapterViewHolder(itemScheduleBinding);
    }

    @Override public void onBindViewHolder(ScheduleAdapterViewHolder holder, int position) {
        holder.bindSchedule(peopleList.get(position));
    }

    @Override public int getItemCount() {
        return peopleList.size();
    }

    public void setScheduleList(List<Schedule> peopleList) {
        this.peopleList = peopleList;
        notifyDataSetChanged();
    }

    public static class ScheduleAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemScheduleBinding itemScheduleBinding;
        Context context;

        public ScheduleAdapterViewHolder(ItemScheduleBinding itemScheduleBinding) {
            super(itemScheduleBinding.itemSchedule);
            this.itemScheduleBinding = itemScheduleBinding;
            this.context = context;
        }

        void bindSchedule(Schedule people) {
            if (itemScheduleBinding.getScheduleViewModel() == null) {
                itemScheduleBinding.setScheduleViewModel(
                        new ItemScheduleViewModel(people,context));
            } else {
                itemScheduleBinding.getScheduleViewModel().setSchedule(people);
            }
        }
    }
}
