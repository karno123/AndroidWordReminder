package com.example.wordremainder.base;

public class ViewWordModel extends NewWord{
	
	private boolean isHeader;
	private String header;
	
	public ViewWordModel(String header)
	{
		super();
		this.header=header;
		this.isHeader=true;
	
	}
	
	public ViewWordModel(String word,String kindOfWord,String meaning,String header)
	{
		
		super();	
		this.isHeader=false;
		setWord(word);
		setMeaning(meaning);
		setKinOfWord(kindOfWord);
	}

	public boolean isHeader() {
		return isHeader;
	}

	public void setHeader(boolean isHeader) {
		this.isHeader = isHeader;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	
}	