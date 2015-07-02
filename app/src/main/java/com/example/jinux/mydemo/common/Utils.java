package com.example.jinux.mydemo.common;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinux on 6/1/15.
 */
public class Utils {

    public static boolean isHome(Context context){
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = manager.getRunningTasks(1);
        return getHome(context).contains(list.get(0).topActivity.getPackageName());
    }

    private static List<String> getHome(Context context) {
        List<String> packageNames = new ArrayList<>();
        PackageManager manager = context.getPackageManager();
        Intent intent =new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        List<ResolveInfo> list= manager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        for(ResolveInfo info : list){
            packageNames.add(info.activityInfo.packageName);
        }
        return packageNames;
    }

    public static int getStatusBarHeight(Context context){
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object object = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = (int)field.get(object);
            return context.getResources().getDimensionPixelSize(x);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void showToast(Context c,String s){
        Toast.makeText(c,s,Toast.LENGTH_SHORT).show();
    }

    public static void log(String s){
        Log.i("msg", s);
    }
}
