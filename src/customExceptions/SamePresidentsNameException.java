package customExceptions;

public class SamePresidentsNameException extends Exception {
	public SamePresidentsNameException(String a) {
		super("There is already a Country with the president: " + a);
	}
}
