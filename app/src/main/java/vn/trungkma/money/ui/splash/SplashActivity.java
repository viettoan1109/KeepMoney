package vn.trungkma.money.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import vn.trungkma.money.R;
import vn.trungkma.money.ui.main.MainActivity;
import vn.trungkma.money.ui.slide.ViewSliderActivity;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }


    private void init() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, ViewSliderActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}