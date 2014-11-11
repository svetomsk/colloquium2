package ru.ifmo.md.colloquium2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Svet on 10.11.2014.
 */
public class Storage {
    DataBaseHelper helper;
    SQLiteDatabase sql;
    Storage(DataBaseHelper helper) {
        this.helper = helper;
    }

    public void insertCandidate(String name) {
        sql = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(helper.columnName, name);
        cv.put(helper.columnVotes, 0);
        sql.insert(helper.tableCandidates, null, cv);

        helper.close();
    }

    public void updateCandidateName(String old, String up) {
        sql = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(helper.columnName, up);
        sql.update(helper.tableCandidates, cv, helper.columnName + " = ?", new String[] {old});

        helper.close();
    }

    public ArrayList<String> getCandidatesNames() {
        sql = helper.getWritableDatabase();

        Cursor c = sql.query(helper.tableCandidates, new String[] {helper.columnName}, null, null, null, null, null, null);
        c.moveToFirst();
        ArrayList<String> result = new ArrayList<String>();
        while(!c.isAfterLast()) {
            String curName = c.getString(c.getColumnIndex(helper.columnName));
            result.add(curName);
        }
        c.close();
        helper.close();
        return result;
    }

    public int getCandidatesCount() {
        sql = helper.getWritableDatabase();
        Cursor c = sql.query(helper.tableCandidates, new String[] {helper.columnName}, null, null, null, null, null, null);
        int result =  c.getCount();
        c.close();
        helper.close();
        return result;
    }

    public ArrayList<Integer> getVoteResults() {
        sql = helper.getWritableDatabase();

        Cursor c = sql.query(helper.tableCandidates, new String[]{helper.columnVotes}, null, null, null, null, null, helper.columnVotes + " DESC");
        c.moveToFirst();
        ArrayList<Integer> result = new ArrayList<Integer>();
        while(!c.isAfterLast()) {
            int curValue = c.getInt(c.getColumnIndex(helper.columnVotes));
            result.add(curValue);
        }
        c.close();
        helper.close();
        return result;
    }

    public int getCandidateVotes(String name) {
        sql = helper.getWritableDatabase();

        Cursor c = sql.query(helper.tableCandidates, new String[]{helper.columnVotes}, helper.columnName + " = '" + name + "'", null, null, null, null);
        c.moveToFirst();
        int result = c.getInt(c.getColumnIndex(helper.columnVotes));
        c.close();
        helper.close();

        return result;
    }

    public void increaseNumberOfVotes(String name) {
        sql = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(helper.columnVotes, getCandidateVotes(name) + 1);
        sql.update(helper.tableCandidates, cv, helper.columnName + " = ?", new String[]{name});

        helper.close();
    }

        public String getCandidateById(int id) {
        sql = helper.getWritableDatabase();
        Cursor cursor = sql.query(helper.tableCandidates, new String[] {helper.columnName}, helper._ID + " = " + (id + 1), null, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(helper.columnName);
        String result = cursor.getString(columnIndex);
        cursor.close();
        helper.close();
        return result;
    }

//    public void insertSubject(String name) {
//        sql = helper.getWritableDatabase();
//
//        ContentValues cv = new ContentValues();
//        cv.put(helper.columnSubject, name);
//        sql.insert(helper.tableSubject, null, cv);
//        helper.close();
//    }
//
//    public void insertMark(String subject, int mark) {
//        sql = helper.getWritableDatabase();
//
//        ContentValues cv = new ContentValues();
//        cv.put(helper.columnSubjectName, subject);
//        cv.put(helper.columnMark, mark);
//        sql.insert(helper.tableMarks, null, cv);
//
//        helper.close();
//    }
//
//    public int getSubjectsSize() {
//        String countQuery = "SELECT * FROM " + helper.tableSubject;
//        sql = helper.getWritableDatabase();
//        Cursor cursor = sql.rawQuery(countQuery, null);
//        cursor.moveToFirst();
//        int result = cursor.getCount();
//        cursor.close();
//        helper.close();
//        return result;
//    }
//
//    public int getMarkCount(String name) {
//        sql = helper.getWritableDatabase();
//
//        Cursor cursor = sql.query(helper.tableMarks, new String[]{helper.columnMark}, helper.columnSubjectName + " = '" + name + "'", null, null, null, null);
//        cursor.moveToFirst();
//        int result = cursor.getCount();
//        Log.i("MESSAGE", "mark count = " + result);
//        cursor.close();
//        helper.close();
//        return result;
//    }
//
//    public String getSubjectById(int id) {
//        sql = helper.getWritableDatabase();
//        Cursor cursor = sql.query(helper.tableSubject, new String[] {helper.columnSubject}, helper._ID + " = " + (id + 1), null, null, null, null);
//        cursor.moveToFirst();
//        int columnIndex = cursor.getColumnIndex(helper.columnSubject);
//        String result = cursor.getString(columnIndex);
//        cursor.close();
//        helper.close();
//        return result;
//    }
//
//    public ArrayList<String> getSubjects() {
//        sql = helper.getWritableDatabase();
//        Cursor cursor = sql.query(helper.tableSubject, new String[] {helper.columnSubject}, null, null, null, null, null);
//        cursor.moveToFirst();
//        ArrayList<String> subjects = new ArrayList<String>();
//        while(!cursor.isAfterLast()) {
//            String cur = cursor.getString(cursor.getColumnIndex(helper.columnSubject));
//            subjects.add(cur);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        helper.close();
//
//        return subjects;
//    }
//
//    public ArrayList<Integer> getMarks(String subject) {
//        sql = helper.getWritableDatabase();
//
//        Cursor cursor = sql.query(helper.tableMarks, new String[] {helper.columnMark},
//                helper.columnSubjectName +  " = '"  + subject + "'", null, null, null, null);
//        cursor.moveToFirst();
//        ArrayList<Integer> marks = new ArrayList<Integer>();
//        while(!cursor.isAfterLast()) {
//            Integer a = cursor.getInt(cursor.getColumnIndex(helper.columnMark));
//            marks.add(a);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        helper.close();
//
//        return marks;
//
//    }
}
