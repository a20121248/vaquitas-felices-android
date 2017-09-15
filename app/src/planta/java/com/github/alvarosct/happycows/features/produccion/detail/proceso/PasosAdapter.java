package com.github.alvarosct.happycows.features.produccion.detail.proceso;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.github.alvarosct.ascthelper.ui.activities.BaseActivity;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.db.models.Paso;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

public class PasosAdapter extends RecyclerView.Adapter<PasosAdapter.ViewHolder> {
    protected List<Paso> objList;
    protected BaseActivity baseActivity;

    public PasosAdapter(List<Paso> objList, BaseActivity baseActivity) {
        this.objList = objList;
        this.baseActivity = baseActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_parametro, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Paso obj = objList.get(position);

        holder.setObj(obj);

        holder.holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public JsonArray getJsonArray(){
        JsonArray jsonArray = new JsonArray();
        for (Paso paso : objList) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id_pasos", paso.getId());
            jsonObject.addProperty("valor_parametros", paso.getValue());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @Override
    public int getItemCount() {
        return objList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_heading;
        private EditText et_value;
        private View holder;
        private Paso obj;

        public ViewHolder(View v) {
            super(v);
            holder = v.findViewById(R.id.holder);
            tv_heading = (TextView) v.findViewById(R.id.tv_heading);
            et_value = (EditText) v.findViewById(R.id.et_value);

        }


        void setObj(Paso i) {
            this.obj = i;

            tv_heading.setText(obj.getParametros());
            et_value.setText(obj.getValue());

            et_value.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    String value = editable.toString().trim();
                    obj.setValue(value);
                }
            });

        }

    }


}
