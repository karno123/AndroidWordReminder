package com.example.wordremainder;

import java.util.ArrayList;

import com.example.data.action.DatabaseAction;
import com.example.data.action.ListViewWordModel;
import com.example.data.source.DatabaseConnection;
import com.example.wordremainder.base.ViewWordModel;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;



public class ViewWord extends Activity{
	
	private ListViewWordModel listArrayAdapter;
	private ListView listWordView;
	private EditText searchTextView;
	private String word;
	private String kindOfWord;
	private String meaning;
	private int id;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.view_word);

		listWordView= (ListView) findViewById(R.id.word_list);
		listWordView.setTextFilterEnabled(true);
		
		generateViewWord();
		
		searchTextView = (EditText) findViewById(R.id.search_text_input);
		
		searchTextView.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				listArrayAdapter.getFilter().filter(arg0.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
	}
	
	
	public void generateViewWord()
	{
	    
		DatabaseAction dataBaseAction =new DatabaseAction();
		
		ArrayList<ViewWordModel> viewWordModelList=dataBaseAction.getAllWordList(getApplicationContext());
		listArrayAdapter=new  ListViewWordModel(this, viewWordModelList);
		listWordView.setAdapter(listArrayAdapter);
		
		registerForContextMenu(listWordView);		
	}


	
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		AdapterContextMenuInfo adapterInfo = (AdapterContextMenuInfo) menuInfo;
		
		ViewWordModel viewWord=(ViewWordModel) listWordView.getItemAtPosition(adapterInfo.position);
		if(!viewWord.isHeader())
		{
			MenuInflater menuInflater =getMenuInflater();
			menuInflater.inflate(R.menu.listwordmenu, menu);
			this.word=viewWord.getWord();
			this.meaning=viewWord.getMeaning();
			this.kindOfWord=viewWord.getKindOfWord();
			this.id=viewWord.getId();
		}	
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater menuInflater =getMenuInflater();
		menuInflater.inflate(R.menu.home_title_bar, menu);
		
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch(item.getItemId())
		{
			case R.id.edit:
				Intent intent=new Intent(getBaseContext(),UpdateWordActivity.class);
				intent.putExtra("word", this.word);
				intent.putExtra("meaning",this.meaning);
				intent.putExtra("kindOfWord", this.kindOfWord);
				intent.putExtra("id", this.id);
				startActivity(intent);
				return true;
			case R.id.delete:
			    AlertDialog.Builder builder =new AlertDialog.Builder(this);
			    builder.setMessage("Are you sure want to delete this item?");
			    builder.setTitle("Delete confirmation");
			    
			    builder.setNegativeButton("Cancel", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
			    
			    builder.setPositiveButton("OK", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						DatabaseConnection dbConnection =new DatabaseConnection(getApplicationContext());
						String status=dbConnection.deleteWord(id);
						
						if(status.equals("SUCCESS"))
						{
							Toast.makeText(getApplicationContext(), "Delete word succes", Toast.LENGTH_SHORT).show();
							generateViewWord();
							
						}else
						{
							Toast.makeText(getApplicationContext(), "Delete word failed", Toast.LENGTH_SHORT).show();
						}
					}
				});
			    
			    AlertDialog dialog =builder.create();
			    
			    
			    dialog.show();
				return true;
		}
		
		return super.onContextItemSelected(item);
	}


	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch(item.getItemId())
		{
			case R.id.title_bar_add:
				Intent newWordIntent=new Intent(getBaseContext(),AddNewWord.class);
				startActivity(newWordIntent);
		}
		
		return super.onOptionsItemSelected(item);
	}


	
}





