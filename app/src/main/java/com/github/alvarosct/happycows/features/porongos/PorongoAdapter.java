package com.github.alvarosct.happycows.features.porongos;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.db.models.Porongo;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

public class PorongoAdapter extends RecyclerView.Adapter<PorongoAdapter.ViewHolder> {
    protected List<Porongo> objList;
    protected Activity activity;

    public PorongoAdapter(Activity activity, List<Porongo> objList) {
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
        final Porongo obj = objList.get(position);

        holder.tv_heading.setText("Porongo #" + obj.getUid());
        holder.tv_subheading.setText(obj.getFamilia());

        holder.holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, CalidadActivity.class);
                i.putExtra("ID", obj.getUid());
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

        public ViewHolder(View v) {
            super(v);
            holder = v.findViewById(R.id.holder);
            tv_heading = (TextView) v.findViewById(R.id.tv_heading);
            tv_subheading = (TextView) v.findViewById(R.id.tv_subheading);
        }
    }


}
