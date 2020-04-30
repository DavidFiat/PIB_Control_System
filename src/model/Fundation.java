package model;

public class Fundation extends NonProfitEnterprise {
	private double help;

	public Fundation(String name, String iD, String creationDate, double spending, double help) {
		super(name, iD, creationDate, spending);
		this.help = help;
	}

	public double getHelp() {
		return help;
	}

	public void setHelp(double help) {
		this.help = help;
	}

	@Override
	public double finalSpending() {

		return totalSpending() - getHelp();
		

	}

	@Override
	public String toString() {
		return "Fundation [name=" + name + ", ID=" + ID + ", creationDate=" + creationDate + ", spending=" + spending
				+ ", help=" + help + "]";
	}
}
