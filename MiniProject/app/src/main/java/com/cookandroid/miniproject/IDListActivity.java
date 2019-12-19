package com.cookandroid.miniproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class IDListActivity extends AppCompatActivity {
    Button btnAdd;
    Button btnList;
    View dialogView=null;
    ListView listView = null;
    DBManager2 dbManager = null;
    SQLiteDatabase db=null;
    IDListViewAdapter  adapter = new IDListViewAdapter();
    Cursor cursor = null;
    AlertDialog.Builder dig;

    public void refresh(){
        adapter.clearView();
        dbManager = DBManager2.getInstance(getApplicationContext());
        db= dbManager.getReadableDatabase();
        cursor = db.rawQuery("select * from Idlist;", null);
        while (cursor.moveToNext()) {
            adapter.addItem(cursor.getString(0),cursor.getString(1), cursor.getString(2), cursor.getString(3));
        }
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        db.close();
        cursor.close();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.id_list);

        btnList=(Button)findViewById(R.id.btnList);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        dbManager = DBManager2.getInstance(getApplicationContext());
        db= dbManager.getReadableDatabase();
        listView = (ListView)findViewById(R.id.listView);
        refresh();
//        adapter = new IDListViewAdapter();
//        listView.setAdapter(adapter);

//        cursor = db.rawQuery("select * from Idlist;", null);
//        while (cursor.moveToNext()) {
//            adapter.addItem(cursor.getString(0),cursor.getString(1), cursor.getString(2), cursor.getString(3));
//        }
//        adapter.notifyDataSetChanged();
//        cursor.close();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialogView = (View)View.inflate(IDListActivity.this,R.layout.id_add_dialog,null);
                dig = new AlertDialog.Builder(IDListActivity.this);
                dig.setTitle("상세 보기");
                dig.setView(dialogView); // dialog에 custom view 세팅

                final String site = ((IDListViewItem)adapter.getItem(position)).getSite();
                Toast.makeText(getApplicationContext(),site,Toast.LENGTH_SHORT).show();
                EditText vsite=(EditText)dialogView.findViewById(R.id.editSite);
                EditText vid=(EditText)dialogView.findViewById(R.id.editId);
                EditText vpwd=(EditText)dialogView.findViewById(R.id.editPwd);
                EditText vcomment=(EditText)dialogView.findViewById(R.id.editComment);
                dbManager = DBManager2.getInstance(getApplicationContext());
                db= dbManager.getReadableDatabase();
                cursor = db.rawQuery("select * from Idlist where Site='"+((IDListViewItem)adapter.getItem(position)).getSite()+"';", null);
                cursor.moveToNext();
                vsite.setText(cursor.getString(0));
                vid.setText(cursor.getString(1));
                vpwd.setText(cursor.getString(2));
                vcomment.setText(cursor.getString(3));
                cursor.close();

                dig.setNegativeButton("수정", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext()," 수정이",Toast.LENGTH_SHORT).show();
                    }
                });
                dig.setPositiveButton("삭제",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.execSQL("delete from Idlist where Site='"+site+"'");
                        Toast.makeText(getApplicationContext(),"삭제되었습니다.",Toast.LENGTH_SHORT).show();
                        refresh();
                    }
                });
                dig.show();
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db= dbManager.getWritableDatabase();
                dialogView = (View)View.inflate(IDListActivity.this,R.layout.id_add_dialog,null);
                dig = new AlertDialog.Builder(IDListActivity.this);
                dig.setTitle("등록");
                dig.setView(dialogView); // dialog에 custom view 세팅
                dig.setPositiveButton("닫기",null);
                dig.setNegativeButton("입력 완료", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 로그인처리
//                        Toast.makeText(getApplicationContext(), "로그인 처리", Toast.LENGTH_SHORT).show();
                        EditText site=(EditText)dialogView.findViewById(R.id.editSite);
                        EditText id=(EditText)dialogView.findViewById(R.id.editId);
                        EditText pwd=(EditText)dialogView.findViewById(R.id.editPwd);
                        EditText comment=(EditText)dialogView.findViewById(R.id.editComment);
                        try{
                            db.execSQL("insert into Idlist(Site,Id,Pwd,Comment) values('"+site.getText().toString()+"','"+id.getText().toString()+"','"+pwd.getText().toString()+"','"+comment.getText().toString()+"')");
                            adapter.addItem(site.getText().toString(),id.getText().toString(), pwd.getText().toString(), comment.getText().toString());
//                            adapter.notifyDataSetChanged();
//                            listView.setAdapter(adapter);
                            refresh();
                        }catch (SQLException e){
                            Toast.makeText(getApplicationContext(),"이미 존재하는 사이트 입니다.",Toast.LENGTH_SHORT).show();
                        }finally {
                            db.close();
                        }
                        Toast.makeText(getApplicationContext(),site.getText().toString()+" 입력 완료",Toast.LENGTH_SHORT).show();
                    }
                });
                dig.show();
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),index.class);
                startActivity(intent);
                db.close();
                finish();

            }
        });

    }
}
