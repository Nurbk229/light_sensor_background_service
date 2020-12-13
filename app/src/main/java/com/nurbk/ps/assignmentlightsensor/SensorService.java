package com.nurbk.ps.assignmentlightsensor;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


public class SensorService extends Service implements SensorEventListener {
        private Context ctx;
    private SensorManager sensorManager;
    private Sensor sensor;
    CameraManager cameraManager;
    public SensorService(Context applicationContext) {
        super();
        ctx = applicationContext;
    }
    public SensorService() {
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent broadcastIntent = new Intent(this, SensorRestartBroadcastReceiver.class);
        sendBroadcast(broadcastIntent);
    }
    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
    @Override
    public boolean stopService(Intent name) {
        return super.stopService(name);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            float value = event.values[0];
            if (value == 0) {
                try {
                    cameraManager.setTorchMode("0", true);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                try {
                    cameraManager.setTorchMode("0", false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Toast.makeText(this, "value " + value, Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
