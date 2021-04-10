package twins.digitalItemAPI;
public class Location {

	private double lat;
	private double log;

	public Location() {
		super();
	}

	public Location(double lat, double log) {
		super();
		this.lat = lat;
		this.log = log;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLog() {
		return log;
	}

	public void setLog(double log) {
		this.log = log;
	}

}
