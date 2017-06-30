package com.github.alvarosct.happycows.utils;

import android.text.TextUtils;

import com.google.android.gms.vision.barcode.Barcode;

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
        int ddddd = hourDay * 3600 + minute * 60 + second;

        String stringId = String.format("%03d%03d%05d", yyy, dayYear, ddddd);
        return Integer.parseInt(stringId);
    }

    public static String getProductFromBarcode(String barcode) {

        String lookupString = "id_producto=";
        int productIndex = barcode.indexOf(lookupString);
        productIndex += lookupString.length();

        String newString = barcode.substring(productIndex);
        return newString;

    }

    public static String getLoteFromBarcode(String barcode) {

        String lookupString = "lote=";
        int productIndex = barcode.indexOf(lookupString);
        productIndex += lookupString.length();

        String newString = barcode.substring(productIndex);
        int endIndex = newString.indexOf("&");

        newString = newString.substring(0,endIndex);
        return newString;

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
