package com.dsapps.androidbrowser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(2500);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
                    startActivity(intent);
                }

            }
        };

        thread.start();

    }

    @Override
    protected void onPause()
    {
        super.onPause();
        finish();
    }
}
