package com.github.alvarosct.happycows.features.insumos.compras.detalle;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.alvarosct.ascthelper.ui.IAdapterDetail;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.db.pojos.DetalleCompraItem;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

public class DetalleCompraAdapter extends RecyclerView.Adapter<DetalleCompraAdapter.ViewHolder> {
    protected List<DetalleCompraItem> objList;
    protected IAdapterDetail iAdapterDetail;

    public DetalleCompraAdapter(List<DetalleCompraItem> objList, IAdapterDetail iAdapterDetail) {
        this.objList = objList;
        this.iAdapterDetail = iAdapterDetail;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final DetalleCompraItem obj = objList.get(position);

        holder.tv_heading.setText(obj.getNombres());
        holder.tv_subheading.setText("Cantidad: " + obj.getCantidad());

//        holder.iv_cloud.setVisibility(UtilMethods.getVisibility(obj.isLocal()));

        holder.holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iAdapterDetail.openDetail(obj.getIdInsumo());
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

        public ViewHolder(View v) {
            super(v);
            holder = v.findViewById(R.id.holder);
            tv_heading = (TextView) v.findViewById(R.id.tv_heading);
            tv_subheading = (TextView) v.findViewById(R.id.tv_subheading);
        }
    }


}
