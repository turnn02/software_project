package com.mkyong.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DB {

	public static User isSuccessfulLogin(String userName, String password) {
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost/mysotd?" + "user=root&password=admin");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery(
					"select * from mysotd.user where name = '" + userName + "' and password = '" + password + "'");

			if (!resultSet.next())
				return null;

			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String psswrd = resultSet.getString("password");
			String email = resultSet.getString("email");

			return new User(id, name, psswrd, email);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(resultSet);
			close(statement);
			close(connect);
		}
		return null;
	}

	public static void sendPost(User user, String post, String productName) {
		Connection connect = null;
		Statement statement = null;
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost/mysotd?" + "user=root&password=admin");
			System.out.println("User log in DB.sendPost: " + user);

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			statement.executeUpdate(
					"insert into mysotd.user_post (user_id, post, product_name) VALUES (" + user.getId() + ",'" + post + "', '" + productName + "')");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(statement);
			close(connect);
		}
	}
	
	
	public static void sendProduct(User user, String product) {
		Connection connect = null;
		Statement statement = null;
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost/mysotd?" + "user=root&password=admin");
			System.out.println("User log in DB.sendProduct: " + user);

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			statement.executeUpdate(
					"insert into mysotd.user_product (user_id, product) VALUES (" + user.getId() + ",'" + product + "')");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(statement);
			close(connect);
		}
	}

	private static void close(AutoCloseable autoCloseable) {
		try {
			if (autoCloseable != null) {
				autoCloseable.close();
			}
		} catch (Exception e) {
		}
	}

	public static List<UserPost> getPosts(User user) {
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<UserPost> list = new ArrayList<UserPost>();
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost/mysotd?" + "user=root&password=admin");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			if (user == null) {
				resultSet = statement.executeQuery("select * from mysotd.user_post order by id desc");
			} else {
				resultSet = statement.executeQuery("select * from mysotd.user_post where user_id = " + user.getId() + " order by id desc");
			}

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String post = resultSet.getString("post");
				String productName = resultSet.getString("product_name");
				
				if (user == null) {
					int userId = resultSet.getInt("user_id");
					User usr = findUserById(userId);
					list.add(new UserPost(id, usr, post, productName));
				} else {
					list.add(new UserPost(id, user, post, productName));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(resultSet);
			close(statement);
			close(connect);
		}
		return list;
	}
	
	
	public static List<UserProduct> getProducts(User user) {
		
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<UserProduct> list = new ArrayList<UserProduct>();
		
		if (user == null) {
			return list;
		}
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost/mysotd?" + "user=root&password=admin");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			
			resultSet = statement.executeQuery("select * from mysotd.user_product where user_id = " + user.getId() + " order by id desc");
			
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String product = resultSet.getString("product");
				
				if (user == null) {
					int userId = resultSet.getInt("user_id");
					User usr = findUserById(userId);
					list.add(new UserProduct(id, usr, product));
				} else {
					list.add(new UserProduct(id, user, product));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(resultSet);
			close(statement);
			close(connect);
		}
		return list;
	}

	private static User findUserById(int id) {
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost/mysotd?" + "user=root&password=admin");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select * from mysotd.user where id = " + id);

			if (!resultSet.next())
				return null;
			
			String name = resultSet.getString("name");
			String psswrd = resultSet.getString("password");
			String email = resultSet.getString("email");

			return new User(id, name, psswrd, email);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(resultSet);
			close(statement);
			close(connect);
		}
		return null;
	}

}
