package model;

public class Child extends Citizen {

	private boolean privateEducation;
	private double educationExpense;

	public Child(String name, String iD, double spending, boolean privateEducation, double educationExpense) {
		super(name, iD, spending);
		this.privateEducation = privateEducation;
		this.educationExpense = educationExpense;
	}

	public boolean isPrivateEducation() {
		return privateEducation;
	}

	public void setPrivateEducation(boolean privateEducation) {
		this.privateEducation = privateEducation;
	}

	public double getEducationExpense() {
		return educationExpense;
	}

	public void setEducationExpense(double educationExpense) {
		this.educationExpense = educationExpense;
	}

	@Override
	public double calculateTotalSpending() {
		return isPrivateEducation()==true?getEducationExpense()+getSpending():getSpending();
	}

}
