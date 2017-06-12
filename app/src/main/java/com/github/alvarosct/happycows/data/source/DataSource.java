package com.github.alvarosct.happycows.data.source;


import com.github.alvarosct.happycows.data.source.callbacks.BaseCallback;
import com.github.alvarosct.happycows.data.db.models.Ganadero;
import com.github.alvarosct.happycows.data.db.models.Porongo;
import com.github.alvarosct.happycows.data.db.models.Pregunta;
import com.github.alvarosct.happycows.data.db.models.User;

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
}