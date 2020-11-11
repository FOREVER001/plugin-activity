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
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity,SecondActivity.class));
            }
        });
    }


}