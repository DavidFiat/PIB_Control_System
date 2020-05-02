package model;

import java.util.ArrayList;

import exceptions.RepeatedEmployeeException;

public class Employee {

	private String name;
	private String ID;
	private double salary;
	private String charge;
	private Employee next;
	private Employee prev;

	public Employee(String name, String iD, double salary, String charge) {
		this.name = name;
		ID = iD;
		this.salary = salary;
		this.charge = charge;
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

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public Employee getNext() {
		return next;
	}

	public void setNext(Employee next) {
		this.next = next;
	}

	public Employee getPrev() {
		return prev;
	}

	public void setPrev(Employee prev) {
		this.prev = prev;
	}

	public int compareTo(Employee a) {
		return ID.compareTo(a.getID());
	}

	public void addEmployee(Employee a) throws RepeatedEmployeeException {

		if (compareTo(a) == 0) {
			throw new RepeatedEmployeeException(a.getID());
		} else {
			if (next == null) {
				setNext(a);
				next.setPrev(this);
			} else {
				next.addEmployee(a);
			}
		}

	}

	public void returns(ArrayList<Employee> acum) {
		if (next != null)
			next.returns(acum);

		acum.add(this);

	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", ID=" + ID + ", salary=" + salary + ", charge=" + charge + "]";
	}
}
