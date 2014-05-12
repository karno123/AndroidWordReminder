package com.example.data.action;

import java.util.List;

import com.example.wordremainder.R;
import com.example.wordremainder.base.ViewWordModel;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListViewWordModel extends ArrayAdapter<ViewWordModel>{

	private Context context;
	private List<ViewWordModel> arrayList;
	
	public ListViewWordModel(Context context, List<ViewWordModel> objects) {
		super(context,R.layout.view_word_list_layout,objects);
		
		this.context=context;
		this.arrayList=objects;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowview=null;
		
		if(!arrayList.get(position).isHeader())
		{
			rowview=layoutInflater.inflate(R.layout.view_word_list_layout, parent,false);
			TextView wordItem=(TextView) rowview.findViewById(R.id.rowTextView);
			wordItem.setText(Html.fromHtml("<b>"+arrayList.get(position).getWord()+"</b>(<i>"+arrayList.get(position).getKinOfWord()+"</i>) :" +arrayList.get(position).getMeaning()));
		}else
		{
			rowview =layoutInflater.inflate(R.layout.view_title,parent,false);
			TextView wordTitle=(TextView) rowview.findViewById(R.id.list_title);
			wordTitle.setText(arrayList.get(position).getHeader());
		}
		
		return rowview;
	}

	
}
