package com.ss.serviceClass;

import java.util.*;
import com.ss.dataAccess.AuthorDAC;
import com.ss.pojo.Author;
import com.ss.userMenu.AuthorOperationMenu;

public class AuthorOperations {
	
	AuthorDAC authorDao = new AuthorDAC(); //creating dao of author
	AuthorOperationMenu authorOperationMenuObj = new AuthorOperationMenu(); //object to communicate with the view.
	
	//method to create a new author
   public void createAuthor() {
	   List<String> newAuthorInfo = authorOperationMenuObj.createAuthorMenu(); // createAuthorMenu() method is described in view which is used to take user 
	                                     //input of all the author properties and returns List of string.
	   Author authorObj = new Author();
		authorObj.setAuthorName(newAuthorInfo.get(0)); 
		authorObj.setAuthorPhoneNumber(newAuthorInfo.get(1));
	   ArrayList<Author> authorList = authorDao.readFromAuthor(); 
	   authorObj.setAuthorId(authorDao.getLastIndex() + 1); //getLastIndex() method returns the last value of id 
	                                                        //so that we can increment it and assign to the new created author.
	   authorList.add(authorObj);
	   authorDao.writeToAuthor(authorList); //write back to the file after adding the new author object.
   }
   //the following method creates an author when a book without any author is created.
   public int createAuthorWhenCreatingBook(String authorName) {
	   Scanner sc = new Scanner(System.in);
	   Author authorObj = new Author();
	   authorObj.setAuthorName(authorName);
		System.out.print("This is a new Author.\nAdd author Phone Number or Press Enter to keep it blank.");
		authorObj.setAuthorPhoneNumber(sc.nextLine());
		
	   ArrayList<Author> authorList = authorDao.readFromAuthor(); 
	   int lastIndex = authorDao.getLastIndex() + 1;
		authorObj.setAuthorId(lastIndex); //getLastIndex() method returns the last value of id 
	                                                        //so that we can increment it and assign to the new created author.
	   authorList.add(authorObj);
	   authorDao.writeToAuthor(authorList); //write back to the file after adding the new author object.
	   return lastIndex;
   }
   //the following method lets the user see the full list of authors.
   public void seeListOfAuthor() {
	   ArrayList<Author> authorList = authorDao.readFromAuthor();
		authorList.forEach(x -> System.out.println("Id: "+x.getAuthorId()+" Name: "+x.getAuthorName()+" Phone Number: "+x.getAuthorPhoneNumber()));
   }
   
   //method to delete author.
   public void deleteAuthor() {
	   ArrayList<Author> authorList = authorDao.readFromAuthor();
	   int idOfAuthorToDelete = authorOperationMenuObj.selectAuthorId("delete",authorList); //ask user to select the id of the author he wants to delete.
	   authorList.removeIf(author -> author.getAuthorId() == idOfAuthorToDelete);
	   BookOperations bookObj = new BookOperations();
	   bookObj.deleteBookByDeleteAuthor(idOfAuthorToDelete);
	   authorDao.writeToAuthor(authorList);
   }
   
   //method to update information about an author
   public void updateAuthor() {
	   ArrayList<Author> authorList = authorDao.readFromAuthor();
	   int authorId = authorOperationMenuObj.selectAuthorId("update", authorList); //gets the id of the author.
	   int authorIndexInList = getAuthorIndexFromList(authorId,authorList); //returns the list index where we can find that id.
	   String[] newUpdatedValues = authorOperationMenuObj.whichAuthorInfoToUpdate(); //whichAuthorInfoToUpdate method returns a String[] which
	                         //contains an array of input. newUpdatedValues[0] is author's new name and newUpdatedValues[1] is the author's
	                         //new phone number. if user doesn't want to update anyone of these, a null value will be stored.
	   if(newUpdatedValues[0]!=null) { //checking if the author's new name is empty.
		   authorList.get(authorIndexInList).setAuthorName(newUpdatedValues[0]);
	   }
	   if(newUpdatedValues[1]!=null) { //checking if author's new phone number is empty.
		   authorList.get(authorIndexInList).setAuthorPhoneNumber(newUpdatedValues[1]);
	   }
       authorDao.writeToAuthor(authorList);
   }
 //the following method returns the author's index in the List<Book> so that update operations can be easier.
   public int getAuthorIndexFromList(int authorId,List<Author> authorList) {
	   int listIndex=0;
	   for(int i=0;i<authorList.size();i++) {
		   if(authorList.get(i).getAuthorId()==authorId) {
			   listIndex = i;
		   }
	   }
	   return listIndex;
   }
   //when creating a new book, this method will return author id back to calling function. if author doesn't exist, their id will be 0
    //and a method createAuthorWhenCreatingBook will be called that lets user to create a new author.
   public int doesAuthorExist(String authorName) {
		ArrayList<Author> authorList = authorDao.readFromAuthor();
		int authorId = 0;
		for(int i=0; i<authorList.size();i++) {
			if(authorName.equalsIgnoreCase(authorList.get(i).getAuthorName().toString()) ) {
				authorId = authorList.get(i).getAuthorId();
			}
		}
		if(authorId == 0 ) {
			authorId = createAuthorWhenCreatingBook(authorName);
		}
		return authorId;
	}
   //this method takes in author's id and returns the name of the author.
   public void getAuthorName(int authorId) {
	   ArrayList<Author> authorList = authorDao.readFromAuthor();
	   authorList.stream().filter(x -> x.getAuthorId()==authorId).forEach(x -> System.out.println("Author: "+x.getAuthorName()));
   }
  
}
