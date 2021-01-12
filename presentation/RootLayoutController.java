package presentation;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RootLayoutController {
	
	
	//Exit the program
	public void handleExit(ActionEvent actionEvent){
		System.exit(0);
	}
	
	//Help Menu button behavior
	public void handleHelp(ActionEvent actionEvent){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Program Information");
		alert.setHeaderText("Aplicatie test BD Dragos!");
		alert.setContentText("You can search,delete,update,insert some data with this program.");
		alert.show();
	}
	
	public void handleClient(ActionEvent actionEvent) throws IOException{
		Stage stage1 = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/presentation/ClientView.fxml"));
		Scene scene = new Scene(root,685,348);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		stage1.setScene(scene);
		stage1.setTitle("Client");
		stage1.show();
	}
	
	public void handleOrder(ActionEvent actionEvent) throws IOException{
		Stage stage2 = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/presentation/OrderView.fxml"));
		Scene scene = new Scene(root,685,348);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		stage2.setScene(scene);
		stage2.setTitle("Order");
		stage2.show();
	}
	
	public void handleProduct(ActionEvent actionEvent) throws IOException{
		Stage stage3 = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/presentation/ProductView.fxml"));
		Scene scene = new Scene(root,685,348);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		stage3.setScene(scene);
		stage3.setTitle("Product");
		stage3.show();
	}
	
	public void handleStock(ActionEvent actionEvent) throws IOException{
		Stage stage4 = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/presentation/ProductView.fxml"));
		Scene scene = new Scene(root,685,348);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		stage4.setScene(scene);
		stage4.setTitle("Stock for Product");
		stage4.show();
	}
}
