package model;

public class RepeatedEmployeeException extends Exception {
	public RepeatedEmployeeException(String a) {
		super("There is already an Employee with ID: " + a);
	}
}
