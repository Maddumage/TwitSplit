package com.maddumage.twitsplit.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class AppUtil {

    private static final String LOG_TAG = "Connectivity State";

    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager manager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            // Network is present and connected
            isAvailable = true;
        }
        return isAvailable;
    }

    public static boolean checkActiveInternetConnection(Context context) {
        if (isNetworkAvailable(context)) {
            try {
                HttpURLConnection urlConnection = (HttpURLConnection) (new URL("http://www.google.com").openConnection());
                urlConnection.setRequestProperty("User-Agent", "Test");
                urlConnection.setRequestProperty("Connection", "close");
                urlConnection.setConnectTimeout(1500);
                urlConnection.connect();
                return (urlConnection.getResponseCode() == 200);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error: ", e);
            }
        } else {
            Log.d(LOG_TAG, "No network present");
        }
        return false;
    }
}
