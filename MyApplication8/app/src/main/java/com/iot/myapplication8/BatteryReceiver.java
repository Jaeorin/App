package com.iot.myapplication8;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BatteryReceiver extends BroadcastReceiver {

    public static final String TAG = "BatteryReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i(TAG, "onReceive() 메서드 호출됨.");

    }
}
