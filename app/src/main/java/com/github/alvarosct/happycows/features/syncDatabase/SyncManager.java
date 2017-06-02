package com.github.alvarosct.happycows.features.syncDatabase;

import android.support.annotation.NonNull;

import com.github.alvarosct.happycows.data.source.DataSourceRepository;
import com.github.alvarosct.happycows.data.source.callbacks.BaseCallback;
import com.github.alvarosct.happycows.data.source.remote.ApiError;
import com.github.alvarosct.happycows.db.AppDatabase;
import com.github.alvarosct.happycows.db.models.TableMaster;
import com.github.alvarosct.happycows.utils.Injector;

import java.lang.reflect.Method;
import java.util.List;


/**
 * Created by Android-Dev on 19/04/2017.
 */

public class SyncManager {


    //    Download
    protected int itemsToDownload = 0;
    protected int itemsDownloadSuccess = 0;
    protected int itemsDownloadFailure = 0;
    protected SyncDatabaseActivity.ISync iSync;

    private DataSourceRepository mRepository = Injector.provideRepository();

    public SyncManager(@NonNull SyncDatabaseActivity.ISync iSync) {
        this.iSync = iSync;
    }


    public void startSync() {
        for (TableMaster tableMaster : AppDatabase.getInstance().tableMasterModel().getAll()) {
            itemsToDownload += 1;
            listByName(tableMaster.getName());
        }
    }

    public void listByName(final String name) {
        DataSourceRepository mRepository = Injector.provideRepository();
        try {
            Method m = mRepository.getClass().getMethod("list" + name, boolean.class, BaseCallback.class);
            m.invoke(mRepository, true, new BaseCallback() {
                @Override
                public void onSuccess(boolean fromRemote, final Object response) {
                    if (fromRemote && response instanceof List) {
                        Thread thread = new Thread() {
                            @Override
                            public void run() {
//                              Use this instead of (SugarRecord.saveInTx();) to save the passport
                                long[] saved = AppDatabase.getInstance().modelByName(name).insertAll((List) response);
                                updateProgressDownload(1, name);

                            }
                        };
                        thread.start();
                    }
                }

                @Override
                public void onError(int statusCode, ApiError apiError) {
                    super.onError(statusCode, apiError);
                    updateProgressDownload(-1, name);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void updateProgressDownload(final int state, final String table) {

//        Always Success
        itemsDownloadSuccess += 1;

        float progress = getProgress(itemsToDownload, itemsDownloadSuccess, itemsDownloadFailure);
        if (progress >= 1) {
            iSync.onSyncDone();
        }

    }

    private float getProgress(int total, int success, int failure) {
        return 1.0f * (success + failure) / total;
    }


}
