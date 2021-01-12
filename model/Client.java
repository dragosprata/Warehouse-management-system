package model;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Client {

    private IntegerProperty id;
    private StringProperty nume;
    private StringProperty prenume;
    private IntegerProperty varsta;

    public Client(){
    	this.id = new SimpleIntegerProperty();
    	this.nume = new SimpleStringProperty();
    	this.prenume = new SimpleStringProperty();
    	this.varsta = new SimpleIntegerProperty(); 
    }
    
    //client_id
    public int getClientId(){
    	return id.get();
    }
    
    public void setClientId(int id){
    	this.id.set(id);
    }
    
    public IntegerProperty clientIdProperty(){
    	return id; 
    }
    
    //nume
    public String getNume(){
    	return nume.get();
    }
    
    public void setNume(String nume){
    	this.nume.set(nume);
    }
    
    public StringProperty numeProperty(){
    	return nume;
    }
    
    //prenume
    public String getPrenume(){
    	return prenume.get();
    }
    
    public void setPrenume(String prenume){
    	this.prenume.set(prenume);
    }
    
    public StringProperty prenumeProperty(){
    	return prenume;
    }
    
    //varsta
    public int getVarsta(){
    	return varsta.get();
    }
    
    public void setVarsta(int varsta){
    	this.varsta.set(varsta);
    }
    
    public IntegerProperty varstaProperty(){
    	return varsta;
    }

	@Override
	public String toString() {
		return "Client [id=" + id + ", nume=" + nume + ", prenume=" + prenume + ", varsta=" + varsta + "]";
	}
}