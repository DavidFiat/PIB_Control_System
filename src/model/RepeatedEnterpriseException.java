package model;

public class RepeatedEnterpriseException extends Exception {
	public RepeatedEnterpriseException(String a) {
		super("There is already an Enterprise with ID: " + a);
	}
}
