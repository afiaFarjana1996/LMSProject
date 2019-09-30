package com.ss.userMenu;

import java.util.*;

import com.ss.pojo.Book;

public class BookOperationMenu {
	private Scanner scan = new Scanner(System.in);
	//The following method is used to ask the user to provide all the information needed to create a new book.
    public List<String> createBookMenu() {
    	
    	List<String> bookInfo = new ArrayList<String>();
    	System.out.print("Book Name:");
		bookInfo.add(scan.nextLine());
		System.out.println("Book Author Name");
		bookInfo.add(scan.nextLine());
		System.out.println("Book Publisher Name");
		bookInfo.add(scan.nextLine());
		return bookInfo;
    }
    public int selectBookId(String extension,List<Book> bookList) {
 	   System.out.print("Select the First letter of the book you want to "+extension+" :"); //if this function is called for update, extension = "update";                                                                                     //if delete then extension = "delete" 
 	   String fLetter = scan.nextLine();   //First letter of the book name that user will input.
 	    bookList.stream()
 	   .filter(x -> x.getBookName().startsWith(fLetter))  //this will filter the list, narrow it down and print it.
 	   .forEach(x -> System.out.println("Id: "+x.getBookId()+ " Name:"+ x.getBookName()));
 	   System.out.println("Select id of the author you want to select: ");
 	    int selectedId = Integer.parseInt(scan.nextLine()); //user will select the id of the book they want to work on.
 	   
 	    return selectedId; //this is return the id of the book.
   }
   public String editBookName() {
	   System.out.println("Write the new Book Name: ");
	   String editedBookName = scan.nextLine();
	   return editedBookName;
   }
}
