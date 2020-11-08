package com.zxh.plugin;

import android.app.Activity;
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
}
