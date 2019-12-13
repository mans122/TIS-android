package com.example.graphic01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        //MyGraphicView를 호출하고 this를 사용해서 화면을 그리겠다.
        setContentView(new MyGraphicView(this));
    }

    private static class MyGraphicView extends View {
        public MyGraphicView(Context context){
            super(context);
        }
        @Override
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);

            Paint paint=new Paint();
            paint.setAntiAlias(true); //외곽선 부드럽게
            paint.setColor(Color.BLUE);
            canvas.drawLine(10,10,300,10,paint);

            paint.setStyle(Paint.Style.FILL);
            Rect rect1=new Rect(10,50,10+100,50+100);
            canvas.drawRect(rect1,paint);

            paint.setStyle(Paint.Style.STROKE);
            Rect rect2=new Rect(130,50,130+100,50+100);
            canvas.drawRect(rect2,paint);

            paint.setStrokeWidth(0);
            paint.setTextSize(30);
            canvas.drawText("안드로이드",10,390,paint);

        }

    }

}
