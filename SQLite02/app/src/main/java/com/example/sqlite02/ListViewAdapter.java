package com.example.sqlite02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<ListViewItem> listViewItemArrayList = new ArrayList<ListViewItem>();
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
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }
        TextView textViewName = (TextView) convertView.findViewById(R.id.textName) ;
        TextView textViewNum = (TextView) convertView.findViewById(R.id.textNum) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ListViewItem listViewItem = listViewItemArrayList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        textViewName.setText(listViewItem.getgName());
        textViewNum.setText(listViewItem.getgNum());
        return convertView;
    }
    public  void clearView(){
        listViewItemArrayList.clear();
    }
    //Item 추가
    public void addItem(String gName, String gNum){
        ListViewItem item = new ListViewItem();
        item.setgName(gName);
        item.setgNum(gNum);
        listViewItemArrayList.add(item);
    }
}
