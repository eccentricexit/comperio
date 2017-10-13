package com.rigel.comperio.view;

import android.support.v13.app.FragmentCompat;
import android.support.v4.app.Fragment;
import android.view.View;

import com.manaschaudhari.android_mvvm.ViewModel;
import com.rigel.comperio.viewmodel.HomeViewModel;

public abstract class BaseFragment extends Fragment {
    ViewModel viewModel;

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


    public void setViewModel(ViewModel viewModel){
        this.viewModel = viewModel;
    }
}
