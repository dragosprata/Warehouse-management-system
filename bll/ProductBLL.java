package bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import bll.validators.ProductPrizeValidator;
import bll.validators.Validator;
import dao.ProductDAO;
import model.Product;

public class ProductBLL {

	private List<Validator<Product>> validators;
	
	public ProductBLL(){
		validators = new ArrayList<Validator<Product>>();
		validators.add(new ProductPrizeValidator());
	}
	
	public Product findProductById(String id) throws ClassNotFoundException, SQLException{
		Product p =ProductDAO.searchProduct(id);
		if(p == null){
			throw new NoSuchElementException("The product with id=" + id + " was not found!");
		}
		return p;
	}
}
