package com.djs.librarymanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHandler extends SQLiteOpenHelper {
    public DbHandler(Context context) {
        super(context, "LibraryManagement.db", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE Book(BOOK_ID VARCHAR(13),TITLE VARCHAR(30),PUBLISHER_NAME VARCHAR(20),PRIMARY KEY (BOOK_ID));");
        DB.execSQL("CREATE TABLE Publisher(NAME VARCHAR(20),ADDRESS VARCHAR(30),PHONE VARCHAR(10),PRIMARY KEY (NAME));");
        DB.execSQL("CREATE TABLE Branch(BRANCH_ID VARCHAR(5),BRANCH_NAME VARCHAR(20),ADDRESS VARCHAR(30),PRIMARY KEY (BRANCH_ID));");
        DB.execSQL("CREATE TABLE Member(CARD_NO INTEGER PRIMARY KEY AUTOINCREMENT,NAME VARCHAR(20),ADDRESS VARCHAR(30),PHONE VARCHAR(10),UNPAID_DUES NUMBER(5,2)  DEFAULT 0,PASSWORD VARCHAR(20));");
        DB.execSQL("CREATE TABLE Book_Author(BOOK_ID VARCHAR(13),AUTHOR_NAME VARCHAR(20),PRIMARY KEY(BOOK_ID, AUTHOR_NAME),FOREIGN KEY(BOOK_ID) REFERENCES Book);");
        DB.execSQL("CREATE TABLE Book_Copy(BOOK_ID VARCHAR(13),BRANCH_ID VARCHAR(5),ACCESS_NO VARCHAR(5),PRIMARY KEY(ACCESS_NO, BRANCH_ID),FOREIGN KEY(BOOK_ID) REFERENCES Book,FOREIGN KEY(BRANCH_ID) REFERENCES Branch);");
        DB.execSQL("CREATE TABLE Book_Loan(ACCESS_NO VARCHAR(5),BRANCH_ID VARCHAR(5),CARD_NO VARCHAR(5),DATE_OUT DATE,DATE_DUE DATE,DATE_RETURNED DATE,PRIMARY KEY(ACCESS_NO, BRANCH_ID, CARD_NO, DATE_OUT),FOREIGN KEY(ACCESS_NO, BRANCH_ID) REFERENCES Book_Copy,FOREIGN KEY(CARD_NO) REFERENCES Member,FOREIGN KEY(BRANCH_ID) REFERENCES Branch);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Book");
        DB.execSQL("drop Table if exists Publisher");
        DB.execSQL("drop Table if exists Branch");
        DB.execSQL("drop Table if exists Member");
        DB.execSQL("drop Table if exists Book_Author");
        DB.execSQL("drop Table if exists Book_Copy");
        DB.execSQL("drop Table if exists Book_Loan");

    }
//Book
    public Boolean insertbookdata(String bookid, String title, String pub_name)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("BOOK_ID", bookid);
        contentValues.put("TITLE", title);
        contentValues.put("PUBLISHER_NAME", pub_name);
        long result=DB.insert("Book", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean updatebookdata(String bookid, String title, String pub_name)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TITLE", title);
        contentValues.put("PUBLISHER_NAME", pub_name);
        Cursor cursor = DB.rawQuery("Select * from Book where BOOK_ID = ?", new String[]{bookid});
        if (cursor.getCount() > 0) {
            long result = DB.update("Book", contentValues, "BOOK_ID=?", new String[]{bookid});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Boolean deletebookdata (String bookid)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Book where BOOK_ID = ?", new String[]{bookid});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Book", "BOOK_ID=?", new String[]{bookid});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getbookdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Book", null);
        return cursor;
    }
//Branch
    public Boolean insertbranchdata(String branchid, String branchname, String address)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("BRANCH_ID", branchid);
        contentValues.put("BRANCH_NAME", branchname);
        contentValues.put("ADDRESS", address);
        long result=DB.insert("Branch", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean updatebranchdata(String branchid, String branchname, String address)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("BRANCH_NAME", branchname);
        contentValues.put("ADDRESS", address);
        Cursor cursor = DB.rawQuery("Select * from Branch where BRANCH_ID = ?", new String[]{branchid});
        if (cursor.getCount() > 0) {
            long result = DB.update("Branch", contentValues, "BRANCH_ID=?", new String[]{branchid});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Boolean deletebranchdata (String branchid)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Branch where BRANCH_ID = ?", new String[]{branchid});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Branch", "BRANCH_ID=?", new String[]{branchid});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getbranchdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Branch", null);
        return cursor;
    }
//Publisher
    public Boolean insertpubdata(String pubname, String pubadd, String pubpn)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", pubname);
        contentValues.put("ADDRESS", pubadd);
        contentValues.put("PHONE", pubpn);
        long result=DB.insert("Publisher", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean updatepubdata(String pubname, String pubadd, String pubpn)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ADDRESS", pubadd);
        contentValues.put("PHONE", pubpn);
        Cursor cursor = DB.rawQuery("Select * from Publisher where NAME = ?", new String[]{pubname});
        if (cursor.getCount() > 0) {
            long result = DB.update("Publisher", contentValues, "NAME=?", new String[]{pubname});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Boolean deletepubdata (String pubname)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Publisher where NAME = ?", new String[]{pubname});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Publisher", "NAME=?", new String[]{pubname});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getpubdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Publisher", null);
        return cursor;
    }

    //Member
    public Boolean insertmemberdata(String memname, String memadd, String mempn, Integer unpaid, String pwd)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", memname);
        contentValues.put("ADDRESS", memadd);
        contentValues.put("PHONE", mempn);
        contentValues.put("UNPAID_DUES", unpaid);
        contentValues.put("PASSWORD", pwd);
        long result=DB.insert("Member", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean updatememberdata(String memname, String memadd, String mempn, String unpaid, String pwd)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", memname);
        contentValues.put("ADDRESS", memadd);
        contentValues.put("PHONE", mempn);
        contentValues.put("UNPAID_DUES", unpaid);
        contentValues.put("PASSWORD", pwd);
        Cursor cursor = DB.rawQuery("Select * from Member where NAME = ?", new String[]{memname});
        if (cursor.getCount() > 0) {
            long result = DB.update("Member", contentValues, "NAME=?", new String[]{memname});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Boolean deletememberdata (String cardno)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Member where CARD_NO = ?", new String[]{cardno});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Member", "CARD_NO=?", new String[]{cardno});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getmemberdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Member", null);
        return cursor;
    }

    public boolean checkMemberCredentials(String memberNumber, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Member WHERE CARD_NO=? AND PASSWORD=?", new String[]{memberNumber, password});
        boolean result = cursor.getCount() > 0;
        cursor.close();
        return result;
    }
}
