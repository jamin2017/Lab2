package com.zjn.entity;

import java.util.ArrayList;
import java.util.List;

public class BookList {
	
	private ArrayList<Book> books;
	public BookList()
	{
		books = new ArrayList<Book>();
	}
	
	public ArrayList<Book> getbooks(){
		return books;
	}
	
	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}
}
