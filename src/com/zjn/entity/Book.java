package com.zjn.entity;

import java.io.Serializable;
import javax.swing.border.TitledBorder;

public class Book implements Serializable {
    /**
     * @author zjn
     */
	private long ISBN;
    private String title;
    private Integer id;
    private String publisher;
    private String publishdate;
    private Integer price;
    private String name;
    private Integer age;
    private String country;
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Book(long aISBN, String atitle, Integer aid, String apublisher, String apublishdate, Integer aprice,String aname,Integer aage,String acountry) {
    	ISBN = aISBN;
    	title = atitle;
    	id = aid;
    	publisher = apublisher;
    	publishdate = apublishdate;
    	price = aprice;
    	name = aname;
    	age = aage;
    	country = acountry;
    }

	public long getISBN() {
		return ISBN;
	}

	public void setISBN(long iSBN) {
		ISBN = iSBN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublishdate() {
		return publishdate;
	}

	public void setPublishdate(String publishdate) {
		this.publishdate = publishdate;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

   
}