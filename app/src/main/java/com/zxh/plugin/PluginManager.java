package com.zxh.plugin;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Environment;
import android.os.ParcelUuid;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class PluginManager {
    public static final String TAG=PluginManager.class.getSimpleName();
    private static PluginManager instance;
    private Context mContext;
    private PluginManager(Context context){
        this.mContext=context;
    }
    public static PluginManager getInstance(Context context){
        if(null==instance){
            synchronized (PluginManager.class){
                if(null==instance){
                    instance=new PluginManager(context);
                }
            }
        }
        return instance;
    }

    private DexClassLoader mDexClassLoader;
    private Resources mResources;
    /**
     * 加载插件
     * 加载Activity class ,加载layout等资源
     */
    public void loadPlugin(){
        try {
            File file=new File("/data/data/com.zxh.plugin/plugin/");
            if(!file.exists()){
                file.mkdirs();
            }
            file=new File(file.getAbsolutePath()+File.separator+"plugin.apk");
            if(!file.exists()){
                return;
            }
            String pluginPath=file.getAbsolutePath();

            //dexClassLoader需要一个缓存目录
            File fileDir = mContext.getDir("pDir", Context.MODE_PRIVATE);
            //加载Activity class，需要用到classLoader
            mDexClassLoader=new DexClassLoader(pluginPath,fileDir.getAbsolutePath(),null,mContext.getClassLoader());

          //加载插件中的Layout，就是加载资源
            AssetManager assetManager = AssetManager.class.newInstance();
            //  public int addAssetPath(String path)
            Method addAssetPathMethod = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPathMethod.invoke(assetManager,pluginPath);
            //宿主Resource
            Resources r = mContext.getResources();
            //特殊的Resource,加载插件里面资源的Resource
            mResources=new Resources(assetManager,r.getDisplayMetrics(),r.getConfiguration());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public ClassLoader getClassLoader(){
        return mDexClassLoader;
    }

    public Resources getResources(){
        return mResources;
    }
}
