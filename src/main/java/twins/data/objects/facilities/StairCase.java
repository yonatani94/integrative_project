package twins.data.objects.facilities;

import java.util.Arrays;

import twins.data.objects.Facility;

public class StairCase extends Facility {
	private int[] floors;

	public StairCase() {
		// Empty Constructor
	}

	public StairCase(String id, boolean isReady, int[] floors) {
		super(id, isReady);
		this.floors = floors;
	}

	public int[] getFloors() {
		return floors;
	}

	public void setFloors(int[] floors) {
		this.floors = floors;
	}

	@Override
	public String toString() {
		return "StairCase [floors=" + Arrays.toString(floors) + "]";
	}

}
