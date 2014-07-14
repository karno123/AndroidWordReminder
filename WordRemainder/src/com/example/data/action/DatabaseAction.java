package com.example.data.action;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

import com.example.data.source.DatabaseConnection;
import com.example.wordremainder.base.NewWord;
import com.example.wordremainder.base.ViewWordModel;

public class DatabaseAction {
	

	public long addNewWord(NewWord newWord,Context context)
	{
		Log.i("Action", "===========add word in===============");
		DatabaseConnection databaseConnection=new DatabaseConnection(context);
		long statusInsert =databaseConnection.addNewWordRows(newWord);
		Log.i("Action", "===========add word out===============");
		
		return statusInsert;
	}

	public ArrayList<ViewWordModel> getAllWordList(Context context)
	{
		Log.i("Action", "===========get all word in===============");
		DatabaseConnection databaseConnection =new DatabaseConnection(context);
		ArrayList<ViewWordModel> allWordList = databaseConnection.getAllWord();
		
		Log.i("Action", "===========get all word out===============");
		return allWordList;
	}
	
	public ArrayList<ViewWordModel> getAllWordListByWord(String word,Context context)
	{
		Log.i("Action", "==========get all word list by specific word in===============");
		DatabaseConnection databaseConnection=new DatabaseConnection(context);
		ArrayList<ViewWordModel> wordListByWord=databaseConnection.getAllWordBySpecificWord(word);
		Log.i("Action", "==========get all word list by specific word out =============");
		return wordListByWord;
	}
	
	public String updateWord(NewWord word,Context context)
	{
		Log.i("Action", "==========update word in===============");
		DatabaseConnection databaseConnection=new DatabaseConnection(context);
		String status=databaseConnection.updateWord(word);
		Log.i("Action", "==========update word out===============");
		return status;
	}
}
