package entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Courier {
	private static int trackingNumberCounter = 1000;

	private int courierID;
	private String senderName;
	private String senderAddress;
	private String receiverName;
	private String receiverAddress;
	private double weight;
	private String status;
	private String trackingNumber;
	private Date deliveryDate;
	private int userId;

	
	public Courier() {
		 trackingNumberCounter++;
	        this.trackingNumber = generateTrackingNumber();
	    }



	
	public Courier(int courierID, String senderName, String senderAddress, String receiverName, String receiverAddress,
			double weight, String status, String trackingNumber, Date deliveryDate, int userId) {
		this.courierID = courierID;
		this.senderName = senderName;
		this.senderAddress = senderAddress;
		this.receiverName = receiverName;
		this.receiverAddress = receiverAddress;
		this.weight = weight;
		this.status = status;
		this.trackingNumber = trackingNumber;
		this.deliveryDate = deliveryDate;
		this.userId = userId;
	}


	public int getCourierID() {
		return courierID;
	}

	public void setCourierID(int courierID) {
		this.courierID = courierID;
	}
	
	
    
	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderAddress() {
		return senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Courier{" + "courierID=" + courierID + ", senderName='" + senderName + '\'' + ", senderAddress='"
				+ senderAddress + '\'' + ", receiverName='" + receiverName + '\'' + ", receiverAddress='"
				+ receiverAddress + '\'' + ", weight=" + weight + ", status='" + status + '\'' + ", trackingNumber='"
				+ trackingNumber + '\'' + ", deliveryDate=" + deliveryDate + ", userId=" + userId + '}';
	}
		
		public String generateTrackingNumber() {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	        String timestamp = dateFormat.format(new Date());
	        return "TN" + timestamp + trackingNumberCounter;
	    }
	
	}
 

