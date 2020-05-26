package model;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import customExceptions.*;

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

	@Override
	public String toString() {
		return "Citizen [name=" + name + ", ID=" + ID + ", spending=" + spending + ", left=" + left + ", right=" + right
				+ "]";
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



	public void citizenData(BufferedWriter bw) throws IOException {

		if (left != null) {
			left.citizenData(bw);
		}
		bw.write(this.toString());
		bw.write(";" + "\n");
		if (right != null) {
			right.citizenData(bw);
		}
		bw.flush();
	}

	public double allCitizensTotalCost() {
		return spending;
		
	}

}
