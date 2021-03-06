package com.github.alvarosct.happycows.features.produccion.detail;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.alvarosct.ascthelper.ui.IAdapterDetail;
import com.github.alvarosct.ascthelper.ui.activities.BaseActivity;
import com.github.alvarosct.ascthelper.utils.Constants;
import com.github.alvarosct.ascthelper.utils.UtilMethods;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.db.models.Proceso;
import com.github.alvarosct.happycows.features.produccion.detail.insumo.InsumoFormActivity;
import com.github.alvarosct.happycows.features.produccion.detail.proceso.ProcesoFormActivity;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

public class OrdenProduccionProcesosAdapter extends RecyclerView.Adapter<OrdenProduccionProcesosAdapter.ViewHolder> {
    protected List<Proceso> objList;
    protected BaseActivity baseActivity;

    public OrdenProduccionProcesosAdapter(List<Proceso> objList, BaseActivity baseActivity) {
        this.objList = objList;
        this.baseActivity = baseActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Proceso obj = objList.get(position);

        holder.tv_heading.setText(obj.getNombre());

        holder.holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(baseActivity, ProcesoFormActivity.class);
                intent.putExtra(com.github.alvarosct.ascthelper.utils.Constants.BUNDLE_ENTITY, new Gson().toJson(obj));
                baseActivity.startActivityForResult(intent, Constants.INTENT_PROCESO);

            }
        });
    }

    @Override
    public int getItemCount() {
        return objList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_heading, tv_subheading;
        private View iv_cloud;
        private View holder;

        public ViewHolder(View v) {
            super(v);
            holder = v.findViewById(R.id.holder);
            tv_heading = (TextView) v.findViewById(R.id.tv_heading);
            tv_subheading = (TextView) v.findViewById(R.id.tv_subheading);
            iv_cloud = v.findViewById(R.id.iv_cloud);

            iv_cloud.setVisibility(View.GONE);
        }
    }


}
