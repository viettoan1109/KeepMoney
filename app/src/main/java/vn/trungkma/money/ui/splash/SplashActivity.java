package vn.trungkma.money.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import vn.trungkma.money.R;
import vn.trungkma.money.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity {
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}