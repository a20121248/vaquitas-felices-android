package com.github.alvarosct.happycows.features.porongos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.github.alvarosct.ascthelper.ui.activities.BaseOnePanelChildActivity;
import com.github.alvarosct.ascthelper.utils.SimpleDividerItemDecoration;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.db.AppDatabase;
import com.github.alvarosct.happycows.db.models.Porongo;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PorongosActivity extends AppCompatActivity {

    @BindView(R.id.rv_data)
    SuperRecyclerView rvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porongo);
        ButterKnife.bind(this);

        setupView();
    }

    private void setupView() {
        rvData.getRecyclerView().setHasFixedSize(true);
        rvData.getRecyclerView().addItemDecoration(
                new SimpleDividerItemDecoration(this, com.github.alvarosct.ascthelper.R.drawable.line_divider_black));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvData.setLayoutManager(layoutManager);

        List<Porongo> list = AppDatabase.getInstance(this).porongoModel().getAll();
        PorongoAdapter adapter = new PorongoAdapter(this, list);
        rvData.setAdapter(adapter);
    }

}
