//package com.hossam.codesroots.helper;
//
//import android.app.ActivityManager;
//import android.content.ComponentName;
//
//import com.onesignal.NotificationExtenderService;
//import com.onesignal.OSNotificationDisplayedResult;
//import com.onesignal.OSNotificationReceivedResult;
//import org.json.JSONException;
//import org.json.JSONObject;
//import java.math.BigInteger;
//import java.util.List;
//
//public class MyNotificationExtenderService extends NotificationExtenderService {
//
//    PreferenceHelper preferenceHelper;
//    @Override
//    protected boolean onNotificationProcessing(OSNotificationReceivedResult receivedResult) {
//
//
//        ActivityManager am = (ActivityManager) this .getSystemService(ACTIVITY_SERVICE);
//        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
//        ComponentName componentInfo = taskInfo.get(0).topActivity;
//        String Actvity_Name = taskInfo.get(0).topActivity.getClassName();
//        try {
//            JSONObject jsonObject = receivedResult.payload.additionalData;
//            String type = jsonObject.getString("type");
//            String message = jsonObject.getString("message");
//            String username = jsonObject.getString("username");
//            int orderid = jsonObject.getInt("orderid");
//            if (type.matches("chat")&&orderid==PreferenceHelper.getOrderId())
//                return true;
//            else  if (type.matches("chat")){
//                OverrideSettings overrideSettings = new OverrideSettings();
//                overrideSettings.extender = builder -> {
//                    // Sets the background notification color to Red on Android 5.0+ devices.
//                    //                        Bitmap icon = BitmapFactory.decodeResource(MyApplication.getInstance().getResources(),
//                    //                                R.drawable.ic_la);
//                    //                          builder.setSmallIcon(icon);
//                    if (type.matches("chat"))
//                        builder.setContentTitle("رسالة من "+username);
//                    builder.setContentText(message);
//                    return builder.setColor(new BigInteger("FF0000FF", 16).intValue());
//                };
//                OSNotificationDisplayedResult displayedResult = displayNotification(overrideSettings);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return true;
//    }
//}