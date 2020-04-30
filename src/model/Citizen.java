package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class Citizen implements Spending, Serializable {

	private String name;
	private String ID;
	private double spending;
	protected Citizen left;
	protected Citizen right;

	public Citizen(String name, String iD, double spending) {
		this.name = name;
		ID = iD;
		this.spending = spending;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public double getSpending() {
		return spending;
	}

	public void setSpending(double spending) {
		this.spending = spending;
	}

	public Citizen getLeft() {
		return left;
	}

	public void setLeft(Citizen left) {
		this.left = left;
	}

	public Citizen getRight() {
		return right;
	}

	public void setRight(Citizen right) {
		this.right = right;
	}

	public int compareTo(Citizen a) {
		return ID.compareTo(a.getID());
	}

	public void addCitizen(Citizen a) throws RepeatedCitizenException {
		if (compareTo(a) == 0)
			throw new RepeatedCitizenException(a.getID());

		if (compareTo(a) > 0) {
			if (left == null) {
				setLeft(a);
			} else {
				left.addCitizen(a);
			}
		} else {
			if (right == null) {
				right = a;
			} else {
				right.addCitizen(a);
			}

		}

	}

	public Citizen search(String ID) {
		if (this.ID.compareTo(ID) == 0) {
			return this;
		} else if (this.ID.compareTo(ID) > 0) {
			return (left == null) ? null : left.search(ID);

		} else {
			return (right == null) ? null : right.search(ID);

		}

	}

	public double allCitizensTotalCost() {
		double allCitizensTotalCost = 0;
		allCitizensTotalCost += left == null ? 0.0 : left.allCitizensTotalCost();
		allCitizensTotalCost += right == null ? 0.0 : right.allCitizensTotalCost();
		return allCitizensTotalCost;
	}

	public void saveCitizen(ObjectOutputStream oos) throws IOException {
		if (left != null) {
			left.saveCitizen(oos);
			oos.writeObject(left);
		} else if (right != null) {
			right.saveCitizen(oos);
			oos.writeObject(right);
		}
		oos.close();

	}

}
