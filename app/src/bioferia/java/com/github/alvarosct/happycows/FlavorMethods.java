package com.github.alvarosct.happycows;

import android.app.Activity;
import android.content.Intent;

import com.github.alvarosct.happycows.features.MainMenuActivity;

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

        new DrawerItem(7, R.id.nav_ventas, 1, VentaRegistrarActivity.class);
        new DrawerItem(14, R.id.nav_nuevo_usuario, 1, UsuarioRegistrarActivity.class);
        new DrawerItem(9, R.id.nav_materiales, 1, MaterialesRegistrarActivity.class);
        new DrawerItem(10, R.id.nav_necesidades, 1, NecesidadesRegistrarActivity.class);
        new DrawerItem(11, R.id.nav_degustaciones, 1, DegustacionesRegistrarActivity.class);

        new DrawerItem(1000, R.id.nav_sync, 1, SyncDatabaseNavActivity.class);
//        new DrawerItem(1001, R.id.nav_bio, 4, BiochemicalActivity.class);
    }
}
