package fun.eriri.searchapk.bean;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class AppInfo  {
    private String lable ;
    private Drawable ico;
    private String packagename;

    public AppInfo(){

    }

    public AppInfo(String lable, Drawable ico, String packagename) {
        this.lable = lable;
        this.ico = ico;
        this.packagename = packagename;
    }

    protected AppInfo(Parcel in) {
        lable = in.readString();
        packagename = in.readString();
        ico = in.readParcelable(Bitmap.class.getClassLoader());
    }



    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public Drawable getIco() {
        return ico;
    }

    public void setIco(Drawable ico) {
        this.ico = ico;
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }





    public static class AppList{
        private static final List<AppInfo> list = new ArrayList<>();

        public static List<AppInfo> getList() {
            return list;
        }

        public static void  setList(List<AppInfo> data){
            list.addAll(data);
        }
    }
}
