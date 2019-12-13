package com.example.customlistview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView;
        ListViewAdapter adapter;

        // Adapter 생성
        adapter = new ListViewAdapter();

        // 리스트 뷰 참조 및 Adapter달기
        listView=(ListView)findViewById(R.id.listview1);
        listView.setAdapter(adapter);

        //ArrayList에 첫 번째 아이템 추가
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.baseline_account_box_black_36dp),"BOX","Account Box Black 36dp");
        //ArraayList에 두 번째 아이템 추가
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.baseline_account_circle_black_36dp),"Circle","Account Circle Black 36dp");
        //ArraayList에 세 번째 아이템 추가
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.baseline_face_black_36dp),"Circle","face Black 36dp");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // get Item
                ListViewItem item = (ListViewItem)parent.getItemAtPosition(position);
                String titleStr = item.getTitleStr();
                String descStr = item.getDescStr();
                Drawable iconDrawable = item.getIconDrawable();

                // TODO: use item data
            }
        });
    }
}
