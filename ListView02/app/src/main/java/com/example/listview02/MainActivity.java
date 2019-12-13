package com.example.listview02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editItem = null;
    Button btnAdd=null;
    ListView listView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editItem = (EditText)findViewById(R.id.editItem);
        btnAdd=(Button)findViewById(R.id.btnAdd);
        listView = (ListView)findViewById(R.id.listView1);

        final ArrayList<String> mid = new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mid);
        listView.setAdapter(adapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mid.add(editItem.getText().toString());
                editItem.setText("");
                adapter.notifyDataSetChanged(); // adapter 새로고침
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mid.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}
