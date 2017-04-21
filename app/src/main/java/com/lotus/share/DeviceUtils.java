package com.lotus.share;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.lang.reflect.Method;

/**
 * Created by Thl on 2017/4/21.
 */

public class DeviceUtils {



    public static String getDeviceSn(){
        String[] propertys = {"ro.boot.serialno", "ro.serialno"};
        for (String key : propertys){
//          String v = android.os.SystemProperties.get(key);
            String v = getAndroidOsSystemProperties(key);
            if (!TextUtils.isEmpty(v)){
                return v;
            }
            Log.e("DeviceUtils", "get " + key + " : " + v);
        }
        return "";
    }

    public static String getAndroidOsSystemProperties(String key) {
        String ret;
        try {
            Method systemProperties_get = Class.forName("android.os.SystemProperties").getMethod("get", String.class);
            if ((ret = (String) systemProperties_get.invoke(null, key)) != null)
                return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return "";
    }

    /**
     * 获取手机的MAC地址
     * @return
     */
    public  static  String getReallyMacAddress(){
        String str="";
        String macSerial="";
        try {
            Process pp = Runtime.getRuntime().exec(
                    "cat /sys/class/net/wlan0/address ");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            for (; null != str;) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();// 去空格
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (macSerial == null || "".equals(macSerial)) {
            try {
                return loadFileAsString("/sys/class/net/eth0/address")
                        .toUpperCase().substring(0, 17);
            } catch (Exception e) {
                e.printStackTrace();

            }

        }
        return macSerial;
    }

    public static String loadFileAsString(String fileName) throws Exception {
        FileReader reader = new FileReader(fileName);
        String text = loadReaderAsString(reader);
        reader.close();
        return text;
    }

    public static String loadReaderAsString(Reader reader) throws Exception {
        StringBuilder builder = new StringBuilder();
        char[] buffer = new char[4096];
        int readLength = reader.read(buffer);
        while (readLength >= 0) {
            builder.append(buffer, 0, readLength);
            readLength = reader.read(buffer);
        }
        return builder.toString();
    }

    /**
     * 判断wifi是否连接上
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return wifiNetworkInfo.isConnected();
    }

    public static int getDrawableByName(Context context, String name){
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }

    /**
     *  根据资源名称获取资源id
     * @param context
     * @param name
     * @param resouceTypeName
     * @param packageName
     * @return
     */
    public static int getResourseIdByName(Context context, String name,String resouceTypeName,String packageName){
        return context.getResources().getIdentifier(name, resouceTypeName, packageName);
    }

    /**
     * 取得wifi名称
     * @return
     */
    public static String getConnectWifiSsid(Context context){
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        Log.d("wifiInfo", wifiInfo.toString());
        Log.d("SSID",wifiInfo.getSSID());
        if(TextUtils.isEmpty(wifiInfo.getSSID())){
            return "暂无";
        }
        return wifiInfo.getSSID();
    }

    /**
     * 获取手机运营商
     * @return
     */
    public static String getPhoneOperatorName(Context context){
        TelephonyManager telManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        String operator = telManager.getSimOperator();
        String phoneOperatorName="";
        if(operator!=null) {
            if (operator.equals("46000") || operator.equals("46002") || operator.equals("46007")) {
                phoneOperatorName = "中国移动";
            } else if (operator.equals("46001")) {
                phoneOperatorName = "中国联通";
            } else if (operator.equals("46003")) {
                phoneOperatorName = "中国电信";
            }
        }
        return phoneOperatorName;
    }

    /**
     * 获取应用版本名称
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        PackageInfo info = null;
        try {
            info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
        return info.versionName;
    }

    /**
     *  获取应用版本号
     * @param context
     * @return
     */
    public static String getVersionCode(Context context) {
        PackageInfo info = null;
        try {
            info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
        return String.valueOf(info.versionCode);
    }
}
