package kr.ac.ansan.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.FileInputStream;

public class lockcheck extends AppCompatActivity {
    EditText check_ed;
    Button btncheck;
    Button button;
    protected void onCreate(final Bundle saveInstaceStace) {
        super.onCreate(saveInstaceStace);
        setContentView(R.layout.lockcheck);
        check_ed = (EditText) findViewById(R.id.check_ed);
        btncheck = (Button) findViewById(R.id.btncheck);


        btncheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream inFs = openFileInput("lock.txt");
                    byte[] txt = new byte[inFs.available()];
                    inFs.read(txt);
                    String str = new String(txt); //내부메모리에 저장된값을 str에 불러옴..
                    inFs.close();
                    str.trim();
                   // Toast.makeText(getApplicationContext(),"저장된비밀번호 ="+str,Toast.LENGTH_SHORT).show();
                    String passwd = check_ed.getText().toString(); //사용자가 입력하는 값을 passwd에 저장
                    passwd.trim();
                  // Toast.makeText(getApplicationContext(),"입력한비밀번호 ="+passwd,Toast.LENGTH_SHORT).show();
                   Log.i("입력",passwd);
                    Log.i("비밀번호",str);
                    if (passwd.equals(str)){
                        Intent it = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(it);
                        finish();
                    }
                    else Toast.makeText(getApplicationContext(),"비밀번호가 틀렸습니다.",Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    String passwd = check_ed.getText().toString(); //사용자가 입력하는 값을 passwd에 저장
                    passwd.trim();
                    if (passwd.equals("0000")) {
                        Intent it = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(it);
                        finish();
                    }
                }
            }

        });



    }

}
