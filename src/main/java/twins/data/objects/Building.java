package twins.data.objects;

import java.util.ArrayList;

public class Building {
	private String ID;
	private ArrayList<Facility> facilities;
	private boolean isReady;
	private ArrayList<Apartment> apartments;
	private int floor;
	private int numOfWorkers;

	public Building() {
		// Empty constructor
	}

	public Building(String iD, ArrayList<Facility> facilities, boolean isReady, ArrayList<Apartment> apartments,
			int floor, int numOfWorkers) {
		super();
		ID = iD;
		this.facilities = facilities;
		this.isReady = isReady;
		this.apartments = apartments;
		this.floor = floor;
		this.numOfWorkers = numOfWorkers;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public ArrayList<Facility> getFacilities() {
		return facilities;
	}

	public void setFacilities(ArrayList<Facility> facilities) {
		this.facilities = facilities;
	}

	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	public ArrayList<Apartment> getApartments() {
		return apartments;
	}

	public void setApartments(ArrayList<Apartment> apartments) {
		this.apartments = apartments;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getNumOfWorkers() {
		return numOfWorkers;
	}

	public void setNumOfWorkers(int numOfWorkers) {
		this.numOfWorkers = numOfWorkers;
	}

	@Override
	public String toString() {
		return "Building [ID=" + ID + ", facilities=" + facilities + ", isReady=" + isReady + ", apartments="
				+ apartments + ", floor=" + floor + ", numOfWorkers=" + numOfWorkers + "]";
	}

}
