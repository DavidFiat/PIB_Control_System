package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import customExceptions.*;

public abstract class Enterprise implements Imports, Exports, Serializable {

	protected String name;
	protected String ID;
	protected String creationDate;
	protected double spending;
	private Enterprise next;
	private Employee firstEmployee;

	public Enterprise(String name, String iD, String creationDate, double spending) {
		this.name = name;
		ID = iD;
		this.creationDate = creationDate;
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

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public double getSpending() {
		return spending;
	}

	public void setSpending(double spending) {
		this.spending = spending;
	}

	public Enterprise getNext() {
		return next;
	}

	public void setNext(Enterprise next) {
		this.next = next;
	}

	public Employee getFirstEmployee() {
		return firstEmployee;
	}

	public void setFirstEmployee(Employee firstEmployee) {
		this.firstEmployee = firstEmployee;
	}

	public double totalSpending() {
		double totalSpending = 0.0;
		if (firstEmployee != null) {
			Employee nex = firstEmployee;
			while (nex != null) {

				totalSpending += nex.getSalary();

				nex = nex.getNext();

			}
		}

		return totalSpending;

	}

	@Override
	public double calculateExports() {
		int a = (int) (Math.random() * 21) + 1;
		double exports = (getSpending()) * a / 100;
		return exports;
	}

	@Override
	public double calculateImports() {
		double imports = (getSpending()) * 34 / 100;
		return imports;
	}

	public double exportsMinusImports() {
		return calculateExports() - calculateImports();
	}

	public int compareTo(Enterprise a) {
		return ID.compareTo(a.getID());
	}

	public void addEnterprise(Enterprise a) throws RepeatedEnterpriseException {

		if (compareTo(a) == 0) {
			throw new RepeatedEnterpriseException(a.getID());
		} else {
			if (next == null) {
				setNext(a);
			} else {
				next.addEnterprise(a);
			}
		}

	}

	public void addEmployee(Employee a) throws RepeatedEmployeeException {
		if (firstEmployee == null)
			setFirstEmployee(a);
		else {
			firstEmployee.addEmployee(a);
		}
	}

	public double finalSpending() {
		return spending;

	}

	public double allEnterprisesFinalCost() {
		return next == null ? 0.0 : next.allEnterprisesFinalCost();
	}

	@Override
	public String toString() {
		return "Enterprise [name=" + name + ", ID=" + ID + ", creationDate=" + creationDate + ", spending=" + spending
				+ "]";
	}

	public void loadEmployees() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("data/employees.txt")));
		String line = "";
		while ((line = br.readLine()) != null) {
			String[] s = line.split(",");
			Employee e = new Employee(s[0], s[1], Double.parseDouble(s[2]), s[3]);
			try {
				addEmployee(e);
			} catch (RepeatedEmployeeException e1) {
				e1.printStackTrace();
			}

		}

		br.close();
	}

	public ArrayList<Employee> getEmployees() {
		if (firstEmployee == null)
			return null;

		else {
			ArrayList<Employee> acum = new ArrayList<Employee>();
			firstEmployee.returns(acum);
			return acum;

		}

	}

	public void saveEmployees() throws IOException {

		BufferedWriter bw = new BufferedWriter(new FileWriter("data/vehicles.txt"));
		for (int i = 0; i < getEmployees().size(); i++) {

			String name = getEmployees().get(i).getName();
			String ID = getEmployees().get(i).getID();
			double salary = getEmployees().get(i).getSalary();
			String charge = getEmployees().get(i).getCharge();

			bw.write(name + "," + ID + "," + salary + "," + charge);
			bw.write("\n");
		}
		bw.close();
	}

}
