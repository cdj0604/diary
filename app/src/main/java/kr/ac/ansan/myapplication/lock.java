package kr.ac.ansan.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class lock extends AppCompatActivity {
    Button lock_btn,btnRead;
    EditText locktext;

    protected void onCreate(final Bundle saveInstaceStace){


        super.onCreate(saveInstaceStace);
        setContentView(R.layout.lock);
        locktext = (EditText) findViewById(R.id.locktext);
        lock_btn = (Button)findViewById(R.id.lock_btn);
        btnRead = (Button)findViewById(R.id.btnRead);


        lock_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                try{
                    FileOutputStream outFs = openFileOutput("lock.txt", Context.MODE_PRIVATE);
                    String str = locktext.getText().toString();
                    outFs.write(str.getBytes());

                    outFs.close();
                    Toast.makeText(getApplicationContext(),"비밀번호 생성완료",Toast.LENGTH_SHORT).show();

                }catch (IOException e){}
            }
        });
        btnRead.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                try{
                    FileInputStream inFs = openFileInput("lock.txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);
                    String str = new String(txt);
                    Toast.makeText(getApplicationContext(),"설정된 비밀번호는 "+str+" 입니다",Toast.LENGTH_SHORT).show();
                    inFs.close();
                } catch (IOException e){
                    Toast.makeText(getApplicationContext(),"설정된 비밀번호 없음",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

        }
