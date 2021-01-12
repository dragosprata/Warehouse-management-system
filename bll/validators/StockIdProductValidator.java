package bll.validators;
import model.Stock;

public class StockIdProductValidator implements Validator<Stock>{

	public void validate(Stock s){
		if(s.getProductId()<1 || s.getProductId()>1000){
			throw new IllegalArgumentException("The stock id product limit is not respected");
		}
	}
}
