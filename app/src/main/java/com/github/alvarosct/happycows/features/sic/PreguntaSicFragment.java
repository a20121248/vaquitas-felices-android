package com.github.alvarosct.happycows.features.sic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.db.models.Pregunta;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreguntaSicFragment extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.tv_question)
    TextView tvQuestion;
    private SicFormActivity activity;
    private Pregunta pregunta;
    private int page;

    public PreguntaSicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.pregunta_sic_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        activity = (SicFormActivity) getActivity();

        String preguntaString = getArguments().getString("QUESTION");
        page = getArguments().getInt("PAGE");
        pregunta = new Gson().fromJson(preguntaString, Pregunta.class);
        tvQuestion.setText(pregunta.getDescripcion());
        if (pregunta == null) {
            tvQuestion.setText("Pregunta #" + page);
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.bt_ok, R.id.bt_good, R.id.bt_very_good, R.id.bt_very_bad, R.id.bt_bad,})
    public void onViewClicked(View view) {
        activity.completeStep(page);
    }
}
