package com.github.alvarosct.happycows.data.source;

import android.support.annotation.NonNull;

import com.github.alvarosct.happycows.data.source.callbacks.BaseCallback;
import com.github.alvarosct.happycows.data.source.callbacks.WrapFallback;
import com.github.alvarosct.happycows.db.models.Ganadero;
import com.github.alvarosct.happycows.db.models.Porongo;
import com.github.alvarosct.happycows.db.models.Pregunta;
import com.github.alvarosct.happycows.db.models.User;

import java.util.List;

/**
 * Created by Alvaro Santa Cruz on 23/02/2017.
 */

public class DataSourceRepository implements DataSource {

    private static DataSourceRepository INSTANCE = null;

    private final DataSource dataSourceLocal;
    private final DataSource dataSourceRemote;

    private DataSourceRepository(@NonNull DataSource dataSourceRemote,
                                 @NonNull DataSource dataSourceLocal) {
        this.dataSourceLocal = dataSourceLocal;
        this.dataSourceRemote = dataSourceRemote;
    }

    public static DataSourceRepository getInstance(DataSource dataSourceRemote,
                                                   DataSource dataSourceLocal) {
        if (INSTANCE == null) {
            INSTANCE = new DataSourceRepository(dataSourceRemote, dataSourceLocal);
        }
        return INSTANCE;
    }

    @Override
    public void updatePorongo(final Porongo entity, final BaseCallback<Porongo> callback) {
        dataSourceRemote.updatePorongo(entity, new WrapFallback<Porongo>(callback) {
            @Override
            public void onLocalDB() {
                dataSourceLocal.updatePorongo(entity, callback);
            }
        });
    }

    @Override
    public void loginUser(final String username, final String password, final BaseCallback<User> callback) {
        dataSourceLocal.loginUser(username, password, callback);

//        dataSourceRemote.loginUser(username, password, new WrapFallback<User>(callback) {
//            @Override
//            public void onLocalDB() {
//                dataSourceLocal.loginUser(username, password, callback);
//            }
//        });

    }

    @Override
    public void listPorongo(boolean loadTableFlg, BaseCallback<List<Porongo>> callback) {
        (loadTableFlg ? dataSourceRemote : dataSourceLocal).listPorongo(loadTableFlg, callback);
    }

    @Override
    public void listGanadero(boolean loadTableFlg, BaseCallback<List<Ganadero>> callback) {
        (loadTableFlg ? dataSourceRemote : dataSourceLocal).listGanadero(loadTableFlg, callback);
    }

    @Override
    public void listUser(boolean loadTableFlg, BaseCallback<List<User>> callback) {
        (loadTableFlg ? dataSourceRemote : dataSourceLocal).listUser(loadTableFlg, callback);
    }

    @Override
    public void listPregunta(boolean loadTableFlg, BaseCallback<List<Pregunta>> callback) {
        (loadTableFlg ? dataSourceRemote : dataSourceLocal).listPregunta(loadTableFlg, callback);
    }

}
