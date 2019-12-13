package com.cookandroid.service01;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
public class MusicService extends Service {
    MediaPlayer mp;
    @Override
    public void onCreate() {
        Log.d("=====서비스 테스트=====","onCreate()");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("=====서비스 테스트=====","onStartCommand()");
        mp=MediaPlayer.create(this,R.raw.song1);
        mp.setLooping(true);
        mp.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Log.d("=====서비스 테스트=====","onDestroy()");
        mp.stop();
        super.onDestroy();
    }
}
