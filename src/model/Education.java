package model;

public class Education extends NonProfitEnterprise implements Tax {

	private String type;
	private String rector;

	public Education(String name, String iD, String creationDate, double spending, String type, String rector) {
		super(name, iD, creationDate, spending);
		this.type = type;
		this.rector = rector;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRector() {
		return rector;
	}

	public void setRector(String rector) {
		this.rector = rector;
	}

	@Override
	public double calculateTax() {
		return (getSpending()) * 7 / 100;
	}

	@Override
	public double finalSpending() {

		return totalSpending() + calculateTax();

	}

	@Override
	public String toString() {
		return "Education [name=" + name + ", ID=" + ID + ", creationDate=" + creationDate + ", spending=" + spending
				+ ", type=" + type + ", rector=" + rector + "]";
	}

}
