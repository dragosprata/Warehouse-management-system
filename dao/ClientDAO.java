package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import connection.ConnectionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Client;

public class ClientDAO {

	//*******************************
    //SELECT a Client
    //*******************************
	public static Client searchClient(String clientId) throws SQLException,ClassNotFoundException{
		//Declare a SELECT statement
		String selectStmt = "SELECT * FROM client WHERE id_client="+clientId;
		
		//Execute SELECT statement
		try{
			//Get ResultSet from dbExecuteQuery method
			ResultSet rsClient = ConnectionFactory.dbExecuteQuery(selectStmt);
			
			//Send ResultSet to the getClientFromResultSet method and get client object
			Client client = getClientFromResultSet(rsClient);
			
			//Return client object
			return client; 
		}catch(SQLException e){
			System.out.println("While searching an client with " + clientId + " id, an error occurred: " + e);
			//Return exception
			throw e;
		}
	}
	
	//Use ResultSet from DB as parameter and set Client Object's attributes and return client object.
	private static Client getClientFromResultSet(ResultSet rs) throws SQLException{
		Client client = null;
		if(rs.next()){
			client = new Client();
			client.setClientId(rs.getInt("ID_CLIENT"));
			client.setNume(rs.getString("NUME"));
			client.setPrenume(rs.getString("PRENUME"));
			client.setVarsta(rs.getInt("VARSTA"));
		}
		return client;
	}
	
	//*******************************
    //SELECT Clients
    //*******************************
	public static ObservableList<Client> searchClients () throws SQLException, ClassNotFoundException {
		//Declare a SELECT statement
		String selectStmt = "SELECT * FROM client";
		
		//Execute SELECT statement
		try{
			//Get ResultSet from dbExecuteQuery method
			ResultSet rsClients = ConnectionFactory.dbExecuteQuery(selectStmt);
			
			//Send ResultSet to the getClientList method and get client object
			ObservableList<Client> clientList = getClientList(rsClients);
			
			//Return client object
			return clientList;
		}catch (SQLException e){
			System.out.println("SQL select operation has been failed: " + e);
			//Return exception
			throw e;
		}
	}
	
	//Select * from clients operation
	private static ObservableList<Client> getClientList(ResultSet rs) throws SQLException,ClassNotFoundException{
		//Declare a observable List which comprises of Client objects
		ObservableList<Client> clientList = FXCollections.observableArrayList();
		
		while (rs.next()){
			Client client = new Client();
			client.setClientId(rs.getInt("ID_CLIENT"));
			client.setNume(rs.getString("NUME"));
			client.setPrenume(rs.getString("PRENUME"));
			client.setVarsta(rs.getInt("VARSTA"));
			clientList.add(client);
		}
		//return clientList (ObservableList of Clients)
		return clientList;
	}
	
	 //*************************************
    //UPDATE a client's age
    //*************************************
	
	public static void updateClientAge(String clientId,String clientAge) throws SQLException, ClassNotFoundException {
		//Declare a UPDATE statement
		String updateStmt = "UPDATE client SET VARSTA='"+clientAge+"' WHERE ID_CLIENT = '"+clientId+"'";
		//Execute UPDATE operation
		try{
			ConnectionFactory.dbExecuteUpdate(updateStmt);
		}catch (SQLException e) {
			System.out.print("Error occurred while UPDATE Operation: " + e);
			throw e;
		}
	}
	
	//*************************************
    //DELETE a client
    //*************************************
	public static void deleteClientWithId(String clientId) throws SQLException, ClassNotFoundException {
		//Declare a DELETE statement
		String deleteStmt = "DELETE FROM client WHERE id_client ="+clientId;
		//Execute DELETE operation
		try{
			ConnectionFactory.dbExecuteUpdate(deleteStmt);
		} catch (SQLException e){
			System.out.print("Error occurred while DELETE Operation: " + e);
			throw e;
		}
	}
	
    //*************************************
    //INSERT a client
    //*************************************
	
	public static void insertClient (String nume,String prenume,String varsta) throws SQLException, ClassNotFoundException{
		//Declare a INSERT statement
		String insertStmt = "INSERT INTO client (NUME,PRENUME,VARSTA) VALUES ('"+nume+"','"+prenume+"','"+varsta+"')";
		
		//Execute INSERT operation
		try{
			ConnectionFactory.dbExecuteUpdate(insertStmt);
		} catch (SQLException e){
			System.out.print("Error occurred while INSERT Operation: " + e);
			throw e;
		}
	}
}
