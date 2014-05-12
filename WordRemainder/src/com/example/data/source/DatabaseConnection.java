package com.example.data.source;

import java.util.ArrayList;

import com.example.wordremainder.base.NewWord;
import com.example.wordremainder.base.ViewWordModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseConnection extends SQLiteOpenHelper{

	private static final int DATABASE_VERSION=1;
	private static final String DATABASE_NAME="NEWWORD_MANAGER";
	private static final String TABLE_NEW_WORD ="NewWord";
	
	
	private static final String ID="id";
	private static final String WORD="word";
	private static final String KINDOFWORD="kindOfWord";
	private static final String MEANING="meaning";
	
	
	public DatabaseConnection(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		Log.i("Database","==============Create table in==================");
		
		String CREATE_NEW_WORD_TABLE="CREATE TABLE "+TABLE_NEW_WORD+ "( "+ID + " INTEGER primary key autoincrement, " 
		+WORD+" TEXT, "+KINDOFWORD+" TEXT ,"+MEANING+" TEXT)";
		
		db.execSQL(CREATE_NEW_WORD_TABLE);
				
		Log.i("Database","==============Create table out==================");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
		db.execSQL("DROP TABLE IF EXIST "+TABLE_NEW_WORD);
	}
	
	
	public long  addNewWordRows(NewWord newWord)
	{
		Log.i("Database","=============Add new word in===================");
		SQLiteDatabase addNewWord=this.getWritableDatabase();
		ContentValues contentValues =new ContentValues();
		contentValues.put("word",newWord.getWord());
		contentValues.put("kindOfWord",newWord.getKinOfWord());
		contentValues.put("meaning",newWord.getMeaning());
		
		long isSuccess=addNewWord.insert(TABLE_NEW_WORD, null, contentValues);
		addNewWord.close();
		Log.i("Database","===========Add New word out==================");
		
		return isSuccess;
	}
	
	
	public int getMaxId()
	{
		Log.i("Database","===========Get Max Id IN=====================");
		int max = 0;
		
		SQLiteDatabase db=this.getReadableDatabase();
		
		String getMaxId="SELECT MAX(ID) FROM "+TABLE_NEW_WORD;
		
		Cursor cursor=db.rawQuery(getMaxId, null);
		
		if(cursor.moveToFirst())
		{
			while(cursor.moveToNext())
			{
				max=cursor.getInt(0);
			}
		}
		Log.i("Database","===========Get Max Id Out=====================");
		db.close();
		return max+1;
	}
	
	public ArrayList<ViewWordModel> getAllWord()
	{
		Log.i("Database","=============Get All Word In===================");
		ArrayList<ViewWordModel> listAllWord=new ArrayList<ViewWordModel>();
		
		SQLiteDatabase db=this.getReadableDatabase();
		String getAllWord="SELECT word,kindOfWord,meaning FROM "+TABLE_NEW_WORD+" order by word asc";
		
		Cursor cursor=db.rawQuery(getAllWord, null);
		
		String header="a";
		ViewWordModel viewWordModel=null;
		ViewWordModel viewWordModelWithHeader=null;
		if(cursor.moveToFirst())
		{
			while(cursor.moveToNext())
			{
				if(header.toLowerCase().equals(cursor.getString(0).substring(0,1).toLowerCase()))
				{
					 viewWordModel=new ViewWordModel(cursor.getString(0),cursor.getString(1),cursor.getString(2), header);
					 viewWordModel.setHeader(false);
					 
				}else
				{
					header=cursor.getString(0).substring(0, 1);
					viewWordModelWithHeader=new ViewWordModel(header);
					listAllWord.add(viewWordModelWithHeader);
					viewWordModel=new ViewWordModel(cursor.getString(0),cursor.getString(1),cursor.getString(2), header);
					
				}
				listAllWord.add(viewWordModel);
				
			}
		}
		db.close();
		Log.i("Database", "============Get All Word Out=================");
		return listAllWord;
		
	}
	
}
