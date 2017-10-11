
package com.zjn.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.el.ArrayELResolver;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.sun.corba.se.impl.protocol.giopmsgheaders.ReplyMessage_1_0;

import com.sun.org.apache.regexp.internal.recompile;
import com.zjn.entity.Book;
import com.zjn.entity.BookList;

import jdk.nashorn.internal.ir.Flags;

/**
 * 用户管理
 * 
 * @author ljm
 */

@Controller
public class UserController {
	 
//	 ArrayList<Book> booklist = new ArrayList<Book>();
	private ArrayList<Book>books = new ArrayList<Book>();
	private int flag = 1;

    @RequestMapping("")
    public String Create(Model model) {
    	
    	return "create";
    }
    
    @RequestMapping("/query")
    public ModelAndView query( String name, Model model) {
//    	System.out.println("++++++++++++"+name);
    	BookList booklist = new BookList();
    	
    	books.clear();
    	System.out.println(name + "queryname");
    	String driver = "com.mysql.jdbc.Driver";
		  //String driver = "com.mysql.jdbc.Driver";
    	String user = System.getenv("ACCESSKEY");
    	String password = System.getenv("SECRETKEY");
    	String url = String.format("jdbc:mysql://%s:%s/%s", System.getenv("MYSQL_HOST"), System.getenv("MYSQL_PORT"),System.getenv("MYSQL_DB"));
		// 开始连接数据库
    	
		try {
			// 加载驱动程序
			Class.forName(driver).newInstance();
			
			// 连续数据库
			Connection conn = DriverManager.getConnection(url, user, password);
			if (!conn.isClosed())
				System.out.println("connecting to the database successfully!");
			String sql = "select AuthorID,Name,Age,Country from author where Name=?";
			PreparedStatement ptmt0 = conn.prepareStatement(sql);
			ptmt0.setString(1, name);
			ResultSet rs0 = ptmt0.executeQuery();
			while(rs0.next())
			{
				System.out.println(rs0.getInt("AuthorID")+"rs0.ID");
				sql = "select ISBN,Title,AuthorID,Publisher,PublishDate,Price from book where AuthorID=?";
				PreparedStatement ptmt = conn.prepareStatement(sql);
				ptmt.setInt(1,rs0.getInt("AuthorID"));
				ResultSet rs = ptmt.executeQuery();

				// 输出student的所有信息
				
				while (rs.next()) {
					Book booktemp = new Book(rs.getLong("ISBN"), 
												rs.getString("Title"), 
												rs.getInt ("AuthorID"), 
												rs.getString("Publisher"),
												rs.getString("PublishDate"),
												rs.getInt("Price"),
												rs0.getString("Name"),
												rs0.getInt("Age"),
												rs0.getString("Country"));
					books.add(booktemp);
				}
				rs.close();
				ptmt.close();
			}
			
			rs0.close();
			ptmt0.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			System.out.println("sorry, can't find the driver!");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		booklist.setBooks(books);
		return new ModelAndView("detail", "booklist", booklist);
//		model.addAttribute("booklist", booklist);
//		return "detail";
	}

    @RequestMapping("/del")
	public void Deletes(HttpServletRequest request, HttpServletResponse response) {
		String items = request.getParameter("isbn");// System.out.println(items);
		long isbn = Long.parseLong(items);
		System.out.println(isbn+"delisbn");
		String driver = "com.mysql.jdbc.Driver";
		  //String driver = "com.mysql.jdbc.Driver";
  	String user = System.getenv("ACCESSKEY");
  	String password = System.getenv("SECRETKEY");
  	String url = String.format("jdbc:mysql://%s:%s/%s", System.getenv("MYSQL_HOST"), System.getenv("MYSQL_PORT"),System.getenv("MYSQL_DB"));
		try {
			// 加载驱动程序
			Class.forName(driver).newInstance();
			
			// 连续数据库
			Connection conn = DriverManager.getConnection(url, user, password);
			if (!conn.isClosed())
				System.out.println("connecting to the database successfully!");
			String sql = "delete from book where ISBN=?";
			PreparedStatement pst = conn.prepareStatement(sql);
            pst.setLong(1,isbn);
            pst.executeUpdate();
            conn.close();
            pst.close();
			
		} catch (ClassNotFoundException e) {
			System.out.println("sorry, can't find the driver!");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}	
    }

    @RequestMapping("/change")
	public void change(HttpServletRequest request, HttpServletResponse response) {
		String items = request.getParameter("changedata");// System.out.println(items);
		System.out.println(items.substring(4));
		String data[] = items.substring(4).split(",");
		long isbn0 = Long.parseLong(data[0]);
		long isbn = Long.parseLong(data[1]);
		String title = data[2];
		int AuthorID = Integer.parseInt(data[3]);
		String Publisher = data[4];
		String PublisherDate = data[5];
		int Price = Integer.parseInt(data[6]);
		
		String driver = "com.mysql.jdbc.Driver";
		  //String driver = "com.mysql.jdbc.Driver";
  	String user = System.getenv("ACCESSKEY");
  	String password = System.getenv("SECRETKEY");
  	String url = String.format("jdbc:mysql://%s:%s/%s", System.getenv("MYSQL_HOST"), System.getenv("MYSQL_PORT"),System.getenv("MYSQL_DB"));
		try {
			// 加载驱动程序
			Class.forName(driver).newInstance();
			
			// 连续数据库
			Connection conn = DriverManager.getConnection(url, user, password);
			if (!conn.isClosed())
				System.out.println("connecting to the database successfully!");
			String sql = "delete from book where ISBN=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setLong(1,isbn0);
			pst.executeUpdate();
			sql = "INSERT INTO book"+ " VALUES ("+isbn+",'"+title+"',"+AuthorID+",'"+Publisher+  
                    "','"+PublisherDate+"',"+Price+")";
			Statement sta= conn.createStatement();
			sta.executeUpdate(sql);
			sta.close();
			conn.close();
			pst.close();
			
		} catch (ClassNotFoundException e) {
			System.out.println("sorry, can't find the driver!");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}	
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping("/add")
	public void add(HttpServletRequest request, HttpServletResponse response) {
    	flag = 1;
		String items = request.getParameter("adddata");
		System.out.println(items);
		JSONObject obj = new JSONObject();
		String data[] = items.split(",");
		long isbn = Long.parseLong(data[0]);
		String title = data[1];
		int AuthorID = Integer.parseInt(data[2]);
		String Publisher = data[3];
		String PublisherDate = data[4];
		int Price = Integer.parseInt(data[5]);
		
		String driver = "com.mysql.jdbc.Driver";
		  //String driver = "com.mysql.jdbc.Driver";
  	String user = System.getenv("ACCESSKEY");
  	String password = System.getenv("SECRETKEY");
  	String url = String.format("jdbc:mysql://%s:%s/%s", System.getenv("MYSQL_HOST"), System.getenv("MYSQL_PORT"),System.getenv("MYSQL_DB"));
		try {
			// 加载驱动程序
			Class.forName(driver).newInstance();
			
			// 连续数据库
			Connection conn = DriverManager.getConnection(url, user, password);
			if (!conn.isClosed())
				System.out.println("connecting to the database successfully!");
			String sql;
			sql = "INSERT INTO book"+ " VALUES ("+isbn+",'"+title+"',"+AuthorID+",'"+Publisher+  
                    "','"+PublisherDate+"',"+Price+")";
			Statement sta= conn.createStatement();
			sta.executeUpdate(sql);
			sta.close();
			
			sql = "select Name from author where AuthorID=?";
			PreparedStatement ptmt0 = conn.prepareStatement(sql);
			ptmt0.setInt(1, AuthorID);
			ResultSet rs0 = ptmt0.executeQuery();
			if(!rs0.next())flag = 0;
			conn.close();
			
		} catch (ClassNotFoundException e) {
			System.out.println("sorry, can't find the driver!");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		obj.put("flag", flag);
		System.out.println(obj);
		try {
			PrintWriter out = response.getWriter();
			out.println(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    @RequestMapping("/author")
	public void author(HttpServletRequest request, HttpServletResponse response) {
		String items = request.getParameter("authordata");// System.out.println(items);
		System.out.println(items);
		String data[] = items.split(",");
		int id = Integer.parseInt(data[0]);
		String Name = data[1];
		int Age = Integer.parseInt(data[2]);
		String Country = data[3];
		
		
		String driver = "com.mysql.jdbc.Driver";
		  //String driver = "com.mysql.jdbc.Driver";
  	String user = System.getenv("ACCESSKEY");
  	String password = System.getenv("SECRETKEY");
  	String url = String.format("jdbc:mysql://%s:%s/%s", System.getenv("MYSQL_HOST"), System.getenv("MYSQL_PORT"),System.getenv("MYSQL_DB"));
		try {
			// 加载驱动程序
			Class.forName(driver).newInstance();
			
			// 连续数据库
			Connection conn = DriverManager.getConnection(url, user, password);
			if (!conn.isClosed())
				System.out.println("connecting to the database successfully!");
			String sql = "INSERT INTO author"+ " VALUES ("+id+",'"+Name+"',"+Age+",'"+Country+"')";
			Statement sta= conn.createStatement();
			sta.executeUpdate(sql);
			sta.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			System.out.println("sorry, can't find the driver!");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}	
    }
}