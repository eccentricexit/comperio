package com.rigel.comperio;

import android.content.ContentResolver;

import com.rigel.comperio.model.Filter;

public interface PersistenceManager {

    Filter loadFilter();
    void saveFilter(Filter filter);
    ContentResolver getContentResolver();
}
