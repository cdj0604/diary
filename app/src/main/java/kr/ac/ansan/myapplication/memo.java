package kr.ac.ansan.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class memo extends AppCompatActivity {
    EditText mMemoEdit = null;
    TextFileManager mTextFileManager = new TextFileManager(this);

    @Override
    protected void onCreate(final Bundle saveInstaceStace){
        super.onCreate(saveInstaceStace);
        setContentView(R.layout.memo);
        mMemoEdit = (EditText)findViewById(R.id.memo_edit);

    }


    public void onClick(View v){
        switch (v.getId()){
            //1.파일에 저장된 메모텍스트파일 불러오기
            case R.id.load_btn: {
                String memoData = mTextFileManager.load();
                mMemoEdit.setText(memoData);

                Toast.makeText(this,"불러오기 완료",Toast.LENGTH_LONG).show();
                break;
            }

            //2.에디트텍스트에 입력된 메모를 텍스트 파일로 저장하기
            case R.id.svae_btn: {
                String memoData = mMemoEdit.getText().toString();
                mTextFileManager.save(memoData);
                mMemoEdit.setText("");
                Toast.makeText(this,"저장 완료",Toast.LENGTH_LONG).show();
                break;

            }

            //3. 저장된 메모파일 삭제하기
            case R.id.delete_btn:{
                mTextFileManager.delete();;
                mMemoEdit.setText("");

                Toast.makeText(this,"삭제 완료",Toast.LENGTH_LONG).show();

            }
        }
    }
    public void lock_btn(View v){
        String memoData = mMemoEdit.getText().toString();
        mTextFileManager.save(memoData);
        mMemoEdit.setText("");
        Toast.makeText(this,"비밀번호 설정완료",Toast.LENGTH_LONG).show();
    }
    public void DJ(View v){
        setContentView(R.layout.dongjoo);
    }

    public void lockbtn(View v){
        Intent it = new Intent(this,lock.class);
        startActivity(it);
        finish();
        setContentView(R.layout.dongjoo);
    }

    public void back(View v) {
        Intent it = new Intent(this,MainActivity.class);
        startActivity(it);
        finish();
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
