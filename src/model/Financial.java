package model;

public class Financial extends ForProfitEnterprise {

	private double interestRate;
	private boolean permission;

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public boolean isPermission() {
		return permission;
	}

	public void setPermission(boolean permission) {
		this.permission = permission;
	}

	public Financial(String name, String iD, String creationDate, double spending, double earnings, double interestRate,
			boolean permission) {
		super(name, iD, creationDate, spending, earnings);
		this.interestRate = interestRate;
		this.permission = permission;
	}

}
