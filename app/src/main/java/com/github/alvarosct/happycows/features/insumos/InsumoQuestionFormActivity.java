package com.github.alvarosct.happycows.features.insumos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.github.alvarosct.ascthelper.utils.UtilMethods;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.db.AppDatabase;
import com.github.alvarosct.happycows.db.models.Pregunta;
import com.github.alvarosct.happycows.db.models.PreguntaInsumo;
import com.github.alvarosct.happycows.features.sic.PreguntaSicFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InsumoQuestionFormActivity extends AppCompatActivity {

    @BindView(R.id.pager)
    ViewPager pager;

    private AppDatabase db;
    private ViewPagerAdapter adapter;
    private int maxStep = 0;
    private int totalSteps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insumo_question_form);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        ButterKnife.bind(this);

        db = AppDatabase.getInstance(InsumoQuestionFormActivity.this);

        setupViewPager(pager);
    }


    public void setupViewPager(ViewPager viewPager) {

        List<PreguntaInsumo> preguntasInsumo = AppDatabase.getInstance().preguntaInsumoModel().getAll();

        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        int page = 0;
        for (PreguntaInsumo pregunta : preguntasInsumo) {
            Fragment frag = new InsumoQuestionFragment();
            Bundle b = new Bundle();
            b.putString("QUESTION", new Gson().toJson(pregunta, PreguntaInsumo.class));
            b.putInt("PAGE", page);
            frag.setArguments(b);
            adapter.addFrag(frag, "PreguntaInsumo #" + page++);

        }

        totalSteps = adapter.getCount();
        viewPager.setAdapter(adapter);
    }

    public void completeStep(int step) {

        int newStep = step + 1;
        if (newStep > maxStep) {
            maxStep = newStep;
        }

        if (maxStep == totalSteps) {
            finish();
            UtilMethods.showToast("El web service aun no está listo");
        } else {
            pager.setCurrentItem(newStep);
        }
    }


    //    VIEW PAGER
    protected class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
