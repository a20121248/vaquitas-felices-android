package com.github.alvarosct.happycows.features.porongos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alvarosct.happycows.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PorongoFormBrixFragment extends Fragment {


    public PorongoFormBrixFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.porongo_brix, container, false);
    }

}
