package exceptions;

public class RepeatedCitizenException extends Exception {

	public RepeatedCitizenException(String a) {
		super("There is already a Citizen with ID: " + a);
	}

}
