package main;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import dao.CourierAdminServiceCollectionImpl;
import dao.CourierUserServiceCollectionImpl;
import entities.Courier;
import entities.CourierCompany;
import entities.CourierCompanyCollection;;

public class mainmodule {
	public static void main(String [] Args) {
		 
        CourierCompanyCollection companyCollection = new CourierCompanyCollection();

        
        CourierCompanyCollection courierCompany = new CourierCompanyCollection();

        
        CourierUserServiceCollectionImpl userService = new CourierUserServiceCollectionImpl(courierCompany);
        CourierAdminServiceCollectionImpl adminService = new CourierAdminServiceCollectionImpl(courierCompany);

   
    Scanner scanner = new Scanner(System.in);
    int choice;

    do {
        System.out.println(" Courier Management System Menu: ");
        System.out.println("1. Place a new courier order");
        System.out.println("2. Get courier order status");
        System.out.println("3. Cancel courier order");
        System.out.println("4. Get assigned orders");
        System.out.println("5. Add new courier staff");
        System.out.println("0. Exit");

        System.out.print("Enter your choice: ");
        choice = scanner.nextInt();

        switch (choice) {
            case 1:
            
                System.out.println("Enter order details:");
                System.out.print("Sender Name: ");
                String senderName = scanner.next();

                
                System.out.print("Sender Address: ");
                String senderAddress = scanner.next();

                System.out.print("Receiver Name: ");
                String receiverName = scanner.next();

                System.out.print("Receiver Address: ");
                String receiverAddress = scanner.next();

                System.out.print("Weight: ");
                double weight = scanner.nextDouble();
                
                System.out.print("Status: ");
                String Status = scanner.next();
                
                System.out.print("UserID: ");
                int UserID = scanner.nextInt();

                System.out.print("Delivery Date (YYYY-MM-DD): ");
                String deliveryDateString = scanner.next();
             
                java.sql.Date deliveryDate = null;
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date parsedDate = dateFormat.parse(deliveryDateString);
                    deliveryDate = new java.sql.Date(parsedDate.getTime());
                } catch (ParseException e) {
                    System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                    break;
                }

               
                Courier newCourier = new Courier();
                newCourier.setSenderName(senderName);
                newCourier.setSenderAddress(senderAddress);
                newCourier.setReceiverName(receiverName);
                newCourier.setReceiverAddress(receiverAddress);
                newCourier.setWeight(weight);
                newCourier.setStatus(Status);
                newCourier.setUserId(UserID);
                newCourier.setDeliveryDate(deliveryDate);

                
                String trackingNumber = userService.placeOrder(newCourier);
                System.out.println("Order placed. Tracking number: " + trackingNumber);
                break;
            case 2:
                System.out.print("Enter tracking number: ");
                String getOrderStatusTrackingNumber = scanner.next();
                String orderStatus = userService.getOrderStatus(getOrderStatusTrackingNumber);
                System.out.println("Order Status: " + orderStatus);
                break;

            case 3:
                
                System.out.print("Enter tracking number: ");
                String cancelTrackingNumber = scanner.next();
                boolean isOrderCanceled = userService.cancelOrder(cancelTrackingNumber);
                System.out.println("Order Canceled: " + isOrderCanceled);
                break;

            case 4:
                System.out.print("Enter courier staff ID: ");
                int courierStaffId = scanner.nextInt();
                List<Courier> assignedOrders = userService.getAssignedOrder(courierStaffId);
                System.out.println("Assigned Orders: " + assignedOrders);
                break;

            case 5:
                System.out.println("Enter new courier staff details:");
                System.out.print("Name: ");
                String staffName = scanner.next();
                System.out.print("Contact Number: ");
                String contactNumber = scanner.next();
                int generatedEmployeeID = adminService.addCourierStaff(staffName, contactNumber);
                System.out.println("Courier staff added. Employee ID: " + generatedEmployeeID);
                break;

            case 0:
                System.out.println("Exiting Courier Management System. Goodbye!");
                break;

            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
    } while (choice != 0);
}
}