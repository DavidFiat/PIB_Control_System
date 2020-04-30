package model;

public class Adult extends Citizen {
	private double liquorSpending;

	public Adult(String name, String iD, double spending, double liquor) {
		super(name, iD, spending);
		liquorSpending = liquor;
	}

	public double getLiquorSpending() {
		return liquorSpending;
	}

	public void setLiquorSpending(double liquorSpending) {
		this.liquorSpending = liquorSpending;
	}

	@Override
	public double calculateTotalSpending() {
		return getSpending()+getLiquorSpending();
	}

}
