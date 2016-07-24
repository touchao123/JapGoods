package com.touchenjoy.japgoods.application;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Created by Administrator on 2016/7/24.
 */
public class JapApplication extends Application {

    public static String APPID ="3025f12f4c12d7cbe719105c45657601";

    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this,APPID);
    }


}
