package com.github.alvarosct.happycows.features.porongos;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.github.alvarosct.ascthelper.utils.UtilMethods;
import com.github.alvarosct.happycows.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PorongoFormPesoFragment extends Fragment {


    @BindView(R.id.et_peso)
    TextInputEditText etPeso;
    Unbinder unbinder;

    private PorongoFormActivity activity;

    public PorongoFormPesoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.porongo_peso, container, false);
        unbinder = ButterKnife.bind(this, view);

        activity = (PorongoFormActivity) getActivity();

        double peso = activity.getPorongo().getPeso();
        String pesoString = peso <= 0 ? "": String.valueOf(peso);
        etPeso.setText(pesoString);

        etPeso.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH
                        || i == EditorInfo.IME_ACTION_DONE
                        || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                        && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    UtilMethods.hideKeyboard(textView, getContext());

                    String value = textView.getText().toString();
                    if (!value.isEmpty()){
                        activity.getPorongo().setPeso(Double.parseDouble(value));
                        activity.completeStep(0);
                    } else {
                        UtilMethods.showToast("El campo no puede esta vacío.");
                    }
                    return true;
                }
                return false;
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
