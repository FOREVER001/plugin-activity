package com.zxh.plugin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.zxh.standard.ReceiverInterface;

public class ProxyReceiver extends BroadcastReceiver {
    //插件里面的Reciever的全类名
    private String mPluginReceiverName;

    public ProxyReceiver(String pluginReceiverName) {
        mPluginReceiverName = pluginReceiverName;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
       //加载插件里面的Receiver
        try {
            Class<?> clazz = PluginManager.getInstance(context).getClassLoader().loadClass(mPluginReceiverName);
            Object mPluginReceiver = clazz.newInstance();
            ReceiverInterface receiverInterface= (ReceiverInterface) mPluginReceiver;
            //执行插件里面的广播接收者的onRecceive方法
            receiverInterface.onReceive(context,intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
