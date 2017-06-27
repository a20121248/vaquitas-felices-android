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
}
