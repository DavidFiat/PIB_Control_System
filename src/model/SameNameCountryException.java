package model;

public class SameNameCountryException extends Exception {

	public SameNameCountryException(String a) {
		super("There is already a Country with the name: " + a);
	}
}
