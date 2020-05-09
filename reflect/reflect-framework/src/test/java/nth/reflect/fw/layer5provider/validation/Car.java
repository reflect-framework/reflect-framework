package nth.reflect.fw.layer5provider.validation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Car {

	private String manufacturer;
	private String licensePlate;
	private int seats;

	public Car(String manufacturer, String licencePlate, int seats) {
		this.manufacturer = manufacturer;
		this.licensePlate = licencePlate;
		this.seats = seats;
	}

	@NotNull
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@NotNull
	@Size(min = 2, max = 14)
	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	@Min(2)
	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	

}