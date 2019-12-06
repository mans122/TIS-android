package com.example.actionbar01;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity implements ActionBar.TabListener{

    ActionBar.Tab tabSong, tabArtist, tabAlbum; // 탭 별 변수생성
    MyTabFragment myFrags[] = new MyTabFragment[3]; //탭별 화면(Fragment)을 저장하는 배열
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        ActionBar bar = getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        tabSong = bar.newTab();
        tabSong.setText("음악별");
        tabSong.setTabListener(this); // 이벤트 처리
        bar.addTab(tabSong);

        tabArtist = bar.newTab();
        tabArtist.setText("가수별");
        tabArtist.setTabListener(this); // 이벤트 처리
        bar.addTab(tabArtist);

        tabAlbum = bar.newTab();
        tabAlbum.setText("앨범별");
        tabAlbum.setTabListener(this); // 이벤트 처리
        bar.addTab(tabAlbum);

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        MyTabFragment myTabFrag=null;
        //처음 탭을 선택한 경우
        if(myFrags[tab.getPosition()]==null){
            //프레그먼트 생성
            myTabFrag=new MyTabFragment();
            Bundle data = new Bundle();
            data.putString("tabName",tab.getText().toString()); // map으로 key값 지정
            myTabFrag.setArguments(data); // data를 myTabFragment에 지정
            myFrags[tab.getPosition()]=myTabFrag; // 배열에 저장해준다.
        }else{
            //이미 탭이 선택된 적 있던 경우
            myTabFrag=myFrags[tab.getPosition()]; // 배열에서 읽어옴
        }
        ft.replace(android.R.id.content,myTabFrag);
    }

    public static class MyTabFragment extends Fragment {
        String tabName;
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Bundle data = getArguments(); // Bundle 로 data를 구해서
            tabName = data.getString("tabName");//tabName의 값을 tabName 에 넘겨줌.
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//            return super.onCreateView(inflater, container, savedInstanceState);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            LinearLayout baseLayout = new LinearLayout(super.getActivity());
            baseLayout.setOrientation(LinearLayout.VERTICAL);
            baseLayout.setLayoutParams(params);
            if(tabName.equals("음악별")){baseLayout.setBackgroundColor(Color.GREEN);
                TextView textView = new TextView(super.getActivity());
                textView.setText("음악별");
                baseLayout.addView(textView);
            }
            if(tabName.equals("가수별")){baseLayout.setBackgroundColor(Color.BLUE);}
            if(tabName.equals("앨범별")){baseLayout.setBackgroundColor(Color.RED);}
            return baseLayout;
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {}
    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {}
}


