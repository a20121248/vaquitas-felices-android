package com.github.alvarosct.happycows.data.source.remote;

import com.github.alvarosct.happycows.data.db.AppDatabase;
import com.github.alvarosct.happycows.data.db.models.Client;
import com.github.alvarosct.happycows.data.db.models.Compra;
import com.github.alvarosct.happycows.data.db.models.DetalleCalidad;
import com.github.alvarosct.happycows.data.db.models.DetalleCompra;
import com.github.alvarosct.happycows.data.db.models.District;
import com.github.alvarosct.happycows.data.db.models.Ganadero;
import com.github.alvarosct.happycows.data.db.models.Insumo;
import com.github.alvarosct.happycows.data.db.models.ParametroCalidad;
import com.github.alvarosct.happycows.data.db.models.Porongo;
import com.github.alvarosct.happycows.data.db.models.Pregunta;
import com.github.alvarosct.happycows.data.db.models.Producto;
import com.github.alvarosct.happycows.data.db.models.Proveedor;
import com.github.alvarosct.happycows.data.db.models.User;
import com.github.alvarosct.happycows.data.db.pojos.InsumoItem;
import com.github.alvarosct.happycows.data.db.pojos.InsumoItemWrapper;
import com.github.alvarosct.happycows.data.db.pojos.VentaFull;
import com.github.alvarosct.happycows.data.source.DataSource;
import com.github.alvarosct.happycows.data.source.callbacks.BaseCallback;
import com.google.gson.JsonObject;

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
        RequestManager.getWebServices().createPorongo(entity).enqueue(callback);
    }


    @Override
    public void loginUser(String username, String password, int type, BaseCallback<User> callback) {
        RequestManager.getWebServices().loginEmpleados(username, password, type).enqueue(callback);
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
    public void listDistrict(boolean loadTableFlg, BaseCallback<List<District>> callback) {
        String updated = AppDatabase.getInstance().tableMasterModel().getLastSyncForTable("District");
        RequestManager.getWebServices().listDistricts(updated).enqueue(callback);

    }

    @Override
    public void listClient(boolean loadTableFlg, BaseCallback<List<Client>> callback) {
        String updated = AppDatabase.getInstance().tableMasterModel().getLastSyncForTable("Client");
        RequestManager.getWebServices().listClients(updated).enqueue(callback);

    }

    @Override
    public void listPregunta(boolean loadTableFlg, BaseCallback<List<Pregunta>> callback) {
        String updated = AppDatabase.getInstance().tableMasterModel().getLastSyncForTable("Pregunta");
        RequestManager.getWebServices().listPregunta(updated).enqueue(callback);
    }

    @Override
    public void listCompra(boolean loadTableFlg, BaseCallback<List<Compra>> callback) {
        String updated = AppDatabase.getInstance().tableMasterModel().getLastSyncForTable("Compra");
        RequestManager.getWebServices().listCompra(updated).enqueue(callback);
    }

    @Override
    public void listDetalleCompra(boolean loadTableFlg, BaseCallback<List<DetalleCompra>> callback) {
        String updated = AppDatabase.getInstance().tableMasterModel().getLastSyncForTable("DetalleCompra");
        RequestManager.getWebServices().listDetalleCompra(updated).enqueue(callback);
    }

    @Override
    public void listParametroCalidad(boolean loadTableFlg, BaseCallback<List<ParametroCalidad>> callback) {
        String updated = AppDatabase.getInstance().tableMasterModel().getLastSyncForTable("ParametroCalidad");
        RequestManager.getWebServices().listParametroCalidad(updated).enqueue(callback);
    }

    @Override
    public void listDetalleCalidad(boolean loadTableFlg, BaseCallback<List<DetalleCalidad>> callback) {
        String updated = AppDatabase.getInstance().tableMasterModel().getLastSyncForTable("DetalleCalidad");
        RequestManager.getWebServices().listDetalleCalidad(updated).enqueue(callback);
    }

    @Override
    public void listProveedor(boolean loadTableFlg, BaseCallback<List<Proveedor>> callback) {
        String updated = AppDatabase.getInstance().tableMasterModel().getLastSyncForTable("Proveedor");
        RequestManager.getWebServices().listProveedor(updated).enqueue(callback);
    }

    @Override
    public void listInsumo(boolean loadTableFlg, BaseCallback<List<Insumo>> callback) {
        RequestManager.getWebServices().listInsumo().enqueue(callback);
    }

    @Override
    public void registerMaterialesUsados(List<InsumoItem> insumoItemList, final BaseCallback<JsonObject> callback) {
        RequestManager.getWebServices().registerMaterialesUsados(new InsumoItemWrapper(insumoItemList)).enqueue(callback);
    }

    @Override
    public void registerDegustaciones(VentaFull ventaFull, final BaseCallback<JsonObject> callback) {
        RequestManager.getWebServices().registerDegustaciones(ventaFull).
                enqueue(callback);

    }

    @Override
    public void registerNecesidades(VentaFull ventaFull, final BaseCallback<JsonObject> callback) {
        RequestManager.getWebServices().registerNecesidades(ventaFull).
                enqueue(callback);
    }

    @Override
    public void registerClient(Client client, final BaseCallback<Client> callback) {
        RequestManager.getWebServices().registerClient(client).enqueue(callback);
    }

    @Override
    public void listProducto(boolean loadTableFlg, final BaseCallback<List<Producto>> callback) {
        RequestManager.getWebServices().listProducto().enqueue(callback);
    }

    @Override
    public void registerVenta(VentaFull ventaFull, BaseCallback<JsonObject> callback) {
        RequestManager.getWebServices().registerVenta(ventaFull).enqueue(callback);
    }
}
