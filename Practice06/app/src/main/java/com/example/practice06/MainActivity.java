package com.example.practice06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnAudio = null;
    Button btnEnd = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final int picCount[] = new int[9];
        for(int i=0;i<9;i++){ //투표수
            picCount[i]=0;
        }
        ImageView image[] = new ImageView[9];
        Integer imageId[] ={R.id.v1,R.id.v2,R.id.v3,R.id.v4,R.id.v5,R.id.v6,R.id.v7,R.id.v8,R.id.v9};
        final String imgName[] = { "독서하는 소녀", "꽃장식 모자 소녀", "부채를 든 소녀",
                "이레느깡 단 베르양", "잠자는 소녀", "테라스의 두 자매", "피아노 레슨", "피아노 앞의 소녀들",
                "해변에서" };
        for(int i=0;i<9;i++){
            final int index;
            index=i;
            image[index]=(ImageView)findViewById(imageId[index]);
            image[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    picCount[index]++;
                    Toast.makeText(getApplicationContext(),"총 "+picCount[index]+"표",Toast.LENGTH_SHORT).show();
                }
            });
        }

        btnEnd=(Button)findViewById(R.id.btnEnd);
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
                intent.putExtra("name",imgName);
                intent.putExtra("count",picCount);
                startActivity(intent);
            }
        });



        btnAudio=(Button)findViewById(R.id.btnAudio);
        btnAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.INTENT_ACTION_MUSIC_PLAYER);
                startActivity(intent);
            }
        });
    }
}
