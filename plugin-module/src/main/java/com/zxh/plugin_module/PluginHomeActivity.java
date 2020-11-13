package com.zxh.plugin_module;


import android.content.Intent;
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

        findViewById(R.id.btn_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(mActivity,PluginService.class));
            }
        });
    }


}