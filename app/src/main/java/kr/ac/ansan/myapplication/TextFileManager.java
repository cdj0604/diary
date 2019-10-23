package kr.ac.ansan.myapplication;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by chae dong ju on 2018-11-26.
 */

class TextFileManager {
    private static final String FILE_NAME="Memo.txt";
    //메모 내용을 저장할 파일 이름
    Context mContext = null;

    public TextFileManager(Context context) {
        mContext = context;
    }

    //파일에 메모를 저장하는 함수
    public void save(String strData) {
        if (strData == null || strData.equals("")){
            return;
        }
        FileOutputStream fosMemo = null;

        try{
            //파일에 데이터를 쓰기위해서 output스트림 생성
            fosMemo = mContext.openFileOutput(FILE_NAME,Context.MODE_PRIVATE);
            //파일에 메모적기
            fosMemo.write(strData.getBytes());
            fosMemo.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //저장된 메모를 불러오는 함수
    public String load(){
        try{
            //파일에서 데이터를 읽기위해 input 스트림생성
            FileInputStream fisMemo = mContext.openFileInput(FILE_NAME);

            //데이터를 읽어 온 뒤,STring타임 객체로 반환
            byte[] memoDate = new byte[fisMemo.available()];
            while (fisMemo.read(memoDate)!=-1){}

            return new String(memoDate);
        }catch (IOException e){}

        return "";
    }

    public void delete() {
        mContext.deleteFile(FILE_NAME);
    }
}
