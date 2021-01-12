package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Stock {

	private IntegerProperty id_produs;
	private StringProperty denumire;
	private IntegerProperty cantitate;
	
	//Constructor
	public Stock(){
		this.id_produs = new SimpleIntegerProperty();
		this.denumire = new SimpleStringProperty();
		this.cantitate = new SimpleIntegerProperty();
	}
	
	//id_produs
	public int getProductId(){
		return id_produs.get();
	}
	
	public void setProductId(int id_produs){
        this.id_produs.set(id_produs);
    }

    public IntegerProperty productIdProperty(){
        return id_produs;
    }
    
    //denumire
    public String getDenumire(){
    	return denumire.get();
    }
    
    public void setDenumire(String denumire){
    	this.denumire.set(denumire);
    }
    
    public StringProperty denumireProperty(){
    	return denumire;
    }
    
    //cantitate
    public int getCantitate(){
    	return cantitate.get(); 
    }
    
    public void setCantitate(int cantitate){
    	this.cantitate.set(cantitate);
    }
    
    public IntegerProperty cantitateProperty(){
    	return cantitate; 
    }

	@Override
	public String toString() {
		return "Stock [id_produs=" + id_produs + ", denumire=" + denumire + ", cantitate=" + cantitate + "]";
	}
}
