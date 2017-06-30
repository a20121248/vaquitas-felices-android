package com.github.alvarosct.happycows;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.github.alvarosct.ascthelper.utils.UtilMethods;
import com.github.alvarosct.happycows.data.db.AppDatabase;
import com.github.alvarosct.happycows.data.db.DatabaseInitializer;
import com.github.alvarosct.happycows.data.db.models.TableMaster;
import com.github.alvarosct.happycows.utils.Constants;

import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Alvaro Santa Cruz on 20/02/2017.
 */

public class App extends Application {

    private static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        App.context = getApplicationContext();
        AppDatabase.getInstance(this);

//        Initialize UtilMethods
        UtilMethods.initialize(getApplicationContext());

//        Set Custom Font Handler
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/ubuntu.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        if (!PreferenceManager.getInstance(context).getFlag(Constants.PREF_FLAG_FIRST)) {
            loadTableMaster();
        }

    }

    public static Context getContext() {
        return App.context;
    }

    private void loadTableMaster() {

        DatabaseInitializer.populateSync(AppDatabase.getInstance());

        List<TableMaster> tables = new ArrayList<>();
        tables.add(new TableMaster("Ganadero"));
        tables.add(new TableMaster("Porongo"));

//        tables.add(new TableMaster("User"));
//        tables.add(new TableMaster("Pregunta"));

        AppDatabase.getInstance(this).tableMasterModel().insertAll(tables);

        PreferenceManager.getInstance(context).saveFlag(Constants.PREF_FLAG_FIRST);


    }
}
