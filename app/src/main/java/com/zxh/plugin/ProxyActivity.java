package com.zxh.plugin;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;

import com.zxh.standard.ActivityInterface;

import java.lang.reflect.Constructor;

import androidx.annotation.Nullable;

/**
 *代理Activity
 */
public class ProxyActivity extends Activity {
    @Override
    public Resources getResources() {
        return PluginManager.getInstance(this).getResources();
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance(this).getClassLoader();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载真正插件里面的Activity
         String className=getIntent().getStringExtra("className");

        try {
           Class mPluginActivityClass= getClassLoader().loadClass(className);
          //实例化插件包里面的Activity
            Constructor constructor = mPluginActivityClass.getConstructor(new Class[]{});
            Object mPluginActivity = constructor.newInstance(new Object[]{});
            ActivityInterface activityInterface= (ActivityInterface) mPluginActivity;

            //注入上下文到插件中
            activityInterface.insertAppContext(this);
            //执行插件里面的onCreate
            Bundle bundle=new Bundle();
            bundle.putString("appName","我是宿主传递过来的信息");
            activityInterface.onCreate(bundle);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startActivity(Intent intent) {
        String className = intent.getStringExtra("className");
        Intent proxyIntent=new Intent(this,ProxyActivity.class);
        proxyIntent.putExtra("className",className);
        super.startActivity(proxyIntent);
    }

    @Override
    public ComponentName startService(Intent service) {
        String className = service.getStringExtra("className");
        Intent proxyIntent=new Intent(this,ProxyService.class);
        proxyIntent.putExtra("className",className);
        return super.startService(proxyIntent);
    }

    @Override
    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        //获取插件中广播接受者的全类么
        String mPluginReceiverName = receiver.getClass().getName();
        //在宿主中注册广播
        return super.registerReceiver(new ProxyReceiver(mPluginReceiverName), filter);
    }

    @Override
    public void sendBroadcast(Intent intent) {
        //发送
        super.sendBroadcast(intent);
    }
}
