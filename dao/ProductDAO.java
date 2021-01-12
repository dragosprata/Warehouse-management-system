package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import connection.ConnectionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Product;


public class ProductDAO {
	
    //*******************************
    //SELECT a Product
    //*******************************
	public static Product searchProduct (String prodId) throws SQLException, ClassNotFoundException {
		//Declare a SELECT statement
		String selectStmt = "SELECT * FROM product WHERE id_produs="+prodId;
		
		//Execute SELECT statement
		try{
			//Get ResultSet from dbExecuteQuery method
			ResultSet rsProd = ConnectionFactory.dbExecuteQuery(selectStmt);
			
			//Send ResultSet to the getProductFromResultSet method and get product object
			Product product = getProductFromResultSet(rsProd);
			
			//Return product object
			return product;
		}catch (SQLException e){
			System.out.println("While searching a product with" + prodId + " id, an error occurred: " + e);
			//Return exception
			throw e;
		}
	}
	
	//Use ResultSet from DB as parameter and set Product Object's attributes and return product object.
	private static Product getProductFromResultSet(ResultSet rs) throws SQLException{
		Product product = null;
		if(rs.next()){
			product = new Product();
			product.setProductId(rs.getInt("ID_PRODUS"));
			product.setDenumire(rs.getString("DENUMIRE"));
			product.setProducator(rs.getString("PRODUCATOR"));
			product.setPrice(rs.getInt("PRET"));
			product.setCantitate(rs.getInt("CANTITATE"));
		}
		return product;
	}
	//*******************************
    //SELECT Products
    //*******************************
	public static ObservableList<Product> searchProducts() throws SQLException, ClassNotFoundException {
		//Declare a SELECT statement
		String selectStmt = "SELECT * FROM product";
		//Execute SELECT statement
		try{
			//Get ResultSet from dbExecuteQuery method
			ResultSet rsProds = ConnectionFactory.dbExecuteQuery(selectStmt);
			//Send ResultSet to the getProductList method and get product object
			ObservableList<Product> prodList = getProductList(rsProds);
			//Return product object
			return prodList;
		}catch (SQLException e) {
			System.out.println("SQL select operation has been failed: " + e);
			//Return exception
			throw e;
		}
	}
	
	//Select * from products operation
	private static ObservableList<Product> getProductList(ResultSet rs) throws SQLException, ClassNotFoundException{
		//Declare a observable List which comprises of Product objects
		ObservableList<Product> prodList = FXCollections.observableArrayList();
		while(rs.next()){
			Product product = new Product();
			product.setProductId(rs.getInt("ID_PRODUS"));
			product.setDenumire(rs.getString("DENUMIRE"));
			product.setProducator(rs.getString("PRODUCATOR"));
			product.setPrice(rs.getInt("PRET"));
			product.setCantitate(rs.getInt("CANTITATE"));
			//Add product to the ObservableList
			prodList.add(product);
		}
		//return prodList (ObservableList of Products)
		return prodList;
	}
	
    //*************************************
    //UPDATE a product's price
    //*************************************
	public static void updateProdPrice(String prodId,String prodPrice) throws SQLException, ClassNotFoundException {
		//Declare a UPDATE statement
		String updateStmt = "UPDATE product SET PRET='"+prodPrice+"' WHERE ID_PRODUS = '"+prodId+"'";
		//Execute UPDATE operation
		try{
			ConnectionFactory.dbExecuteUpdate(updateStmt);
		}catch (SQLException e){
			System.out.print("Error occurred while UPDATE Operation: " + e);
			throw e;
		}
	}
	
	 //*************************************
    //UPDATE a product's cantitate
    //*************************************
public static void updateProdCantitate(String prodId, String prodCantitate) throws SQLException, ClassNotFoundException {
	//Declare a UPDATE statement
	String updateStmt = "UPDATE product SET CANTITATE='"+prodCantitate+"' WHERE ID_PRODUS = '"+prodId+"'";
			//Execute UPDATE operation
			try{
				ConnectionFactory.dbExecuteUpdate(updateStmt);
			}catch (SQLException e){
				System.out.print("Error occurred while UPDATE Operation: " + e);
				throw e;
			}
		
	}
	
    //*************************************
    //DELETE a product
    //*************************************
	public static void deleteProdWithId(String prodId) throws SQLException, ClassNotFoundException{
		//Declare a DELETE statement
		String deleteStmt = "DELETE FROM product WHERE id_produs ="+prodId;
		//Execute DELETE operation
		try{
			ConnectionFactory.dbExecuteUpdate(deleteStmt);
		}catch (SQLException e){
			System.out.print("Error occurred while DELETE Operation: " + e);
			throw e;
		}
	}
	
	 //*************************************
    //INSERT a product
   //*************************************
	public static void insertProd(String denumire,String producator,String pret) throws SQLException, ClassNotFoundException{
		//Declare a insert statement
		String insertStmt = "INSERT INTO product (DENUMIRE,PRODUCATOR,PRET) VALUES ('"+denumire+"','"+producator+"','"+pret+"')";
		//Execute INSERT operation
		try{
			ConnectionFactory.dbExecuteUpdate(insertStmt);
		}catch (SQLException e) {
			System.out.print("Error occurred while INSERT Operation: " + e);
			throw e;
		}
	}
}
