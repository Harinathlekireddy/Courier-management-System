package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourierCompanyCollection {

    private List<Courier> courierList;
    private List<Employee> employeeList;
    private Map<Integer, Location> locationMap;

    public CourierCompanyCollection() {
        this.courierList = new ArrayList<>();
        this.employeeList = new ArrayList<>();
        this.locationMap = new HashMap<>();
    }

    public List<Courier> getCourierList() {
        return courierList;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public Map<Integer, Location> getLocationMap() {
        return locationMap;
    }

    // Add methods to manipulate the collections as needed
    public void addCourier(Courier courier) {
        courierList.add(courier);
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void addLocation(int locationId, Location location) {
        locationMap.put(locationId, location);
    }

    public List<Courier> getAssignedOrders(int courierStaffId) {
        List<Courier> assignedOrders = new ArrayList<>();
        for (Courier courier : courierList) {
           
            if (Employee.getEmployeeID() == courierStaffId) {
                assignedOrders.add(courier);
            }
        }
        return assignedOrders;
    }

    
}


