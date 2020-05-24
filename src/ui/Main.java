package ui;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.*;
import customExceptions.*;

public class Main {

	private Software software;
	private Scanner reader;

	public Main() throws IOException {

		reader = new Scanner(System.in);
		software = new Software();

	}

	public static void main(String[] args) throws IOException {
		Main m = null;
		try {
			m = new Main();
		} catch (IOException a) {
			a.printStackTrace();
		}
		m.showMenu();
	}

	public void showMenu() throws IOException {

		int option = 0;
		while (option != 5) {
			System.out.println(" Welcome To PIB calculator.");
			System.out.println(" 1.To get a country's PIB.");
			System.out.println(" 2.To register a citizen.");
			System.out.println(" 3.To register a country.");
			System.out.println(" 4.To register an enterprise.");
			System.out.println(" 5.To register an employee.");
			System.out.println(" 6.To register a vehicle.");
			System.out.println(" 7.To show all countries respective a determinated attribute.");

			try {
				option = reader.nextInt();
				reader.nextLine();
			} catch (InputMismatchException a) {
				reader.nextLine();
				System.out.println("Please type a correct option.");
			}
			switch (option) {

			case (1):
				System.out.println("Type country's name");
				String name = reader.nextLine();
				System.out.println("This country's PIB is ");
				System.out.println("" + software.PIB(name));
				break;
			case (2):
				System.out.println("Citizen's name");
				String name1 = reader.nextLine();
				System.out.println("Citizen's ID");
				String ID = reader.nextLine();
				System.out.println("Citizen's spending");
				double spending = reader.nextDouble();
				System.out.println("Choose an option: 1.New Adult,2.New Child,3.New Pet");
				int choice = reader.nextInt();
				if (choice == 1) {
					System.out.println("Adult's liquor spending");
					double liquorSpending = reader.nextDouble();
					Citizen c = new Adult(name1, ID, spending, liquorSpending);
					System.out.println("Choose which country to add the citizen");
					String country = reader.nextLine();
					software.addCitizen(c, country);
				} else if (choice == 2) {
					System.out.println("If child has private education type 1, if it does not type 2");
					int education = reader.nextInt();
					boolean privateEducation = education == 1 ? true : false;
					System.out.println("Type education expense");
					double educationExpense = reader.nextDouble();
					Citizen c = new Child(name1, ID, spending, privateEducation, educationExpense);
					System.out.println("Choose which country to add the citizen");
					String country = reader.nextLine();
					software.addCitizen(c, country);
				} else if (choice == 3) {
					System.out.println("If pet has pedigree type 1, if it does not type 2");
					int pedigree = reader.nextInt();
					boolean isPedigree = pedigree == 1 ? true : false;
					System.out.println("Pet's race");
					String race = reader.nextLine();
					Citizen c = new Pet(name1, ID, spending, isPedigree, race);
					System.out.println("Choose which country to add the citizen");
					String country = reader.nextLine();
					software.addCitizen(c, country);
				}
				break;
			case (3):
				System.out.println("Country's name");
				String name2 = reader.nextLine();
				System.out.println("Country's population");
				int population = Integer.parseInt(reader.nextLine());
				System.out.println("Country's extension");
				double extension = Double.parseDouble(reader.nextLine());
				System.out.println("Country's president");
				String president = reader.nextLine();
				System.out.println("Country's public spending");
				double publicSpending = Double.parseDouble(reader.nextLine());
				System.out.println("Country's main sea");
				String sea = reader.nextLine();
				Country c = new Country(name2, population, extension, president, publicSpending, sea);
				try {
					software.addCountry(c);
				} catch (SameNameCountryException | SamePresidentsNameException e) {
					e.printStackTrace();
				}
				System.out.println(software.getCountries());
				break;
//				
			}
		}
	}

}
