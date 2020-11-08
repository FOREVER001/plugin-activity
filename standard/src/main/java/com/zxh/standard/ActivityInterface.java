package com.zxh.standard;

import android.content.Context;
import android.os.Bundle;

public interface ActivityInterface {

    void insertAppContext(Context context);

    void onCreate(Bundle savedInstanceState);

    void onResume();

    void onStart();

    void onDestroy();
}
