package com.inarts.inarts_user.mymap;

import android.app.Application;

/**
 * Created by inarts-user on 10/6/16.
 */

public class MyApplication extends Application {
    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
