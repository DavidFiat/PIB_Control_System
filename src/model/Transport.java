package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import customExceptions.*;
import threads.VehicleDataThread;

public class Transport extends ForProfitEnterprise {
	private BufferedWriter bw;
	private Vehicle rootVehicle;

	public Transport(String name, String iD, String creationDate, double spending, double earnings) throws IOException {
		super(name, iD, creationDate, spending, earnings);
		InitThread();

	}

	public void vehicleData() throws IOException {
		bw = new BufferedWriter(new FileWriter("data/transportEnterprise's_Data/" + name + "'s vehicles" + ".csv"));
		bw.write("type" + ";" + "Brand" + ";" + "ID" + ";" + "\n");

		if (rootVehicle != null) {
			rootVehicle.citizenData(bw);
		}
	}

	private void InitThread() {
		VehicleDataThread vdt = new VehicleDataThread(this);
		vdt.start();
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
