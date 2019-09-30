package com.ss.serviceClass;

import java.util.*;

import com.ss.dataAccess.PublisherDAC;
import com.ss.pojo.Author;
import com.ss.pojo.Publisher;
import com.ss.userMenu.PublisherOperationMenu;

public class PublisherOperations {
	private Scanner sc = new Scanner(System.in);
	PublisherDAC publisherDao = new PublisherDAC(); 
	PublisherOperationMenu publisherOperationMenuObj = new PublisherOperationMenu(); //object to communicate with the view.
	public void createPublisher() {
		   List<String> newPublisherInfo = publisherOperationMenuObj.createPublisherMenu();// createAuthorMenu() method is described in view which is used to take user 
           //input of all the publisher properties and returns List of string.
		   Publisher publisherObj = new Publisher();
		   publisherObj.setPublisherName(newPublisherInfo.get(0));
		   publisherObj.setPublisherAddress(newPublisherInfo.get(1));
		   ArrayList<Publisher> publisherList = publisherDao.readFromPublisher(); 
		   publisherObj.setPublisherId(publisherDao.getLastIndex() + 1); //getLastIndex() method returns the last value of id 
		                                                        //so that we can increment it and assign to the new created author.
		   publisherList.add(publisherObj);
		   publisherDao.writeToPublisher(publisherList); //write back to the file after adding the new author object.
	}
	//the following method deletes a publisher.
	public void deletePublisher() {
		ArrayList<Publisher> publisherList = publisherDao.readFromPublisher();
		int idOfPublisherToDelete = publisherOperationMenuObj.selectPublisherId("delete",publisherList); //ask user to select the id of the author he wants to delete.
		publisherList.removeIf(publisher -> publisher.getPublisherId() == idOfPublisherToDelete);
		BookOperations bookObj = new BookOperations();
		bookObj.deleteBookByDeletePublisher(idOfPublisherToDelete);
		publisherDao.writeToPublisher(publisherList);
	}
	//the following method updates a publisher.
	public void updatePublisher() {
		ArrayList<Publisher> publisherList = publisherDao.readFromPublisher();
		   int idOfPublisherToUpdate = publisherOperationMenuObj.selectPublisherId("update",publisherList); 
		 //selectPublisherId method asks user to provided the first letter of the publisher they want to update, this narrows down their search
			//and then they provide the publisher id which is returned by this method.
		   int publisherIndexInList = getPublisherIndexFromList(idOfPublisherToUpdate,publisherList);
		 //getPublisherIndexFromList method returns the publisher's index in the List<Publisher> .
		   String[] updatedInfo = publisherOperationMenuObj.whichOneToUpdate();
		 //contains an array of input. updatedInfo[0] is publisher's new name and updatedInfo[1] is the publisher's
           //new address. if user doesn't want to update anyone of these, a null value will be stored.
		   
		   if(updatedInfo[0]!=null) {
			   publisherList.get(publisherIndexInList).setPublisherName(updatedInfo[0]);
		   }
          if(updatedInfo[1]!=null) {
			   publisherList.get(publisherIndexInList).setPublisherAddress(updatedInfo[1]);
		   }
		   
		   publisherDao.writeToPublisher(publisherList);
	}
	//the following method returns the publisher's index in the List<Publisher> so that update operations can be easier.
	public int getPublisherIndexFromList(int publisherId,List<Publisher> publisherList) {
		   int listIndex=0;
		   for(int i=0;i<publisherList.size();i++) {
			   if(publisherList.get(i).getPublisherId()==publisherId) {
				   listIndex = i;
			   }
		   }
		   return listIndex;
	   }
	//the following method lets the user see the full list of publishers.
	public void seePublisherList() {
		ArrayList<Publisher> publisherList = publisherDao.readFromPublisher();
		publisherList.stream().forEach(x -> System.out.println("Id :"+x.getPublisherId()+" Name: "+x.getPublisherName()+" Address: "+x.getPublisherAddress()));
	}
	//when creating a new publisher, this method will return publisher id back to calling function. if publisher doesn't exist, their id will be 0
    //and a method createPublisherWhenCreatingBook that lets user to create a new publisher will be called.
	public int doesPublisherExist(String publisherName) {
		ArrayList<Publisher> publisherList = publisherDao.readFromPublisher();
		int publisherId = 0;
		for(int i=0; i<publisherList.size();i++) {
			if(publisherName.equalsIgnoreCase(publisherList.get(i).getPublisherName().toString()) ) {
				publisherId = publisherList.get(i).getPublisherId();
			}
		}
		if(publisherId == 0 ) {
			publisherId = createPublisherWhenCreatingBook(publisherName);
		}
		return publisherId;
	}
	//the following method creates a publisher when a book without any publisher is created.
	private int createPublisherWhenCreatingBook(String publisherName) {
		Publisher publisherObj = new Publisher();
		publisherObj.setPublisherName(publisherName);
			System.out.print("This is a new Publisher.\nAdd publisher address or Press Enter to keep it blank.");
				publisherObj.setPublisherAddress(sc.nextLine());
			
		   ArrayList<Publisher> publisherList = publisherDao.readFromPublisher(); 
		   int lastIndex = publisherDao.getLastIndex() + 1;
		   publisherObj.setPublisherId(lastIndex); //getLastIndex() method returns the last value of id 
		                                                        //so that we can increment it and assign to the new created author.
		   publisherList.add(publisherObj);
		   publisherDao.writeToPublisher(publisherList); //write back to the file after adding the new author object.
		   return lastIndex;
	}
	//the following method takes in publisher id and returns publisher name.
	public void getPublisherName(int publisherId) {
		ArrayList<Publisher> publisherList = publisherDao.readFromPublisher();
		publisherList.stream().filter(x -> x.getPublisherId()==publisherId).forEach(x -> System.out.println("Publisher: "+x.getPublisherName()));
	}
}
