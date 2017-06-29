package com.github.alvarosct.happycows.features.porongos;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.alvarosct.ascthelper.ui.IAdapterDetail;
import com.github.alvarosct.ascthelper.utils.Constants;
import com.github.alvarosct.ascthelper.utils.UtilMethods;
import com.github.alvarosct.happycows.App;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.db.pojos.PorongoFullItem;
import com.github.alvarosct.happycows.data.db.pojos.PorongoItem;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

public class PorongoAdapter extends RecyclerView.Adapter<PorongoAdapter.ViewHolder> {
    protected List<PorongoFullItem> objList;
    protected IAdapterDetail iAdapterDetail;

    public PorongoAdapter(List<PorongoFullItem> objList, IAdapterDetail iAdapterDetail) {
        this.objList = objList;
        this.iAdapterDetail = iAdapterDetail;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.porongo_list_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PorongoFullItem obj = objList.get(position);

        holder.tv_heading.setText(obj.getNombres());
        holder.tv_subheading.setText(UtilMethods.calendarStringToString(
                obj.getPorongo().getFechaHoraEntrega(),
                Constants.BD_DATETIME_FORMAT,
                Constants.DATETIME_FORMAT
        ));
        holder.tv_peso.setText(String.valueOf(obj.getPorongo().getPeso()));

        if (obj.getPorongo().getAccepted() == 0){
            holder.iv_icon.setImageResource(R.drawable.ic_clear);
            holder.iv_icon.setColorFilter(ContextCompat.getColor(App.getContext(),R.color.red));
        } else {
            holder.iv_icon.setImageResource(R.drawable.ic_check);
            holder.iv_icon.setColorFilter(ContextCompat.getColor(App.getContext(), R.color.green));
        }

//        holder.iv_cloud.setVisibility(UtilMethods.getVisibility(obj.isLocal()));

        holder.holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iAdapterDetail.openDetail(obj.getPorongo().getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return objList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_heading, tv_subheading, tv_peso;
        private ImageView iv_icon;
        private View holder;

        public ViewHolder(View v) {
            super(v);
            holder = v.findViewById(R.id.holder);
            tv_heading = (TextView) v.findViewById(R.id.tv_heading);
            tv_subheading = (TextView) v.findViewById(R.id.tv_subheading);
            iv_icon = (ImageView) v.findViewById(R.id.iv_icon);
            tv_peso = (TextView) v.findViewById(R.id.tv_peso);
        }
    }


}
