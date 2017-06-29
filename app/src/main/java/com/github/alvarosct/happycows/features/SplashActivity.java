package com.github.alvarosct.happycows.features;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.github.alvarosct.happycows.PreferenceManager;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.features.syncDatabase.SyncDatabaseActivity;

import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_TIME_OUT = 1200;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goToApp();
            }
        }, SPLASH_TIME_OUT);
    }

    public void goToApp() {
        PreferenceManager pref = PreferenceManager.getInstance(this);
        if (pref.isUserLoged()) {
            startActivity(new Intent(SplashActivity.this, SyncDatabaseActivity.class));
        } else {
//            TODO: Send to LOGIN Activity
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        }
        finish();
    }


}
