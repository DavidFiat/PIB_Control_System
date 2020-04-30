package model;

public class Pet extends Citizen {

	public static final double PET_BASE_SUPPORT = 500;
	private boolean pedigree;
	private String race;
	private double petExpense;

	public Pet(String name, String iD, double spending, boolean pedigree, String race) {
		super(name, iD, spending);
		this.pedigree = pedigree;
		this.race = race;
		this.petExpense = PET_BASE_SUPPORT;
	}

	public boolean isPedigree() {
		return pedigree;
	}

	public void setPedigree(boolean pedigree) {
		this.pedigree = pedigree;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public double getPetExpense() {
		return petExpense;
	}

	public void setPetExpense(double petExpense) {
		this.petExpense = petExpense;
	}

	@Override
	public double calculateTotalSpending() {
		return isPedigree() == true ? (getPetExpense() * 2) + getSpending() : getPetExpense() + getSpending();
	}

}
