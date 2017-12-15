package com.rigel.comperio.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;
import com.rigel.comperio.R;
import com.rigel.comperio.adapters.SubjectAdapter;
import com.rigel.comperio.databinding.ActivityNewScheduleBinding;
import com.rigel.comperio.model.Subject;
import com.rigel.comperio.viewmodel.NewScheduleViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class NewScheduleActivity extends BaseActivity {

    private static final String TAG = NewScheduleActivity.class.getSimpleName();

    private NewScheduleViewModel newScheduleViewModel;
    private ActivityNewScheduleBinding newScheduleActivityBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initObserveInternetConnectivity();
    }

    private void initObserveInternetConnectivity() {
        ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override public void accept(Boolean isConnectedToInternet) {
                        newScheduleViewModel.isConnectedToInternet = isConnectedToInternet;
                    }
                });
    }

    @Override
    protected void initDataBinding() {
        newScheduleActivityBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_new_schedule);

        newScheduleViewModel = new NewScheduleViewModel(getNavigationManager(),
                getPersistanceManager(), getLogger(), this);

        newScheduleActivityBinding.setNewScheduleViewModel(newScheduleViewModel);

        SubjectAdapter adapter = new SubjectAdapter(
                this,
                android.R.layout.simple_spinner_item,
                newScheduleActivityBinding.getNewScheduleViewModel().subjects
        );
        newScheduleActivityBinding.setSpinnerAdapter(adapter);
        newScheduleActivityBinding.spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        newScheduleActivityBinding.getNewScheduleViewModel().schedule.subjectName =
                                ((Subject) newScheduleActivityBinding.spinner.getSelectedItem()).name;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        //no op
                    }
                });
    }

    @Override
    protected String getTag() {
        return TAG;
    }

}
