package com.github.alvarosct.happycows.features.materiales;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.alvarosct.ascthelper.utils.dialogs.DialogCustom;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.db.models.Insumo;
import com.github.alvarosct.happycows.data.db.pojos.InsumoItem;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

public class MaterialesRegistrarAdapter extends RecyclerView.Adapter<MaterialesRegistrarAdapter.ViewHolder> {
    protected List<InsumoItem> objList;
    private Context context;

    public MaterialesRegistrarAdapter(Context context, List<InsumoItem> objList) {
        this.objList = objList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.material_register_row_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final InsumoItem obj = objList.get(position);


        holder.setObj(obj);

        holder.holder.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new DialogCustom(context,
                        "¡Atención!", "¿Estás seguro que deseas eliminar este item?",
                        new DialogCustom.ButtonBehaviour("Si", new DialogCustom.IButton() {
                            @Override
                            public void onButtonClick() {
                                removeRow(obj);
                            }
                        }), "No").show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return objList.size();
    }

    public void appendRow(Insumo entity) {

//        To avoid duplication
        for (InsumoItem item : objList) {
            if (item.getId() == entity.getId()){
                return;
            }
        }

        InsumoItem insumoItem = new InsumoItem(entity.getId(), entity.getNombres());
        objList.add(insumoItem);
        notifyDataSetChanged();
    }

    public void removeRow(InsumoItem obj) {
        objList.remove(obj);
        notifyDataSetChanged();
    }

    public List<InsumoItem> getObjList() {
        return objList;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_heading, tv_value, tv_unidad;
        private View holder;
        private View bt_plus, bt_minus;
        private InsumoItem obj;

        public ViewHolder(View v) {
            super(v);
            holder = v.findViewById(R.id.holder);
            tv_heading = (TextView) v.findViewById(R.id.tv_heading);
            tv_value = (TextView) v.findViewById(R.id.tv_value);
            tv_unidad = (TextView) v.findViewById(R.id.tv_unidad);
            bt_plus = v.findViewById(R.id.bt_plus);
            bt_minus = v.findViewById(R.id.bt_minus);

        }

        void setObj(InsumoItem i){
            this.obj = i;

            tv_heading.setText(obj.getNombres());
            tv_value.setText(obj.getCantidadString());
            tv_unidad.setText(obj.getUnidad());

            bt_minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obj.addCantidad(-1);
                    tv_value.setText(obj.getCantidadString());
                }
            });
            bt_plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obj.addCantidad(1);
                    tv_value.setText(obj.getCantidadString());
                }
            });
        }


    }


}
