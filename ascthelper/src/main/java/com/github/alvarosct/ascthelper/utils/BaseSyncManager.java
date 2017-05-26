package com.github.alvarosct.ascthelper.utils;

import android.support.annotation.NonNull;

import com.github.alvarosct.ascthelper.ui.activities.BaseActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Android-Dev on 19/04/2017.
 */

public abstract class BaseSyncManager {

    public int currentCount;
    public int totalCount;
    public String date;

//    public DataSourceRepository mRepository;

    public List<String> errorList;
    public ILoading iLoading;
    public BaseActivity activity;

    public BaseSyncManager(BaseActivity activity, int totalCount, @NonNull ILoading iLoading) {
        this.totalCount = totalCount;
        this.currentCount = 0;
        this.activity = activity;
        this.errorList = new ArrayList<>();
        this.date = UtilMethods.calendarToString();
        this.iLoading = iLoading;
    }

    public void updateProgressDownload(final int state, final String table) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String message = "";

                switch (state) {
                    case -1:
                        getErrorList().add(table);
                        currentCount += 1;
                        message = String.format("Ocurrio un error en la carga de la tabla %s", table);
                        break;
                    case 1:
                        message = String.format("Se registro la tabla %s", table);
                        setLastSync(table, date);
                        currentCount += 1;
                        break;
                }

                float percentage = 1.0f * currentCount / totalCount;
                iLoading.updateLoadingStatus(percentage, message);
                if (percentage == 1) {
                    if (errorList.isEmpty()) {
                        iLoading.onDownloadSucceed();
                    } else {
                        iLoading.onDownloadFailed(getErrorList());
                    }
                }
            }
        });
    }


    public void updateProgressUpload(final int state, final String table) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String message = "";

                switch (state) {
                    case -1:
                        getErrorList().add(table);
                        currentCount += 1;
                        message = String.format("Ocurrio un error en la carga de un %s", table);
                        break;
                    case 1:
                        currentCount += 1;
                        message = String.format("Se cargo un %s a la nube", table);
                        break;
                }

                float percentage = 1.0f * currentCount / (2 * totalCount);
                iLoading.updateLoadingStatus(1.0f * currentCount / totalCount, message);
                if (percentage == 1) {
                    if (errorList.isEmpty()) {
                        iLoading.onDownloadSucceed();
                    } else {
                        iLoading.onDownloadFailed(getErrorList());
                    }
                }
            }
        });
    }

    public abstract void setLastSync(String table, String date);

    public abstract void downloadAll();

    public abstract void updaloadAll();

    public abstract void listByName(String name);

    public abstract void updaloadByName(String name);

    public List<String> getErrorList() {
        return errorList;
    }

    public interface ILoading {
        void updateLoadingStatus(float percentage, String message);

        void onDownloadSucceed();

        void onDownloadFailed(List<String> errorList);

        void onUploadSucceed();

        void onUploadFailed(List<String> errorList);
    }

//    public void downloadAll() {
//        for (TableMaster tableMaster : TableMaster.listAll(TableMaster.class)) {
//            listByName(tableMaster.getName());
//        }
//    }
//
//    private void listByName(String name) {
//        try {
//            Method m = mRepository.getClass().getMethod("list" + name, boolean.class, BaseCallback.class);
//            Class<?> clazz = Class.forName("pe.com.dms.inia.data.source.callbacks.SyncDownCallback");
//            Constructor<?> constructor = clazz.getConstructor(String.class, SyncManager.class);
//            Object instance = constructor.newInstance(name, this);
//            m.invoke(mRepository, true, instance);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}
