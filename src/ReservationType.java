/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author bedryabalema
 */
public interface ReservationType {
    public void addPassenger(Passenger p);
	default public Passenger getPassenger(int i, int  j){return null;}
	public boolean deletePassenger(String name);
	public void showAvailableSeats();
	public void showReservedSeats();
	public String getEmptySeat(char position);
	public String getAnyEmptySeat();
	public int availableSeats();
	
    
}
