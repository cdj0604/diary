package kr.ac.ansan.myapplication;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.content.Intent;
import android.widget.Toast;

public class MainActivity extends TabActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        final TabHost tabHost = getTabHost();
        tabHost.addTab(tabHost.newTabSpec("show")
                .setIndicator("일기보기")
                .setContent(new Intent(this, ShowMyData.class)));

        tabHost.addTab(tabHost.newTabSpec("write")
                .setIndicator("일기쓰기")
                .setContent(new Intent(this, WriteDiaryActivity.class)));

        tabHost.addTab(tabHost.newTabSpec("memo")
                .setIndicator("메모장")
                .setContent(new Intent(this, memo.class)));

        tabHost.setCurrentTab(0);






       /* Intent in = getIntent();
        int a = in.getIntExtra("show",1);
        Toast.makeText(getApplicationContext(),"hap" +a , Toast.LENGTH_SHORT).show();

       if (a ==1) {
          tabHost.setCurrentTab(0);
        }
        else{
        tabHost.setCurrentTab(1);
    }*/
    }

    private long time= 0;
    @Override
    public void onBackPressed(){
        if(System.currentTimeMillis()-time>=2000){
            time=System.currentTimeMillis();
            Toast.makeText(getApplicationContext(),"뒤로 버튼을 한번 더 누르면 종료합니다.",Toast.LENGTH_SHORT).show();
        }else if(System.currentTimeMillis()-time<2000){
            finish();
        }
    }

}






