package com.acquaintsoft.notification.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Binder;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.IBinder;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import com.acquaintsoft.notification.R;
import com.acquaintsoft.notification.utils.Common;
import com.acquaintsoft.notification.utils.Constants;
import com.acquaintsoft.notification.utils.PrefUtils;


public class BackgroundService extends Service {


    public static int ID_SMALL_NOTIFICATION = 1;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        PrefUtils.saveBoolean(getApplicationContext(), Constants.PREF_ON_STARTED, true);
        //5 min countdown timer
        CountDownTimer countDownTimer = new CountDownTimer((1000 * 60 * 5), 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //remaining time calculation mm:ss format
                int minutes = (int) (millisUntilFinished / (1000 * 60));
                int seconds = (int) (millisUntilFinished / (1000));
                seconds = seconds % 60;
                showNotification(Common.getDigits(minutes), Common.getDigits(seconds));
            }

            @Override
            public void onFinish() {
                //timer time is finished then remove notification
                stopForeground(true);
            }
        };
        countDownTimer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    //show notification
    private void showNotification(String minutes, String seconds) {
        //If android version more then oreo then create notification channel
        NotificationCompat.Builder mBuilder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mBuilder = new NotificationCompat.Builder(getApplicationContext(), createNotificationChannel());
        } else {
            mBuilder = new NotificationCompat.Builder(getApplicationContext());
        }

        Notification notification;
        notification = mBuilder.setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentTitle("You are on the break")
                .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setOngoing(true)
                .setContentText("Time left: " + minutes + ":" + seconds)
                .setChannelId("" + ID_SMALL_NOTIFICATION)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();

        notification.flags = notification.flags | Notification.FLAG_AUTO_CANCEL;
        startForeground(ID_SMALL_NOTIFICATION, notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //Create notification channel
    @RequiresApi(Build.VERSION_CODES.O)
    private String createNotificationChannel() {
        String channelId = "" + ID_SMALL_NOTIFICATION;
        String channelName = "Background Service";
        NotificationChannel chan = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_LOW);
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager service = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        service.createNotificationChannel(chan);
        return channelId;
    }
}
