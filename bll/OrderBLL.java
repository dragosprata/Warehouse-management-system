package bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import bll.validators.OrderAmountValidator;
import bll.validators.Validator;
import dao.OrderDAO;
import model.Order;

public class OrderBLL {

	private List<Validator<Order>> validators;
	
	public OrderBLL(){
		validators = new ArrayList<Validator<Order>>();
		validators.add(new OrderAmountValidator());
	}
	
	public Order findUserById(String id) throws ClassNotFoundException, SQLException{
		Order o = OrderDAO.searchOrder(id);
		if(o == null){
			throw new NoSuchElementException("The order with idUser=" + id + "was not found!");
		}
		return o;
	}
	
	public Order findProductById(String id) throws ClassNotFoundException, SQLException{
		Order o = OrderDAO.searchOrder(id);
		if(o == null){
			throw new NoSuchElementException("The order with idProduct=" + id + "was not found!");
		}
		return o;
	}
	
}
