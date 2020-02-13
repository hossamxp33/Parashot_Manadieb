package com.hossam.codesroots.helper;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.hossam.codesroots.delivery24.R;


import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/**
 * Created by Hossam on 06/12/2019.
 */
public class Utility {


    public static void setLocale(Context context, String languageCode) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        android.content.res.Configuration configuration = resources.getConfiguration();
        configuration.locale = new Locale(languageCode.toLowerCase());
        resources.updateConfiguration(configuration, displayMetrics);
    }

    @TargetApi(Build.VERSION_CODES.N)
    public static Locale getCurrentLocale(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return context.getResources().getConfiguration().getLocales().get(0);
        } else {
            return context.getResources().getConfiguration().locale;
        }
    }

    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }






    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static ProgressDialog showProgressDialog(Context context, int messageResourceId) {
        return showProgressDialog(context, messageResourceId, false);
    }

    private static ProgressDialog showProgressDialog(Context context, int messageResourceId,
                                                     boolean cancelable) {
        return showProgressDialog(context, context.getString(messageResourceId), cancelable);
    }

    public static ProgressDialog showProgressDialog(Context context, String message,
                                                    boolean cancelable) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage(message);
        dialog.setCanceledOnTouchOutside(cancelable);
        dialog.setCancelable(cancelable);
        dialog.show();
        return dialog;
    }

    public static void hideProgress(ProgressDialog dialog) {

        if (dialog != null && dialog.isShowing()) dialog.dismiss();
    }

    public static boolean stringMatch(String word1, String word2) {
        if (word1.trim().equals(word2)) {
            return true;
        } else {
            return false;
        }
    }



    @NonNull
    public static RequestBody createPartFromString(String s) {
        return RequestBody.create(
                MultipartBody.FORM, s);
    }


    public static String getDateFromString(String comingDate) {

        return comingDate.substring(0, comingDate.indexOf(" "));
    }

    public static String getDateFromString2(String comingDate) {

        return comingDate.substring(0, comingDate.indexOf("T"));
    }

    public static String getTimeFromString(String comingDate) {
        int begin = comingDate.indexOf("T");
        int last = comingDate.indexOf("+");
        return comingDate.substring(begin + 1, last);
    }

    public static Float convertDoubleToFloat(double doubleValue) {
        return (float) doubleValue;
    }
}
