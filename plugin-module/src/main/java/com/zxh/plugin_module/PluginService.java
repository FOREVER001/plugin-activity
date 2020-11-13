package com.zxh.plugin_module;

import android.content.Intent;
import android.util.Log;

/**
 * 插件服务
 */
public class PluginService extends BaseService {
    public static final String TAG=PluginService.class.getSimpleName();
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       //开启子线程，执行耗时任务
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        Log.d(TAG,"插件里面的服务正在执行中...");
                    }
                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }
}
