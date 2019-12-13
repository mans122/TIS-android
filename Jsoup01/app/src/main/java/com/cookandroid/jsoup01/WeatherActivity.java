package com.cookandroid.jsoup01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class WeatherActivity extends AppCompatActivity {
    ListView list=null;
    ArrayList<String> name = new ArrayList<String>();
    ArrayList<String> weather = new ArrayList<String>();
    ArrayList<String> temp = new ArrayList<String>();
    ArrayList<String> total = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        setTitle("날씨");
        list = (ListView)findViewById(R.id.listView);

        new JsoupAsyncTask().execute();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,total);
        list.setAdapter(adapter);




    }

    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect("https://m.kma.go.kr/m/nation/current.jsp").get();
                Document doc2 = Jsoup.connect("https://m.kma.go.kr/m/nation/current.jsp?ele=2").get();
                Elements placeName = doc.select(".nation_map dl dt");
                Elements placeWeather = doc.select(".nation_map dl img");
                Elements placeTem = doc2.select(".nation_map dl span");
                for (Element place : placeName) {
                    name.add(place.text());
                }
                for(Element wt : placeWeather){
                    weather.add(wt.attr("alt"));
                }
                for(Element tp : placeTem){
                    temp.add(tp.text());
                }
                for(int i=0; i<name.size();i++){
                    total.add(name.get(i) + " : "+weather.get(i)+"      기온 :"+temp.get(i));
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
