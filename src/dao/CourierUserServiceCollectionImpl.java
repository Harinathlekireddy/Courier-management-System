package dao;

import entities.Courier;
import Exceptions.trackingNumberNotFoundException;
import Exceptions.invalidEmployeeIdException;
import entities.CourierCompanyCollection;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connectionutil.DBConnection;

public class CourierUserServiceCollectionImpl implements ICourierUserService {

    private CourierCompanyCollection companyObj;

    public CourierUserServiceCollectionImpl(CourierCompanyCollection companyObj) {
        this.companyObj = companyObj;
    }
     public CourierCompanyCollection getCompanyObj() {
    	 return companyObj;
     }
	@Override
	public String placeOrder(Courier courierobj) {
		try {
			Connection connection = DBConnection.getConnection(); 
		
		  
		    companyObj.addCourier(courierobj);

		  
		    String trackingNumber = courierobj.generateTrackingNumber();

		   
		    String insertQuery = "INSERT INTO Courier (SenderName, SenderAddress, ReceiverName, ReceiverAddress, Weight, Status, TrackingNumber, DeliveryDate, UserID) " +
		            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		    try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
		        preparedStatement.setString(1, courierobj.getSenderName());
		        preparedStatement.setString(2, courierobj.getSenderAddress());
		        preparedStatement.setString(3, courierobj.getReceiverName());
		        preparedStatement.setString(4, courierobj.getReceiverAddress());
		        preparedStatement.setDouble(5, courierobj.getWeight());
		        preparedStatement.setString(6, courierobj.getStatus());
		        preparedStatement.setString(7, trackingNumber);
		       
		        if (courierobj.getDeliveryDate() != null) {
		            preparedStatement.setDate(8, new java.sql.Date(courierobj.getDeliveryDate().getTime()));
		        } else {
		            preparedStatement.setNull(8, Types.DATE);
		        }
		        preparedStatement.setInt(9, courierobj.getUserId());

		        preparedStatement.executeUpdate();
		    }

		    return trackingNumber;
		} catch (SQLException e) {
		    e.printStackTrace();
		    throw new RuntimeException("Error placing the order");
		}
	}
	@Override
	public String getOrderStatus(String trackingnum) {
    	try {
    		Connection connection = DBConnection.getConnection();
    	
           
            String selectQuery = "SELECT Status FROM Courier WHERE TrackingNumber = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, trackingnum);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("Status");
                    } else {
                        throw new trackingNumberNotFoundException("No order found with the given tracking number.");
                    }
                }catch(trackingNumberNotFoundException e) {
                     return "No order found with the given Tracking Number: "+trackingnum;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("No order found with the given Tracking Number: "+trackingnum,e);
        }
    }


	@Override
	public boolean cancelOrder(String trackingnum) {
		try {
			Connection connection = DBConnection.getConnection();
            
            String updateQuery = "UPDATE Courier SET Status = 'Canceled' WHERE TrackingNumber = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, trackingnum);
                int rowsAffected = preparedStatement.executeUpdate();
             
                return rowsAffected > 0;
            
         }   
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new trackingNumberNotFoundException("No order found to cancel order with given Trackingnumber"+trackingnum);
        }
    }


	@Override
	public List<Courier> getAssignedOrder(int courierstaffID) {
		try {
			Connection connection = DBConnection.getConnection();
            
            List<Courier> assignedOrders = new ArrayList<>();
            String selectQuery = "SELECT * FROM Courier WHERE CourierID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setInt(1, courierstaffID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Courier courier = new Courier();
                      
                        courier.setCourierID(resultSet.getInt("CourierID"));
                        courier.setSenderName(resultSet.getString("SenderName"));
                        courier.setSenderAddress(resultSet.getString("SenderAddress"));
                        

                        assignedOrders.add(courier);
                    }
                }
            }
     
            return assignedOrders;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("No orders Assingned with given EmployeeID");
        }
    }
}