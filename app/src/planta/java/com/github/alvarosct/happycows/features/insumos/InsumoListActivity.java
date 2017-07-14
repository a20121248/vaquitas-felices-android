package com.github.alvarosct.happycows.features.insumos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.github.alvarosct.ascthelper.ui.IAdapterDetail;
import com.github.alvarosct.ascthelper.utils.SimpleDividerItemDecoration;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.db.AppDatabase;
import com.github.alvarosct.happycows.data.db.models.Insumo;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InsumoListActivity extends AppCompatActivity implements IAdapterDetail {

    @BindView(R.id.rv_data)
    SuperRecyclerView rvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_insumo);
        ButterKnife.bind(this);

        setupView();
    }

    private void setupView() {
        rvData.getRecyclerView().setHasFixedSize(true);
        rvData.getRecyclerView().addItemDecoration(
                new SimpleDividerItemDecoration(this, com.github.alvarosct.ascthelper.R.drawable.line_divider_black));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvData.setLayoutManager(layoutManager);

        List<Insumo> list = AppDatabase.getInstance(this).insumoModel().listAllUtils();
        InsumosAdapter adapter = new InsumosAdapter(this, list);
        rvData.setAdapter(adapter);
    }

//    @OnClick(R.id.fab_add)
//    public void onViewClicked() {
//        Intent i = new Intent(this, PorongoFormActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putInt("ID", );
//        startActivity(i, bundle);
//    }

    @Override
    public void openDetail(int id) {
//        Intent i = new Intent(this, PorongoFormActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putInt("ID", id);
//        startActivity(i, bundle);

    }
}
