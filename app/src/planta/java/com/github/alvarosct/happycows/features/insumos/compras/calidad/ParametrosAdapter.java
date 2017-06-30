package com.github.alvarosct.happycows.features.insumos.compras.calidad;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.github.alvarosct.ascthelper.ui.IAdapterDetail;
import com.github.alvarosct.ascthelper.ui.IAdapterSwitch;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.db.AppDatabase;
import com.github.alvarosct.happycows.data.db.models.DetalleCalidad;
import com.github.alvarosct.happycows.data.db.models.ParametroCalidad;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

public class ParametrosAdapter extends RecyclerView.Adapter<ParametrosAdapter.ViewHolder> {
    protected List<ParametroCalidad> objList;
    protected IAdapterSwitch iAdapterSwitch;
    private List<DetalleCalidad> detalleCalidadList;

    public ParametrosAdapter(List<ParametroCalidad> objList, List<DetalleCalidad> detalleList, IAdapterSwitch iAdapterSwitch) {
        this.objList = objList;
        this.iAdapterSwitch = iAdapterSwitch;
        this.detalleCalidadList = detalleList;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.insumo_parametros_list_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ParametroCalidad obj = objList.get(position);

        holder.tv_heading.setText(obj.getDescripcion());
        holder.tv_subheading.setText(obj.getAbreviatura());

        for (DetalleCalidad detalleCalidad : detalleCalidadList) {
            if (detalleCalidad.getIdParamCalidad() == obj.getId()){
                obj.setCumple(1);
                detalleCalidadList.remove(detalleCalidad);
                break;
            }
        }
        holder.sw_cumple.setChecked(obj.getCumple() != 0);

        holder.sw_cumple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obj.switchCumple();
                iAdapterSwitch.onSwitchChange(obj.getId(), obj.getCumple());
            }
        });
    }

    @Override
    public int getItemCount() {
        return objList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_heading, tv_subheading;
        private Switch sw_cumple;
        private View holder;

        public ViewHolder(View v) {
            super(v);
            holder = v.findViewById(R.id.holder);
            tv_heading = (TextView) v.findViewById(R.id.tv_heading);
            tv_subheading = (TextView) v.findViewById(R.id.tv_subheading);
            sw_cumple = (Switch) v.findViewById(R.id.sw_cumple);
        }
    }


}
