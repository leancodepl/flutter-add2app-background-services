package co.leancode.add2appbackgroundservice

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import co.leancode.add2appbackgroundservice.pigeon.Api


class ComputationServiceNotification {
    companion object {
        private const val LOG_TAG = "ComputationServiceNotification"
        private const val TITLE_TAG = "ComputationServiceNotificationTitle"
        private const val MESSAGE_TAG = "ComputationServiceNotificationMessage"
        private const val PROGRESS_TAG = "ComputationServiceNotification_Progress"
        private const val CHANNEL_ID = "ComputationServiceNotification"
        private const val NOTIFICATION_ID = 1

        fun createNotificatioChannel(context: Context) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel1 = NotificationChannel(
                    CHANNEL_ID,
                    "Service Channel",
                    NotificationManager.IMPORTANCE_HIGH
                )
                val manager: NotificationManager = context.getSystemService(
                    NotificationManager::class.java
                )
                manager.createNotificationChannel(channel1)
            }
        }

        fun createNotification(context: Context, intent: Intent) : Notification? {
            val pendingIntent = getPendingIntentGoToForeground(context)
            val notification = extractNotificationFromIntent(intent)
            return getBuilder(context, notification)
                .setContentIntent(pendingIntent)
                .build()
        }

        fun appendNotificationToIntent(intent: Intent, notification: Api.ComputationNotification) {
            intent.putExtra(TITLE_TAG, notification.title)
            intent.putExtra(MESSAGE_TAG, notification.message)
            intent.putExtra(PROGRESS_TAG, notification.percentProgress)
        }

        fun updateNotification(context: Context, notification: Api.ComputationNotification) {
            val pendingIntent = getPendingIntentGoToForeground(context)
            val builder = getBuilder(context, notification)
                .setFullScreenIntent(pendingIntent, true)

            NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build())
        }

        private fun getBuilder(context: Context, notification: Api.ComputationNotification): NotificationCompat.Builder {
            val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setCategory(Notification.CATEGORY_SERVICE)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setOngoing(true)
                .setOnlyAlertOnce(true)
                .setTicker(notification.title + notification.message)

            if (notification.percentProgress >= 0) {
                builder.setProgress(100, notification.percentProgress.toInt(), false)
            }
            if (notification.title.isNotEmpty()) {
                builder.setContentTitle(notification.title)
            }
            if (notification.message.isNotEmpty()) {
                builder.setContentText(notification.message)
                    .setStyle(NotificationCompat.BigTextStyle().bigText(notification.message))
            }
            return builder
        }

        private fun extractNotificationFromIntent(intent: Intent): Api.ComputationNotification {
            return Api.ComputationNotification.Builder()
                .setTitle(intent.getStringExtra(TITLE_TAG)!!)
                .setMessage(intent.getStringExtra(MESSAGE_TAG)!!)
                .setPercentProgress(intent.getLongExtra(PROGRESS_TAG, 0))
                .build()
        }

        private fun getPendingIntentGoToForeground(context: Context?): PendingIntent? {
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.action = Intent.ACTION_MAIN
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            var flags = FLAG_UPDATE_CURRENT
            if (Build.VERSION.SDK_INT >= 23) {
                flags = flags or PendingIntent.FLAG_IMMUTABLE
            }

            return PendingIntent.getActivity(context, 0, intent, flags)
        }
    }
}