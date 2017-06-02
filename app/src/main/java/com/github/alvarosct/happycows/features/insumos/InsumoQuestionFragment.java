package com.github.alvarosct.happycows.features.insumos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.db.models.PreguntaInsumo;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class InsumoQuestionFragment extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.tv_question)
    TextView tvQuestion;
    private InsumoQuestionFormActivity activity;
    private PreguntaInsumo pregunta;
    private int page;

    public InsumoQuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.insumo_question_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        activity = (InsumoQuestionFormActivity) getActivity();

        String preguntaString = getArguments().getString("QUESTION");
        page = getArguments().getInt("PAGE");
        pregunta = new Gson().fromJson(preguntaString, PreguntaInsumo.class);
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

    @OnClick({R.id.bt_yes, R.id.bt_no})
    public void onViewClicked(View view) {
        activity.completeStep(page);
    }
}
