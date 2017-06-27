package com.github.alvarosct.happycows.data.source;

import android.support.annotation.NonNull;

import com.github.alvarosct.ascthelper.utils.UtilMethods;
import com.github.alvarosct.happycows.data.db.models.Client;
import com.github.alvarosct.happycows.data.db.models.Insumo;
import com.github.alvarosct.happycows.data.db.models.Producto;
import com.github.alvarosct.happycows.data.db.pojos.InsumoItem;
import com.github.alvarosct.happycows.data.db.pojos.ProductoItem;
import com.github.alvarosct.happycows.data.db.pojos.VentaFull;
import com.github.alvarosct.happycows.data.source.callbacks.BaseCallback;
import com.github.alvarosct.happycows.data.source.callbacks.WrapFallback;
import com.github.alvarosct.happycows.data.db.models.Ganadero;
import com.github.alvarosct.happycows.data.db.models.Porongo;
import com.github.alvarosct.happycows.data.db.models.Pregunta;
import com.github.alvarosct.happycows.data.db.models.User;
import com.google.gson.JsonObject;

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

        entity.setFechaHoraEntrega(UtilMethods.calendarToString());

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

    @Override
    public void listInsumo(boolean loadTableFlg, BaseCallback<List<Insumo>> callback) {
        dataSourceRemote.listInsumo(false, callback);
    }

    @Override
    public void registerMaterialesUsados(List<InsumoItem> insumoItemList, BaseCallback<String> callback) {
        dataSourceRemote.registerMaterialesUsados(insumoItemList, callback);
    }

    @Override
    public void registerDegustaciones(List<ProductoItem> productoItemList, BaseCallback<String> callback) {
        dataSourceRemote.registerDegustaciones(productoItemList, callback);

    }

    @Override
    public void registerNecesidades(List<ProductoItem> productoItemList, BaseCallback<String> callback) {
        dataSourceRemote.registerNecesidades(productoItemList, callback);

    }

    @Override
    public void registerClient(Client client, BaseCallback<Client> callback) {
        dataSourceRemote.registerClient(client, callback);

    }

    @Override
    public void listProductos(boolean loadTableFlg, BaseCallback<List<Producto>> callback) {
        dataSourceRemote.listProductos(false, callback);
    }

    @Override
    public void registerVenta(VentaFull ventaFull, BaseCallback<JsonObject> callback) {
        dataSourceRemote.registerVenta(ventaFull, callback);
    }

}
