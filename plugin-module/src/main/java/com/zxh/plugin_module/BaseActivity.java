package com.zxh.plugin_module;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zxh.standard.ActivityInterface;

public class BaseActivity extends Activity implements ActivityInterface {
    public Activity mActivity;

    @Override
    public void insertAppContext(Context context) {
        this.mActivity = (Activity) context;
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

    public View findViewById(int id) {
        return mActivity.findViewById(id);
    }

    public void setContentView(int layoutId){
        mActivity.setContentView(layoutId);
    }
    public void startActivity(Intent intent){
        Intent newIntent=new Intent();
        newIntent.putExtra("className",intent.getComponent().getClassName());
        mActivity.startActivity(newIntent);
    }


    @Override
    public ComponentName startService(Intent service) {
        Intent newIntent=new Intent();
        newIntent.putExtra("className",service.getComponent().getClassName());
        return mActivity.startService(newIntent);
    }
}
