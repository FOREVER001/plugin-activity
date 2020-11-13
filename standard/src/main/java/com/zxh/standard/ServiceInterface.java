package com.zxh.standard;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public interface ServiceInterface {

    void insertAppContext(Service service);

    IBinder onBind(Intent intent);

    void onCreate();

    int onStartCommand(Intent intent, int flags, int startId);

    void onDestroy();
}
