package presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import model.Client;
import dao.ClientDAO;

import java.sql.SQLException;

public class ClientController {
	
	@FXML
	private TextArea resultArea;
	@FXML
	private TextField numeText;
	@FXML
	private TextField prenumeText;
	@FXML
	private TextField varstaText;
	@FXML
	private TextField clientIdText;
	@FXML 
	private TextField newAgeText;
	@FXML
	private TableView clientTable;
	@FXML
	private TableColumn<Client,Integer> clientIdColumn;
	@FXML
	private TableColumn<Client,String> clientNumeColumn;
	@FXML
	private TableColumn<Client,String> clientPrenumeColumn;
	@FXML
	private TableColumn<Client,Integer> clientVarstaColumn;
	
	//Search a client
	@FXML
	private void searchClient(ActionEvent actionEvent) throws ClassNotFoundException,SQLException{
		try{
		//Get client information
		Client client = ClientDAO.searchClient(clientIdText.getText());
		//Populate Client on TableView and Display on TextArea
		populateAndShowClient(client);
	}catch (SQLException e){
		e.printStackTrace();
		resultArea.setText("Error occurred while getting client information from DB.\n" + e);
		throw e;
	}
}
	
	//Search all clients
	@FXML
	private void searchClients(ActionEvent actionEvent) throws SQLException,ClassNotFoundException{
		try{
			//Get all Clients information
			ObservableList<Client> clientData = ClientDAO.searchClients();
			//Populate Clients on TableView
			populateClients(clientData);
		} catch (SQLException e){
			System.out.println("Error occurred while getting clients information from DB.\n" + e);
			throw e;
		}
	}
	
	//Initializing the controller class.
	// //This method is automatically called after the fxml file has been loaded.
	@FXML
	private void initialize(){
		clientIdColumn.setCellValueFactory(cellData -> cellData.getValue().clientIdProperty().asObject());
		clientNumeColumn.setCellValueFactory(cellData -> cellData.getValue().numeProperty());
		clientPrenumeColumn.setCellValueFactory(cellData -> cellData.getValue().prenumeProperty());
		clientVarstaColumn.setCellValueFactory(cellData -> cellData.getValue().varstaProperty().asObject());
	}
	
	//Populate Client
	@FXML
	private void populateClient(Client client) throws ClassNotFoundException{
		//Declare and ObservableList for table view
		ObservableList<Client> clientData = FXCollections.observableArrayList();
		//Add client to the ObservableList
		clientData.add(client);
		//Set items to the clientTable
		clientTable.setItems(clientData);
	}
	
	//Set Client information to Text Area
	@FXML
	private void setClientInfoToTextArea(Client client){
		resultArea.setText("Nume: " + client.getNume() + "\n" + "Prenume: " + client.getPrenume());
	}
	
	//Populate Client for TableView and Display Client on TextArea
	@FXML
	private void populateAndShowClient(Client client) throws ClassNotFoundException{
		if(client != null){
			populateClient(client);
			setClientInfoToTextArea(client);
		}else{
			resultArea.setText("This client does not exist!\n");
		}
	}
	
	//Populate Clients for TableView 
	@FXML
	private void populateClients(ObservableList<Client> clientData) throws ClassNotFoundException{
		//Set items to the clientTable
		clientTable.setItems(clientData);
	}
	
	//Update client's age with the age which is written on newAgeText field
	@FXML
	private void updateClientAge(ActionEvent actionEvent) throws SQLException,ClassNotFoundException {
		try{
			ClientDAO.updateClientAge(clientIdText.getText(),newAgeText.getText() + "\n");
		}catch (SQLException e){
			resultArea.setText("Problem occurred while updating age: " + e);
		}
	}
	
	//Insert a client to the DB
	@FXML
	private void insertClient(ActionEvent actionEvent) throws SQLException,ClassNotFoundException{
		try{
			ClientDAO.insertClient(numeText.getText(), prenumeText.getText(),varstaText.getText());
			resultArea.setText("Client inserted!\n");
		}catch (SQLException e){
			resultArea.setText("Problem occurred while inserting client " + e);
			throw e;
		}
	}
	
	//Delete a client with a given client Id from DB
	@FXML
	private void deleteClient(ActionEvent actionEvent) throws SQLException,ClassNotFoundException{
		try{
			ClientDAO.deleteClientWithId(clientIdText.getText());
			resultArea.setText("Client deleted!Client id: "+ clientIdText.getText() + "\n");
		}catch(SQLException e){
			resultArea.setText("Problem occurred while deleting client " + e);
			throw e;
		}
	}
}
