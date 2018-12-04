/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author bedryabalema
 */
public class ReservationSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ReservationType fc = new First();
	ReservationType ec = new Economy();
	ArrayList<Group> groups = new ArrayList<>();
		
        String fileName = "CL34";
	String line="";		
		
	if(fileName!=null){
			
		try{
			Scanner fileIn = new Scanner(Paths.get("CL34.txt"));
				
			while(fileIn.hasNext()){
                            fileIn.useDelimiter(";");//read until ;
                            line=fileIn.nextLine();
                            String[] temp = line.split(",");
                                    String serviceClass = temp[temp.length-1].substring(0, temp[temp.length-1].length()-1);
                                    if(temp.length==3)
                                    {
                                            Passenger p = new Passenger(temp[0], temp[1],serviceClass);
                                            if(p.getServiceClass().equals("first"))
                                            {
                                                    fc.addPassenger(p);
                                            }
                                            else if(p.getServiceClass().equals("economy"))
                                            {
                                                    ec.addPassenger(p);
                                            }
                                    }
                                    else
                                    {
                                        String gName=temp[0];
                                        Passenger[] passengers =new Passenger[(temp.length-2)/2];
                                        int j=0;
                                        for(int i=1; i<temp.length-1; i+=2)
                                            {
                                                Passenger p= new Passenger(temp[i],temp[i+1],serviceClass);
                                                passengers[j++] = p;
                                            }
                                            Group g= new Group(gName, passengers);
                                            groups.add(g);
                                            if(g.getPassengers()[0].getServiceClass().equals("first"))
                                            {
                                                    for(Passenger p: g.getPassengers())
                                                            fc.addPassenger(p);
                                            }
                                            else if(g.getPassengers()[0].getServiceClass().equals("economy"))
                                            {
                                                    for(Passenger p: g.getPassengers())
                                                            ec.addPassenger(p);
                                            }

                                    }

                            }
                            fileIn.close();
                    }catch(Exception e){
                            try {
                                    PrintWriter out = new PrintWriter("CL34.txt");
                                    out.close();
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }

                    }
            }	

            Scanner in=new Scanner(System.in);
            String command="";
            while(!command.toUpperCase().equals("Q")) {

              System.out.println("\n Add [P]assenger \n Add [G]roup \n [C]ancel Reservations \n Print Seating [A]vailability Chart \n Print [M]anifest \n [Q]uit");
              command=in.next();
              if(command.toUpperCase().equals("P"))
              {
                        String seat="";
                            System.out.println("Name: ");
                            String individualName=in.next();
                            System.out.println("Service Class(first/economy): ");
                            String sClass=in.next();
                            System.out.println("Seat Preference (W/C/A): ");
                            char pref=in.next().charAt(0);
                            if(sClass.equals("first"))
                            {
                                    seat = fc.getEmptySeat(pref);
                                    if(!seat.equals(""))
                                    {
                                            Passenger p= new Passenger(individualName, seat, sClass);
                                            fc.addPassenger(p);
                                            System.out.println("\n Seat " + p.getSeatId() + " Resrved for Passenger: " + p.getName());
                                    }
                                    else
                                    {
                                            System.out.println("\n Sorry! Prefered Seat Not Available. Please Try Again.");
                                    }

                            }
                            else if(sClass.equals("economy"))
                            {
                                    seat = ec.getEmptySeat(pref);
                                    if(!seat.equals(""))
                                    {
                                            Passenger p= new Passenger(individualName, seat, sClass);
                                            ec.addPassenger(p);
                                            System.out.println("\n Seat " + p.getSeatId() + " Resrved for Passenger: " + p.getName());
                                    }
                                    else
                                    {
                                            System.out.println("\n Sorry! Prefered Seat Not Available. Please Try Again.");
                                    }

                            }
			  
		  }
		  if(command.toUpperCase().equals("G"))
		  {
			 // ArrayList<String> seats = new ArrayList<>();
			    System.out.println("Group Name: ");
				String groupName=in.next();
				System.out.println("Service Class(first/economy): ");
				String sClass=in.next();
				in.nextLine();
				System.out.println("Enter Passenger Names Seperated by comma: ");
				
				String names = in.nextLine();
				String[] splitNames= names.split(",");
				Passenger[] passengers =new Passenger[(splitNames.length)];
				if(sClass.equals("first"))
				{
					boolean groupSeated=true;
					int freeSeats= fc.availableSeats();
					if(freeSeats>= splitNames.length)
					{
						int i=0;
						for(String s: splitNames)
						{							
							String seat = fc.getAnyEmptySeat();
							if(!seat.equals(""))
							{
								Passenger p= new Passenger(s, seat, sClass);
								passengers[i++]=p;
								fc.addPassenger(p);
							}
							else
							{
								System.out.println("\n Sorry! Enough Seats Not Available. Please Try Later.");
								groupSeated = false;
								break;
							}
						}
						if(groupSeated){
							Group g= new Group(groupName, passengers);
							groups.add(g);
							for(Passenger p: passengers)
							{
								System.out.println("\n Seat " + p.getSeatId() + " Resrved for Passenger: " + p.getName());
							}
						}
					}
				}
				else if(sClass.equals("economy"))
				{
					boolean groupSeated = true;;
					int freeSeats= ec.availableSeats();
					if(freeSeats>= splitNames.length)
					{
						int i=0;
						for(String s: splitNames)
						{							
							String seat = ec.getAnyEmptySeat();
							if(!seat.equals(""))
							{
								Passenger p= new Passenger(s, seat, sClass);
								passengers[i++]=p;
								ec.addPassenger(p);
							}
							else
							{
								System.out.println("\n Sorry! Enough Seats Not Available. Please Try Later.");
								groupSeated = false;
								break;
							}
						}
						if(groupSeated){
							Group g= new Group(groupName, passengers);
							groups.add(g);
							for(Passenger p: passengers)
							{
								System.out.println("\n Seat " + p.getSeatId() + " Resrved for Passenger: " + p.getName());
							}
						}
					}
				}
		  }
		  if(command.toUpperCase().equals("C"))
		  {
			  System.out.println("[I]ndividual or [G]roup? ");
			  String p=in.next();
			  if(p.toUpperCase().equals("I"))
			  {
				  System.out.println("Name: ");
				  String name=in.next();
				  if(!fc.deletePassenger(name))
					 ec.deletePassenger(name);
				  
			  }
			  else if(p.toUpperCase().equals("G"))
			  {
				  System.out.println("Group Name: ");
				  String gname=in.next();
				  
				  for(Group g: groups)
				  {
					 if(g.getGroupName().equals(gname) )
					 {
						 for(Passenger temp: g.getPassengers())
						 {
							 if(temp.getServiceClass().equals("first"))
								 fc.deletePassenger(temp.getName());
							 else
								 ec.deletePassenger(temp.getName());
						 }
						 
						 groups.remove(g);
						 break;
					 }
				  }
				  
			  }
			  
		  }
		  if(command.toUpperCase().equals("A"))
		  {
			  System.out.println("Service Class: ");
			  String aClass=in.next();	
				if(aClass.toLowerCase().equals("first"))
				{
					fc.showAvailableSeats();
				}
				else if(aClass.toLowerCase().equals("economy"))
				{
					ec.showAvailableSeats();
				}
		  }
		  if(command.toUpperCase().equals("M"))
		  {
			  System.out.println("Service Class: ");
			  String aClass=in.next();
				if(aClass.toLowerCase().equals("first"))
				{
					fc.showReservedSeats();
				}
				else if(aClass.toLowerCase().equals("economy"))
				{
					ec.showReservedSeats();
				}
								
		  }
		  if(command.toUpperCase().equals("Q"))
		  {
			  try {
				  	String s="";
					PrintWriter out = new PrintWriter("CL34.txt");
					for(Group g: groups)
					  {					
						 out.print(g.getGroupName()+",");
						 for(Passenger temp: g.getPassengers())
						 {
							 s =temp.getServiceClass();
							 if(s.equals("first"))
							 {
								 out.print(temp.getName()+","+temp.getSeatId()+",");
								 fc.deletePassenger(temp.getName());
							 }
							 else
							 {
								 out.print(temp.getName()+","+temp.getSeatId()+",");
								 ec.deletePassenger(temp.getName());
							 }
						 }
						 out.print(s + ";\n");
						// groups.remove(g);
						 
					  }
					
					 for(int i=0; i<4; i++)
					 {
						 for(int j=0;j<2;j++ )
						 {
							Passenger p =  fc.getPassenger(i, j);
							if(p.getName()!=null)
							{
								out.print(p.getName()+","+p.getSeatId()+","+ p.getServiceClass()+";\n");
							}
						 }
						 
					 }
					 for(int i=0; i<6; i++)
					 {
for(int j=0;j<20;j++ )
						 {
							Passenger p =  ec.getPassenger(i, j);
							if(p.getName()!=null)
							{
								out.print(p.getName()+","+p.getSeatId()+","+ p.getServiceClass()+";\n");
							}
						 }
						 
					 }
					  out.close();
				} catch (Exception e1) {
				
					e1.printStackTrace();
				}
			  
		  }
	   }
	   in.close();
        // TODO code application logic here
    }
    
}
