package bll.validators;
import model.Product;

public class ProductPrizeValidator implements Validator<Product>{

	public void validate(Product p){
		if(p.getPrice() < 0){
			throw new IllegalArgumentException("The product prize limit is not respected");
		}
	}
}
