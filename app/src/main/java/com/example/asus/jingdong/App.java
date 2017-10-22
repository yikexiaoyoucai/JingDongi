package com.example.asus.jingdong;

import android.app.Application;
import android.content.Context;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by asus on 2017/10/11.
 */

public class App extends Application {
    {
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        UMShareAPI.get(this);
    }
}
