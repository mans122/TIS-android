package com.example.thread01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar1,seekBar2;
    Button btnStart,btnStart2;

    class MyAsyncTask extends AsyncTask<String,String,Void>{
        @Override
        protected Void doInBackground(String...strings) {
            //주작업은 여기에 코딩
            for(int i=0;i<=100;i++) {
                //onProgressUpdate호출
//                publishProgress(Integer.toString(2*i),Integer.toString(i));
                publishProgress(Integer.toString(2*i));


                SystemClock.sleep(100);//0.1초 지연
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            //UI Thread에 요청해서 seekBar를 변경
            seekBar1.setProgress(Integer.parseInt(values[0]));
            seekBar2.setProgress(Integer.parseInt(values[1]));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar1 =(SeekBar)findViewById(R.id.seekbar1);
        seekBar2 =(SeekBar)findViewById(R.id.seekbar2);
        btnStart = (Button)findViewById(R.id.btn1);
        btnStart2 = (Button)findViewById(R.id.btn2);

        btnStart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyAsyncTask().execute();
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    public  void run(){
                        for(int i=seekBar1.getProgress();i<100;i=i+2){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    seekBar1.setProgress(seekBar1.getProgress()+2);

                                }
                            });
                            SystemClock.sleep(1000);
                        }
                    }
                }.start();

                new Thread(){
                    public  void run(){
                        for(int i=seekBar2.getProgress();i<100;i=i+1){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    seekBar2.setProgress(seekBar2.getProgress()+1);

                                }
                            });
                            SystemClock.sleep(1000);
                        }
                    }
                }.start();
            }
        });



    }
}
