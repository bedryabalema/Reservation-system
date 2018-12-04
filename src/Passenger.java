/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author bedryabalema
 */
public class Passenger {
    private String name;
	private String seatId;
	private String serviceClass;
	
	public Passenger(){
		name=null;
		seatId = null;
		serviceClass = null;
	}
	
	public Passenger(String aName, String aSeatId, String aServiceClass){
		name=aName;
		seatId = aSeatId;
		serviceClass = aServiceClass;
		
	}

	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getServiceClass() {
		return serviceClass;
	}
	public void setServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
        }
}
