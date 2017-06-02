package com.github.alvarosct.happycows.features.porongos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.db.AppDatabase;
import com.github.alvarosct.happycows.db.models.Porongo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PorongoDisplayActivity extends AppCompatActivity {

    @BindView(R.id.pager)
    ViewPager pager;

    private AppDatabase db;
    private Porongo porongo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.porongo_form);
        ButterKnife.bind(this);

        porongo = new Porongo();
        porongo.setGanaderoId(getIntent().getExtras().getInt("ID", -1));

        db = AppDatabase.getInstance(PorongoDisplayActivity.this);

        setupViewPager(pager);
    }


    public void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new PorongoFormPesoFragment(), "Peso");
        adapter.addFrag(new PorongoFormColorFragment(), "Color");
        adapter.addFrag(new PorongoFormOlorFragment(), "Olor");
        adapter.addFrag(new PorongoFormAlcoholFragment(), "Alcohol");
        adapter.addFrag(new PorongoFormBrixFragment(), "Brix");
        viewPager.setAdapter(adapter);
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
