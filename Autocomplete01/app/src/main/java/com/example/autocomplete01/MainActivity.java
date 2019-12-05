package com.example.autocomplete01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView auto = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auto=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        String[] mid={"CSI-뉴욕","CSI-마이애미","CSI-라스배거스","friends","fringe","lost"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,mid);
        auto.setAdapter(adapter);
    }
}
