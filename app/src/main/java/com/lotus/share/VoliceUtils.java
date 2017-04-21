package com.lotus.share;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Vibrator;

/**
 * Created by Thl on 2017/4/20.
 */

public class VoliceUtils {

    public static void playDefaultSound(Context context){
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone ringTone = RingtoneManager.getRingtone(context, uri);
        ringTone.play();

    }

    public static void playSound(Context context,int resRawId){
        String uriStr ="android.resource://" + context.getPackageName() + "/"+resRawId;
        Uri uri= Uri.parse(uriStr);
        Ringtone ringTone = RingtoneManager.getRingtone(context, uri);
        ringTone.play();
    }

    /*
    * 想设置震动大小可以通过改变pattern来设定，如果开启时间太短，震动效果可能感觉不到
    * */
    public static  void playVibrator(Context context,long millisTime){
        final Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        long [] pattern = {100,400,100,400};   // 停止 开启 停止 开启
        vibrator.vibrate(pattern, 0);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (vibrator.hasVibrator()){
                    vibrator.cancel();
                }
            }
        },millisTime);
    }

    public static void playSoundAndVibrator(Context context,long millisTime){
        playDefaultSound(context);
        playVibrator(context,millisTime);
    }
}
