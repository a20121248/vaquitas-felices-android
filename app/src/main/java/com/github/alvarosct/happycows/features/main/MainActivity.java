package com.github.alvarosct.happycows.features.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.db.AppDatabase;
import com.github.alvarosct.happycows.db.DatabaseInitializer;
import com.github.alvarosct.happycows.features.porongos.PorongosActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DatabaseInitializer.populateSync(AppDatabase.getInstance(this));

    }

    @OnClick({R.id.bt_sic, R.id.bt_qa})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_sic:
                break;
            case R.id.bt_qa:
                Intent i = new Intent(MainActivity.this, PorongosActivity.class);
                startActivity(i);
                break;
        }
    }
}
