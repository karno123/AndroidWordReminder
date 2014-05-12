package com.example.wordremainder;

import java.util.ArrayList;

import com.example.data.action.DatabaseAction;
import com.example.data.action.ListViewWordModel;
import com.example.wordremainder.base.ViewWordModel;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ViewWord extends Activity{
	
	private ListViewWordModel listArrayAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_word);
		generateViewWord();
	}
	
	public void generateViewWord()
	{
		ListView listFootballView= (ListView) findViewById(R.id.word_list);
		DatabaseAction dataBaseAction =new DatabaseAction();
		
		ArrayList<ViewWordModel> viewWordModelList=dataBaseAction.getAllWordList(getApplicationContext());
		listArrayAdapter=new  ListViewWordModel(this, viewWordModelList);
		
		listFootballView.setAdapter(listArrayAdapter);
		
	}
	

}



