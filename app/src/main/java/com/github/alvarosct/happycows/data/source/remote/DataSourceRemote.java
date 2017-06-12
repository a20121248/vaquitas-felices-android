package com.github.alvarosct.happycows.data.source.remote;

import com.github.alvarosct.happycows.data.source.DataSource;
import com.github.alvarosct.happycows.data.source.callbacks.BaseCallback;
import com.github.alvarosct.happycows.data.db.AppDatabase;
import com.github.alvarosct.happycows.data.db.models.Ganadero;
import com.github.alvarosct.happycows.data.db.models.Porongo;
import com.github.alvarosct.happycows.data.db.models.Pregunta;
import com.github.alvarosct.happycows.data.db.models.User;

import java.util.List;

/**
 * Created by Alvaro Santa Cruz on 23/02/2017.
 */

public class DataSourceRemote implements DataSource {


    private static DataSourceRemote INSTANCE = null;

    public static DataSourceRemote getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataSourceRemote();
        }
        return INSTANCE;
    }

    @Override
    public void updatePorongo(Porongo entity, BaseCallback<Porongo> callback) {
        if (entity.isLocalCreate() || entity.idNull()) {
            RequestManager.getWebServices().createPorongo(entity).enqueue(callback);
        } else {
            RequestManager.getWebServices().updatePorongo(entity.getId(), entity).enqueue(callback);
        }
    }


    @Override
    public void loginUser(String username, String password, BaseCallback<User> callback) {
//        RequestManager.getWebServices().login(username, password).enqueue(callback);
    }

    @Override
    public void listPorongo(boolean loadTableFlg, BaseCallback<List<Porongo>> callback) {
        String updated = AppDatabase.getInstance().tableMasterModel().getLastSyncForTable("Porongo");
        RequestManager.getWebServices().listPorongo(updated).enqueue(callback);
    }

    @Override
    public void listGanadero(boolean loadTableFlg, BaseCallback<List<Ganadero>> callback) {
        String updated = AppDatabase.getInstance().tableMasterModel().getLastSyncForTable("Ganadero");
        RequestManager.getWebServices().listGanadero(updated).enqueue(callback);
    }

    @Override
    public void listUser(boolean loadTableFlg, BaseCallback<List<User>> callback) {
        String updated = AppDatabase.getInstance().tableMasterModel().getLastSyncForTable("User");
        RequestManager.getWebServices().listUser(updated).enqueue(callback);

    }

    @Override
    public void listPregunta(boolean loadTableFlg, BaseCallback<List<Pregunta>> callback) {
        String updated = AppDatabase.getInstance().tableMasterModel().getLastSyncForTable("Pregunta");
        RequestManager.getWebServices().listPregunta(updated).enqueue(callback);
    }
}
