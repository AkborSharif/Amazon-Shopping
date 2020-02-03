import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class amazon {
	static Scanner input = new Scanner(System.in);
	public static void printTable(Statement statement) {
		ResultSet rs;
		try {
			rs = statement.executeQuery("select * from customer;"); //running the queries that selects all the atttributes from customer table
		/*
		 * printing out custoemr data
		 */
		while (rs.next()) {
			System.out.print(rs.getString("c_custID"));             
			System.out.print(", ");
			System.out.print(rs.getString("c_name"));
			System.out.println();
		}
		rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void entryInCustomer(Statement statement) {
		Scanner input = new Scanner(System.in);
		String name = "", email = "",  address = "", phone = "";

		
/*
 * taking input from customer
 */
			System.out.print("Name: ");
			name = input.nextLine();
			
			System.out.print("address: ");
			address = input.nextLine();

			System.out.print("email: ");
			email = input.nextLine();

			System.out.print("phone: ");
			phone = input.nextLine();

			/*
			 * technique to generate new id. Since id's are unique we will have to find the id of the last customer and then increment each time we insert an id
			 */
			String[] array = null;
			int size = 0;
			try {
				ResultSet resultSet = statement.executeQuery("select * from customer;");    //running the queries that selects all the attributes from customer table
				while (resultSet.next()) {
					size++;                                   //size increments until the customer table reaches to the last column to get the (Number of column)
				}

				array = new String[size];    //an array with size of the number of columns to keep all the existing ID from customer table
				resultSet.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			try {

				ResultSet rs = statement.executeQuery("select * from customer;");
				int k = 0;
				while (rs.next()) {
					array[k] = rs.getString("c_custID");         //copying the existing customer id to the array to see what is the last id
					k++;									
				}

				for (int j = 0; j < array.length - 1; j++) {
					if (array[0] != null && array[j].equals(k)) {
						k++;                        //we increment the k if the array contains values as k
					}
				}
					
				/*
				 * insert queries to insert the data into the customer table with an unique id 
				 */
					statement.executeUpdate("insert into customer values(" + "'" + (k + 1) + "'" + ", '" + name
							+ "', '" + address + "','" + email + "','" + phone  + "'"
							+ ");");
				
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		
	}
	public static void entryInSeller(Statement statement) {
		Scanner input = new Scanner(System.in);
		String name = "", email = "",  address = "", phone = "";

		/*
		 * insertion for seller and buyer are almost the same and also takes in the same datas
		 */

			System.out.print("Name: ");
			name = input.nextLine();
			
			System.out.print("address: ");
			address = input.nextLine();

			System.out.print("email: ");
			email = input.nextLine();

			System.out.print("phone: ");
			phone = input.nextLine();

			/*
			 * technique to generate new id. Since id's are unique we will have to find the id of the last seller and then increment each time we insert an id
			 */
			String[] array = null;
			int size = 0;
			try {
				ResultSet resultSet = statement.executeQuery("select * from seller;");
				while (resultSet.next()) {
					size++;
				}

				array = new String[size];
				resultSet.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			try {

				ResultSet rs = statement.executeQuery("select * from seller;");
				int k = 0;
				while (rs.next()) {
					array[k] = rs.getString("s_sellerID");
					k++;
				}

				for (int j = 0; j < array.length - 1; j++) {
					if (array[0] != null && array[j].equals(k)) {
						k++;
					}
				}

					statement.executeUpdate("insert into seller values(" + "'" + (k + 1) + "'" + ", '" + name
							+ "', '" + address + "','" + email + "','" + phone  + "'"
							+ ");");
				
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
	}
	
	public static void insertItem(Statement statement) {
		Scanner input = new Scanner(System.in);
		String model= "", shipingStatus = "available", availablility = "yes";
		double price = 0, productionYear = 0000;
		//shiping status: available
		
/*
 * taking input for the item tables. this also has almost the same implementation as seller and customer
 */
			System.out.print("Model: ");
			model = input.nextLine();
			
			System.out.print("Price: ");
			price = input.nextDouble();

			System.out.print("ProductionYear: ");
			productionYear = input.nextDouble();
			
			String i_model = "";
			double i_itemID = 0;
			
			/*
			 * technique to generate new id. Since id's are unique we will have to find the id of the last item and then increment each time we insert an id
			 */
				String[] array = null;
				int size = 0;
				try {
					ResultSet resultSet = statement.executeQuery("select * from items;");
					while (resultSet.next()) {
						size++;
					}

					array = new String[size];
					resultSet.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				try {

					ResultSet rs = statement.executeQuery("select * from items;");
					int k = 0;
					while (rs.next()) {
						array[k] = rs.getString("i_itemID");
						k++;
					}

					for (int j = 0; j < array.length - 1; j++) {
						if (array[0] != null && array[j].equals(k)) {
							k++;
						}
					}

					statement.executeUpdate("insert into items values(" + "'" + price + "'" + ", '" + (k+1)
							+ "', '" + model + "','" + shipingStatus  + "','" + productionYear  + "','" + availablility  + "'"
							+ ");");
				
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				}
	}
	public static void viewItem(Statement statement) {
		ResultSet rs;
		try {
			/*
			 *printing items that are available online
			 */
			rs = statement.executeQuery("select * from items where available = 'yes'");
			System.out.print("\t\t");
			System.out.print("Items");
			System.out.print("\t\t");
			System.out.print("Price");
			System.out.print("\t\t");
			System.out.print("Production Year" + "\n");
			
		while (rs.next()) {
			/*
			 * printing out model, price, production year from the items table
			 */
			System.out.print("\t\t");
			System.out.print(rs.getString("I_model"));
			System.out.print("\t\t");
			System.out.print("$");
			System.out.print(rs.getString("I_price"));
			System.out.print("\t\t");
			System.out.print(rs.getString("I_productionYear"));
			System.out.println();
		}
		rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void makeOrders(Statement statement) {
		Scanner input = new Scanner(System.in);
		String model= "";
		double price = 0 , number = 0;
	    ResultSet rs = null;
			System.out.print("Model: ");
			model = input.nextLine();
			
			try {
				/*
				 * looking for model and the price of the model from the items table that the customer is searching for
				 */
				rs = statement.executeQuery("select i_model, i_price from items where i_model = '" + model + "' and available = 'yes' "+ ";");
				
				while (rs.next()) {
					model = rs.getString("i_model");
					price = rs.getDouble("i_price");
				}
			
			System.out.println("Price of " + model + " is $" + price + "\n");  //printing the price of the model that the customer looking for
			System.out.print("Would you like to place an order (1:YES  2:NO): "); //1 for ordering the item and 2 for canceling 
			number = input.nextDouble();
		/*
		 * if number is one then customer buys the item so we change the availability of the model to no  and print out the total
		 */
			if (number == 1) {
			 statement.executeUpdate("update items set available = 'no' where i_model = '" + model + "';");
			 System.out.print("Your Total: " + price);
			}
			
			/*
			 * else we print out the message 
			 */
			else {
				System.out.println("Thank you for looking");
			}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
	}
	public static void sellItem(Statement statement) {
		Scanner input = new Scanner(System.in);
		String model= "";

	    ResultSet rs = null;
			System.out.print("Model: ");
			model = input.nextLine();
			String i_model;
			String availability = "";
			try {
				/*
				 * getting the model and price from the items that needs to be sold and check if its available
				 */
				rs = statement.executeQuery("select i_model, available from items where i_model = '" + model + "' and available = 'yes' "+ ";");
				while (rs.next()) {
					i_model = rs.getString("i_model");
					availability = rs.getString("available");
				}
			
			//	System.out.println("test");
				/*
				 * now that the item is sold we need to update the availability of the model to no 
				 */
			 statement.executeUpdate("update items set available = 'no' where i_model = '" + model + "';");
			 
			 System.out.println("Item Sold!!!!!");   //and then print out the text
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
	}
	public static void deleteBuyer(Statement statement) {
		Scanner input = new Scanner(System.in);
		String name = "";

	   /*
	    * name of the customer that needs to delete its id
	    */
			System.out.print("Customer name To delete: ");
			name = input.nextLine();
			name = name.toUpperCase();
			
			try {
				/*
				 * delete the customer tuple that needs to be deleted 
				 */
				statement.executeUpdate("delete from customer where c_name = '" + name + "' ;" );
			 
			 System.out.println( name + "'s account has been deleted"); //print the text to confirm the tuple is deleted
			
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	public static void deleteSeller(Statement statement) {
		Scanner input = new Scanner(System.in);
		String name = "";

		  /*
		    * name of the seller that needs to delete its id
		    */
			System.out.print("Seller name To delete: ");
			name = input.nextLine();
			name = name.toUpperCase();
			
			try {
				/*
				 * delete the seller tuple that needs to be deleted 
				 */
				statement.executeUpdate("delete from seller where s_name = '" + name + "' ;" );
			 
			 System.out.println( name + "'s account has been deleted");
			
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	public static void main(String[] args) {
	
		Connection connection = null;                                                // Create an instance of the class Connection
		try {
			String url = "jdbc:sqlite:/Users/mohammadsharif/Documents/SQLite/Amazon.db";
			connection = DriverManager.getConnection(url);                          // connect to the SQLite database.
			Statement statement = connection.createStatement();                     // main object that controls the database is a statement
			/*
			 * Taking inputs(ID) from the users 
			 */
			System.out.println("∙Create an account  	              1: Customer  ID: 1  "+ "\n"
					+ "				      2: Seller    ID: 2 "              + "\n"
					 + "∙Insert Item                                       ID: 3"+ "\n"		
							 + "∙View Available Item                               ID: 4"+ "\n"
							 + "∙Make Orders                                       ID: 5"+ "\n"
							 + "∙Sell Item                                         ID: 6"+ "\n"
							 + "∙Delete Account                       7: Customer  ID: 7" +  "\n" + 
							 "				      8: Seller    ID: 8 " + "\n"
							 + "∙Exit                                              ID: 9"+ "\n");
			int id;
			System.out.println();
			System.out.print("Put an ID: ");
			id = input.nextInt();
			System.out.println();
			
			
			while(id != 0) {
			switch (id) {
			case 1:
				entryInCustomer(statement);         //insert into customer table
				break;
			case 2:
				entryInSeller(statement);           //insert into seller table
			case 3:
				 insertItem(statement);				 //insert into items table
				break;
			case 4:
				 viewItem(statement);                //view the available items
				break;
			case 5:
				 makeOrders(statement);            	 //make an order 
				break;
			case 6:
				sellItem(statement);				// sell an item
				break;
			case 7:
				deleteBuyer(statement);				//delete the customer account
				break;
			case 8:
				deleteSeller(statement);				// delete the seller account
				break;
			default:
				System.out.println("Program Ended");
				return;
			}
			System.out.println();
			System.out.print("Put an ID: ");
			id = input.nextInt();
			System.out.println();
		}
			connection.close();
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}