package com.amirahmed.mykidsforteachers.Utils;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.amirahmed.mykidsforteachers.MainActivity;
import com.amirahmed.mykidsforteachers.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FCMService extends FirebaseMessagingService {

    String notificationBody;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        notificationBody = remoteMessage.getData().get("notificationBody");
        String message = remoteMessage.getData().get("message");
        String hendiware = remoteMessage.getData().get("hendiware");

        MediaPlayer mpintro = MediaPlayer.create(this,R.raw.snap);
        mpintro.start();

        SendNotification(remoteMessage.getData().get("notificationBody"));

        //sendNotification(remoteMessage.getData().get("title"), remoteMessage.getData().get("message"));
    }

    private void SendNotification(String messageBody) {


        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Uri sound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                + "://" + getPackageName() + "/raw/snap");
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(notificationBody)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(sound)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }


}
