package com.github.alvarosct.happycows.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.github.alvarosct.happycows.data.db.dao.BaseDao;
import com.github.alvarosct.happycows.data.db.dao.ClientDao;
import com.github.alvarosct.happycows.data.db.dao.CompraDao;
import com.github.alvarosct.happycows.data.db.dao.DetalleCalidadDao;
import com.github.alvarosct.happycows.data.db.dao.DetalleCompraDao;
import com.github.alvarosct.happycows.data.db.dao.DistrictDao;
import com.github.alvarosct.happycows.data.db.dao.ElaboracionIngredienteDao;
import com.github.alvarosct.happycows.data.db.dao.ElaboracionPasoDao;
import com.github.alvarosct.happycows.data.db.dao.EncuestaDao;
import com.github.alvarosct.happycows.data.db.dao.GanaderoDao;
import com.github.alvarosct.happycows.data.db.dao.IngredienteDao;
import com.github.alvarosct.happycows.data.db.dao.InsumoDao;
import com.github.alvarosct.happycows.data.db.dao.OrdenProduccionDao;
import com.github.alvarosct.happycows.data.db.dao.ParametroCalidadDao;
import com.github.alvarosct.happycows.data.db.dao.PasoDao;
import com.github.alvarosct.happycows.data.db.dao.PorongoDao;
import com.github.alvarosct.happycows.data.db.dao.PreguntaDao;
import com.github.alvarosct.happycows.data.db.dao.PreguntaInsumoDao;
import com.github.alvarosct.happycows.data.db.dao.ProcesoDao;
import com.github.alvarosct.happycows.data.db.dao.ProductoDao;
import com.github.alvarosct.happycows.data.db.dao.ProveedorDao;
import com.github.alvarosct.happycows.data.db.dao.TableMasterDao;
import com.github.alvarosct.happycows.data.db.dao.UserDao;
import com.github.alvarosct.happycows.data.db.models.Client;
import com.github.alvarosct.happycows.data.db.models.Compra;
import com.github.alvarosct.happycows.data.db.models.DetalleCalidad;
import com.github.alvarosct.happycows.data.db.models.DetalleCompra;
import com.github.alvarosct.happycows.data.db.models.District;
import com.github.alvarosct.happycows.data.db.models.ElaboracionIngrediente;
import com.github.alvarosct.happycows.data.db.models.ElaboracionPaso;
import com.github.alvarosct.happycows.data.db.models.Encuesta;
import com.github.alvarosct.happycows.data.db.models.Ganadero;
import com.github.alvarosct.happycows.data.db.models.Ingrediente;
import com.github.alvarosct.happycows.data.db.models.Insumo;
import com.github.alvarosct.happycows.data.db.models.OrdenProduccion;
import com.github.alvarosct.happycows.data.db.models.ParametroCalidad;
import com.github.alvarosct.happycows.data.db.models.Paso;
import com.github.alvarosct.happycows.data.db.models.Porongo;
import com.github.alvarosct.happycows.data.db.models.Pregunta;
import com.github.alvarosct.happycows.data.db.models.PreguntaInsumo;
import com.github.alvarosct.happycows.data.db.models.Proceso;
import com.github.alvarosct.happycows.data.db.models.Producto;
import com.github.alvarosct.happycows.data.db.models.Proveedor;
import com.github.alvarosct.happycows.data.db.models.TableMaster;
import com.github.alvarosct.happycows.data.db.models.User;

import java.lang.reflect.Method;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Database(entities = {
        TableMaster.class,
        Porongo.class, Encuesta.class, Ganadero.class,
        Pregunta.class, PreguntaInsumo.class, Insumo.class, User.class,
        Compra.class, DetalleCompra.class, ParametroCalidad.class, Proveedor.class, DetalleCalidad.class,
        Paso.class, Ingrediente.class, ElaboracionIngrediente.class, ElaboracionPaso.class,
        OrdenProduccion.class, Proceso.class, Producto.class,
        Client.class, District.class
}, version = 7)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public BaseDao modelByName(String name) {
        String formattedName = name.substring(0, 1).toLowerCase() + name.substring(1);
        try {
            Method m = this.getClass().getMethod(formattedName + "Model");
            return (BaseDao) m.invoke(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract TableMasterDao tableMasterModel();

    public abstract PasoDao pasoModel();

    public abstract IngredienteDao ingredienteModel();

    public abstract ElaboracionIngredienteDao elaboracionIngredienteModel();

    public abstract ElaboracionPasoDao elaboracionPasoModel();

    public abstract OrdenProduccionDao ordenProduccionModel();

    public abstract ProcesoDao procesoModel();

    public abstract PorongoDao porongoModel();

    public abstract EncuestaDao encuestaModel();

    public abstract PreguntaInsumoDao preguntaInsumoModel();

    public abstract InsumoDao insumoModel();

    public abstract DistrictDao districtModel();

    public abstract ClientDao clientModel();

    public abstract CompraDao compraModel();

    public abstract DetalleCompraDao detalleCompraModel();

    public abstract ParametroCalidadDao parametroCalidadModel();

    public abstract ProveedorDao proveedorModel();

    public abstract DetalleCalidadDao detalleCalidadModel();

    public abstract GanaderoDao ganaderoModel();

    public abstract PreguntaDao preguntaModel();

    public abstract ProductoDao productoModel();

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
