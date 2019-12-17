
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;



public class Java_SQL_ClothesShop {

	static Connection conn = null;
	
	public static void main(String[] args) throws SQLException {
		
		System.out.println("Connecting to database...");
		conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-V18U6ND:1433;databaseName=ClothesShop;user=[username];password=[password];");  //Connect to a database
		System.out.println("Connected to database.");
		
		System.out.println("");
        System.out.println("");
        System.out.println("");
		
		try { 
			//Select all items from the table "ClothesInventory"
	        Statement itemStmt = conn.createStatement();
	        String itemQuery = "SELECT item FROM ClothesInventory";
	        ResultSet itemsSet = itemStmt.executeQuery(itemQuery);
	        
	        //Select all quantities from the table "ClothesInventory"
	        Statement quantityStmt = conn.createStatement();
	        String quantityQuery = "SELECT quantity FROM ClothesInventory";
	        ResultSet quantitiesSet = quantityStmt.executeQuery(quantityQuery);
	        
	        //Print out all items and their respective quantities
	        while(itemsSet.next() && quantitiesSet.next()) {
	        	System.out.println(itemsSet.getString("item") + " x " + quantitiesSet.getInt("quantity"));
	        }
	        
	        System.out.println("");
	        System.out.println("");
	        System.out.println("");
	        
	        purchaseItem("Blue shirt", 2);  //Execute the purchaseItem() function to purchase 2 Blue shirts.
	        returnItem("Dark Green shirt", 3);  //Execute the returnItem() function to return 3 Dark Green shirts.
	        
	        System.out.println("Closing connection...");
	        conn.close();
	        System.out.println("Connection closed.");
		}
		catch (SQLException ex1) {
	        ex1.printStackTrace();
		}
	}
	
	
	
	/*	The purchaseItem() function takes in 2 inputs: the name of an item and the quantity of the item you would like to purchase.
			This function will decrease the current quantity of the item by the quantity you want to purchase.
			After this transaction, the printClothesInventory() function will display the current data from the ClothesInventory table.  */
	public static void purchaseItem(String itemName, int quantity) {
		try {
			Statement purchaseItemsStmt = conn.createStatement();
			String purchaseItemsQuery = "UPDATE ClothesInventory SET quantity = ((SELECT quantity FROM ClothesInventory WHERE item = '" + itemName + "') - " + quantity + ") WHERE item = '" + itemName + "';";
			System.out.println("purchaseItemsQuery: " + purchaseItemsQuery);
			purchaseItemsStmt.executeUpdate(purchaseItemsQuery);
			
			System.out.println("");
	        System.out.println("");
	        System.out.println("");
	        
	        printClothesInventory();
		}
		catch (SQLException ex2) {
	        ex2.printStackTrace();
		}
	}
	
	/*	The returnItem() function takes in 2 inputs: the name of an item and the quantity of the item you would like to return.
			This function will increase the current quantity of the item by the quantity you want to return.
			After this transaction, the printClothesInventory() function will display the current data from the ClothesInventory table.  */
	public static void returnItem(String itemName, int quantity) {
		try {
			Statement returnItemsStmt = conn.createStatement();
			String returnItemsQuery = "UPDATE ClothesInventory SET quantity = ((SELECT quantity FROM ClothesInventory WHERE item = '" + itemName + "') + " + quantity + ") WHERE item = '" + itemName + "';";
			System.out.println("returnItemsQuery: " + returnItemsQuery);
			returnItemsStmt.executeUpdate(returnItemsQuery);
			
			System.out.println("");
	        System.out.println("");
	        System.out.println("");
	        
	        printClothesInventory();
		}
		catch (SQLException ex2) {
	        ex2.printStackTrace();
		}
	}
	
	/*	The printClothesInventory() function does not take in any inputs.
			This function retrieves all of the data from the ClothesInventory table and displays it.  */
	public static void printClothesInventory() {
		try {
			Statement allStmt = conn.createStatement();
	        String allQuery = "SELECT * FROM ClothesInventory";
	        ResultSet allSet = allStmt.executeQuery(allQuery);
	        
	        while(allSet.next()) {
	        	System.out.println(allSet.getString("item") + " x " + allSet.getInt("quantity"));
	        }
	        
	        System.out.println("");
	        System.out.println("");
	        System.out.println("");
		}
		catch (SQLException ex3) {
	        ex3.printStackTrace();
		}
	}
	
}
