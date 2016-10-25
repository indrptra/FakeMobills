package com.indrap.FakeMobills;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.indrap.navigation.MainActivity;
import com.indrap.navigation.R;

public class Splash extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 1000;
    String check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(" ");
        setSupportActionBar(toolbar);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                SharedPreferences sharedPre = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                check = sharedPre.getString("email", "");
                if (!check.equals("")) {
                    Intent i = new Intent(Splash.this, MainActivity.class);
                    startActivity(i);
                }else{
                    Intent i = new Intent(Splash.this, Login.class);
                    startActivity(i);
                }
                Splash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
