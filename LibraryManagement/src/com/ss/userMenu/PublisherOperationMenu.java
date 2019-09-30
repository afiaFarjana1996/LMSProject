package com.ss.userMenu;

import java.util.*;
import com.ss.pojo.Publisher;

public class PublisherOperationMenu {
	private Scanner sc = new Scanner(System.in);
public List<String> createPublisherMenu() {
	
	  List<String> newPublisherInfo = new ArrayList<String>();
	  System.out.print("Name of the publisher: ");
	  newPublisherInfo.add(sc.nextLine()); //enter the publisher name
	   System.out.print("Publisher Address: ");
	   newPublisherInfo.add(sc.nextLine());  //enter the publisher address
	   
	   return newPublisherInfo; //return this info to the service layer.
	}
public int selectPublisherId(String extension,List<Publisher> publisherList) {
	   System.out.print("Select the First letter of the publisher you want to "+extension+" :"); //if this function is called for update, extension = "update"; 
	                                                                                       //if delete then extension = "delete" 
	  
	   String fLetter = sc.nextLine();  //First letter of the author name that user will input.
	   publisherList.stream()
	   .filter(x -> x.getPublisherName().startsWith(fLetter))  //this will filter the list, narrow it down and print it.
	   .forEach(x -> System.out.println("Id: "+x.getPublisherId()+ " Name: "+ x.getPublisherName()+"Address: "+x.getPublisherAddress()));
	   System.out.println("Select id of the publisher you want to "+extension+" :");
	    int selectedId = Integer.parseInt(sc.nextLine()); //user will select the id of the author they want to work on.
	    
	    return selectedId; //this is return the id of the author.
}

public String[] whichOneToUpdate() {
	  System.out.println("Write 1 to update publisher name\nWrite 2 to update publisher address\nWrite 3 to update both.");
	  String choice = sc.nextLine();
	  String[] updatedInfo = new String[2];
	  switch(choice) {
	  case "1":
		  System.out.println("Write the new publisher name: ");
		  updatedInfo[0] = sc.nextLine();
		  updatedInfo[1] = null;
		  break;
	  case "2":
		  System.out.println("Write the new address: ");
		  updatedInfo[0]=null;
		  updatedInfo[1]=sc.nextLine();
		  break;
	  case "3":
		  System.out.println("Write the new publisher name: ");
		  updatedInfo[0] = sc.nextLine();
		  System.out.println("Write the new address: ");
		  updatedInfo[1] = sc.nextLine();
		  break;
	 default:
		 System.out.println("Not a valid number.");
		 updatedInfo[0] = null;
		 updatedInfo[1] = null;
	  }
	  
	  return updatedInfo;
}

}
