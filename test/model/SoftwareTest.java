package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SoftwareTest {
	private Software software;

	@Test
	public void setupSoftwareStage() {
		software = new Software();

		Country a = new Country("Colombia", 123, 456.78, "Uribe", 56, "Pacifico");
		Country b = new Country("Venezuela", 23, 4556.78, "Maduro", 428, "Pacifico");
		Country c = new Country("Japon", 56123, 4456.78, "Dan-Chi", 6556, "Indico");

		software.getCountries().add(a);
		software.getCountries().add(b);
		software.getCountries().add(c);
	}

	@Test
	public void testSortCountriesByName() {
		setupSoftwareStage();
		software.sortCountriesByName();
		for (int i = 0; i < software.getCountries().size() - 1; i++) {
			assertTrue(software.getCountries().get(i).compareByName(software.getCountries().get(i + 1)) < 0);
		}
	}

	@Test
	public void testSortCountriesByPopulation() {
		setupSoftwareStage();
		software.sortCountriesByPopulation();
		for (int i = 0; i < software.getCountries().size() - 1; i++) {
			assertTrue(software.getCountries().get(i).compareByPopulation(software.getCountries().get(i + 1)) <= 0);
		}
	}

	@Test
	public void testSortCountriesByExtension() {
		setupSoftwareStage();
		software.sortCountriesByExtension();
		for (int i = 0; i < software.getCountries().size() - 1; i++) {
			assertTrue(software.getCountries().get(i).compareByExtension(software.getCountries().get(i + 1)) <= 0);
		}
	}

	@Test
	public void testSortCountriesByPresident() {
		setupSoftwareStage();
		software.sortCountriesByPresident();
		for (int i = 0; i < software.getCountries().size() - 1; i++) {
			assertTrue(software.getCountries().get(i).compareByPresident(software.getCountries().get(i + 1)) < 0);
		}
	}

	@Test
	public void testSortCountriesByPublicSpending() {
		setupSoftwareStage();
		software.sortCountriesByPublicSpending();
		for (int i = 0; i < software.getCountries().size() - 1; i++) {
			assertTrue(software.getCountries().get(i).compareByPublicSpending(software.getCountries().get(i + 1)) <= 0);
		}
	}

	@Test
	public void testSortCountriesBySea() {
		setupSoftwareStage();
		software.sortCountriesBySea();
		for (int i = 0; i < software.getCountries().size() - 1; i++) {
			assertTrue(software.getCountries().get(i).compareBySea(software.getCountries().get(i + 1)) <= 0);
		}
	}

	@Test
	public void testBinarySearchCountryByName() {
		setupSoftwareStage();
		software.sortCountriesByName();
		Country z = software.binarySearchCountryByName("Colombia");
		assertTrue(z == software.getCountries().get(0));
		assertTrue(z.getName().equalsIgnoreCase("colombia"));
		// .equals(new Country("Colombia", 123, 456.78, "Duque", 56, "Pacifico")));

	}

	@Test
	public void testBinarySearchCountryByPresident() {
		setupSoftwareStage();
		software.sortCountriesByPresident();

		Country z = software.binarySearchCountryByPresident("Maduro");
		assertTrue(z == software.getCountries().get(1));
		assertTrue(z.getName().equalsIgnoreCase("veneZuELa"));
		// .equals(new Country("Venezuela", 23, 4556.78, "Maduro", 428, "Pacifico")));

	}

}
