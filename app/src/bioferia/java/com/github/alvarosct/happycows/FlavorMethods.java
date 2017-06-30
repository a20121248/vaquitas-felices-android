package com.github.alvarosct.happycows;

import android.app.Activity;
import android.content.Intent;

import com.github.alvarosct.happycows.features.MainMenuActivity;
import com.github.alvarosct.happycows.features.MenuHandler;
import com.github.alvarosct.happycows.features.degustaciones.DegustacionesRegistrarActivity;
import com.github.alvarosct.happycows.features.materiales.MaterialesRegistrarActivity;
import com.github.alvarosct.happycows.features.necesidades.NecesidadesRegistrarActivity;
import com.github.alvarosct.happycows.features.syncDatabase.SyncDatabaseNavActivity;
import com.github.alvarosct.happycows.features.usuario.UsuarioRegistrarActivity;
import com.github.alvarosct.happycows.features.venta.register.VentaRegistrarActivity;

/**
 * Created by Android-Dev on 27/06/2017.
 */

public class FlavorMethods {

    public static void openMainActivity(final Activity activity) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(activity, MainMenuActivity.class);
                activity.startActivity(i);
                activity.finish();
            }
        });
    }



    public static void setupDrawerItems() {

        new MenuHandler.DrawerItem(7, R.id.nav_ventas, 1, VentaRegistrarActivity.class);
        new MenuHandler.DrawerItem(14, R.id.nav_nuevo_usuario, 1, UsuarioRegistrarActivity.class);
        new MenuHandler.DrawerItem(9, R.id.nav_materiales, 1, MaterialesRegistrarActivity.class);
        new MenuHandler.DrawerItem(10, R.id.nav_necesidades, 1, NecesidadesRegistrarActivity.class);
        new MenuHandler.DrawerItem(11, R.id.nav_degustaciones, 1, DegustacionesRegistrarActivity.class);

        new MenuHandler.DrawerItem(1000, R.id.nav_sync, 1, SyncDatabaseNavActivity.class);
//        new DrawerItem(1001, R.id.nav_bio, 4, BiochemicalActivity.class);
    }
}
