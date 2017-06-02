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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.porongo_display);
        ButterKnife.bind(this);
    }
}
