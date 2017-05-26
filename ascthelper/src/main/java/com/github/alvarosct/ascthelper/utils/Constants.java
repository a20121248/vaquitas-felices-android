package com.github.alvarosct.ascthelper.utils;

/**
 * Created by Alvaro Santa Cruz on 27/02/2017.
 */

public class Constants {

    //    Callback Constants
    public static final int HTTP_INTERNAL_SERVER_ERROR = 500;
    public static final int HTTP_CLIENT_ERROR = 400;
    public static final int LOCAL_DB_ERROR = 120;
    public static final int NO_INTERNET = 0;

    //    Resources
    public static final int STATUS_AVAILABLE = 1;

    public static final int RESOURCE_FITO = 1;
    public static final int RESOURCE_ZOO = 2;
    public static final int RESOURCE_MICRO = 3;
    public static final int RESOURCE_ALL = 4;


    public static final String BUNDLE_ACTION = "Action";
    public static final String BUNDLE_ENTITY = "Entity";
    public static final String BUNDLE_ENTITY_ID = "EntityId";
    public static final String BUNDLE_ITEM_ID = "ItemId";
    public static final String BUNDLE_RESOURCE_ID = "ResourceId";
    public static final String BUNDLE_SECOND_PANEL = "SecondPanel";
    public static final String BUNDLE_FORM_FRAGMENT = "FormFragment";


    //    Resources
    public static final int COUNTRY_PERU = 173;


    //    Log Tag
    public static final String TAG = "INIA";

    //    Preference Manager
    public static final String PREFERENCES_NAME = "INIA_ANDROID_APP";
    public static final String PREF_USER = "USER";
    public static final String PREF_MODIFIED_DATE = "MODIFIED_DATE";
    public static final String PREF_FLAG_FIRST = "FIRST_TIME";
    public static final String PREF_FLAG_BD_SYNCED = "BD_LOCAL";

    //    Sugar
    public static final long ID_UNIQUE_SUGAR = 99l;


    //    App View Format
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String TIME_FORMAT = "hh:mm a";
    public static final String DATETIME_FORMAT = "dd/MM/yyyy hh:mm a";



    //    WS Format
    public static final String BD_DATE_FORMAT = "yyyy-MM-dd";
    public static final String BD_TIME_FORMAT = "HH:mm:ss";
    public static final String BD_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String EXTRA_TOOLBAR_PRIMARY = "TOOLBAR_PRIMARY";
    public static final String ACTION_SHOW = "Detalle";
    public static final String ACTION_EDIT = "Editar";
    public static final String ACTION_CREATE = "Nuevo";

    public static final String EXTRA_PASSPORT_ID = "EXTRA_PASSPORT_ID";


    public static final int INTENT_FORM = 222;

//    Only APP
    public static final int INTENT_SYNC_ACTIVITY = 333;
}
