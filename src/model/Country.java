package model;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import customExceptions.*;
import threads.CitizenDataThread;

public class Country implements Serializable {
	private String name;
	private int population;
	private double extension;
	private String president;
	private double publicSpending;
	private String sea;
	private Citizen rootCitizen;
	private Enterprise firstEnterprise;
	private BufferedWriter bw;

	public Country(String name, int population, double extension, String president, double publicSpending, String sea)
			throws IOException {
		this.name = name;
		this.population = population;
		this.extension = extension;
		this.president = president;
		this.publicSpending = publicSpending;
		if (sea.equals("")) {
			this.sea = "NINGUNO";
		} else {
			this.sea = sea;
		}
		InitThread();

		citizenDataInit();
	}

	private void InitThread() {
		CitizenDataThread cdt = new CitizenDataThread(this);
		cdt.start();
	}

	private void citizenDataInit() throws IOException {
		bw = new BufferedWriter(new FileWriter("data/" + name + "'s Citizens" + ".csv"));
		bw.write("name" + ";" + "ID" + ";" + "Spending" + ";" + "Other Information" + ";" + "\n");

	}

	public void citizenData() throws IOException {
		if (rootCitizen != null) {
			rootCitizen.citizenData(bw);
		}

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

	public void setRootCitizen(Citizen rootCitizen) {
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
			setRootCitizen(a);
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

	public Enterprise searchEnterprise(String enterprise) {
		return firstEnterprise == null ? null : firstEnterprise.searchEnterprise(enterprise);
	}

}
