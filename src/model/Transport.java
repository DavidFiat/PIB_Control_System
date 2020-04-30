package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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

	public void loadVehicles() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("data/vehicles.txt")));
		String line = "";
		while ((line = br.readLine()) != null) {
			String[] s = line.split(",");
			Vehicle v = new Vehicle(s[0], s[1], s[2]);
			try {
				addVehicle(v);
			} catch (RepeatedVehicleException e) {
				e.printStackTrace();
			}

		}

		br.close();
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

	public void save() throws IOException {

		BufferedWriter bw = new BufferedWriter(new FileWriter("data/vehicles.txt"));
		for (int i = 0; i < getVehicles().size(); i++) {

			String type = getVehicles().get(i).getType();
			String brand = getVehicles().get(i).getBrand();
			String ID = getVehicles().get(i).getID();
			bw.write(type + "," + brand + "," + ID);
			bw.write("\n");
		}
		bw.close();
	}

}
