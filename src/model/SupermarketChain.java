package model;

public class SupermarketChain extends ForProfitEnterprise {

	private String owner;
	private double calification;
	private int quantityOfSupermarkets;

	public SupermarketChain(String name, String iD, String creationDate, double spending, double earnings, String owner,
			double calification, int quantityOfSupermarkets) {
		super(name, iD, creationDate, spending, earnings);
		this.owner = owner;
		this.calification = calification;
		this.quantityOfSupermarkets = quantityOfSupermarkets;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public double getCalification() {
		return calification;
	}

	public void setCalification(double calification) {
		this.calification = calification;
	}

	public int getQuantityOfSupermarkets() {
		return quantityOfSupermarkets;
	}

	public void setQuantityOfSupermarkets(int quantityOfSupermarkets) {
		this.quantityOfSupermarkets = quantityOfSupermarkets;
	}

}
