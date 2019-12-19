package com.cookandroid.miniproject;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class IDListViewAdapter extends BaseAdapter {
    private ArrayList<IDListViewItem> listViewItemArrayList = new ArrayList<IDListViewItem>();
    @Override
    public int getCount() {return listViewItemArrayList.size();}
    @Override
    public Object getItem(int position) {
        return listViewItemArrayList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context= parent.getContext();
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.id_listview_item, parent, false);
        }
        TextView textSite = (TextView)convertView.findViewById(R.id.textSite);
        TextView textId = (TextView) convertView.findViewById(R.id.textId) ;
        TextView textComment = (TextView) convertView.findViewById(R.id.textComment) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        IDListViewItem listViewItem = listViewItemArrayList.get(position);
        // 아이템 내 각 위젯에 데이터 반영
        textSite.setText(listViewItem.getSite());
        textSite.setTextSize(20);
        textSite.setTextColor(Color.rgb(200,200,200));
        textId.setText(listViewItem.getId());
        textComment.setText(listViewItem.getComment());
        return convertView;
    }
    public  void clearView(){
        listViewItemArrayList.clear();
    }
    //Item 추가
    public void addItem(String Site, String Id, String Pwd, String Comment){
        IDListViewItem item = new IDListViewItem();
        item.setSite(Site);
        item.setId(Id);
        item.setPwd(Pwd);
        item.setComment(Comment);
        listViewItemArrayList.add(item);
    }
}

