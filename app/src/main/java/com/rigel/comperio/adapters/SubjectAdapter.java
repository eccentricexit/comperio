package com.rigel.comperio.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rigel.comperio.model.Subject;

public class SubjectAdapter extends ArrayAdapter<Subject> {


    private Context context;

    private Subject[] values;

    public SubjectAdapter(Context context, int textViewResourceId,
                          Subject[] values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }

    public int getCount() {
        return values.length;
    }

    public Subject getItem(int position) {
        return values[position];
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView label = new TextView(context);
        label.setText(values[position].getName());

        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = new TextView(context);
        label.setText(values[position].getName());

        return label;
    }
}