package com.github.alvarosct.happycows.features.client;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.db.models.Client;
import com.github.alvarosct.happycows.utils.IDetail;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

public class ClientSelectAdapter extends RecyclerView.Adapter<ClientSelectAdapter.ViewHolder> {
    protected List<Client> objList;
    protected IDetail<Client> iDetail;

    public ClientSelectAdapter(IDetail<Client> iAdapterDetail, List<Client> objList) {
        this.objList = objList;
        this.iDetail = iAdapterDetail;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simple_row_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Client obj = objList.get(position);

        holder.tv_heading.setText(obj.getFullname());

        holder.holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iDetail.openDetail(obj);
            }
        });
    }

    @Override
    public int getItemCount() {
        return objList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_heading, tv_subheading;
        private View holder;
        private View iv_cloud;

        public ViewHolder(View v) {
            super(v);
            holder = v.findViewById(R.id.holder);
            tv_heading = (TextView) v.findViewById(R.id.tv_heading);
            tv_subheading = (TextView) v.findViewById(R.id.tv_subheading);
            iv_cloud = v.findViewById(R.id.iv_cloud);
        }
    }


}
