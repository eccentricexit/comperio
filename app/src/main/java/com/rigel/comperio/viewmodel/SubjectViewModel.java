package com.rigel.comperio.viewmodel;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.manaschaudhari.android_mvvm.ViewModel;
import com.rigel.comperio.MessageHelper;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.SettingsManager;

import java.util.Set;


public class SubjectViewModel implements ViewModel {

    public final ObservableField<String> subject = new ObservableField<>("");
    @NonNull
    private final Navigator navigator;
    @NonNull
    private final SettingsManager settingsManager;
    @NonNull
    private final MessageHelper messageHelper;

    public SubjectViewModel(Navigator navigator, SettingsManager settingsManager, MessageHelper messageHelper) {
        this.navigator = navigator;
        this.settingsManager = settingsManager;
        this.messageHelper = messageHelper;
    }

    public void nextOnClick(){
        if (subject.get().equals("")) {
            messageHelper.requestSubjectSelection();
            return;
        }

        saveToSharedPreferences();
        navigator.navigateToFreeTimeActivity();
    }

    private void saveToSharedPreferences() {
        settingsManager.saveSubject(subject.get());
    }
}
