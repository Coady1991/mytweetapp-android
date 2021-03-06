package coady.mytweetapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import coady.mytweetapp.R;

// https://www.androidhive.info/2013/07/how-to-implement-android-splash-screen-2/
public class SplashScreen extends Activity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, Welcome.class);
                startActivity(i);

                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
