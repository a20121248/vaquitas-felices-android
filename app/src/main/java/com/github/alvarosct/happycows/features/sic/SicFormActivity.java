package com.github.alvarosct.happycows.features.sic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.github.alvarosct.ascthelper.utils.UtilMethods;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.db.AppDatabase;
import com.github.alvarosct.happycows.data.db.models.Pregunta;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SicFormActivity extends AppCompatActivity {

    @BindView(R.id.pager)
    ViewPager pager;

    private AppDatabase db;
    private ViewPagerAdapter adapter;
    private int maxStep = 0;
    private int totalSteps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pregunta_sic_form);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        ButterKnife.bind(this);

        db = AppDatabase.getInstance(SicFormActivity.this);

        setupViewPager(pager);
    }


    public void setupViewPager(ViewPager viewPager) {

        List<Pregunta> preguntasSic = AppDatabase.getInstance().preguntaModel().getAll();

        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        int page = 0;
        for (Pregunta pregunta : preguntasSic) {
            Fragment frag = new PreguntaSicFragment();
            Bundle b = new Bundle();
            b.putString("QUESTION", new Gson().toJson(pregunta, Pregunta.class));
            b.putInt("PAGE", page);
            frag.setArguments(b);
            adapter.addFrag(frag, "Pregunta #" + page++);

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
