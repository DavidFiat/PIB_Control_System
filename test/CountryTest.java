import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.*;
import exceptions.*;

class CountryTest {

	private Enterprise enterprise;
	private Citizen citizen;

	@Test
	private void setupStage() {
		enterprise = new Transport("Confenalco", "535336", "22/11/1980", 65435.76, 54678);
		try {
			enterprise.addEnterprise(enterprise);
		} catch (RepeatedEnterpriseException e) {
			fail("The participant must be added");
		}

		Enterprise a = new Education("Icesi", "5675", "22/05/1990", 56993, "University", "Piedrahita");

		try {
			enterprise.addEnterprise(a);
		} catch (RepeatedEnterpriseException e1) {
			fail("The participant must be added");
		}

		Enterprise b = new Financial("Davivienda", "123", "12/07/1999", 456675, 5555, 12, false);

		try {
			enterprise.addEnterprise(b);
		} catch (RepeatedEnterpriseException e1) {
			fail("The participant must be added");
		}

		citizen = new Pet("Pepe", "3456", 5678, true, "Labrador");

		try {
			citizen.addCitizen(citizen);
		} catch (RepeatedCitizenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Citizen t = new Adult("Daniel", "64643456", 5678, 35);

		try {
			citizen.addCitizen(t);
		} catch (RepeatedCitizenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Citizen r = new Child("Juan", "1002567502", 34567, true, 345);

		try {
			citizen.addCitizen(r);
		} catch (RepeatedCitizenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void searchCitizenTest() {
		setupStage();

		// Searching an inexistent Participant
		String ID = "1";
		citizen.search(ID);
		assertNull(citizen.search(ID));

		// Searching one it does exist at the first position
		ID = "3456";
		citizen.search(ID);
		assertEquals("Pepe", citizen.search(ID).getName());
		assertEquals(true, ((Pet) citizen.search(ID)).isPedigree());

		// Searching one it does exist at other position
		ID = "64643456";
		citizen.search(ID);
		assertEquals("Daniel", citizen.search(ID).getName());
		assertEquals(5678, citizen.search(ID).getSpending());
	}

}
