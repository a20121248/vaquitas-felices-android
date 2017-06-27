package com.github.alvarosct.happycows.features.insumos;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.alvarosct.ascthelper.utils.UtilMethods;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.db.models.Insumo;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

public class InsumosAdapter extends RecyclerView.Adapter<InsumosAdapter.ViewHolder> {
    protected List<Insumo> objList;
    protected Activity activity;

    public InsumosAdapter(Activity activity, List<Insumo> objList) {
        this.objList = objList;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Insumo obj = objList.get(position);

        holder.tv_heading.setText(obj.getNombres());
        holder.tv_subheading.setText(obj.getNombres());

        holder.iv_cloud.setVisibility(UtilMethods.getVisibility(obj.isLocal()));

        holder.holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, InsumoQuestionFormActivity.class);
                i.putExtra("ID", obj.getId());
                activity.startActivity(i);
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
