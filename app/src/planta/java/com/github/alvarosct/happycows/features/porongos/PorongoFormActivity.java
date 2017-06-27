package com.github.alvarosct.happycows.features.porongos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.source.callbacks.LoadingCallback;
import com.github.alvarosct.happycows.data.db.AppDatabase;
import com.github.alvarosct.happycows.data.db.models.Porongo;
import com.github.alvarosct.happycows.utils.Injector;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PorongoFormActivity extends AppCompatActivity {

    @BindView(R.id.pager)
    ViewPager pager;

    private AppDatabase db;
    private Porongo porongo;
    private ViewPagerAdapter adapter;
    private int maxStep = 0;
    private int totalSteps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.porongo_form);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        ButterKnife.bind(this);

        porongo = new Porongo();
        porongo.setGanaderoId(getIntent().getExtras().getInt("ID", -1));

        db = AppDatabase.getInstance(PorongoFormActivity.this);

        setupViewPager(pager);
    }


    public void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new PorongoFormPesoFragment(), "Peso");
        adapter.addFrag(new PorongoFormColorFragment(), "Color");
        adapter.addFrag(new PorongoFormOlorFragment(), "Olor");
        adapter.addFrag(new PorongoFormAlcoholFragment(), "Alcohol");
        adapter.addFrag(new PorongoFormBrixFragment(), "Brix");

        totalSteps = adapter.getCount();
        viewPager.setAdapter(adapter);
    }

    public void completeStep(int step){

        int newStep = step + 1;
        if (newStep > maxStep){
            maxStep = newStep;
        }

        if (maxStep == totalSteps){
            Injector.provideRepository().updatePorongo(porongo,
                    new LoadingCallback<Porongo>(PorongoFormActivity.this, "Registrando Porongo...") {
                        @Override
                        public void onSuccess(boolean fromRemote, Porongo response) {
                            super.onSuccess(fromRemote, response);
                            if (fromRemote){
                                AppDatabase.getInstance().porongoModel().insert(response);
                            }
                            finish();
                        }
                    });
        } else {
            pager.setCurrentItem(newStep);
        }

    }

    public Porongo getPorongo() {
        return porongo;
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
