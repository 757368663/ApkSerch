package fun.eriri.searchapk.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fun.eriri.searchapk.Broadcast;
import fun.eriri.searchapk.MyApplication;
import fun.eriri.searchapk.R;
import fun.eriri.searchapk.adapter.MyAdapter;
import fun.eriri.searchapk.bean.AppInfo;

public class MainActivity extends AppCompatActivity {

    Broadcast broadcast;
    MyAdapter adapter;
    TextWatcher textWatcher;

    private ListView listView;
    private EditText editText;

//    private ServiceConnection serviceConnection= new ServiceConnection(){
//
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);
        editText = findViewById(R.id.search);

        getTextWatcher();
        //当搜索框内容变换时，搜索应用
        editText.addTextChangedListener(textWatcher);
        //动态注册广播，接收信息
        broadcast = new Broadcast();
        //广播器提供接口使用，接收广播后的回调
        broadcast.setRecive(new Broadcast.Recive() {
            @Override
            public void recive() {
                adapter= new MyAdapter(MainActivity.this);
                adapter.setData();
                listView.setAdapter(adapter);
            }
        });
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyApplication.SEND_DATA);
        registerReceiver(broadcast,intentFilter);
    }


    //初始化 textwatcher
    void getTextWatcher(){
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            //搜索框内容变化则进行模糊查询
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<AppInfo> list = new ArrayList<>();
                for (int i = 0;i<AppInfo.AppList.getList().size();i++){
                    String lable = AppInfo.AppList.getList().get(i).getLable();
                    if (lable.trim().contains(s)){
                        list.add(AppInfo.AppList.getList().get(i));
                    }
                }
                adapter.setData(list);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcast);
    }
}
