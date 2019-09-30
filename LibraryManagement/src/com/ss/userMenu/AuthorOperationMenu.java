package com.ss.userMenu;

import java.util.*;

import com.ss.pojo.Author;

public class AuthorOperationMenu {
	private Scanner sc = new Scanner(System.in);
  public List<String> createAuthorMenu() {
	  Scanner sc = new Scanner(System.in);
	  List<String> newAuthorInfo = new ArrayList<String>();
	  System.out.print("Name of the author: ");
	  String authorName = sc.nextLine();
	  newAuthorInfo.add(authorName); //enter the author name
	   System.out.print("Author Phone Number: ");
	   String pn = sc.next();
	   newAuthorInfo.add(pn);  //enter the author phone number
	   return newAuthorInfo; //return this info to the service layer.
  }
  public int selectAuthorId(String extension,List<Author> authorList) {
	   System.out.print("Select the First letter of the author you want to "+extension+" :"); //if this function is called for update, extension = "update";                                                                                      //if delete then extension = "delete" 
	   String fLetter = sc.nextLine();   //First letter of the author name that user will input.
	   authorList.stream()
	   .filter(x -> x.getAuthorName().startsWith(fLetter))  //this will filter the list, narrow it down and print it.
	   .forEach(x -> System.out.println("Id: "+x.getAuthorId()+ " Name:"+ x.getAuthorName()));
	   System.out.println("Select id of the author you want to select: ");
	    int selectedId = Integer.parseInt(sc.nextLine()); //user will select the id of the author they want to work on.
	    return selectedId; //this is return the id of the author.
  }
  public String[] whichAuthorInfoToUpdate() {
	  
	  System.out.println("Write 1 to update author name.\n2 to update author phone number."
	  		+ "\n3 to update both.");
	  String[] s = new String[2];
	  String choice = sc.nextLine();
	  switch(choice) {
	  case "1":
		  System.out.println("Write the new Author Name: ");
		  s[0]=sc.nextLine();
		  s[1] = null;
		  break;
	  case "2":
		  System.out.println("Write the new Author phone number: ");
		  s[0]= null;
		  s[1] = sc.nextLine();
		  break;
	  case "3":
		  System.out.println("Write the new Author Name: ");
		  s[0]= sc.nextLine();
		  System.out.println("Write the new Author phone number: ");
		  s[1] = sc.nextLine();
		  break;
	  default:
		  System.out.println("Wrong number.");
		  s[0]=null;
		  s[1]=null;
	  }
	  return s;
  }
  
  public String createAuthorPhoneNumberWhenCreatingNewBook() {
	  Scanner sc = new Scanner(System.in);
	  System.out.println("Author Phone Number: ");
	  String authorPhoneNumber = sc.nextLine();
	  
	  return authorPhoneNumber;
  }
}
