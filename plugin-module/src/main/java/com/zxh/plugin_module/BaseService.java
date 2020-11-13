package com.zxh.plugin_module;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.zxh.standard.ServiceInterface;

import androidx.annotation.Nullable;

public class BaseService extends Service implements ServiceInterface {
    public Service appService;

    /**
     * 将宿主环境注入到插件早上
     * @param service
     */
    @Override
    public void insertAppContext(Service service) {
        this.appService=service;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

    }

    @SuppressLint("WrongConstant")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return 0;
    }

    @Override
    public void onDestroy() {

    }
}
