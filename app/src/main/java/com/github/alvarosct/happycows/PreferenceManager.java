package com.github.alvarosct.happycows;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.alvarosct.happycows.data.db.models.User;
import com.github.alvarosct.happycows.utils.Constants;
import com.google.gson.Gson;

/**
 * Created by Alvaro Santa Cruz on 27/03/2017.
 */

public class PreferenceManager {

    private static PreferenceManager preferencesManager;
    private final SharedPreferences mPreferences;

    private PreferenceManager(Context context) {
        mPreferences = context.getSharedPreferences(Constants.PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static PreferenceManager getInstance(Context context) {
        if (preferencesManager == null) {
            preferencesManager = new PreferenceManager(context);
        }
        return preferencesManager;
    }

    public void saveUser(User user){
        String userString = new Gson().toJson(user, User.class);
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(Constants.PREF_USER, userString);
        editor.apply();
    }

    public void removeUser() {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.remove(Constants.PREF_USER);
        editor.apply();
    }


    public User getUserInfo(){
        String userString = mPreferences.getString(Constants.PREF_USER, "");
        return new Gson().fromJson(userString, User.class);
    }

    public boolean isUserLoged(){
        return (getUserInfo() != null);
    }

    public void removeFlags() {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.remove(Constants.PREF_FLAG_FIRST);
        editor.remove(Constants.PREF_FLAG_BD_SYNCED);
        editor.apply();
    }

    public void saveFlag(String flagName){
        saveFlag(flagName, true);
    }

    public void saveFlag(String flagName, boolean flag){
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(flagName, flag);
        editor.apply();
    }

    public boolean getFlag(String flagName){
        return mPreferences.getBoolean(flagName, false);
    }

}
