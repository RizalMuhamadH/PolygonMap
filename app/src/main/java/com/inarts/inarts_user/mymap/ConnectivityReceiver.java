package com.inarts.inarts_user.mymap;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by inarts-user on 10/6/16.
 */

public class ConnectivityReceiver extends BroadcastReceiver {
    public static ConnectivityReceiverListener connectivityReceiverListener;
    public ConnectivityReceiver(){
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if (connectivityReceiverListener != null){
            connectivityReceiverListener.onNetworkConnectionChanged(isConnected);
        }

    }
    public static boolean isConnected() {
        ConnectivityManager
                cm = (ConnectivityManager) MyApplication.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
    }
    public interface ConnectivityReceiverListener {
        void onNetworkConnectionChanged(boolean isConnected);
    }
}
