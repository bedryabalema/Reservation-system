/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

/**
 *
 * @author bedryabalema
 */
public class Economy implements ReservationType {
    private ArrayList<ArrayList<Passenger>> economyClassSeats;
	private static final int row = 20;
	private static final int col = 6;

	public Economy(){
		
		economyClassSeats = new ArrayList<ArrayList<Passenger>>(6);
		
		
		for(int i=0 ; i< col; i++)
		{
			economyClassSeats.add(i, new ArrayList<Passenger>());
			for(int j=0; j<row; j++)
				economyClassSeats.get(i).add(j,new Passenger());
		}		
		
	}


	@Override
	public void addPassenger(Passenger P) {
		
		String seat= P.getSeatId();
		int i,j;
		j= seat.charAt(0)-49;
		i= 70-(seat.charAt(1));
		economyClassSeats.get(i).set(j, P);		
	}
	
	public Passenger getPassenger(int i, int  j){
		return economyClassSeats.get(i).get(j);
	}
	
	public String getEmptySeat(char position){
		int i, j;
		String seat="";
		
		if(position=='w'|| position=='W'){
			
			for(i=0;i<col && seat.equals("");i+=5)
			{
				for( j=0;j<row && seat.equals("");j++)
				{
					if(economyClassSeats.get(i).get(j).getName() == null)
					{
						j++;
						seat =seat + j+ (char)(70-i);
						j--;		
					}
				}
			}
		}
		else if(position=='a'|| position=='A'){
			
			for(i=2;i<=3 && seat.equals("");i++)
			{
				for( j=0;j<row && seat.equals("");j++)
				{
					if(economyClassSeats.get(i).get(j).getName() == null)
					{
						j++;
						seat =seat + j+ (char)(70-i);
						j--;		
					}
				}
			}
		}
		else if(position=='c'|| position=='C'){
					
			for(i=1;i<=4 && seat.equals("");i+=3)
			{
				for( j=0;j<row && seat.equals("");j++)
				{
					if(economyClassSeats.get(i).get(j).getName() == null)
					{
						j++;
						seat =seat + j+ (char)(70-i);
						j--;		
					}
				}
			}
		}
		
		
		return seat;
		
	}


	@Override
	public boolean deletePassenger(String name) {
		
		for(int i=0; i<col;i++)
		{
			for(int j=0; j<row;j++)
			{
				if(economyClassSeats.get(i).get(j).getName()!=null && economyClassSeats.get(i).get(j).getName().equals(name))
				{
					economyClassSeats.get(i).set(j, new Passenger());
					
					return true;
					
				}
			}
		}
		
		
		return false;
	}

	@Override
	public void showAvailableSeats() {
		
		String seat="";
		
		for(int i=0; i<col;i++)
		{
			for(int j=0; j<row;j++)
			{
				if(economyClassSeats.get(i).get(j).getName()==null)
				{
					j++;
					seat =seat + j+ (char)(70-i);
					System.out.print(seat + " ");
					seat="";
					j--;
				}
				else
					System.out.print("   ");
			}
			System.out.println();
		}
		
	}
	@Override
	public void showReservedSeats() {
		String seat="";
		
		for(int i=0; i<col;i++)
		{
			for(int j=0; j<row;j++)
			{
				if(economyClassSeats.get(i).get(j).getName()!=null)
				{
					j++;
					seat =seat + j+ (char)(70-i);
					j--;
					System.out.println(seat + " " + economyClassSeats.get(i).get(j).getName() + " ");
					seat="";
					
				}
			}
		}
		
	}


	@Override
	public String getAnyEmptySeat() {
		String seat="";
		for(int i=0;i<col && seat.equals("");i++)
		{
			for(int j=0;j<row && seat.equals("");j++)
			{
				if(economyClassSeats.get(i).get(j).getName() == null)
				{
					j++;
					seat =seat + j+ (char)(70-i);
					j--;		
				}
			}
		}
		return seat;
	}

	@Override
	public int availableSeats() {
		int count=0;
		for(int i=0; i<col;i++)
		{
			for(int j=0; j<row;j++)
			{
				if(economyClassSeats.get(i).get(j).getName()==null)
				{
					count++;
					
				}
			}
		}
		return count;
    
}
}