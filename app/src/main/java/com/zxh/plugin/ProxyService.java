package com.zxh.plugin;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.zxh.standard.ServiceInterface;

import androidx.annotation.Nullable;

public class ProxyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String className = intent.getStringExtra("className");
        try {
            Class<?> clazz = PluginManager.getInstance(this).getClassLoader().loadClass(className);
            Object pluginService = clazz.newInstance();
            ServiceInterface serviceInterface= (ServiceInterface) pluginService;
            serviceInterface.insertAppContext(this);
            serviceInterface.onStartCommand(intent,flags,startId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
