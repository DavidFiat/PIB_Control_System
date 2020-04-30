package model;

public class Cooperative extends NonProfitEnterprise implements Tax {

	private String objective;
	private int calification;

	public Cooperative(String name, String iD, String creationDate, double spending, String objective,
			int calification) {
		super(name, iD, creationDate, spending);
		this.objective = objective;
		this.calification = calification;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public int getCalification() {
		return calification;
	}

	public void setCalification(int calification) {
		this.calification = calification;
	}

	@Override
	public double calculateTax() {
		return (getCalification() / 100) * getSpending();
	}

	@Override
	public double finalSpending() {

		return totalSpending() + calculateTax();

	}

	@Override
	public String toString() {
		return "Cooperative [name=" + name + ", ID=" + ID + ", creationDate=" + creationDate + ", spending=" + spending
				+ ", objective=" + objective + ", calification=" + calification + "]";
	}

}
