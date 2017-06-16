package com.github.alvarosct.happycows.data.source.local;

import com.github.alvarosct.happycows.data.db.models.Client;
import com.github.alvarosct.happycows.data.db.models.Insumo;
import com.github.alvarosct.happycows.data.db.pojos.InsumoItem;
import com.github.alvarosct.happycows.data.source.DataSource;
import com.github.alvarosct.happycows.data.source.callbacks.BaseCallback;
import com.github.alvarosct.happycows.data.source.remote.ApiError;
import com.github.alvarosct.happycows.data.db.AppDatabase;
import com.github.alvarosct.happycows.data.db.models.Ganadero;
import com.github.alvarosct.happycows.data.db.models.Porongo;
import com.github.alvarosct.happycows.data.db.models.Pregunta;
import com.github.alvarosct.happycows.data.db.models.User;
import com.github.alvarosct.happycows.utils.UtilMethodsCustom;

import java.util.List;

/**
 * Created by Alvaro Santa Cruz on 23/02/2017.
 */

public class DataSourceLocal implements DataSource {


    private static DataSourceLocal INSTANCE = null;

    public static DataSourceLocal getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataSourceLocal();
        }
        return INSTANCE;
    }

    @Override
    public void updatePorongo(Porongo entity, BaseCallback<Porongo> callback) {
        if (entity.idNull()) {
            int localId = UtilMethodsCustom.createLocalId();
            entity.setId(localId);
        }
        entity.setLocalChange(true);
        long newId = AppDatabase.getInstance().porongoModel().insert(entity);
        if (newId > 0) {
            callback.onSuccess(false, entity);
        } else {
            callback.onError(0, new ApiError("No se pudo hacer el registro en la base de datos local"));
        }
    }

    @Override
    public void loginUser(String username, String password, BaseCallback<User> callback) {

//        TODO: Change depending on the used encryption
//        String hashedPass = UtilMethods.md5("ab513c75f48d82bcd30aa48e478d2e6e" + password);
//        User user = AppDatabase.getInstance().userModel().validateUser(username, hashedPass);

        User user = AppDatabase.getInstance().userModel().validateUser(username, password);

        if (user != null) {
            callback.onSuccess(false, user);
        } else {
            callback.onError(0, new ApiError("Usuario no encontrado en base de datos local"));
        }
    }

    @Override
    public void listPorongo(boolean loadTableFlg, BaseCallback<List<Porongo>> callback) {
        //DO NOT IMPLEMENT THIS METHOD
    }

    @Override
    public void listGanadero(boolean loadTableFlg, BaseCallback<List<Ganadero>> callback) {
        //DO NOT IMPLEMENT THIS METHOD
    }

    @Override
    public void listUser(boolean loadTableFlg, BaseCallback<List<User>> callback) {
        //DO NOT IMPLEMENT THIS METHOD
    }

    @Override
    public void listPregunta(boolean loadTableFlg, BaseCallback<List<Pregunta>> callback) {
        //DO NOT IMPLEMENT THIS METHOD
    }

    @Override
    public void listInsumo(boolean loadTableFlg, BaseCallback<List<Insumo>> callback) {

    }

    @Override
    public void registerMaterialesUsados(List<InsumoItem> insumoItemList, BaseCallback<String> callback) {

    }

    @Override
    public void registerClient(Client client, BaseCallback<String> callback) {

    }
}
