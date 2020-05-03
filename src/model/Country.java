package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import exceptions.RepeatedCitizenException;
import exceptions.RepeatedEnterpriseException;

public class Country implements Serializable{
	private String name;
	private int population;
	private double extension;
	private String president;
	private double publicSpending;
	private String sea;
	private Citizen rootCitizen;
	private Enterprise firstEnterprise;

	public Country(String name, int population, double extension, String president, double publicSpending, String sea) {
		this.name = name;
		this.population = population;
		this.extension = extension;
		this.president = president;
		this.publicSpending = publicSpending;
		this.sea = sea;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public double getExtension() {
		return extension;
	}

	public void setExtension(double extension) {
		this.extension = extension;
	}

	public String getPresident() {
		return president;
	}

	public void setPresident(String president) {
		this.president = president;
	}

	public double getPublicSpending() {
		return publicSpending;
	}

	public void setPublicSpending(double publicSpending) {
		this.publicSpending = publicSpending;
	}

	public String getSea() {
		return sea;
	}

	public void setSea(String sea) {
		this.sea = sea;
	}

	public Citizen getRootCitizen() {
		return rootCitizen;
	}

	public void setRootClient(Citizen rootCitizen) {
		this.rootCitizen = rootCitizen;
	}

	public Enterprise getFirstEnterprise() {
		return firstEnterprise;
	}

	public void setFirstEnterprise(Enterprise firstEnterprise) {
		this.firstEnterprise = firstEnterprise;
	}

	public int compareByName(Country p) {
		return name.compareTo(p.getName());
	}

	public int compareByPopulation(Country p) {
		return population - (p.getPopulation());
	}

	public double compareByExtension(Country p) {
		return (extension - (p.getExtension()));
	}

	public int compareByPresident(Country p) {
		return president.compareTo(p.getPresident());
	}

	public double compareByPublicSpending(Country p) {
		return (publicSpending - (p.getPublicSpending()));
	}

	public int compareBySea(Country p) {
		return sea.compareTo(p.getSea());
	}

	public void addCitizen(Citizen a) throws RepeatedCitizenException {
		if (rootCitizen == null) {
			setRootClient(a);
		} else {
			rootCitizen.addCitizen(a);
		}

	}

	public Citizen search(String ID) {
		return rootCitizen == null ? null : rootCitizen.search(ID);
	}

	public void addEnterprise(Enterprise a) throws RepeatedEnterpriseException {
		if (firstEnterprise == null)
			setFirstEnterprise(a);
		else {
			firstEnterprise.addEnterprise(a);
		}
	}

	public double allEnterprisesFinalCost() {

		return firstEnterprise == null ? 0.0 : firstEnterprise.allEnterprisesFinalCost();
	}

	public double allCitizensTotalCost() {

		return rootCitizen == null ? 0.0 : rootCitizen.allCitizensTotalCost();
	}

	public double PIB() {
		return getPublicSpending() + allEnterprisesFinalCost() + allCitizensTotalCost();
	}

	@Override
	public String toString() {
		return "Country [name=" + name + ", population=" + population + ", extension=" + extension + ", president="
				+ president + ", publicSpending=" + publicSpending + ", sea=" + sea + "]";
	}

	public void saveEnterprises() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/enterprises.fiat"));
		if (firstEnterprise != null) {
			Enterprise nex = firstEnterprise;
			while (nex != null) {
				oos.writeObject(nex);
			}
		}
		oos.close();
	}
	public void loadEnterprises() throws IOException, ClassNotFoundException, RepeatedEnterpriseException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/enterprises.fiat"));
		addEnterprise((Enterprise) ois.readObject());

	}
	public void saveCitizen() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/citizens.fiat"));
		if (rootCitizen != null) {
			rootCitizen.saveCitizen(oos);
			oos.writeObject(rootCitizen);
		}
		oos.close();

	}
	public void loadCitizen() throws IOException, ClassNotFoundException, RepeatedCitizenException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/citizens.fiat"));
		addCitizen((Citizen) ois.readObject());
	}

}
