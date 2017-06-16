package com.github.alvarosct.happycows.data.source;


import com.github.alvarosct.happycows.data.db.models.Client;
import com.github.alvarosct.happycows.data.db.models.Insumo;
import com.github.alvarosct.happycows.data.db.models.Producto;
import com.github.alvarosct.happycows.data.db.pojos.InsumoItem;
import com.github.alvarosct.happycows.data.db.pojos.VentaFull;
import com.github.alvarosct.happycows.data.source.callbacks.BaseCallback;
import com.github.alvarosct.happycows.data.db.models.Ganadero;
import com.github.alvarosct.happycows.data.db.models.Porongo;
import com.github.alvarosct.happycows.data.db.models.Pregunta;
import com.github.alvarosct.happycows.data.db.models.User;
import com.google.gson.JsonObject;

import java.util.List;

/**
 * Created by Alvaro Santa Cruz on 23/02/2017.
 */

public interface DataSource {

    void updatePorongo(Porongo entity, BaseCallback<Porongo> callback);

    void loginUser(String username, String password, BaseCallback<User> callback);

    void listPorongo(boolean loadTableFlg, BaseCallback<List<Porongo>> callback);

    void listGanadero(boolean loadTableFlg, BaseCallback<List<Ganadero>> callback);

    void listUser(boolean loadTableFlg, BaseCallback<List<User>> callback);

    void listPregunta(boolean loadTableFlg, BaseCallback<List<Pregunta>> callback);





    void listInsumo(boolean loadTableFlg, BaseCallback<List<Insumo>> callback);

    void registerMaterialesUsados(List<InsumoItem> insumoItemList, BaseCallback<String> callback);

    void registerClient(Client client, BaseCallback<Client> callback);

    void listProductos(boolean loadTableFlg, BaseCallback<List<Producto>> callback);

    void registerVenta(VentaFull ventaFull, BaseCallback<JsonObject> callback);
}