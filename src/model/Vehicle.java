package model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import customExceptions.*;

public class Vehicle implements Serializable{

	public static final int LAND_VEHICLE = 1;
	public static final int AIR_VEHICLE = 2;
	public static final int MARITIME_VEHICLE = 2;

	private String type;
	private String brand;
	private String ID;
	private Vehicle left;
	private Vehicle right;

	public Vehicle getLeft() {
		return left;
	}

	public void setLeft(Vehicle left) {
		this.left = left;
	}

	public Vehicle getRight() {
		return right;
	}

	public void setRight(Vehicle right) {
		this.right = right;
	}

	public Vehicle(String type, String brand, String iD) {
		this.type = type;
		this.brand = brand;
		ID = iD;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public int compareTo(Vehicle a) {
		return ID.compareTo(a.getID());
	}

	public void addVehicle(Vehicle a) throws RepeatedVehicleException {
		if (compareTo(a) == 0)
			throw new RepeatedVehicleException(a.getID());

		if (compareTo(a) > 0) {
			if (left == null) {
				setLeft(a);
			} else {
				left.addVehicle(a);
			}
		} else {
			if (right == null) {
				right = a;
			} else {
				right.addVehicle(a);
			}

		}

	}

	public void inorden(ArrayList<Vehicle> acum) {
		if (left != null)
			left.inorden(acum);

		acum.add(this);

		if (right != null)
			right.inorden(acum);

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

	@Override
	public String toString() {
		return type + ";" + brand + ";" + ID;
	}

}
