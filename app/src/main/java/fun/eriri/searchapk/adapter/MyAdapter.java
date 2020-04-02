package fun.eriri.searchapk.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fun.eriri.searchapk.R;
import fun.eriri.searchapk.bean.AppInfo;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private List<AppInfo> appInfos;
    private LayoutInflater layoutInflater;

    public MyAdapter(Context context){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setData(List<AppInfo> list){
        this.appInfos = list;
        notifyDataSetChanged();
    }
    public void  setData(){
        this.appInfos = AppInfo.AppList.getList();
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return appInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return appInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AppInfo appInfo = appInfos.get(position);
        ViewHolder viewHolder = null;
        if (convertView ==null){
            convertView = layoutInflater.inflate(R.layout.item_main,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.ico = convertView.findViewById(R.id.ico);
            viewHolder.lable = convertView.findViewById(R.id.label);
            viewHolder.internet = convertView.findViewById(R.id.internet);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.ico.setImageDrawable(appInfo.getIco());
        viewHolder.lable.setText(appInfo.getLable());


        return convertView;
    }

    public static class ViewHolder {
        private ImageView ico;
        private TextView lable;
        private TextView internet;
    }
}
