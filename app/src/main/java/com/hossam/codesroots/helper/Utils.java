package com.hossam.codesroots.helper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.speech.RecognizerIntent;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.snackbar.Snackbar;
import com.hossam.codesroots.delivery24.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;


/**
 * Created by Hossam on 06/12/2019.
 */
public class Utils {
    public static int getScreenWidth() {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static int getScreenHeight() {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return metrics.heightPixels;
    }

    public static void showKeyboard(View view) {
        view.requestFocus();
        if (!isHardKeyboardAvailable(view)) {
            InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            Objects.requireNonNull(inputMethodManager).showSoftInput(view, 0);
        }
    }

    private static boolean isHardKeyboardAvailable(View view) {
        return view.getContext().getResources().getConfiguration().keyboard != Configuration.KEYBOARD_NOKEYS;
    }

    public static void hideKeyboard(Context context) {
        if (context instanceof Activity) {
            hideKeyboards((Activity) context);
        }
    }

    public static void hideKeyboards(Activity activity) {
        try {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (Objects.requireNonNull(activity.getCurrentFocus()).getWindowToken() != null)
                Objects.requireNonNull(inputManager).hideSoftInputFromWindow(activity.getWindow().getDecorView().getRootView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static boolean isVoiceAvailable(Context context) {
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        return activities.size() > 0;
    }

    public static Bitmap getBitmapFromURL(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            return BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static double calculateDistance(double latPoint1, double lngPoint1,
                                           String latPoint22, String lngPoint22) {
        double latPoint2 = Double.parseDouble(latPoint22);
        double lngPoint2 = Double.parseDouble(lngPoint22);
        if (latPoint1 == latPoint2 && lngPoint1 == lngPoint2) {
            return 0d;
        }

        final double EARTH_RADIUS = 6371.0; //km value;

        //converting to radians
        latPoint1 = Math.toRadians(latPoint1);
        lngPoint1 = Math.toRadians(lngPoint1);
        latPoint2 = Math.toRadians(latPoint2);
        lngPoint2 = Math.toRadians(lngPoint2);

        double distance = Math.pow(Math.sin((latPoint2 - latPoint1) / 2.0), 2)
                + Math.cos(latPoint1) * Math.cos(latPoint2)
                * Math.pow(Math.sin((lngPoint2 - lngPoint1) / 2.0), 2);
        distance = 2.0 * EARTH_RADIUS * Math.asin(Math.sqrt(distance));

        return distance; //km value
    }

    public static double calculateDistance(double latPoint1, double lngPoint1,
                                           double latPoint2, double lngPoint2) {
        if (latPoint1 == latPoint2 && lngPoint1 == lngPoint2) {
            return 0d;
        }

        final double EARTH_RADIUS = 6371.0; //km value;

        //converting to radians
        latPoint1 = Math.toRadians(latPoint1);
        lngPoint1 = Math.toRadians(lngPoint1);
        latPoint2 = Math.toRadians(latPoint2);
        lngPoint2 = Math.toRadians(lngPoint2);

        double distance = Math.pow(Math.sin((latPoint2 - latPoint1) / 2.0), 2)
                + Math.cos(latPoint1) * Math.cos(latPoint2)
                * Math.pow(Math.sin((lngPoint2 - lngPoint1) / 2.0), 2);
        distance = 2.0 * EARTH_RADIUS * Math.asin(Math.sqrt(distance));

        return distance; //km value
    }

    public static String getTimeFormat(String text) throws ParseException {
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        SimpleDateFormat output = new SimpleDateFormat("mm");
        Date d = input.parse(text);
        int min = Integer.parseInt(output.format(d));

        SimpleDateFormat hourDate = new SimpleDateFormat("HH");
        Date dd = input.parse(text);
        int hour = Integer.parseInt(hourDate.format(dd));

        String hourOfDay = "";
        if (hour > 12) {
            hour = hour - 12;
            hourOfDay = "pm";
        } else if (hour == 12) {
            hourOfDay = "pm";
        } else if (hour < 12) {
            if (hour != 0) {
                hourOfDay = "am";
            } else {
                hourOfDay = "am";
            }
        }
        if (min > 0) {
            return String.valueOf(hour) + ":" + String.valueOf(min) + " " + hourOfDay;
        } else {
            return String.valueOf(hour) + " " + hourOfDay;
        }
    }
//
//    @SuppressLint("SimpleDateFormat")
//    public static String TimeFormat_TimeDifferent(Context context, String time) throws ParseException {
//        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
//        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        Date d = input.parse(time);
//        String comparedTime = output.format(d);
//
//        String total = "";
//        try {
//            Date oldDate = output.parse(comparedTime);
//            System.out.println(oldDate);
//
//            Date currentDate = new Date();
//
//            long diff = currentDate.getTime() - oldDate.getTime();
//            long seconds = diff / 1000;
//            long minutes = seconds / 60;
//            long hours = minutes / 60;
//            long days = hours / 24;
//
//            if (oldDate.before(currentDate)) {
//                if (minutes <= 120) {
//                    total = minutes + " " + context.getString(R.string.min);
//                } else if (hours <= 48) {
//                    total = hours + " " + context.getString(R.string.hour);
//                } else {
//                    total = days + " " + context.getString(R.string.day);
//                }
//            }
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return total;
//    }
//
//    public static void showGPSDisabledAlertToUser(Context context) {
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//        alertDialogBuilder.setMessage(context.getString(R.string.gpsOff))
//                .setCancelable(false)
//                .setPositiveButton(context.getString(R.string.enableGps),
//                        (dialog, id) -> {
//                            Intent callGPSSettingIntent = new Intent(
//                                    android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                            context.startActivity(callGPSSettingIntent);
//                        });
//        alertDialogBuilder.setNegativeButton(context.getString(R.string.cancel),
//                (dialog, id) -> dialog.cancel());
//        AlertDialog alert = alertDialogBuilder.create();
//        alert.show();
//    }

    public static String generateRandomPassword() {
        Random random = new SecureRandom();
        String letters = "abcdefghjklmnopqrstuvwxyzABCDEFGHJKMNOPQRSTUVWXYZ1234567890";
        String numbers = "1234567890";
        String specialChars = "!@#$%^&*_=+-/";
        String pw = "";
        for (int i = 0; i < 8; i++) {
            int index = (int) (random.nextDouble() * letters.length());
            pw += letters.substring(index, index + 1);
        }
        int indexA = (int) (random.nextDouble() * numbers.length());
        pw += numbers.substring(indexA, indexA + 1);
        int indexB = (int) (random.nextDouble() * specialChars.length());
        pw += specialChars.substring(indexB, indexB + 1);
        return pw;
    }

    public static String generateRandomId() {
        Random random = new SecureRandom();
        String letters = "abcdefghjklmnopqrstuvwxyzABCDEFGHJKMNOPQRSTUVWXYZ1234567890";
        String numbers = "1234567890";
        String pw = "";
        for (int i = 0; i < 8; i++) {
            int index = (int) (random.nextDouble() * letters.length());
            pw += letters.substring(index, index + 1);
        }
        int indexA = (int) (random.nextDouble() * numbers.length());
        pw += numbers.substring(indexA, indexA + 1);
        return pw;
    }

//    public static void showShakeError(Context context, View viewToAnimate) {
//        try {
//            viewToAnimate.startAnimation(AnimationUtils.loadAnimation(context, R.anim.shake_error));
//        } catch (Resources.NotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }


    public static Bitmap decodeUri(Context context, Uri uri,
                                   final int requiredSize) throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(context.getContentResolver()
                .openInputStream(uri), null, o);

        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;

        while (true) {
            if (width_tmp / 2 < requiredSize || height_tmp / 2 < requiredSize)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(context.getContentResolver()
                .openInputStream(uri), null, o2);
    }

    public static Bitmap downloadImage(String url) {
        Bitmap bitmap = null;
        InputStream stream = null;
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inSampleSize = 1;

        try {
            stream = getHttpConnection(url);
            bitmap = BitmapFactory.decodeStream(stream, null, bmOptions);
            stream.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return bitmap;
    }

    public static InputStream getHttpConnection(String urlString) throws IOException {

        InputStream stream = null;
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();

        try {
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            httpConnection.setRequestMethod("GET");
            httpConnection.connect();

            if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                stream = httpConnection.getInputStream();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return stream;
    }


    public static double calculateAverage(List<Double> marks) {
        Double sum = 0.0;
        if (!marks.isEmpty()) {
            for (Double mark : marks) {
                sum += mark;
            }
            return sum / marks.size();
        }
        return sum;
    }

    public static int getRandomColorCode() {

        Random random = new Random();

        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));

    }

    public static String customFormat(BigDecimal value) {
        DecimalFormat myFormatter = new DecimalFormat("###,###.##");
        return myFormatter.format(value);
    }

//    public static boolean FieldValidation(Context context, EditText field, int amount, String msg, View layout) {
//        if (field.getText().toString().trim().length() > amount) {
//            return true;
//        } else {
//            Snackbar.make(layout, msg, Snackbar.LENGTH_LONG).setAction(context.getString(R.string.close), view -> {
//            }).setActionTextColor(context.getResources().getColor(android.R.color.white)).show();
//            return false;
//        }
//    }
//
//    public static boolean EmailValidation(Context context, EditText field, String msg, View layout) {
//        String emailPattern = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
//                "\\@" +
//                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
//                "(" +
//                "\\." +
//                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
//                ")+";
//        if (field.getText().toString().trim().matches(emailPattern)) {
//            return true;
//        } else {
//            Snackbar.make(layout, msg, Snackbar.LENGTH_LONG).setAction(context.getString(R.string.close), view -> {
//            }).setActionTextColor(context.getResources().getColor(android.R.color.white)).show();
//            return false;
//        }
//    }

    static public String PriceFormat(double value) {
        BigDecimal unscaled = new BigDecimal(value);
        BigDecimal scaled = unscaled.scaleByPowerOfTen(-2);
        return customFormat(scaled);
    }

    public static boolean isEmulator() {
        return Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT);
    }
}
