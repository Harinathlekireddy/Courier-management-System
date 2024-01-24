package entities;

import java.util.ArrayList;
import java.util.List;

public class CourierCompany {
	private List<Courier> courierList;
	private String companyName;
	private List<Employee> employeeDetails;

	private CourierCompanyCollection courierCompanyCollection;
	public CourierCompany() {
		
	}

	public CourierCompany(String companyName) {
		this.courierList = new ArrayList<>();
		this.companyName = companyName;
		this.courierCompanyCollection = new CourierCompanyCollection();
	}
	public List<Courier> getCourierList() {
		return courierList;
	}

	public String getCompanyName() {
		return companyName;
	}

	public CourierCompanyCollection getCourierCompanyCollection() {
		return courierCompanyCollection;
	}
	public void setCourierCompanyCollection() {
		this.courierCompanyCollection=courierCompanyCollection;
	}

	public List<Employee> getEmployeeDetails() {
		return employeeDetails;
	}

	@Override
	public String toString() {
		return "CourierCompany{" + "companyName='" + companyName + '\'' + ", courierCompanyCollection="
				+ courierCompanyCollection + '}';
	}

	public void addCourier(Courier courier) {
		courierList.add(courier);
	}


}
