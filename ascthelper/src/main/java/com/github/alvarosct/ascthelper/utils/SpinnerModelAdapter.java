package com.github.alvarosct.ascthelper.utils;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.github.alvarosct.ascthelper.IBaseModel;

import java.util.List;

/**
 * Created by Android-Dev on 02/05/2017.
 */

public class SpinnerModelAdapter<T extends IBaseModel> extends ArrayAdapter<T> {

    private List<T> items;

    public SpinnerModelAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<T> objects) {
        super(context, resource, objects);
        this.items = objects;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public int getPosById(int itemId) {
        for (int i = 0; i < items.size(); i++) {
            T item = items.get(i);
            if (item.getModelId() == itemId) {
                return i;
            }
        }
        return -1;
    }

    public int getIdByValue(String value) {
        for (int i = 0; i < items.size(); i++) {
            T item = items.get(i);
            if (item.toString().equals(value)) {
                return item.getModelId();
            }
        }
        return 0;
    }
}
