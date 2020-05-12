package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import exceptions.*;

public class Software {

	private ArrayList<Country> countries;

	public Software() {
		countries = new ArrayList<Country>();
	}

	public ArrayList<Country> getCountries() {
		return countries;
	}

	public void setCountries(ArrayList<Country> countries) {
		this.countries = countries;
	}

	// By Insertion
	public void sortCountriesByName() {
		for (int i = 1; i < countries.size(); i++) {
			for (int i2 = i; i2 > 0 && countries.get(i2 - 1).compareByName(countries.get(i2)) > 0; i2--) {
				Country temp = countries.get(i2);
				countries.set(i2, countries.get(i2 - 1));
				countries.set(i2 - 1, temp);
			}
		}

	}

	// By Insertion
	public void sortCountriesByPopulation() {
		for (int i = 1; i < countries.size(); i++) {
			for (int i2 = i; i2 > 0 && countries.get(i2 - 1).compareByPopulation(countries.get(i2)) > 0; i2--) {
				Country temp = countries.get(i2);
				countries.set(i2, countries.get(i2 - 1));
				countries.set(i2 - 1, temp);
			}
		}

	}

	// By bubble
	public void sortCountriesByExtension() {

		for (int i = 0; i < countries.size(); i++) {
			for (int i2 = 0; i2 < countries.size() - 1 - i; i2++) {
				if (countries.get(i2).compareByExtension(countries.get(i2 + 1)) > 0) {

					Country temp = countries.get(i2);
					countries.set(i2, countries.get(i2 + 1));
					countries.set(i2 + 1, temp);

				}
			}
		}

	}

	// By bubble
	public void sortCountriesByPresident() {

		for (int i = 0; i < countries.size(); i++) {
			for (int i2 = 0; i2 < countries.size() - 1 - i; i2++) {
				if (countries.get(i2).compareByPresident(countries.get(i2 + 1)) > 0) {

					Country temp = countries.get(i2);
					countries.set(i2, countries.get(i2 + 1));
					countries.set(i2 + 1, temp);

				}
			}
		}

	}

	// By Selection
	public void sortCountriesByPublicSpending() {
		for (int i = 0; i < countries.size() - 1; i++) {
			Country small = countries.get(i);
			int i3 = i;
			for (int i2 = i + 1; i2 < countries.size(); i2++) {
				if (countries.get(i2).compareByPublicSpending(small) < 0) {
					i3 = i2;
					small = countries.get(i2);
				}
			}
			Country temp = countries.get(i);
			countries.set(i, small);
			countries.set(i3, temp);
		}
	}

	// By Selection
	public void sortCountriesBySea() {
		for (int i = 0; i < countries.size() - 1; i++) {
			Country small = countries.get(i);
			int i3 = i;
			for (int i2 = i + 1; i2 < countries.size(); i2++) {
				if (countries.get(i2).compareBySea(small) < 0) {
					i3 = i2;
					small = countries.get(i2);
				}
			}
			Country temp = countries.get(i);
			countries.set(i, small);
			countries.set(i3, temp);
		}
	}

	// Binary search

	public Country binarySearchCountryByName(String e) {
		sortCountriesByName();
		Country a = null;
		int begining = 0;
		int end = countries.size() - 1;
		boolean find = false;
		while (begining <= end && !find) {
			int half = (begining + end) / 2;
			if (countries.get(half).getName().equalsIgnoreCase(e)) {
				a = countries.get(half);
				find = true;
			} else if (countries.get(half).getName().compareToIgnoreCase(e) > 0) {
				end = half - 1;
			} else {
				begining = half + 1;
			}
		}
		return a;
	}

	public Country binarySearchCountryByPresident(String e) {
		sortCountriesByPresident();
		Country a = null;
		int begining = 0;
		int end = countries.size() - 1;
		boolean find = false;
		while (begining <= end && !find) {
			int half = (begining + end) / 2;
			if (countries.get(half).getPresident().equalsIgnoreCase(e)) {
				a = countries.get(half);
				find = true;
			} else if (countries.get(half).getPresident().compareToIgnoreCase(e) > 0) {
				end = half - 1;
			} else {
				begining = half + 1;
			}
		}
		return a;
	}

	public void addCountry(Country p) throws SameNameCountryException, SamePresidentsNameException {
		if (countries.size() == 0) {
			countries.add(p);
		} else {
			boolean sameCountry = false;
			boolean samePresident = false;
			for (int i = 0; !sameCountry && !samePresident && i < countries.size(); i++) {
				if (p.getName().equals(countries.get(i).getName())) {
					sameCountry = true;
				}

				if (p.getPresident().equals(countries.get(i).getPresident())) {
					samePresident = true;

				}
			}
			if (sameCountry) {
				throw new SameNameCountryException(p.getName());

			} else if (samePresident) {
				throw new SamePresidentsNameException(p.getPresident());

			} else {
				countries.add(p);
			}
		}

	}

	public double PIB(String country) {
		double PIB = 0.0;
		for (int i = 0; i < countries.size(); i++) {
			if (countries.get(i).getName().equals(country)) {
				PIB = countries.get(i).PIB();
			}

		}
		return PIB;
	}

	public void saveCountries() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/countries.fiat"));
		for (int i = 0; i < countries.size(); i++) {

			oos.writeObject(countries.get(i));

		}
		oos.close();

	}

	@SuppressWarnings("unchecked")
	public void loadCountries() throws IOException, ClassNotFoundException, SameNameCountryException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/enterprises.fiat"));
		countries = (ArrayList<Country>) ois.readObject();
		ois.close();
	}

	public void addCitizen(Citizen a, String country) {
		for (int i = 0; i < countries.size(); i++) {
			if (country.equals(countries.get(i).getName())) {
				try {
					countries.get(i).addCitizen(a);
				} catch (RepeatedCitizenException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void addEnterprise(Enterprise a, String country) {
		for (int i = 0; i < countries.size(); i++) {
			if (country.equals(countries.get(i).getName())) {
				try {
					countries.get(i).addEnterprise(a);
				} catch (RepeatedEnterpriseException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
