package com.example.android.preview.support.wearable.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Broadcast receiver to post toast messages in response to notification intents firing.
 */
public class NotificationIntentReceiver extends BroadcastReceiver {
    public static final String ACTION_EXAMPLE =
            "com.example.android.preview.support.wearable.notifications.ACTION_EXAMPLE";
    public static final String ACTION_ENABLE_MESSAGES =
            "com.example.android.preview.support.wearable.notifications.ACTION_ENABLE_MESSAGES";
    public static final String ACTION_DISABLE_MESSAGES =
            "com.example.android.preview.support.wearable.notifications.ACTION_DISABLE_MESSAGES";

    private boolean mEnableMessages = true;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION_EXAMPLE)) {
            if (mEnableMessages) {
                String message = intent.getStringExtra(NotificationUtil.EXTRA_MESSAGE);
                String replyMessage = intent.getStringExtra(NotificationUtil.EXTRA_REPLY);
                if (replyMessage != null) {
                    message = message + ": \"" + replyMessage + "\"";
                }
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        } else if (intent.getAction().equals(ACTION_ENABLE_MESSAGES)) {
            mEnableMessages = true;
        } else if (intent.getAction().equals(ACTION_DISABLE_MESSAGES)) {
            mEnableMessages = false;
        }
    }
}
