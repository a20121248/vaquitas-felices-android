package com.github.alvarosct.ascthelper.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {
    public View holder;
    public View ivConnection;

    public BaseViewHolder(View v) {
        super(v);
        holder = setHolder(v);
        ivConnection = setIvConnection(v);
    }

    public abstract View setHolder(View v);
    public abstract View setIvConnection(View v);
}


