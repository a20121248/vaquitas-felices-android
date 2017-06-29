package com.github.alvarosct.happycows;

import android.app.Activity;
import android.content.Intent;

import com.github.alvarosct.happycows.features.MainMenuActivity;
import com.github.alvarosct.happycows.features.MenuHandler;
import com.github.alvarosct.happycows.features.insumos.InsumoListActivity;
import com.github.alvarosct.happycows.features.porongos.PorongoFormActivity;
import com.github.alvarosct.happycows.features.porongos.PorongoFormDirectActivity;
import com.github.alvarosct.happycows.features.porongos.PorongoListActivity;
import com.github.alvarosct.happycows.features.sic.GanaderosSicActivity;
import com.github.alvarosct.happycows.features.syncDatabase.SyncDatabaseNavActivity;

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

        new MenuHandler.DrawerItem(11, R.id.nav_porongos, 1, PorongoListActivity.class);
        new MenuHandler.DrawerItem(12, R.id.nav_nuevo_porongo, 1, PorongoFormDirectActivity.class);
//        new MenuHandler.DrawerItem(13, R.id.nav_insumos, 1, PorongoListActivity.class);
//        new MenuHandler.DrawerItem(14, R.id.nav_produccion, 1, PorongoListActivity.class);

        new MenuHandler.DrawerItem(1000, R.id.nav_sync, 1, SyncDatabaseNavActivity.class);
//        new DrawerItem(1001, R.id.nav_bio, 4, BiochemicalActivity.class);
    }
}
