package com.github.alvarosct.happycows.features.produccion;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.alvarosct.ascthelper.ui.IAdapterDetail;
import com.github.alvarosct.ascthelper.utils.Constants;
import com.github.alvarosct.ascthelper.utils.UtilMethods;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.db.pojos.ProduccionItem;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

public class OrdenProduccionAdapter extends RecyclerView.Adapter<OrdenProduccionAdapter.ViewHolder> {
    protected List<ProduccionItem> objList;
    protected IAdapterDetail iAdapterDetail;

    public OrdenProduccionAdapter(List<ProduccionItem> objList, IAdapterDetail iAdapterDetail) {
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
        final ProduccionItem obj = objList.get(position);

        holder.tv_heading.setText(obj.getNombre());
        holder.tv_subheading.setText(UtilMethods.calendarStringToString(
                obj.getFechaProgramada(),
                Constants.BD_DATE_FORMAT,
                Constants.DATE_FORMAT
        ));

        holder.holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iAdapterDetail.openDetail(obj.getId());
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
