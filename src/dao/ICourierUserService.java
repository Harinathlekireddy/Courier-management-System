package dao;

import entities.Courier;
import Exceptions.trackingNumberNotFoundException;
import Exceptions.invalidEmployeeIdException;
import java.util.List;

public interface ICourierUserService {
	String placeOrder(Courier courierobj);
	String getOrderStatus(String trackingnum)throws trackingNumberNotFoundException;
    boolean cancelOrder(String trackingnum)throws trackingNumberNotFoundException;
    List<Courier> getAssignedOrder(int courierstaffID) throws invalidEmployeeIdException;

}
