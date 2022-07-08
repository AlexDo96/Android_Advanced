package com.example.downloadfile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class QuanLyFileDownload extends SQLiteOpenHelper {

	private static final String DB_NAME="quanlyfiledownload";
	private static final String T_NAME="filedownload";
	private static final int DB_VERSION=1;
	public QuanLyFileDownload(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	public QuanLyFileDownload(Context context)
	{
		super(context, DB_NAME,null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	
		//db.execSQL("drop table if exists " + T_NAME);	
		//co the dung lenh create table if not exists
		String sql="create table "+T_NAME+
				"( "+
					"_id text primary key, " 	+
					"ten text, " 				+
					"duongdan text, " 			+
					"mota text "				+
				")";
		db.execSQL(sql);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists "+T_NAME);
		onCreate(db);
	}
	public void taolai()
	{
		SQLiteDatabase db=this.getWritableDatabase();
		db.execSQL("drop table if exists "+T_NAME);
		onCreate(db);
	}
	public void taoDuLieu(String id, String ten, String duongdan, String mota)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues noidung=new ContentValues();
		noidung.put("_id", id);
		noidung.put("ten", ten);
		noidung.put("duongdan",duongdan);
		noidung.put("mota",mota);
		String chophepnull=null;
		db.insert(T_NAME,chophepnull, noidung);
	}
	public Cursor getall()
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor c=db.rawQuery("select * from "+T_NAME, null);
		return c;
	}
	public int demsophantu()
	{
		SQLiteDatabase db=this.getReadableDatabase();
		long x= DatabaseUtils.queryNumEntries(db, T_NAME);
		return (int)x;
	}
}
