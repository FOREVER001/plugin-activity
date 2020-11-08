package com.zxh.plugin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 加载插件
     * @param view
     */
    public void loadPlugin(View view) {
       PluginManager.getInstance(this).loadPlugin();

    }
    /**
     * 启动插件
     * @param view
     */
    public void launchPlugin(View view) {
        File file=new File("/data/data/com.zxh.plugin/plugin/");
        if(!file.exists()){
            file.mkdirs();
        }
       file=new File(file.getAbsolutePath()+File.separator+"plugin.apk");
      String path=file.getPath();

      //获取插件包里面的Activity
        PackageManager packageManager = getPackageManager();
        PackageInfo packageArchiveInfo = packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);

        ActivityInfo activityInfo = packageArchiveInfo.activities[0];

        //占位 代理Activity
        Intent intent=new Intent(this,ProxyActivity.class);
        intent.putExtra("className",activityInfo.name);
        startActivity(intent);


    }
}