package com.rigel.comperio.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.manaschaudhari.android_mvvm.adapters.ViewModelBinder;
import com.manaschaudhari.android_mvvm.adapters.ViewProvider;
import com.rigel.comperio.viewmodel.ScheduleViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class ScheduleRecyclerViewAdapter extends
        RecyclerView.Adapter<ScheduleRecyclerViewAdapter.DataBindingViewHolder> {


    @NonNull
    private final ViewProvider viewProvider;
    @NonNull
    private final ViewModelBinder binder;
    @NonNull
    private final Observable<List<ScheduleViewModel>> source;
    @NonNull
    private final HashMap<RecyclerView.AdapterDataObserver, Disposable> subscriptions = new HashMap<>();
    @NonNull
    private List<ScheduleViewModel> latestViewModels = new ArrayList<>();

    public ScheduleRecyclerViewAdapter(@NonNull Observable<List<ScheduleViewModel>> viewModels,
                                       @NonNull ViewProvider viewProvider,
                                       @NonNull ViewModelBinder viewModelBinder) {
        this.viewProvider = viewProvider;
        this.binder = viewModelBinder;
        source = viewModels
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<List<ScheduleViewModel>>() {
                    @Override
                    public void accept(List<ScheduleViewModel> viewModels) throws Exception {
                        latestViewModels = viewModels != null ? viewModels : new ArrayList<ScheduleViewModel>();
                        notifyDataSetChanged();
                    }
                })
                .share();
    }

    @Override
    public int getItemViewType(int position) {
        return viewProvider.getView(latestViewModels.get(position));
    }

    @Override
    public ScheduleRecyclerViewAdapter.DataBindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewType, parent, false);
        return new ScheduleRecyclerViewAdapter.DataBindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ScheduleRecyclerViewAdapter.DataBindingViewHolder holder, int position) {
        binder.bind(holder.viewBinding, latestViewModels.get(position));
        holder.viewBinding.executePendingBindings();
    }

    @Override
    public void onViewRecycled(ScheduleRecyclerViewAdapter.DataBindingViewHolder holder) {
        binder.bind(holder.viewBinding, null);
        holder.viewBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return latestViewModels.size();
    }

    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        subscriptions.put(observer, source.subscribe());
        super.registerAdapterDataObserver(observer);
    }

    @Override
    public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.unregisterAdapterDataObserver(observer);
        Disposable subscription = subscriptions.remove(observer);
        if (subscription != null && !subscription.isDisposed()) {
            subscription.dispose();
        }
    }

    public static class DataBindingViewHolder extends RecyclerView.ViewHolder {
        @NonNull
        final ViewDataBinding viewBinding;

        public DataBindingViewHolder(@NonNull ViewDataBinding viewBinding) {
            super(viewBinding.getRoot());
            this.viewBinding = viewBinding;
        }
    }
}