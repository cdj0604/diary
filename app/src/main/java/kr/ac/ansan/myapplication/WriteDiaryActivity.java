package kr.ac.ansan.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteDiaryActivity extends Activity{
    private DBManager dbmgr;
    // 현재시간을 msec 으로 구한다.
    /*long now = System.currentTimeMillis();
    // 현재시간을 date 변수에 저장한다.
    Date date = new Date(now);
    // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    // nowDate 변수에 값을 저장한다.
    String formatDate = sdfNow.format(date);*/

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writediary);
        EditText et_name = (EditText)findViewById(R.id.edit_name);
        String diary_date = et_name.getText().toString();
        TextView date1 = (TextView)findViewById(R.id.date);
        long now = System.currentTimeMillis(); // 1970년 1월 1일부터 몇 밀리세컨드가 지났는지를 반환함
        Date date = new Date(now);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//형식 지정
        String getTime1 = simpleDateFormat.format(date);
        et_name.setText(getTime1);
        //  Button btn = (Button)findViewById(R.id.button_store);
        //  btn.setOnClickListener((View.OnClickListener) this);
    }

    public void saveDate(View v) {

        EditText et_name = (EditText)findViewById(R.id.edit_name);
        String diary_date = et_name.getText().toString();

        EditText et_name2 = (EditText)findViewById(R.id.edit_dairy);
        String diary_content = et_name2.getText().toString();



        try {
            dbmgr = new DBManager(this);
            SQLiteDatabase sdb;

            sdb = dbmgr.getWritableDatabase();
            sdb.execSQL("insert into diaryTB values('" + diary_date + "','" + diary_content + "');");
            dbmgr.close();
        }catch (SQLiteException e){}

        Intent it = new Intent(this,MainActivity.class);
        startActivity(it);
        finish();
    }

    public void finish(View v) {
        Button finish = (Button)findViewById(R.id.finish);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Finish")        // 제목 설정

                .setMessage("앱을 종료 하시 겠습니까?")        // 메세지 설정

                .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정

                .setPositiveButton("확인", new DialogInterface.OnClickListener(){

                    // 확인 버튼 클릭시 설정

                    public void onClick(DialogInterface dialog, int whichButton){

                        finish();

                    }

                })

                .setNegativeButton("취소", new DialogInterface.OnClickListener(){

                    // 취소 버튼 클릭시 설정

                    public void onClick(DialogInterface dialog, int whichButton){

                        dialog.cancel();

                    }

                });



        AlertDialog dialog = builder.create();    // 알림창 객체 생성

        dialog.show();    // 알림창 띄우기

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
