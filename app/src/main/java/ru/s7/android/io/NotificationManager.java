package ru.s7.android.io;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;

import java.util.HashMap;

import ru.s7.android.R;
import ru.s7.android.ui.activity.AchievementsActivity;


/**
 * The type Notification manager.
 *
 * @author celikindv
 * @since 25/05/2017.
 * <p>
 * This class responsible for work with notifications.
 */
public class NotificationManager {
    private static final String TAG = "NotificationManager";
    private Context context;
    private NotificationManagerCompat notificationManager;
    private NotificationGroup ng;

    /**
     * Instantiates a new Notification manager.
     *
     * @param context application context
     */
    public NotificationManager(Context context) {
        this.context = context;
        this.notificationManager = NotificationManagerCompat.from(context);
        this.ng = new NotificationGroup();

    }


    private void notifyIntent(Intent notifyIntent, int id, String message) {

        // Prepare intent for ChatActivity
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Prepare notification event
        Notification notification =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_notifications_white_24dp)
                        .setContentTitle(context.getString(R.string.app_name))
                        .setContentText(message)
                        .setContentIntent(pendingIntent)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setPriority(Notification.PRIORITY_HIGH)
                        .setGroup(NotificationGroup.GROUP)
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)).build();

        // Notify
        notificationManager.notify(id, notification);
    }


    public void notifyNewMessage(Context context, String message) {
        Intent notifyIntent = new Intent(context, AchievementsActivity.class);
        notifyIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        notifyIntent(notifyIntent, 0, message);
    }

    /**
     * Clears all notification.
     */
    public void clearNotifications() {
        notificationManager.cancelAll();
        ng.clear();
    }


    public class NotificationGroup {

        /**
         * The constant GROUP.
         */
        public static final String GROUP = "notification_group";
        private HashMap<String, Integer> map;
        private int unique_id;

        /**
         * Instantiates a new Notification group.
         */
        public NotificationGroup() {
            if (map == null) {
                map = new HashMap<>();
                unique_id = 0;
            }
        }

        /**
         * Clear notification queue
         */
        public void clear() {
            map.clear();
        }
    }

}
