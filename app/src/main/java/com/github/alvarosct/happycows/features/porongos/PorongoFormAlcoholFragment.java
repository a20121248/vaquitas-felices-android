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
public class PorongoFormAlcoholFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.et_alcohol)
    TextInputEditText etAlcohol;
    private PorongoFormActivity activity;

    public PorongoFormAlcoholFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.porongo_alcohol, container, false);
        unbinder = ButterKnife.bind(this, view);

        activity = (PorongoFormActivity) getActivity();

        double peso = activity.getPorongo().getAlcohol();
        String pesoString = peso <= 0 ? "": String.valueOf(peso);
        etAlcohol.setText(pesoString);

        etAlcohol.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH
                        || i == EditorInfo.IME_ACTION_DONE
                        || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                        && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    UtilMethods.hideKeyboard(textView, getContext());

                    String value = textView.getText().toString();
                    if (!value.isEmpty()) {
                        activity.getPorongo().setAlcohol(Integer.parseInt(value));
                        activity.completeStep(3);
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
