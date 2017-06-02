package com.github.alvarosct.happycows.features.porongos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.github.alvarosct.happycows.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GanaderosActivity extends AppCompatActivity {

    @BindView(R.id.bt_done)
    Button btDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.porongo_display);
        ButterKnife.bind(this);
    }
}
