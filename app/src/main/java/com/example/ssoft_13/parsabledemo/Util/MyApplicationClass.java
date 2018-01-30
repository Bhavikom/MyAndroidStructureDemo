package com.example.ssoft_13.parsabledemo.Util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.multidex.MultiDex;
import android.util.Log;

import java.io.File;

/**
 * Created by SSoft-13 on 16-06-2017.
 */

public class MyApplicationClass extends Application{

    private static MyApplicationClass instance;
    private SharedPreferences prefs, settingsPref;
    Context con;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        con = this;
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        settingsPref = getSharedPreferences(MyConstant.PREFERENCES, Context.MODE_PRIVATE);
    }
    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }
    public static MyApplicationClass getInstance(){
        return instance;
    }

    /* to clear application data */
    public void clearApplicationData() {
        File cache = getCacheDir();
        File appDir = new File(cache.getParent());
        if(appDir.exists()){
            String[] children = appDir.list();
            for(String s : children){
                if(!s.equals("lib")){
                    deleteDir(new File(appDir, s));
                    Log.i("TAG", "File /data/data/APP_PACKAGE/" + s +" DELETED ");
                }
            }
        }
    }
    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
    public void saveData(String variable, String data)
    {
        prefs.edit().putString(variable, data).commit();
    }
    public String getData(String variable, String defaultValue)
    {
        return prefs.getString(variable, defaultValue);
    }

    public void clearAllPreferences()
    {
        prefs.edit().clear();
    }
    public void clearSingleValue(String variable)
    {
        prefs.edit().remove(variable).commit();
    }
}
