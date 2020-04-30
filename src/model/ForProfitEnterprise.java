package model;

public abstract class ForProfitEnterprise extends Enterprise implements Tax {
	private double earnings;

	public ForProfitEnterprise(String name, String iD, String creationDate, double spending, double earnings) {
		super(name, iD, creationDate, spending);
		this.earnings = earnings;
	}

	public double getEarnings() {
		return earnings;
	}

	public void setEarnings(double earnings) {
		this.earnings = earnings;
	}

	@Override
	public double calculateTax() {
		return (getEarnings()) * 19 / 100;
	}

	@Override
	public double finalSpending() {
		return totalSpending() + calculateTax() + getEarnings();

	}

}
