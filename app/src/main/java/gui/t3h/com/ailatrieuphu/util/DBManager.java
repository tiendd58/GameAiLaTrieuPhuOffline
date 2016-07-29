package gui.t3h.com.ailatrieuphu.util;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import gui.t3h.com.ailatrieuphu.model.Question;

/**
 * Created by duyti on 7/16/2016.
 */
public class DBManager {

    private static final String PATH_DB= Environment.getDataDirectory().getPath()+"/data/"+"gui.t3h.com.ailatrieuphu/databases";
    private static final String DB_NAME="Question";
    private static final String TAG = "TAG";
    private static final String SQL_GET_15_QUESTION= "select * from (select* from Question order by random()) group by level order by level limit 15";
    private static final String SQL_SET_HIGH_SCORE = "insert into HighScore values";

    private SQLiteDatabase mSQLiteDB;
    private List<Question> listQuestion = new ArrayList<>();

    public DBManager(Context context) {
        copyDB(context);
    }

    public void openDB() {
        if(mSQLiteDB==null || !mSQLiteDB.isOpen()){
            mSQLiteDB = SQLiteDatabase.openDatabase(PATH_DB+"/"+DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
        }
    }

    public void changeQuestionByLevel(int mLevel){
        String sql = "select * from Question where level = '"+mLevel+"' order by random()  limit 1";
        Cursor cursor = mSQLiteDB.rawQuery(sql, null);
        cursor.moveToFirst();
        int questionIndex = cursor.getColumnIndex("question");
        int levelIndex = cursor.getColumnIndex("level");
        int caseAIndex = cursor.getColumnIndex("casea");
        int caseBIndex = cursor.getColumnIndex("caseb");
        int caseCIndex = cursor.getColumnIndex("casec");
        int caseDIndex = cursor.getColumnIndex("cased");
        int trueCaseIndex = cursor.getColumnIndex("truecase");
        String question, caseA, caseB, caseC, caseD;
        int level, trueCase;
        question=cursor.getString(questionIndex);
        caseA=cursor.getString(caseAIndex);
        caseB= cursor.getString(caseBIndex);
        caseC=cursor.getString(caseCIndex);
        caseD=cursor.getString(caseDIndex);
        level=cursor.getInt(levelIndex);
        trueCase = cursor.getInt(trueCaseIndex);
        Question item = new Question(question,caseA,caseB,caseC,caseD,level,trueCase);
        listQuestion.set(level-1,item);
    }

    public void closeDB(){
        if(mSQLiteDB!=null && mSQLiteDB.isOpen()){
            mSQLiteDB.close();
        }
    }

    public boolean insert(String table, String columNames[][]){
        ContentValues values = new ContentValues();
        for(int i=0;i<columNames.length;i++){
            values.put(columNames[i][0],columNames[i][1]);
        }
        long result=mSQLiteDB.insert(table,null,values);
        return result>-1;
    }

    public List<Question> getListQuestion() {
        return listQuestion;
    }

    public boolean getQuestion() {
        Cursor cursor = mSQLiteDB.rawQuery(SQL_GET_15_QUESTION, null);
        if(cursor!=null){
            cursor.moveToFirst();
            int questionIndex = cursor.getColumnIndex("question");
            int levelIndex = cursor.getColumnIndex("level");
            int caseAIndex = cursor.getColumnIndex("casea");
            int caseBIndex = cursor.getColumnIndex("caseb");
            int caseCIndex = cursor.getColumnIndex("casec");
            int caseDIndex = cursor.getColumnIndex("cased");
            int trueCaseIndex = cursor.getColumnIndex("truecase");
            while (cursor.isAfterLast()==false){
                String question, caseA, caseB, caseC, caseD;
                int level, trueCase;
                question=cursor.getString(questionIndex);
                caseA=cursor.getString(caseAIndex);
                caseB= cursor.getString(caseBIndex);
                caseC=cursor.getString(caseCIndex);
                caseD=cursor.getString(caseDIndex);
                level=cursor.getInt(levelIndex);
                trueCase = cursor.getInt(trueCaseIndex);
                Question item = new Question(question,caseA,caseB,caseC,caseD,level,trueCase);
                listQuestion.add(item);
                cursor.moveToNext();
            }
            return true;
        }
        return false;

    }

    public Question getQuestion(int level){
        return listQuestion.get(level-1);
    }

    private void copyDB(Context mContext){
        new File(PATH_DB).mkdir();
        File file = new File(PATH_DB+"/"+DB_NAME);
        if(file.exists()){
            return;
        }else{
            AssetManager assetManager = mContext.getAssets();
            try {
                InputStream input = assetManager.open(DB_NAME);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] buff = new byte[1024];
                int len = 0;
                len = input.read(buff);
                while (len>0){
                    fileOutputStream.write(buff,0,len);
                    len = input.read(buff);
                }
                input.close();
                fileOutputStream.close();
                Log.i(TAG, "DB is copied");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
