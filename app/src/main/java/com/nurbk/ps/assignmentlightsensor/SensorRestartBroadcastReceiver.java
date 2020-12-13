package com.nurbk.ps.assignmentlightsensor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.ActivityCompat;

public class SensorRestartBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ActivityCompat.startForegroundService(context, new Intent(context, SensorService.class));
    }
}
