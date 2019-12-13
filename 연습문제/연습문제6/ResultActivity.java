package com.example.practice06;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class ResultActivity extends AppCompatActivity {
    int maxIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        setTitle("투표결과");
        ImageView ivTop = (ImageView)findViewById(R.id.ivTop);
        TextView tvTop = (TextView)findViewById(R.id.tvTop);

        TextView tv[] = new TextView[9];
        RatingBar rbar[] = new RatingBar[9];
        Integer tvId[] = {R.id.tv1,R.id.tv2,R.id.tv3,R.id.tv4,R.id.tv5,R.id.tv6,R.id.tv7,R.id.tv8,R.id.tv9};
        Integer rbarId[] = {R.id.rbar1,R.id.rbar2,R.id.rbar3,R.id.rbar4,R.id.rbar5,R.id.rbar6,R.id.rbar7,R.id.rbar8,R.id.rbar9};

        Intent intent =getIntent();
        String[] name = intent.getStringArrayExtra("name");
        int[] picCount = intent.getIntArrayExtra("count");
        int imageId[] ={R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4,R.drawable.pic5,R.drawable.pic6,R.drawable.pic7,R.drawable.pic8,R.drawable.pic9};
        int max=0;


        for(int i=0;i<9;i++){
            tv[i] = (TextView)findViewById(tvId[i]);
            rbar[i] = (RatingBar)findViewById(rbarId[i]);

            tv[i].setText(name[i]);
            rbar[i].setRating((float)picCount[i]);
            if(max < picCount[i]){
                max = picCount[i];
                maxIndex = i;
            }
        }
        //투표수 가장 높은 사진
        ivTop.setImageResource(imageId[maxIndex]);
        tvTop.setText(name[maxIndex]);

        Button btnReturn = (Button)findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent1);
            }
        });
    }
}
