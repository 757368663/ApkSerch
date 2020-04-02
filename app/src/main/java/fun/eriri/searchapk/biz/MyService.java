package fun.eriri.searchapk.biz;

import android.app.Service;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Binder;
import android.os.IBinder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fun.eriri.searchapk.MyApplication;
import fun.eriri.searchapk.bean.AppInfo;

public class MyService extends Service {

    private final List<AppInfo> list = new ArrayList<>();
    public MyService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        //获取所有apk
       getAll();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public class MyBinder extends Binder{

    }

    void getAll(){
        PackageManager packageManager = getPackageManager();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
        for (int i = 0;i<installedPackages.size();i++){
            ApplicationInfo packageInfo = installedPackages.get(i).applicationInfo;
            AppInfo appInfo = new AppInfo(packageInfo.loadLabel(packageManager).toString(), packageInfo.loadIcon(packageManager),packageInfo.packageName);
            list.add(appInfo);
        }
        AppInfo.AppList.setList(list);
        Intent intent = new Intent(MyApplication.SEND_DATA);
        sendBroadcast(intent);
    }

//    Bitmap getBitMap(Drawable drawable){
//        Bitmap bitmap ;
//        int w = drawable.getIntrinsicWidth();
//        int h = drawable.getIntrinsicHeight();
//
//        Bitmap.Config config =   Bitmap.Config.ARGB_8888;
//
//        bitmap = Bitmap.createBitmap(w,h,config);
//        Canvas canvas = new Canvas(bitmap);
//        drawable.setBounds(0,0,w,h);
//        drawable.draw(canvas);
//        return bitmap;
//    }

}
