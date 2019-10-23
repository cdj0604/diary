package kr.ac.ansan.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ShowMyData extends Activity {
    int nowData = 0;
    Cursor cursor;
    TextView date;
    TextView t1;
    String diary_content;
    String diary_date;
    int numburOfData;

    @Override
    public void onCreate (Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.show);

        date = (TextView) findViewById(R.id.date);
        t1 = (TextView) findViewById(R.id.t1);

        try {
            DBManager dbmgr = new DBManager(this);
            SQLiteDatabase sdb = dbmgr.getReadableDatabase();
            cursor = sdb.query("diaryTB",null,null,null,null,null,null);
            numburOfData = cursor.getCount();
            cursor.moveToFirst();

            if (numburOfData == 0) nowData = 0;
            else nowData = 1;

            if (cursor.getCount() > 0) {
                diary_content= cursor.getString(0);
                diary_date = cursor.getString(1);
            }
            cursor.close();
            dbmgr.close();
        }catch (SQLException e) {}
        date.setText(diary_content);
        t1.setText(diary_date);
    }
    public void nextData(View v) {
        try {
            DBManager dbmgr = new DBManager(this);
            SQLiteDatabase sdb = dbmgr.getReadableDatabase();
            cursor = sdb.query("diaryTB",null,null,null,null,null,null);


            //numburOfData == 테이블 갯수
            //nowData == 보고있는 화면
            if (numburOfData == 0) nowData = 0; //테이블갯수가 0이면 보고있는것도 0
            if(cursor.getCount() > 0 && nowData <= numburOfData) {  //테이블 갯수가 0보다 크고 보고있는 화면이 테이블 갯수보다 작거나같으면
                nowData += 1;   //보고 있는 화면 +1
                if (nowData >= numburOfData) {nowData = numburOfData;}
                cursor.moveToPosition(nowData - 1);
                diary_content= cursor.getString(0);
                diary_date = cursor.getString(1);
            }
            cursor.close();
            dbmgr.close();
        }catch (SQLException e){}
        date.setText(diary_content);
        t1.setText(diary_date);
    }
    public void previousData(View v) {
        try {
            DBManager dbmgr = new DBManager(this);
            SQLiteDatabase sdb = dbmgr.getReadableDatabase();
            cursor = sdb.query("diaryTB",null,null,null,null,null,null);

            if (numburOfData == 0) nowData = 0;
            if(cursor.getCount() > 0 && nowData > 1) {
                nowData -= 1;
                if(nowData <=1) nowData = 1;
                cursor.moveToPosition(nowData - 1);
                diary_content= cursor.getString(0);
                diary_date = cursor.getString(1);
            }
            cursor.close();
            dbmgr.close();
        }catch (SQLException e){}
        date.setText(diary_content);
        t1.setText(diary_date);
    }

    public void asd() {
        if (numburOfData >= 1)
            try {

                if (nowData == numburOfData)
                {
                    DBManager dbmgr = new DBManager(this);
                    SQLiteDatabase sdb;

                    sdb = dbmgr.getWritableDatabase();
                    cursor = sdb.query("diaryTB", null, null, null, null, null, null);
                    cursor.moveToPosition(nowData - 1);
                    diary_content = cursor.getString(0);
                    nowData -= 1;
                    String sql = String.format("DELETE FROM diaryTB WHERE data1 = '%s'", diary_content);

                    sdb.execSQL(sql);
                    cursor.close();
                    dbmgr.close();

                    try {
                        DBManager dbmgrw = new DBManager(this);
                        SQLiteDatabase sdbw = dbmgrw.getReadableDatabase();
                        cursor = sdbw.query("diaryTB",null,null,null,null,null,null);

                        if (numburOfData == 0) nowData = 0;
                        if(cursor.getCount() > 0 && nowData > 1) {
                            if(nowData <=1) nowData = 1;
                            cursor.moveToPosition(nowData - 1);
                            diary_content= cursor.getString(0);
                            diary_date = cursor.getString(1);
                        }
                        cursor.close();
                        dbmgrw.close();
                    }catch (SQLException e){}
                    date.setText(diary_content);
                    t1.setText(diary_date);
                }

                else
                {
                    DBManager dbmgr = new DBManager(this);
                    SQLiteDatabase sdb;

                    sdb = dbmgr.getWritableDatabase();
                    cursor = sdb.query("diaryTB", null, null, null, null, null, null);
                    cursor.moveToPosition(nowData - 1);
                    diary_content = cursor.getString(0);
                    nowData -= 1;
                    String sql = String.format("DELETE FROM diaryTB WHERE data1 = '%s'", diary_content);

                    sdb.execSQL(sql);
                    cursor.close();
                    dbmgr.close();

                    try {
                        DBManager dbmgr1 = new DBManager(this);
                        SQLiteDatabase sdb1 = dbmgr1.getReadableDatabase();
                        cursor = sdb1.query("diaryTB", null, null, null, null, null, null);

                        if (numburOfData == 0) nowData = 0;
                        if (cursor.getCount() > 0 && nowData <= numburOfData) {
                            nowData += 1;
                            if (nowData >= numburOfData) nowData = numburOfData;
                            cursor.moveToPosition(nowData - 1);
                            diary_content = cursor.getString(0);
                            diary_date = cursor.getString(1);
                        }

                        cursor.close();
                        dbmgr1.close();
                    } catch (SQLException e) {
                    }
                    date.setText(diary_content);

                    t1.setText(diary_date);
                }

                Toast.makeText(getApplicationContext(), "삭제되었습니다", Toast.LENGTH_SHORT).show();

            }catch (SQLiteException e){}
    }
    public void deleteData(View v) {
        Button modify = (Button)findViewById(R.id.modify);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);     // 여기서 this는 Activity의 this

// 여기서 부터는 알림창의 속성 설정

        builder.setTitle("Delete")        // 제목 설정

                .setMessage("삭제하시겠습니까?")        // 메세지 설정

                .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정

                .setPositiveButton("확인", new DialogInterface.OnClickListener(){

                    // 확인 버튼 클릭시 설정

                    public void onClick(DialogInterface dialog, int whichButton){
                        asd();
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

    public void modifyData(View v) {
        Intent it = new Intent(this, ModifyMyData.class);
        String msg = nowData + "";
        it.putExtra("it_name",msg);

        startActivity(it);
        finish();
    }

    public void finish(View v) {
        Button finish = (Button)findViewById(R.id.finish);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);     // 여기서 this는 Activity의 this



// 여기서 부터는 알림창의 속성 설정

        builder.setTitle("종료")        // 제목 설정

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