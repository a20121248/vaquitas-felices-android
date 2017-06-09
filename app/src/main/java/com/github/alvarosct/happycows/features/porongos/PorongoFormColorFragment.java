package com.github.alvarosct.happycows.features.porongos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.db.models.Porongo;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PorongoFormColorFragment extends Fragment {


    Unbinder unbinder;
    private PorongoFormActivity activity;

    public PorongoFormColorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.porongo_color, container, false);
        unbinder = ButterKnife.bind(this, view);
        activity = (PorongoFormActivity) getActivity();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.bt_red, R.id.bt_yellow, R.id.bt_white})
    public void onViewClicked(View view) {

        String color = "";
        switch (view.getId()) {
            case R.id.bt_red:
                color = Porongo.RED;
                break;
            case R.id.bt_yellow:
                color = Porongo.YELLOW;
                break;
            case R.id.bt_white:
                color = Porongo.WHITE;
                break;
        }

        activity.getPorongo().setColor(color);
        activity.completeStep(1);

    }
}
