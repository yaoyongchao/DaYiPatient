package com.fh.baselib.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Process;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/** 
 * @author MaTianyu 
 * @date 2014-12-10 
 */ 
public class AppUtil { 
 
    /** 
     * 是否是主进程 
     */ 
    public static boolean isMainProcess(Context context) {
        String procName = getCurrentProcessName(context);
        return procName == null || procName.equalsIgnoreCase(context.getPackageName());
    } 
 
    /** 
     * 获取当前进程名字 
     */ 
    public static String getCurrentProcessName(Context context) {
        int pid = Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    public static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 调用系统分享
     */
    public static void shareToOtherApp(Context context, String title, String content, String dialogTitle) {
        Intent intentItem = new Intent(Intent.ACTION_SEND);
        intentItem.setType("text/plain");
        intentItem.putExtra(Intent.EXTRA_SUBJECT, title);
        intentItem.putExtra(Intent.EXTRA_TEXT, content);
        intentItem.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intentItem, dialogTitle));
    }

    /**
     * need < uses-permission android:name =“android.permission.GET_TASKS” />
     * 判断是否前台运行
     */
    public static boolean isRunningForeground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskList = am.getRunningTasks(1);
        if (taskList != null && !taskList.isEmpty()) {
            ComponentName componentName = taskList.get(0).topActivity;
            if (componentName != null && componentName.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取App包 信息版本号
     */
    public static PackageInfo getPackageInfo(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo;
    }

    /**
     * [获取应用程序版本名称信息]
     * @param context
     * @return 当前应用的版本名称
     */
    public static synchronized String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * [获取应用程序版本名称信息]
     * @param context
     * @return 当前应用的版本名称
     */
    public static synchronized int getVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void killTheApp() {
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    public static Intent callPhone(String phone) {
        //获取输入的电话号码
                         //创建打电话的意图
                         Intent intent = new Intent();
                         //设置拨打电话的动作
                         intent.setAction(Intent.ACTION_DIAL);
                         //设置拨打电话的号码
                         intent.setData(Uri.parse("tel:" + phone));
                         //开启打电话的意图
        return intent;
    }

    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    public void diallPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
//        startActivity(intent);
    }

    /**
     * 调起系统发短信功能
     * @param phoneNumber
     * @param message
     */
    public static Intent doSendSMSTo(String phoneNumber,String message){
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phoneNumber));
            intent.putExtra("sms_body", message);
            return intent;
    }

    public static Intent doSendSMSTo(String phoneNumber){
        return doSendSMSTo(phoneNumber,"");
    }

    /**
     * 拨打电话（直接拨打电话）
     *
     * @param phoneNum 电话号码
     */
//    public void callPhone(String phoneNum) {
//        Intent intent = new Intent(Intent.ACTION_CALL);
//        Uri data = Uri.parse("tel:" + phoneNum);
//        intent.setData(data);
////        startActivity(intent);
//    }

    /**
     * 获得应用版本名称
     *
     * @param context
     * @return
     */
    public static String softVersionName(Context context) {
        PackageInfo info = null;
        try {
            synchronized (context) {
                info = context.getPackageManager().getPackageInfo(
                        context.getPackageName(), 0);
                return info.versionName;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    /**
     * 比较VersionName
     * @param oldVersion  当前安装版本。
     * @param newVersion  服务器最新版本。
     * @return
     */
    public static boolean compareVersionName(String oldVersion,String newVersion) {
        String[] oldV = oldVersion.split("\\.");
        String[] newV = newVersion.split("\\.");

        /*for (int i = 0; i <oldV.length; i++) {
            if (Integer.parseInt(oldV[i]) < Integer.parseInt(newV[i]))
                return true;
        }*/

        oldVersion = oldVersion.replace(".","");
        newVersion = newVersion.replace(".","");

        int oVersion = Integer.parseInt(oldVersion);
        int nVersion = Integer.parseInt(newVersion);

//        Log.e("aa","o:" + oVersion + "____n:" + nVersion);
//        Log.e("aa","o:" + oldVersion + "____n:" + nVersion);

        return oVersion<nVersion;
    }
} 