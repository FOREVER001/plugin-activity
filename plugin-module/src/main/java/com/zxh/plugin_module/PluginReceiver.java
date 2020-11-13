package com.zxh.plugin_module;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.zxh.standard.ReceiverInterface;

/**
 * 插件Receiver
 */
public class PluginReceiver extends BroadcastReceiver implements ReceiverInterface {
    public static final String TAG=PluginReceiver.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
       Log.d(TAG,"===我是插件里面的广播接收者=====");
    }
}
