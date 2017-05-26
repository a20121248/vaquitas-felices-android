package com.github.alvarosct.happycows.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.github.alvarosct.happycows.db.dao.CalidadDao;
import com.github.alvarosct.happycows.db.dao.EncuestaDao;
import com.github.alvarosct.happycows.db.dao.GanaderoDao;
import com.github.alvarosct.happycows.db.dao.PorongoDao;
import com.github.alvarosct.happycows.db.dao.PreguntaDao;
import com.github.alvarosct.happycows.db.models.Calidad;
import com.github.alvarosct.happycows.db.models.Encuesta;
import com.github.alvarosct.happycows.db.models.Ganadero;
import com.github.alvarosct.happycows.db.models.Porongo;
import com.github.alvarosct.happycows.db.models.Pregunta;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Database(entities = {
        Porongo.class, Encuesta.class, Ganadero.class,
        Pregunta.class, Calidad.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract PorongoDao porongoModel();

    public abstract EncuestaDao encuestaModel();

    public abstract GanaderoDao ganaderoModel();

    public abstract PreguntaDao preguntaModel();

    public abstract CalidadDao calidadModel();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context, AppDatabase.class, "vaquitas_db")
                            // To simplify the codelab, allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();


        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
