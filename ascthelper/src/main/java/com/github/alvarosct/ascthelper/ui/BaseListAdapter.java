package com.github.alvarosct.ascthelper.ui;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.alvarosct.ascthelper.IBaseModel;
import com.github.alvarosct.ascthelper.R;
import com.github.alvarosct.ascthelper.utils.UtilMethods;

import java.util.List;

public abstract class BaseListAdapter<T extends IBaseModel, V extends BaseViewHolder> extends RecyclerView.Adapter<V> {
    protected List<T> objList;
    private IAdapterDetail iAdapterDetail;
    private Context context;
    private int selectedItem;

    public BaseListAdapter(List<T> objList, Context context, IAdapterDetail iAdapterDetail) {
        this.objList = objList;
        this.context = context;
        this.iAdapterDetail = iAdapterDetail;
    }

    public int getObjectFirstSelectionId() {
        if (objList != null && !objList.isEmpty()) {
            return getObjectSelectionId(objList.get(0));
        }
        return 0;
    }

    public abstract int getObjectSelectionId(T entity);

    public abstract void onBindViewHolder(V holder, T entity);

    @Override
    public void onBindViewHolder(V holder, int position) {
        final T obj = objList.get(position);

        if (getObjectSelectionId(obj) == selectedItem) {
            holder.holder.setBackground(ContextCompat.getDrawable(context, R.drawable.bluebackground));
        } else {
            holder.holder.setBackgroundResource(0);
        }

        holder.ivConnection.setVisibility(UtilMethods.getVisibility(obj.isLocalChange()));

        onBindViewHolder(holder, obj);

        holder.holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newSelection = getObjectSelectionId(obj);
                if (newSelection != selectedItem){
                    iAdapterDetail.openDetail(newSelection);
                }
            }
        });
    }

    public void selectItemSelected(int selectedItemId) {
        this.selectedItem = selectedItemId;
        notifyDataSetChanged();
    }

    public void removeSelectedItem() {
        selectedItem = -1;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return objList.size();
    }


}

