package com.github.alvarosct.happycows.features.venta.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.db.models.Client;
import com.github.alvarosct.happycows.data.db.models.Venta;
import com.github.alvarosct.happycows.utils.IDetail;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

public class VentasListAdapter extends RecyclerView.Adapter<VentasListAdapter.ViewHolder> {
    protected List<Venta> objList;
    protected IDetail<Venta> iDetail;

    public VentasListAdapter(IDetail<Venta> iAdapterDetail, List<Venta> objList) {
        this.objList = objList;
        this.iDetail = iAdapterDetail;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ventas_list_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Venta obj = objList.get(position);

        if (obj.getClienteEmp() == null){
            Client client = new Client();
            client.setFirstname("Cliente");
            client.setLastname("Anonimo");
            obj.setClienteEmp(client);
        }
        holder.tv_heading.setText(obj.getClienteEmp().getFullname());
        holder.tv_subheading.setText(obj.getFecha());
        holder.tv_total.setText(String.valueOf(obj.getMontoTotal()));

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
        private TextView tv_total;
        private View holder;
        private View iv_cloud;

        public ViewHolder(View v) {
            super(v);
            holder = v.findViewById(R.id.holder);
            tv_heading = (TextView) v.findViewById(R.id.tv_heading);
            tv_subheading = (TextView) v.findViewById(R.id.tv_subheading);
            tv_total = (TextView) v.findViewById(R.id.tv_total);
            iv_cloud = v.findViewById(R.id.iv_cloud);
        }
    }


}
