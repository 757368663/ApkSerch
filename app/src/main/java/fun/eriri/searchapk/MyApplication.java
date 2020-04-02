package fun.eriri.searchapk;

import android.app.Application;
import android.content.Intent;

import fun.eriri.searchapk.biz.MyService;

public class MyApplication extends Application {

    public static final String SEND_DATA = "fun.eriri.data";

    @Override
    public void onCreate() {
        super.onCreate();
        //启动service
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }
}
