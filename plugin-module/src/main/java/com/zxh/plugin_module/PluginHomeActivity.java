package com.zxh.plugin_module;


import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

/**
 * 插件Activity
 */
public class PluginHomeActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity.setContentView(R.layout.activity_main);
        initEvent();

    }

    private void initEvent() {
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity,SecondActivity.class));
            }
        });

        /**
         * 开始服务
         */
        findViewById(R.id.btn_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(mActivity,PluginService.class));
            }
        });

        /**
         * 注册广播
         */
        findViewById(R.id.btn_receiver).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentFilter intentFilter=new IntentFilter();
                intentFilter.addAction("com.zxh.plugin_module.action");
                registerReceiver(new PluginReceiver(),intentFilter);
            }
        });
        /**
         * 插件内发送广播
         */
        findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction("com.zxh.plugin_module.action");
                sendBroadcast(intent);
            }
        });
    }


}