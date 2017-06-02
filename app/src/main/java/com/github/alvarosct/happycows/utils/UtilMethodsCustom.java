package com.github.alvarosct.happycows.utils;

import java.util.Calendar;

/**
 * Created by Alvaro Santa Cruz on 27/02/2017.
 */

public class UtilMethodsCustom {

    public static int createLocalId() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int dayYear = now.get(Calendar.DAY_OF_YEAR);
        int hourDay = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int second = now.get(Calendar.SECOND);

        int yyy = year % 1000;
        int ddddd = hourDay * 3600 + minute *60 + second;

        String stringId = String.format("%03d%03d%05d", yyy, dayYear, ddddd );
        return Integer.parseInt(stringId);
    }

//    public static void sleep(int millis){
//        try {
//            Thread.sleep(millis);
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    public static <T> void displayLocalDbError(IBaseCallback<T> callback){
//        callback.onError(Constants.LOCAL_DB_ERROR, new ApiError("Error en la base de datos local"));
//    }


}
