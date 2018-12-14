package com.team2.saleftp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {
    private boolean mIsBackButtonPressed;
    private static final int SPLASH_DURATION = 1500; // 1.5 seconds


    @SuppressLint("ResourceType")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_screen);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                finish();
                if (!mIsBackButtonPressed) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    SplashActivity.this.startActivity(intent);
                }
            }
        }, SPLASH_DURATION);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public void onBackPressed() {
        mIsBackButtonPressed = true;
        super.onBackPressed();

    }
}
