package dao;

import java.sql.*;

import connectionutil.DBConnection;
import entities.CourierCompanyCollection;
import entities.Employee;

public class CourierAdminServiceCollectionImpl extends CourierUserServiceCollectionImpl implements ICourierAdminService {

    public CourierAdminServiceCollectionImpl(CourierCompanyCollection companyObj) {
        super(companyObj);
    }
    

	@Override
	public int addCourierStaff(String staffname, String contactnum) {
		try {
			Connection connection = DBConnection.getConnection();
           
            int generatedEmployeeID;

            
            String insertQuery = "INSERT INTO Employee (Name, ContactNumber) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, staffname);
                preparedStatement.setString(2, contactnum);

                preparedStatement.executeUpdate();

               
                try (var generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedEmployeeID = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Creating courier staff failed, no ID obtained.");
                    }
                }
            }

         
            super.getCompanyObj().getEmployeeList().add(new Employee(generatedEmployeeID, staffname, null, contactnum, null, 0.0));

            return generatedEmployeeID;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding courier staff");
        }
    }

  

}