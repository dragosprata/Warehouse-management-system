package bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import bll.validators.StockIdProductValidator;
import bll.validators.Validator;
import dao.StockDAO;
import model.Stock;

public class StockBLL {
	
	private List<Validator<Stock>> validators;
	
	public StockBLL(){
		validators = new ArrayList<Validator<Stock>>();
		validators.add(new StockIdProductValidator());
	}
	
	public Stock findProductById(String id) throws ClassNotFoundException, SQLException{
		Stock s = StockDAO.searchStock(id);
		if(s == null){
			throw new NoSuchElementException("The product with id =" + id + " was not found!");
		}
		return s;
	}
}
