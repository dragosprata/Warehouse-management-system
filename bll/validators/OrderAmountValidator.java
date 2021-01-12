package bll.validators;

import model.Order;

public class OrderAmountValidator implements Validator<Order>{

	public void validate(Order o){
		if(o.getCantitate() < 1){
			throw new IllegalArgumentException("The order amount limit is not respected");
		}
	}
}
