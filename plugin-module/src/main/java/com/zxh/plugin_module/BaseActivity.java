package com.zxh.plugin_module;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.zxh.standard.ActivityInterface;

public class BaseActivity extends Activity implements ActivityInterface {
    public Activity mActivity;
    @Override
    public void insertAppContext(Context context) {
        this.mActivity= (Activity) context;
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onResume() {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onStart() {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onDestroy() {

    }
}
