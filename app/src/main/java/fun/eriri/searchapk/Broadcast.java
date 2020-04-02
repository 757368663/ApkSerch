package fun.eriri.searchapk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.io.Serializable;
import java.util.List;

import fun.eriri.searchapk.bean.AppInfo;


//广播
public class Broadcast extends BroadcastReceiver {

    private Recive recive;
    public Broadcast() {
        super();
    }

    public void setRecive(Recive recive){
        this.recive = recive;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(MyApplication.SEND_DATA)){
            recive.recive();
        }
    }


    public interface Recive{
        void recive();
    }
}
