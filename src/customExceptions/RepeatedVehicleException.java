package customExceptions;

public class RepeatedVehicleException extends Exception {
	public RepeatedVehicleException(String a) {
		super("There is already a Vehicle with ID: " + a);
	}
}
