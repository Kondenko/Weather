package com.aleksandersh.weather.network;


import android.content.Context;
import android.net.ConnectivityManager;


/**
 * Created by Vladimir Kondenko on 02.08.17.
 */

public class NetworkUtils {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().isConnected();
    }

}
