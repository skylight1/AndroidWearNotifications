package com.example.android.preview.support.wearable.notifications;

import android.app.Notification;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationCompat.WearableExtender;

/**
 * Collection of notification builder presets.
 */
public class NotificationPresets {
    private static final String EXAMPLE_GROUP_KEY = "example";

    public static final NotificationPreset[] PRESETS = new NotificationPreset[] {
            new BasicNotificationPreset(),
            new InboxNotificationPreset(),
            new BigPictureNotificationPreset(),
            new BigTextNotificationPreset(),
            new BigActionNotificationPreset(),
            new MultiplePageNotificationPreset(),
            new NotificationBundlePreset()
    };

    private static Builder buildBasicNotification(Context context, NotificationPreset.BuildOptions options) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle(context.getString(R.string.example_content_title))
                .setContentText(context.getString(R.string.example_content_text))
                .setSmallIcon(R.mipmap.ic_app_notification_studio)
                .setDeleteIntent(NotificationUtil.getExamplePendingIntent(context, R.string.example_notification_deleted));
        
        options.actionsPreset.apply(context, builder);
        options.priorityPreset.apply(builder);
        
        if (options.includeLargeIcon) {
            builder.setLargeIcon(BitmapFactory.decodeResource(
                    context.getResources(), R.drawable.example_large_icon));
        }
        if (options.isLocalOnly) {
            builder.setLocalOnly(true);
        }
        if (options.hasContentIntent) {
            builder.setContentIntent(NotificationUtil.getExamplePendingIntent(context, R.string.content_intent_clicked));
        }
        return builder;
    }

    private static class BasicNotificationPreset extends NotificationPreset {
        public BasicNotificationPreset() {
            super(R.string.basic_example);
        }

        @Override
        public Notification[] buildNotifications(Context context, BuildOptions options) {
            Notification notification = buildBasicNotification(context, options).build();
            return new Notification[] { notification };
        }
    }

    private static class InboxNotificationPreset extends NotificationPreset {
        public InboxNotificationPreset() {
            super(R.string.inbox_example);
        }

        @Override
        public Notification[] buildNotifications(Context context, BuildOptions options) {
            NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
            style.addLine(context.getString(R.string.inbox_style_example_line1));
            style.addLine(context.getString(R.string.inbox_style_example_line2));
            style.addLine(context.getString(R.string.inbox_style_example_line3));
            style.setBigContentTitle(context.getString(R.string.inbox_style_example_title));
            style.setSummaryText(context.getString(R.string.inbox_style_example_summary_text));
            NotificationCompat.Builder builder = buildBasicNotification(context, options);
            builder.setStyle(style);
            return new Notification[] { builder.build() };
        }
    }

    private static class BigPictureNotificationPreset extends NotificationPreset {
        public BigPictureNotificationPreset() {
            super(R.string.big_picture_example);
        }

        @Override
        public Notification[] buildNotifications(Context context, BuildOptions options) {
            NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
            style.bigPicture(BitmapFactory.decodeResource(context.getResources(), R.drawable.example_big_picture));
            style.setBigContentTitle(context.getString(R.string.big_picture_style_example_title));
            style.setSummaryText(context.getString(R.string.big_picture_style_example_summary_text));
            
            NotificationCompat.Builder builder = buildBasicNotification(context, options);
            builder.setStyle(style);
            return new Notification[] { builder.build() };
        }
    }

    private static class BigTextNotificationPreset extends NotificationPreset {
        public BigTextNotificationPreset() {
            super(R.string.big_text_example);
        }

        @Override
        public Notification[] buildNotifications(Context context, BuildOptions options) {
            NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle();
            style.bigText(context.getString(R.string.big_text_example_big_text));
            style.setBigContentTitle(context.getString(R.string.big_text_example_title));
            style.setSummaryText(context.getString(R.string.big_text_example_summary_text));
            
            NotificationCompat.Builder builder = buildBasicNotification(context, options);
            builder.setStyle(style);
            return new Notification[] { builder.build() };
        }
    }

    private static class BigActionNotificationPreset extends NotificationPreset {
        public BigActionNotificationPreset() {
            super(R.string.big_action_example);
        }

        @Override
        public Notification[] buildNotifications(Context context, BuildOptions options) {
        	
        	NotificationCompat.Builder builder = buildBasicNotification(context, options)
        		.extend(new WearableExtender().setHintHideIcon(true))

// deprecated (preview only):
//       		.setBigActionIcon(R.mipmap.ic_app_notification_studio, context.getString(R.string.icon_subtext))
            
        	    .setContentIntent(NotificationUtil.getExamplePendingIntent(context, R.string.content_intent_clicked));
            
        	return new Notification[] { builder.build() };
        }

        @Override
        public boolean contentIntentRequired() {
            return true;
        }
    }

    private static class MultiplePageNotificationPreset extends NotificationPreset {
        public MultiplePageNotificationPreset() {
            super(R.string.multiple_page_example);
        }

        @Override
        public Notification[] buildNotifications(Context context, BuildOptions options) {
        	
            Notification secondPage = new NotificationCompat.Builder(context)
                    .setContentTitle(context.getString(R.string.second_page_content_title))
                    .setContentText(context.getString(R.string.second_page_content_text))
                    .build();
            
            Builder notificationBuilder = buildBasicNotification(context, options);
            		
            Notification twoPageNotification = new WearableExtender()
                            .addPage(secondPage)
                            .extend(notificationBuilder)
                            .build();
            
            return new Notification[] { twoPageNotification };
        }
    }

    private static class NotificationBundlePreset extends NotificationPreset {
        public NotificationBundlePreset() {
            super(R.string.notification_bundle_example);
        }

        @Override
        public Notification[] buildNotifications(Context context, BuildOptions options) {

            NotificationCompat.Builder childBuilder1 = new NotificationCompat.Builder(context)
                    .setContentTitle(context.getString(R.string.first_child_content_title))
                    .setContentText(context.getString(R.string.first_child_content_text))
            		.setGroup(EXAMPLE_GROUP_KEY);

             Notification child1 = childBuilder1.build();

            NotificationCompat.Builder childBuilder2 = new NotificationCompat.Builder(context)
                    .setContentTitle(context.getString(R.string.second_child_content_title))
                    .setContentText(context.getString(R.string.second_child_content_text))
                    .addAction(R.mipmap.ic_app_notification_studio,
                            context.getString(R.string.second_child_action),
                            NotificationUtil.getExamplePendingIntent(context, R.string.second_child_action_clicked))
            		.setGroup(EXAMPLE_GROUP_KEY);
            
            Notification child2 = childBuilder2.build();

            Notification summary = buildBasicNotification(context, options)
                   .setGroup(EXAMPLE_GROUP_KEY)
                   .setGroupSummary(true)
                   .build();

            return new Notification[] { child2, child1, summary };
        }
    }
}
