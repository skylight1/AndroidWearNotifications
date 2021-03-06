AndroidWearNotifications
========================

This is the WearablesNotificationSample for the Android Wear Preview ported to Android Wear 1.0

This is an Eclipse compatible project but requires the following jars to be places in the libs folder:

android-support-v4.jar (right click on project -> Android Tools -> Add Support Library...)

androidwear-1.0.0.jar (created manually by extracting classes from the .aar file *)


 *see http://stackoverflow.com/questions/24520240/import-android-support-wearable-cannot-be-resolved
 
 Note: this targets API Level 19 instead of 20 as there isn't a Wearable component.
 
 Note: this project is an excercise for those who studied the earlier Android Wear Preview APIs so for those just starting out, use the new official SDK sample code.
 
 The official replacement project is available in the SDK samples for API Level 20:
 samples/android-20/wearable/Notifications
 
 Here's what's different (notice new presets and one removed preset: BigActionNotification):
 
 NotificationPresets.java from AndroidWearPreview:
 
     public static final NotificationPreset[] PRESETS = new NotificationPreset[] {
            new BasicNotificationPreset(),
            new InboxNotificationPreset(),
            new BigPictureNotificationPreset(),
            new BigTextNotificationPreset(),
            new BigActionNotificationPreset(),
            new MultiplePageNotificationPreset(),
            new NotificationBundlePreset()
    };
    
Updated NotificationPresets.java from AndroidWear 1.0.0:
 
    public static final NotificationPreset[] PRESETS = new NotificationPreset[] {
            BASIC,
            STYLIZED_TEXT,
            INBOX,
            BIG_PICTURE,
            BIG_TEXT,
            BOTTOM_ALIGNED,
            GRAVITY,
            CONTENT_ACTION,
            CONTENT_ICON,
            MULTIPLE_PAGE,
            BUNDLE
    };

There is also now a Wearable component for the watch (hence target API 20) with the following presets support:

    public static final NotificationPreset[] PRESETS = new NotificationPreset[] {
            new BasicPreset(),
            new StylizedTextPreset(),
            new DisplayIntentPreset(),
            new MultiSizeDisplayIntentPreset(),
            new AnimatedDisplayIntentPreset(),
            new ContentIconPreset()
    };
    
For information on manually packaging the Wearbable apk inside the Application apk:

https://developer.android.com/training/wearables/apps/packaging.html


Note in Eclipse you'll need to do this manually (as you'll have 2 projects) in AS (Android Studio) you don't need to unless you require a paid app - the workaround for the paid app issue at this time is here:

http://android-developers.blogspot.com/2014/07/update-on-android-wear-paid-apps.html

The workaround for Eclipse is here:

http://stackoverflow.com/questions/24520240/import-android-support-wearable-cannot-be-resolved

However it's better to include resources as well as the classes, so instead make all the contents of the aar file an Android Library (mark it as such in Eclipse) and add that lib project dependency to your wear project.

