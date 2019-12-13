package com.example.mp3player_1;

import android.Manifest;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView1=null;
    Button button1,button2;
    TextView textView1,textView2;
    SeekBar seekBar1;
    //mp3목록 구하기
    String mp3Path= Environment.getExternalStorageDirectory().getPath()+"/";
    MediaPlayer mPlayer;
    ArrayList<String> mp3List;
    String selectedMP3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPlayer=new MediaPlayer();
        listView1=(ListView)findViewById(R.id.listView1);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        textView1=(TextView)findViewById(R.id.textView1);
        textView2=(TextView)findViewById(R.id.textView2);
        seekBar1=(SeekBar)findViewById(R.id.seekbar1);

        //SD카드 권한 요청
        ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);

        //ArrayList 생성
        mp3List=new ArrayList<String>();
        File[] listFiles=new File(mp3Path).listFiles();
        String fileName,extName;
        for(File file:listFiles){
            fileName=file.getName();
            extName=fileName.substring(fileName.length()-3);
            if(extName.equals("mp3")){ //확장자가 mp3이면
                mp3List.add(fileName);
            }
        }

        //ArrayAdapter생성
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,mp3List);

        //listView에 adapter적용
        listView1.setAdapter(adapter);
        listView1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        //mp3를 선택
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try{
                    selectedMP3=mp3List.get(position);
                    mPlayer.setDataSource(mp3Path + selectedMP3);
                    mPlayer.prepare();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        // 시작 버튼
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    button1.setClickable(false);
                    button2.setClickable(true);
                    mPlayer.start();
                    textView1.setText("실행중인 음악 : "+selectedMP3);
                    new Thread(){
                        SimpleDateFormat timeFormat=new SimpleDateFormat("mm:ss");//시간 출력 형식
                        public void run(){
                            //MediaPlayer가 생성되지 않았으면 중지
                            if(mPlayer==null){
                                return;
                            }
                            //seekbar의 최대값을 mp3의 플레이시간으로 설정
                            seekBar1.setMax(mPlayer.getDuration());
                            while (mPlayer.isPlaying()){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        seekBar1.setProgress(mPlayer.getCurrentPosition());
                                        textView2.setText("진행 시간 : "+timeFormat.format(mPlayer.getCurrentPosition()));
                                    }
                                });
                                SystemClock.sleep(200);
                            }
                        }//run 끝
                    }.start(); // Thread 시작
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        // 정지 버튼
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    mPlayer.pause();
                    button1.setClickable(true);
                    button2.setClickable(false);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }
}