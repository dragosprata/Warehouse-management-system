package presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Product;
import dao.ProductDAO;

import java.sql.SQLException;

public class ProductController {

	 @FXML
	    private TextField denumireText;
	    @FXML
	    private TextArea resultArea;
	    @FXML
	    private TextField producatorText;
	    @FXML
	    private TextField pretText;
	    @FXML
	    private TextField prodIdText;
	    @FXML
	    private TextField newPriceText;
	    @FXML 
	    private TextField newCantitateText;
	    @FXML
	    private TableView productTable;
	    @FXML
	    private TableColumn<Product, Integer>  prodIdColumn;
	    @FXML
	    private TableColumn<Product, String>  prodDenumireColumn;
	    @FXML
	    private TableColumn<Product, String> prodProducatorColumn;
	    @FXML
	    private TableColumn<Product, Integer> prodPretColumn;
	    @FXML
	    private TableColumn<Product, Integer> prodCantitateColumn;

	    //Search a product
	    @FXML
	    private void searchProduct (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
	        try {
	            //Get Product information
	            Product prod = ProductDAO.searchProduct(prodIdText.getText());
	            //Populate Product on TableView and Display on TextArea
	            populateAndShowProduct(prod);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            resultArea.setText("Error occurred while getting product information from DB.\n" + e);
	            throw e;
	        }
	    }

	    //Search all products
	    @FXML
	    private void searchProducts(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
	        try {
	            //Get all Products information
	            ObservableList<Product> prodData = ProductDAO.searchProducts();
	            //Populate Products on TableView
	            populateProducts(prodData);
	        } catch (SQLException e){
	            System.out.println("Error occurred while getting products information from DB.\n" + e);
	            throw e;
	        }
	    }

	    //Initializing the controller class.
	    //This method is automatically called after the fxml file has been loaded.
	    @FXML
	    private void initialize () {
	       
	    	prodIdColumn.setCellValueFactory(cellData -> cellData.getValue().productIdProperty().asObject());
	    	prodDenumireColumn.setCellValueFactory(cellData -> cellData.getValue().denumireProperty());
	    	prodProducatorColumn.setCellValueFactory(cellData -> cellData.getValue().producatorProperty());
	    	prodPretColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
	    	prodCantitateColumn.setCellValueFactory(cellData -> cellData.getValue().cantitateProperty().asObject());
	    }

	    //Populate Product
	    @FXML
	    private void populateProduct (Product prod) throws ClassNotFoundException {
	        //Declare and ObservableList for table view
	        ObservableList<Product> prodData = FXCollections.observableArrayList();
	        //Add product to the ObservableList
	        prodData.add(prod);
	        //Set items to the employeeTable
	        productTable.setItems(prodData);
	    }

	    //Set Product information to Text Area
	    @FXML
	    private void setProdInfoToTextArea ( Product prod) {
	        resultArea.setText("Denumire: " + prod.getDenumire() + "\n" +"Pret: " + prod.getPrice());
	    }

	    //Populate Product for TableView and Display Product on TextArea
	    @FXML
	    private void populateAndShowProduct(Product prod) throws ClassNotFoundException {
	        if (prod != null) {
	            populateProduct(prod);
	            setProdInfoToTextArea(prod);
	        } else {
	            resultArea.setText("This product does not exist!\n");
	        }
	    }

	    //Populate Products for TableView
	    @FXML
	    private void populateProducts (ObservableList<Product> prodData) throws ClassNotFoundException {
	        //Set items to the productTable
	    	productTable.setItems(prodData);
	    }

	    //Update product's price with the price which is written on newPriceText field
	    @FXML
	    private void updateProductPrice (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
	        try {
	            ProductDAO.updateProdPrice(prodIdText.getText(),newPriceText.getText());
	            resultArea.setText("Price has been updated for, product id: " + prodIdText.getText() + "\n");
	        } catch (SQLException e) {
	            resultArea.setText("Problem occurred while updating price: " + e);
	        }
	    }
	    
	    //Update product's cantitate with the cantitate which is written on newCantitateText field
	    @FXML
	    private void updateProductCantitate (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
	        try {
	            ProductDAO.updateProdCantitate(prodIdText.getText(),newCantitateText.getText());
	            resultArea.setText("Cantitate has been updated for, product id: " + prodIdText.getText() + "\n");
	        } catch (SQLException e) {
	            resultArea.setText("Problem occurred while updating cantitate: " + e);
	        }
	    }

	    //Insert a product to the DB
	    @FXML
	    private void insertProduct (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
	        try {
	            ProductDAO.insertProd(denumireText.getText(),producatorText.getText(),pretText.getText());
	            resultArea.setText("Product inserted! \n");
	        } catch (SQLException e) {
	            resultArea.setText("Problem occurred while inserting product " + e);
	            throw e;
	        }
	    }

	    //Delete a product with a given product Id from DB
	    @FXML
	    private void deleteProduct (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
	        try {
	            ProductDAO.deleteProdWithId(prodIdText.getText());
	            resultArea.setText("Product deleted! Product id: " + prodIdText.getText() + "\n");
	        } catch (SQLException e) {
	            resultArea.setText("Problem occurred while deleting product " + e);
	            throw e;
	        }
	    }
}
