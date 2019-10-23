package kr.ac.ansan.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

public class ModifyMyData extends Activity {
    int nowDate = 0;
    Cursor cursor;

    TextView date;
    EditText t1;
    String diary_date;
    String diray_content;

    @Override

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        setContentView(R.layout.modify);
        date = (TextView)findViewById(R.id.date);
        t1 = (EditText)findViewById(R.id.t1);

        Intent it = getIntent();

        String str_name = it.getStringExtra("it_name");
        nowDate = Integer.parseInt(str_name);

        try{
            DBManager dbmgr = new DBManager(this);
            SQLiteDatabase sdb = dbmgr.getWritableDatabase();

            cursor = sdb.query("diaryTB", null,null,null,null,null,null);
            cursor.moveToPosition(nowDate -1);

            diary_date = cursor.getString(0);
            diray_content = cursor.getString(1);

            cursor.close();
            dbmgr.close();
        }catch (SQLiteException e){}
        date.setText(diary_date);
        t1.setText(diray_content);
    }

    public void asd()
    {
        try {
            DBManager dbmgr = new DBManager(this);
            SQLiteDatabase sdb = dbmgr.getWritableDatabase();

            cursor = sdb.query("diaryTB", null,null,null,null,null,null);
            cursor.moveToPosition(nowDate -1);

            diary_date = cursor.getString(0);

            String str_sex = t1.getText().toString();
            String sql = String.format("UPDATE diaryTB SET data2 = '%s' WHERE data1 = '%s'",str_sex, diary_date);
            sdb.execSQL(sql);

            cursor.close();
            dbmgr.close();
            Toast.makeText(getApplicationContext(), "수정되었습니다", Toast.LENGTH_SHORT).show();
        }catch (SQLException e){}
        Intent it = new Intent();
        it = new Intent(this,MainActivity.class);
        it.putExtra("show",1);
        startActivity(it);
        finish();
    }

    public void modifyData(View v) {

        Button modify = (Button)findViewById(R.id.modify);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);     // 여기서 this는 Activity의 this

// 여기서 부터는 알림창의 속성 설정

        builder.setTitle("Modify")        // 제목 설정

                .setMessage("수정하시겠습니까?")        // 메세지 설정

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
    public void a() {
        Intent it = new Intent(this,MainActivity.class);
        Toast.makeText(getApplicationContext(), "취소되었습니다", Toast.LENGTH_SHORT).show();
        startActivity(it);
        finish();
    }
    public void canceltDate(View v) {
        Button modify = (Button)findViewById(R.id.modify);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);     // 여기서 this는 Activity의 this

// 여기서 부터는 알림창의 속성 설정

        builder.setTitle("Cancel")        // 제목 설정

                .setMessage("취소하시겠습니까?")        // 메세지 설정

                .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정

                .setPositiveButton("확인", new DialogInterface.OnClickListener(){

                    // 확인 버튼 클릭시 설정

                    public void onClick(DialogInterface dialog, int whichButton){
                        a();
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
