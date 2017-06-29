package com.github.alvarosct.ascthelper.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.alvarosct.ascthelper.utils.dialogs.DialogLoading;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Alvaro Santa Cruz on 23/02/2017.
 */

public class UtilMethods {

    private static Toast toast;
    private static DialogLoading progressDialog;
    private static Context context;

    public static void initialize(Context context) {
        UtilMethods.context = context;
        toast = Toast.makeText(context, "message_blank", Toast.LENGTH_SHORT);
    }

    public static void showToast(String message) {
        toast.setText(message);
        toast.show();
    }

    public static String parseNumber(int value){
        return value == 0? "-" : String.valueOf(value);
    }

    public static String parseNumber(double value){
        return value == 0? "-" : String.valueOf(value);
    }

    public static String parseNumber(float value){
        return value == 0? "-" : String.valueOf(value);
    }

    public static void notYet() {
        showToast("Proximamente...");
    }

    public static void shouldNeverHappen() {
        throw new RuntimeException("ASCT. This should never occurs");
    }

    public static Calendar stringToCalendar(String dateString, String format) {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        try {
            if (!TextUtils.isEmpty(dateString)) {
                calendar.setTime(sdf.parse(dateString));
            }
        } catch (ParseException e) {
            Log.e("ParseException", e.getMessage());
        }
        return calendar;
    }

    public static float pxFromDp(Context context, float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public static float pxFromSp(Context context, float sp) {
        return sp * context.getResources().getDisplayMetrics().scaledDensity;
    }


    public static int getVisibility(boolean flg) {
        return flg ? View.VISIBLE : View.GONE;
    }

    public static String calendarToString() {
        return calendarToString(Calendar.getInstance());
    }

    public static String calendarToString(Calendar calendar) {
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.BD_DATETIME_FORMAT, Locale.US);
        return sdf.format(date);
    }

    public static String calendarStringToString(String dateString, String formatInput, String formatOutput) {
        Calendar calendar = stringToCalendar(dateString, formatInput);
        return calendarToString(calendar, formatOutput);
    }

    public static String calendarToString(Calendar calendar, String format) {
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        return sdf.format(date);
    }

    public static String calendarToString(String format) {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        return sdf.format(date);
    }

    public static void showDialog(String message, Context context) {
        if (progressDialog != null) return;

        progressDialog = new DialogLoading(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    public static String getCammelCase(String field) {
        String[] names = field.split("_");
        String fullName = names[0];
        for (int i = 1; i < names.length; i++) {
            String name = names[i];
            fullName += (name.substring(0, 1).toUpperCase() + name.substring(1));
        }
        return fullName;
    }

    public static void hideDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public static void hideKeyboard(View view, Context context) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void toggleEnable(View view, boolean flag) {

        if (view instanceof TabLayout) {
//            view.setEnabled(flag);
        } else if (view instanceof Spinner) {
            view.setEnabled(flag);
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = ((ViewGroup) view);
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                toggleEnable(viewGroup.getChildAt(i), flag);
            }
        } else {
            view.setEnabled(flag);
            view.setClickable(flag);
        }
    }

    public static boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }
}
