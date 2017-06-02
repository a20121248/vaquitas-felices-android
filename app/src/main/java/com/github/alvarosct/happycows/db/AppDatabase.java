package com.github.alvarosct.happycows.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.github.alvarosct.happycows.db.dao.BaseDao;
import com.github.alvarosct.happycows.db.dao.TableMasterDao;
import com.github.alvarosct.happycows.db.dao.UserDao;
import com.github.alvarosct.happycows.db.dao.EncuestaDao;
import com.github.alvarosct.happycows.db.dao.GanaderoDao;
import com.github.alvarosct.happycows.db.dao.PorongoDao;
import com.github.alvarosct.happycows.db.dao.PreguntaDao;
import com.github.alvarosct.happycows.db.models.TableMaster;
import com.github.alvarosct.happycows.db.models.User;
import com.github.alvarosct.happycows.db.models.Encuesta;
import com.github.alvarosct.happycows.db.models.Ganadero;
import com.github.alvarosct.happycows.db.models.Porongo;
import com.github.alvarosct.happycows.db.models.Pregunta;

import java.lang.reflect.Method;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Database(entities = {
        TableMaster.class,
        Porongo.class, Encuesta.class, Ganadero.class,
        Pregunta.class, User.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public BaseDao modelByName(String name){
        String formattedName = name.substring(0,1).toLowerCase() + name.substring(1);
        try {
            Method m = this.getClass().getMethod( formattedName + "Model");
            return (BaseDao) m.invoke(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract TableMasterDao tableMasterModel();

    public abstract PorongoDao porongoModel();

    public abstract EncuestaDao encuestaModel();

    public abstract GanaderoDao ganaderoModel();

    public abstract PreguntaDao preguntaModel();

    public abstract UserDao userModel();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context, AppDatabase.class, "vaquitas_db")
//                            TODO: Remove this
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }
    public static AppDatabase getInstance() {
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
