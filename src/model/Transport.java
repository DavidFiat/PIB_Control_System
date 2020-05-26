package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import customExceptions.*;

public class Transport extends ForProfitEnterprise {

	private Vehicle rootVehicle;

	public Transport(String name, String iD, String creationDate, double spending, double earnings) {
		super(name, iD, creationDate, spending, earnings);
	}

	public Vehicle getRootVehicle() {
		return rootVehicle;
	}

	public void setRootVehicle(Vehicle rootVehicle) {
		this.rootVehicle = rootVehicle;
	}

	public void addVehicle(Vehicle a) throws RepeatedVehicleException {
		if (rootVehicle == null) {
			setRootVehicle(a);
		} else {
			rootVehicle.addVehicle(a);
		}

	}

	public ArrayList<Vehicle> getVehicles() {
		if (rootVehicle == null)
			return null;

		else {
			ArrayList<Vehicle> acum = new ArrayList<Vehicle>();
			rootVehicle.inorden(acum);
			return acum;

		}

	}

}
