package com.app.app_fcm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFcmService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        String title, body;
        title = message.getNotification().getTitle();
        body = message.getNotification().getBody();
        Log.i("fcm", "title : " + title +", body : " + body);
        // 將訊息彈出
        final String CHINNEL_ID = "my_channel_id";
        // 設定頻道
        NotificationChannel channel = new NotificationChannel(CHINNEL_ID, "my_name", NotificationManager.IMPORTANCE_HIGH);
        // 建立頻道
        getSystemService(NotificationManager.class).createNotificationChannel(channel);
        // 將 resource image 轉 bitmap
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.pig);
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, CHINNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                        .setSmallIcon(android.R.drawable.star_on)
                                .setLargeIcon(bmp)
                                        .setAutoCancel(true);
        NotificationManagerCompat.from(this).notify(1, notification.build());
        super.onMessageReceived(message);
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.i("fcm", "onNewToken : " + token);
    }
}
