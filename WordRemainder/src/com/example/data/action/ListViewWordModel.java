package com.example.data.action;

import java.util.ArrayList;

import com.example.wordremainder.R;
import com.example.wordremainder.base.ViewWordModel;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Filter;

public class ListViewWordModel extends ArrayAdapter<ViewWordModel>{

	private Context context;
	private ArrayList<ViewWordModel> wordList;
	private ArrayList<ViewWordModel> wordListOriginal;
	private ListFilter filter;
	
	public ListViewWordModel(Context context, ArrayList<ViewWordModel> objects) {
		super(context,R.layout.view_word_list_layout,objects);
		
		this.context=context;
		this.wordList=new ArrayList<ViewWordModel>();
		this.wordList.addAll(objects);
		this.wordListOriginal=new ArrayList<ViewWordModel>();
		this.wordListOriginal.addAll(objects);
	
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowview=null;
		
		if(!this.wordList.get(position).isHeader())
		{
			rowview=layoutInflater.inflate(R.layout.view_word_list_layout, parent,false);
			TextView wordItem=(TextView) rowview.findViewById(R.id.rowTextView);
			wordItem.setText(Html.fromHtml("<b>"+this.wordList.get(position).getWord()+"</b>(<i>"+this.wordList.get(position).getKindOfWord()+"</i>) :" +this.wordList.get(position).getMeaning()));
		}else
		{
			rowview =layoutInflater.inflate(R.layout.view_title,parent,false);
			TextView wordTitle=(TextView) rowview.findViewById(R.id.list_title);
			wordTitle.setText(this.wordList.get(position).getHeader());
		}
		
		return rowview;
	}

	@Override
	public Filter getFilter() {
		// TODO Auto-generated method stub
		if(filter==null)
		{
			filter =new ListFilter();
		}
		return  filter;
	}

	private class ListFilter extends Filter
	{

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			// TODO Auto-generated method stub
			
			String wordSearched=constraint.toString().toLowerCase();
			FilterResults listFilterResult =new FilterResults();
			
			Log.i("Searched Word",wordSearched);
			if(wordSearched!=null && wordSearched.length()>0)
			{
				ArrayList<ViewWordModel> filteredItem=new ArrayList<ViewWordModel>();
				
				Log.i("item","arrayList size :"+wordListOriginal.size());
				
				for(int i=0;i<wordListOriginal.size();i++)
				{
					ViewWordModel viewWordModel=wordListOriginal.get(i);
					Log.i("View word model object ","header "+ viewWordModel.getHeader()+" word :"+viewWordModel.getWord());
					if(viewWordModel.getWord()!=null && viewWordModel.getWord().toLowerCase().contains(wordSearched) )
					{
						Log.i("filtering",viewWordModel.getWord());
						filteredItem.add(viewWordModel);
					}
				}
				listFilterResult.values=filteredItem;
				listFilterResult.count=filteredItem.size();
			}else
			{
				synchronized(this)
				{
					Log.i("wordreminder", "no filter arrayList size :"+wordListOriginal.size());
					listFilterResult.values=wordListOriginal;
					listFilterResult.count=wordListOriginal.size();
				}
			}
			
			return listFilterResult;
		}

		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {
			// TODO Auto-generated method stub
			wordList=(ArrayList<ViewWordModel>) results.values;
			notifyDataSetChanged();
			clear();
		    for(int i = 0, l = wordList.size(); i < l; i++)
		     add(wordList.get(i));
		    notifyDataSetInvalidated();
		   }
	}
	

	
}
