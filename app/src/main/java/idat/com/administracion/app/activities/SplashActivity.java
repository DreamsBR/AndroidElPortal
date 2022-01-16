package idat.com.administracion.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import idat.com.administracion.app.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        final Intent intentSplash = new Intent(this,DecisionActivity.class);
        Thread timer = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(3200);
                }
                catch (InterruptedException exception){

                }
                finally {
                    startActivity(intentSplash);
                    finish();
                }
            }
        };
        timer.start();
    }
}