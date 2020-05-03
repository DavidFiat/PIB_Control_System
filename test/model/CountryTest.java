package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.RepeatedCitizenException;
import exceptions.RepeatedEnterpriseException;

class CountryTest {

	private Country country;

	@Test
	private void setupStage() {
		country = new Country("Perú", 3234555, 2463563, "Fiat", 683466, "Pacifico");
	}

	@Test
	private void addEnterpriseTest() {
		setupStage();
		Enterprise a1 = new Transport("Confenalco", "535336", "22/11/1980", 65435.76, 54678);
		Enterprise a = new Education("Icesi", "5675", "22/05/1990", 56993, "University", "Piedrahita");
		Enterprise b = new Financial("Davivienda", "123", "12/07/1999", 456675, 5555, 12, false);

		try {
			country.addEnterprise(a1);
		} catch (RepeatedEnterpriseException e) {
			fail("The country must be added");
		}

		try {
			country.addEnterprise(a);
		} catch (RepeatedEnterpriseException e1) {
			fail("The country must be added");
		}

		try {
			country.addEnterprise(b);
		} catch (RepeatedEnterpriseException e1) {
			fail("The country must be added");
		}

		boolean catched = false;
		try {
			country.addEnterprise(b);
		} catch (RepeatedEnterpriseException e1) {
			catched = true;
		}
		assertTrue(catched);

	}

	@Test
	public void addCitizenTest() {
		setupStage();
		Citizen c = new Pet("Pepe", "3456", 5678, true, "Labrador");
		Citizen t = new Adult("Daniel", "64643456", 5678, 35);
		Citizen r = new Child("Juan", "1002567502", 34567, true, 345);

		try {
			country.addCitizen(c);
		} catch (RepeatedCitizenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			country.addCitizen(t);
		} catch (RepeatedCitizenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			country.addCitizen(r);
		} catch (RepeatedCitizenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		boolean catched = false;
		try {
			country.addCitizen(r);
		} catch (RepeatedCitizenException e1) {
			catched = true;
		}
		assertTrue(catched);
	}

	@Test
	public void searchCitizenTest() {
		setupStage();
		addCitizenTest();

		// Searching an inexistent Citizen
		String ID = "1";
		country.search(ID);
		assertNull(country.search(ID));

		// Searching one it does exist at the first position
		ID = "3456";
		country.search(ID);
		assertEquals("Pepe", country.search(ID).getName());
		assertEquals(true, ((Pet) country.search(ID)).isPedigree());

		// Searching one it does exist at other position
		ID = "64643456";
		country.search(ID);
		assertEquals("Daniel", country.search(ID).getName());
		assertEquals(5678, country.search(ID).getSpending());
	}

}
