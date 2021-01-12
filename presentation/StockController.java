package presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import model.Stock;
import dao.StockDAO;

import java.sql.SQLException;

public class StockController {

	@FXML
    private TextField stockDenumireText;
    @FXML
    private TextArea resultArea;
    @FXML
    private TextField stockCantitateText;
    @FXML
    private TextField stockIdText;
    @FXML
    private TextField newAmountText;
    @FXML
    private TableView stockTable;
    @FXML
    private TableColumn<Stock, Integer>  stockIdColumn;
    @FXML
    private TableColumn<Stock, String>  stockDenumireColumn;
    @FXML
    private TableColumn<Stock, Integer> stockCantitateColumn;

    //Search a product
    @FXML
    private void searchProduct (ActionEvent actionEvent) throws 

ClassNotFoundException, SQLException {
        try {
            //Get Stock information
            Stock stock = StockDAO.searchStock(stockIdText.getText());
            //Populate Stock on TableView and Display on TextArea
            populateAndShowStock(stock);
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Error occurred while getting stock information from DB.\n" + e);
            throw e;
        }
    }

    //Search all products
    @FXML
    private void searchProducts(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            //Get all Products information
            ObservableList<Stock> stockData = StockDAO.searchProducts();
            //Populate Products on TableView
            populateStock(stockData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting products information from DB.\n" + e);
            throw e;
        }
    }

    //Initializing the controller class.
    //This method is automatically called after the fxml file has been loaded.
    @FXML
    private void initialize () {
       
    	stockIdColumn.setCellValueFactory(cellData -> cellData.getValue().productIdProperty().asObject());
    	stockDenumireColumn.setCellValueFactory(cellData -> cellData.getValue().denumireProperty());
    	stockCantitateColumn.setCellValueFactory(cellData -> cellData.getValue().cantitateProperty().asObject());
    }

    //Populate Stock
    @FXML
    private void populateStock (Stock stock) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<Stock> stockData = FXCollections.observableArrayList();
        //Add product to the ObservableList
        stockData.add(stock);
        //Set items to the productTable
        stockTable.setItems(stockData);
    }

    //Set Stock information to Text Area
    @FXML
    private void setStockInfoToTextArea ( Stock stock) {
        resultArea.setText("Denumire: " + stock.getDenumire() + "\n" +"Cantitate: " + stock.getCantitate());
    }

    //Populate Stock for TableView and Display Stock on TextArea
    @FXML
    private void populateAndShowStock(Stock stock) throws ClassNotFoundException {
        if (stock != null) {
            populateStock(stock);
            setStockInfoToTextArea(stock);
        } else {
            resultArea.setText("This product does not exist!\n");
        }
    }

    //Populate Stock for TableView
    @FXML
    private void populateStock (ObservableList<Stock> stockData) throws ClassNotFoundException {
        //Set items to the stockTable
    	stockTable.setItems(stockData);
    }

    //Update stock's amount with the amount which is written on newAmountText field
    @FXML
    private void updateStockAmount (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            StockDAO.updateStockAmount(stockIdText.getText(),newAmountText.getText());
            resultArea.setText("Amount has been updated for, product id: " + stockIdText.getText() + "\n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while updating amount: " + e);
        }
    }

    //Insert a product to the DB
    @FXML
    private void insertProduct (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            StockDAO.insertProd(stockDenumireText.getText(),stockCantitateText.getText());
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
            StockDAO.deleteProdWithId(stockIdText.getText());
            resultArea.setText("Product deleted! Product id: " + stockIdText.getText() + "\n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while deleting product " + e);
            throw e;
        }
    }
}
