package com.example.android.preview.support.wearable.notifications;

import android.app.Notification;
import android.support.v4.app.NotificationCompat;

/**
 * Collection of notification priority presets.
 */
public class PriorityPresets {
    public static final PriorityPreset DEFAULT = new SimplePriorityPreset(R.string.default_priority, Notification.PRIORITY_DEFAULT);

    public static final PriorityPreset AMBIENT = new AmbientPriorityPreset();

    public static final PriorityPreset[] PRESETS = new PriorityPreset[] {
            AMBIENT,
            new SimplePriorityPreset(R.string.low_priority, Notification.PRIORITY_LOW),
            DEFAULT,
            new SimplePriorityPreset(R.string.high_priority, Notification.PRIORITY_HIGH),
            new SimplePriorityPreset(R.string.max_priority, Notification.PRIORITY_MAX)
    };

    /**
     * Simple notification priority preset that sets a priority using
     * {@link android.support.v4.app.NotificationCompat.Builder#setPriority}
     */
    private static class SimplePriorityPreset extends PriorityPreset {
        private final int mPriority;

        public SimplePriorityPreset(int nameResId, int priority) {
            super(nameResId);
            mPriority = priority;
        }

        @Override
        public void apply(NotificationCompat.Builder builder) {
            builder.setPriority(mPriority);
        }
    }

    /**
     * Notification priority preset that sets priority using
     * {@link WearableNotifications.Builder#setMinPriority}
     */
    private static class AmbientPriorityPreset extends PriorityPreset {
        public AmbientPriorityPreset() {
            super(R.string.ambient_min_priority);
        }

        @Override
        public void apply(NotificationCompat.Builder builder) {
            builder.setPriority(NotificationCompat.PRIORITY_MIN);
        }

    }
}
