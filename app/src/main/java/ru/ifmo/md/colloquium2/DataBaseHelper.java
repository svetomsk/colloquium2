package ru.ifmo.md.colloquium2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by Svet on 10.11.2014.
 */
public class DataBaseHelper extends SQLiteOpenHelper implements BaseColumns {

    final String tableCandidates = "CandidatesTable";
    final String columnName = "CandidateName";
    final String columnVotes = "CandidateVotes";

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.i("MESSAGE", "in constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i("MESSAGE", "inOnCreate");
        String scriptSubject = "create table "
                + tableCandidates + " ( " + BaseColumns._ID + " integer primary key autoincrement, " +
                columnVotes + " integer , " +
                columnName + " text not null );";
        sqLiteDatabase.execSQL(scriptSubject);

//        String scriptMarks = "create table " +
//                tableMarks + " ( " + BaseColumns._ID + " integer primary key autoincrement, " +
//                columnMark + " integer, " +
//                columnSubjectName + " text not null );";
//        sqLiteDatabase.execSQL(scriptMarks);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
