package com.rigel.comperio.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v13.app.FragmentCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manaschaudhari.android_mvvm.ViewModel;
import com.rigel.comperio.R;
import com.rigel.comperio.viewmodel.HomeViewModel;

import static com.manaschaudhari.android_mvvm.utils.BindingUtils.getDefaultBinder;

public abstract class BaseFragment extends Fragment {
    ViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, getLayout(), container, false);

        getDefaultBinder().bind(binding, viewModel);

        return binding.getRoot();
    }

    public static <T extends BaseFragment> T getInstance(Class<T> t, ViewModel viewModel) {

        T fragment;
        try {
            fragment = t.newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

        fragment.setViewModel(viewModel);

        return fragment;
    }

    protected abstract int getLayout();

    public void setViewModel(ViewModel viewModel){
        this.viewModel = viewModel;
    }
}
