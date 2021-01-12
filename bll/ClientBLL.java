package bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import bll.validators.ClientAgeValidator;
import bll.validators.Validator;
import dao.ClientDAO;
import model.Client;

public class ClientBLL {

	private List<Validator<Client>> validators;
	
	public ClientBLL(){
		validators = new ArrayList<Validator<Client>>();
		validators.add(new ClientAgeValidator());
	}
	
	public Client findClientById(String id) throws ClassNotFoundException, SQLException{
		Client u = ClientDAO.searchClient(id);
		if(u == null){
			throw new NoSuchElementException("The client with id =" + id + " was not found!");
		}
		return u;
	}
}
