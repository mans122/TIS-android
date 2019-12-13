package com.cookandroid.jsoup01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView list=null;
    ArrayList<String> title = new ArrayList<String>();
    ArrayList<String> url = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView)findViewById(R.id.listView);

        new JsoupAsyncTask().execute();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,title);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                intent.putExtra("title",title.get(position));
                intent.putExtra("url",url.get(position));
                startActivity(intent);
            }
        });

        Button btnWeather = (Button)findViewById(R.id.btnWeather);
        btnWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),WeatherActivity.class);
                startActivity(intent);
            }
        });
    }
    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect("http://news.naver.com").get();
                Elements newsHeadlines = doc.select(".hdline_article_tit a");
                for (Element headline : newsHeadlines) {
                    title.add(headline.text());
                    url.add(headline.attr("href"));
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            adapter.notifyDataSetChanged();
        }
    }
}
