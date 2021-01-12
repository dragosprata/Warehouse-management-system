package bll.validators;
import model.Client;

public class ClientAgeValidator implements Validator<Client>{
	private static final int MIN_AGE = 18;
	
	public void validate(Client u){
		if(u.getVarsta() < MIN_AGE){
			throw new IllegalArgumentException("The user age limit is not respected");
		}
	}
}
