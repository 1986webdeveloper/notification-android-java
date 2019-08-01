package com.acquaintsoft.notification;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.acquaintsoft.notification.services.BackgroundService;
import com.acquaintsoft.notification.utils.Constants;
import com.acquaintsoft.notification.utils.PrefUtils;


public class MainActivity extends AppCompatActivity {
    private TextView tvAppMode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvAppMode = findViewById(R.id.tvAppMode);
        //Task 1:check app is on production mode or debug mode(Test Mode)
        if (BuildConfig.DEBUG) {
            tvAppMode.setText(getString(R.string.qa));
        } else {
            tvAppMode.setText(getString(R.string.production));
        }

        //Task 2 Task 2 Check timer is started or not if the timer is not started then start background service with 5 min timer
        if (!PrefUtils.getBoolean(this, Constants.PREF_ON_STARTED, false)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(new Intent(this, BackgroundService.class));
            } else {
                startService(new Intent(this, BackgroundService.class));

            }
        }
    }

}
