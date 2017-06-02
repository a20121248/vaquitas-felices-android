package com.github.alvarosct.happycows.features.porongos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alvarosct.happycows.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PorongoFormOlorFragment extends Fragment {


    Unbinder unbinder;
    private PorongoFormActivity activity;

    public PorongoFormOlorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.porongo_olor, container, false);
        unbinder = ButterKnife.bind(this, view);
        activity = (PorongoFormActivity) getActivity();
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.bt_bad, R.id.bt_good})
    public void onViewClicked(View view) {

        String olor = "";

        switch (view.getId()) {
            case R.id.bt_bad:
                olor = "Bueno";
                break;
            case R.id.bt_good:
                olor = "Malo";
                break;
        }

        activity.getPorongo().setOlor(olor);
        activity.completeStep(2);
    }
}
