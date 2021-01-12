package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import connection.ConnectionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Stock;

public class StockDAO {

	//*******************************
    //SELECT a Product
    //*******************************
	public static Stock searchStock(String prodId) throws SQLException, ClassNotFoundException{
		//Declare a SELECT statement
		String selectStmt = "SELECT * FROM stock WHERE id_produs="+prodId;
		
		//Execute SELECT statement
		try{
			//Get ResultSet from dbExecuteQuery method
			ResultSet rsStock = ConnectionFactory.dbExecuteQuery(selectStmt);
			
			//Send ResultSet to the getStockFromResultSet method and get stock object
			Stock stock = getStockFromResultSet(rsStock);
			
			//return stock object
			return stock;
		} catch (SQLException e){
			System.out.println("While searching a product with" + prodId + " id, an error occurred: " + e);
			//Return exception
			throw e;
		}
	}
	
	//Use ResultSet from DB as parameter and set Stock Object's attributes and return stock object
	private static Stock getStockFromResultSet(ResultSet rs) throws SQLException{
		Stock stock = null;
		if(rs.next()){
			stock = new Stock();
			stock.setProductId(rs.getInt("ID_PRODUS"));
			stock.setDenumire(rs.getString("DENUMIRE"));
			stock.setCantitate(rs.getInt("CANTITATE"));
		}
		return stock;
	}
	
	//*******************************
    //SELECT Products
    //*******************************
	public static ObservableList<Stock> searchProducts () throws SQLException, ClassNotFoundException {
		//Declare a SELECT statement
		String selectStmt = "SELECT * FROM stock";
		//Execute SELECT statement
		try{
			//Get ResultSet from dbExecuteQuery method
			ResultSet rsProds = ConnectionFactory.dbExecuteQuery(selectStmt);
			//Send ResultSet to the getProductList method and get product object
			ObservableList<Stock> stockList = getStockList(rsProds);
			
			//Return product object
			return stockList;
		}catch (SQLException e) {
			System.out.println("SQL select operation has been failed: " + e);
			//Return exception
			throw e;
		}
	}
	
	//Select * from Stock operation
	private static ObservableList<Stock> getStockList(ResultSet rs) throws SQLException, ClassNotFoundException {
		//Declare a observable List which comprises of Stock objects
		ObservableList<Stock> stockList = FXCollections.observableArrayList();
		
		while(rs.next()){
			Stock stock = new Stock();
			stock.setProductId(rs.getInt("ID_PRODUS"));
			stock.setDenumire(rs.getString("DENUMIRE"));
			stock.setCantitate(rs.getInt("CANTITATE"));
			//Add product to the ObservableList
			stockList.add(stock);
		}
		//return stockList(ObservableList of Products)
		return stockList;
	}
	
	 //*************************************
    //UPDATE a product's amount
    //*************************************
	public static void updateStockAmount (String prodId,String cantitate) throws SQLException, ClassNotFoundException {
		//Declare a UPDATE statement
		String updateStmt = "UPDATE stock SET CANTITATE = '"+cantitate+"' WHERE ID_PRODUS = '"+prodId+"'";
		//Execute UPDATE operation
		try{
			ConnectionFactory.dbExecuteUpdate(updateStmt);
		} catch (SQLException e){
			System.out.print("Error occurred while UPDATE Operation: " + e);
			throw e;
		}
	}
	
    //*************************************
    //DELETE a product
    //*************************************
	public static void deleteProdWithId(String prodId) throws SQLException, ClassNotFoundException {
		//Declare a DELETE statement
		String deleteStmt = "DELETE FROM stock WHERE id_produs ="+prodId;
		
		//Execute DELETE operation
		try{
			ConnectionFactory.dbExecuteUpdate(deleteStmt);
		}catch(SQLException e){
			System.out.print("Error occurred while DELETE Operation: " + e);
			throw e;
		}
	}
	  //*************************************
    //INSERT a product
    //*************************************
	
	public static void insertProd(String denumire,String cantitate) throws SQLException, ClassNotFoundException{
		//Declare a INSERT statement
		String insertStmt = "INSERT INTO stock(DENUMIRE,CANTITATE) VALUES ('"+denumire+"','"+cantitate+"')";
		//Execute INSERT operation
		try{
			ConnectionFactory.dbExecuteUpdate(insertStmt);
		}catch (SQLException e){
			System.out.print("Error occurred while INSERT Operation: " + e);
			throw e;
		}
	}
}