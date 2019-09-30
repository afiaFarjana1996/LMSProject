package com.ss.serviceClass;

import java.util.*;
import com.ss.dataAccess.BookDAC;
import com.ss.pojo.Book;
import com.ss.userMenu.BookOperationMenu;

public class BookOperations {
	BookDAC bookDao = new BookDAC(); //creating dao of Book
	BookOperationMenu bookOperationMenuObj = new BookOperationMenu(); //object to communicate with the view.
	
	//service method: calls createBookMenu to get information from user and create a new book.
	public void createBook() {
		BookOperationMenu bookMenuObj = new BookOperationMenu();
		List<String> bookInfo = bookMenuObj.createBookMenu();// createAuthorMenu() method is described in view which is used to take user 
        //input of all the book properties and returns List of string.
    	Book bookObj = new Book();
        bookObj.setBookName(bookInfo.get(0));
		//identify the author or create a new author.
		AuthorOperations authorObj = new AuthorOperations();
		int authorId = authorObj.doesAuthorExist(bookInfo.get(1)); 
		     //doesAuthorExist() checks if the provided exists in author file. if exists, then returns the id which is stored as authorId
		     //if doesn't exist, then calls method to create a new author obj with that name. The user will be asked to provide author phone number.
		bookObj.setBookAuthorId(authorId);
		PublisherOperations publisherObj = new PublisherOperations();
		int publisherId = publisherObj.doesPublisherExist(bookInfo.get(2));
		 //doesPublisherExist() functions the same way as doesAuthorExist() function.
		bookObj.setBookPublisherId(publisherId);
		ArrayList<Book> bookList = bookDao.readFromBook();
		bookObj.setBookId(bookDao.getLastIndex() + 1);//getLastIndex() method returns the last value of id 
                                                    //so that we can increment it and assign to the new created book.
		bookList.add(bookObj);
		bookDao.writeToBook(bookList); //write back to the file after adding the new book object.
	}
	
	//The following method is used to update a book. since after publishing author name or publisher name doesn't changed, only book name is updated.
	public void updateBook() {
		ArrayList<Book> bookList = bookDao.readFromBook();
		int bookId = bookOperationMenuObj.selectBookId("update", bookList);
		//selectBookId method asks user to provided the first letter of the book they want to update, this narrows down their search
		//and then they provide the book id which is returned by this method.
		int bookIndexInList = getBookIndexInList(bookId,bookList);
		//getBookIndexInList method returns the book's index in the List<Book> .
		String updatedBookName = bookOperationMenuObj.editBookName();
		//editBookName returns the new name user wants to set up.
		bookList.get(bookIndexInList).setBookName(updatedBookName);
		bookDao.writeToBook(bookList);
	}
	//getBookIndexInList method returns the book's index in the List<Book> so that update operations can be easier.
	public int getBookIndexInList(int bookId,List<Book> bookList) {
		int listIndex=0;
		   for(int i=0;i<bookList.size();i++) {
			   if(bookList.get(i).getBookId()==bookId) {
				   listIndex = i;
			   }
		   }
		   return listIndex;
	}
	//the following method deletes a book.
	public void deleteBook() {
		   ArrayList<Book> bookList = bookDao.readFromBook();
		   int idOfBookToDelete = bookOperationMenuObj.selectBookId("delete",bookList); //ask user to select the id of the author he wants to delete.
		   bookList.removeIf(book -> book.getBookId() == idOfBookToDelete); //if the  id matches anywhere in the List, it will be removed.
		   bookDao.writeToBook(bookList);
	}
	//the following method removes a book when an author is deleted.
	public void deleteBookByDeleteAuthor(int deletedAuthorId) {
		ArrayList<Book> bookList = bookDao.readFromBook();
		bookList.removeIf(x -> x.getBookAuthorId()==deletedAuthorId); //this will remove all the book where authorId matches the author's id deleted.
		 bookDao.writeToBook(bookList);
	}
	//the following method removes a book when a publisher is removed.
	public void deleteBookByDeletePublisher(int deletedPublisherId) {
		ArrayList<Book> bookList = bookDao.readFromBook();
		bookList.removeIf(x -> x.getBookPublisherId()==deletedPublisherId);
		 bookDao.writeToBook(bookList);
	}
 //the following method lets the user see the full list of books.
	public void seeBookList() {
		ArrayList<Book> bookList = bookDao.readFromBook();
		AuthorOperations authorObj = new AuthorOperations();
		PublisherOperations publisherObj = new PublisherOperations();
		for(int i=0;i<bookList.size();i++){
			System.out.println("Id: "+bookList.get(i).getBookId()+" "+"Name: "+bookList.get(i).getBookName());
			authorObj.getAuthorName(bookList.get(i).getBookAuthorId()); //as the book list stores id of the author, getAuthorName method will be
			                                                            //used to get the author's name.
			publisherObj.getPublisherName(bookList.get(i).getBookPublisherId()); //as the book list stores id of the publisher, getPublisherName method will be
                                                                                 //used to get the author's name.
		}
	}
	 
}
