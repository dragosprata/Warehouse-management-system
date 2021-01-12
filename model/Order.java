package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Order {

	private IntegerProperty id;
	private StringProperty denumire;
	private StringProperty nume;
	private IntegerProperty cantitate;
	
	//Constructor
	public Order(){
		this.id = new SimpleIntegerProperty();
		this.denumire = new SimpleStringProperty();
		this.nume = new SimpleStringProperty();
		this.cantitate = new SimpleIntegerProperty();
		
	}
	
	//order_id
	public int getOrderId(){
		return id.get();
	}
	
	public void setOrderId(int id){
		this.id.set(id);
	}
	
	public IntegerProperty orderIdProperty(){
        return id;
    }
	
	//denumire_produs
	public String getDenumire(){
		return denumire.get();
	}
	
	public void setDenumire(String denumire){
		this.denumire.set(denumire);
	}
	
	public StringProperty denumireProperty(){
		return denumire;
	}
	
	//nume_user
	
	public String getNume(){
		return nume.get();
	}
	
	public void setNume(String nume){
		this.nume.set(nume);
	}
	
	public StringProperty numeProperty(){
		return nume;
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
		return "Order [id=" + id + ", denumire=" + denumire + ", nume=" + nume + ", cantitate=" + cantitate + "]";
	}
}
