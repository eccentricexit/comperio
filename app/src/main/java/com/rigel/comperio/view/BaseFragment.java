package com.rigel.comperio.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manaschaudhari.android_mvvm.ViewModel;

import static com.manaschaudhari.android_mvvm.utils.BindingUtils.getDefaultBinder;

public abstract class BaseFragment extends Fragment {
    ViewModel viewModel;
    ViewDataBinding binding;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false);

        getDefaultBinder().bind(binding, viewModel);

        return binding.getRoot();
    }

    protected abstract int getLayout();

    public void setViewModel(ViewModel viewModel){
        this.viewModel = viewModel;
    }
}
