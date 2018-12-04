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
public class First implements ReservationType{
    private ArrayList<ArrayList<Passenger>> firstClassSeats;
	
	private static final int row = 2;
	private static final int col = 4;
	
	public First(){
		
		firstClassSeats = new ArrayList<ArrayList<Passenger>>(4);
		
		
		for(int i=0 ; i< col; i++)
		{
			firstClassSeats.add(i, new ArrayList<Passenger>());
			for(int j=0; j<row; j++)
				firstClassSeats.get(i).add(j,new Passenger());
		}
	
	}

	@Override
	public void addPassenger(Passenger p) {
		
		String seat= p.getSeatId();
		int i,j;
		j= seat.charAt(0)-49;
		i= 68-(seat.charAt(1));
		firstClassSeats.get(i).set(j, p);	
	}
	
	public Passenger getPassenger(int i, int  j){
		
		return firstClassSeats.get(i).get(j);
	}
	public String getEmptySeat(char position){
		int i, j;
		String seat="";
		
		if(position=='w'|| position=='W'){
			
			for(i=0;i<col && seat.equals("");i+=3)
			{
				for( j=0;j<row && seat.equals("");j++)
				{
					if(firstClassSeats.get(i).get(j).getName() == null)
					{
						j++;
						seat =seat + j+ (char)(68-i);
						j--;		
					}
				}
			}
		}
		else if(position=='a'|| position=='A'){
			
			for(i=1;i<=2&& seat.equals("");i++)
			{
				for( j=0;j<row&& seat.equals("");j++)
				{
					if(firstClassSeats.get(i).get(j).getName() == null)
					{
						j++;
						seat =seat + j+ (char)(68-i);
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
				if(firstClassSeats.get(i).get(j).getName()!=null && firstClassSeats.get(i).get(j).getName().equals(name))
				{
					firstClassSeats.get(i).set(j, new Passenger());
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
				if(firstClassSeats.get(i).get(j).getName()==null)
				{
					j++;
					seat =seat + j+ (char)(68-i);
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
				if(firstClassSeats.get(i).get(j).getName()!=null)
				{
					j++;
					seat =seat + j+ (char)(68-i);
					j--;
					System.out.println(seat + " "+ firstClassSeats.get(i).get(j).getName() + " ");
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
				if(firstClassSeats.get(i).get(j).getName() == null)
				{
					j++;
					seat =seat + j+ (char)(68-i);
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
				if(firstClassSeats.get(i).get(j).getName()==null)
				{
				count++;
					
				}
			}
		}
		return count;
        }
}
