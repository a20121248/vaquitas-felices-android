package com.github.alvarosct.happycows.features.venta.register;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.github.alvarosct.ascthelper.utils.dialogs.DialogCustom;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.db.models.Producto;
import com.github.alvarosct.happycows.data.db.pojos.ProductoItem;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

public class VentaRegistrarAdapter extends RecyclerView.Adapter<VentaRegistrarAdapter.ViewHolder> {
    protected List<ProductoItem> objList;
    private Context context;
    private ITotalize iTotalize;

    interface ITotalize {
        void onTotalChanged(double total);
    }

    public VentaRegistrarAdapter(Context context, List<ProductoItem> objList, ITotalize iTotalize) {
        this.objList = objList;
        this.iTotalize = iTotalize;
        this.context = context;
        calculateTotal();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.venta_quantity_row_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ProductoItem obj = objList.get(position);


        holder.setObj(obj);

        holder.holder.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new DialogCustom(context,
                        "¡Atención!", "¿Estás seguro que deseas eliminar este producto?",
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

    public void appendRow(Producto entity) {

//        To avoid duplication
        for (ProductoItem item : objList) {
            if (item.getId() == entity.getId()) {
                item.addCantidad(1);
                notifyDataSetChanged();
                return;
            }
        }

        ProductoItem productoItem = new ProductoItem(entity.getId(), entity.getNombre(), entity.getPrecioVenta());
        productoItem.addCantidad(1);
        productoItem.setLoteId(entity.getLoteId());
        objList.add(productoItem);
        notifyDataSetChanged();
    }

    public void removeRow(ProductoItem obj) {
        objList.remove(obj);
        notifyDataSetChanged();
        calculateTotal();
    }

    public List<ProductoItem> getObjList() {
        return objList;
    }

    private void calculateTotal(){
        double total = 0;
        for (ProductoItem productoItem : objList) {
            total += productoItem.getCostoTotal();
        }
        iTotalize.onTotalChanged(total);
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_heading, tv_value;
        private View holder;
        private View bt_plus, bt_minus;
        private EditText et_price;
        private ProductoItem obj;

        public ViewHolder(View v) {
            super(v);
            holder = v.findViewById(R.id.holder);
            tv_heading = (TextView) v.findViewById(R.id.tv_heading);
            tv_value = (TextView) v.findViewById(R.id.tv_value);
            bt_plus = v.findViewById(R.id.bt_plus);
            bt_minus = v.findViewById(R.id.bt_minus);
            et_price = (EditText) v.findViewById(R.id.et_price);

        }

        void setObj(ProductoItem i) {
            this.obj = i;

            tv_heading.setText(obj.getNombres());
            tv_value.setText(obj.getCantidadString());
            et_price.setText(String.valueOf(obj.getCostoUnit()));
            calculateTotal();

            et_price.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    obj.setCostoUnit(editable.toString().isEmpty() ? 0 :
                            Double.parseDouble(editable.toString()));
                    calculateTotal();
                }
            });

            bt_minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obj.addCantidad(-1);
                    tv_value.setText(obj.getCantidadString());
                    calculateTotal();
                }
            });
            bt_plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obj.addCantidad(1);
                    tv_value.setText(obj.getCantidadString());
                    calculateTotal();
                }
            });
        }


    }


}
