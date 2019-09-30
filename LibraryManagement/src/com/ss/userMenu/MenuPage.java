/**
 * 
 */
package com.ss.userMenu;
import java.util.*;
import com.ss.serviceClass.AuthorOperations;
import com.ss.serviceClass.BookOperations;
import com.ss.serviceClass.PublisherOperations;

/**
 * @author afia
 *
 */
public class MenuPage {
	private Scanner scan = new Scanner(System.in);
	
	public void mainMenu() {
		System.out.println("Select what you wish to work on: \n1)Book\n2)Author\n3)Publisher");
	     String choice = scan.nextLine();
	     MenuPage optionObject = new MenuPage();
	     switch(choice) {
	     case "1":
	    	 optionObject.chooseBookOptions(); //book menu
	    	 break;
	     case "2":
	    	 optionObject.chooseAuthorOptions(); //author menu
	    	 break;
	     case "3":
	    	 optionObject.choosePublisherOptions(); //publisher menu
	    	 break;
	    default:
	    	System.out.println("Invalid options");
	    	WelcomePage.main(null);
	     }
	}

	public void chooseBookOptions() {
		System.out.println("Choose one of the following actions:\n"
				+ "1)Create a new Book.\n"
				+ "2)Edit information of an existing Book\n"
				+ "3)Delete an existing Book\n"
				+ "4)See full list of Books\n"
				+ "5)To go back to main menu");
		
		int bookChoice = Integer.parseInt(scan.nextLine());
		BookOperations bookObj = new BookOperations();
		switch(bookChoice) {
		case 1:
			bookObj.createBook();
			break;
		case 2:
			bookObj.updateBook();
			break;
		case 3:
			bookObj.deleteBook();
			break;
		case 4:
			bookObj.seeBookList();
			break;
		case 5:
			mainMenu();
			break;
		default:
			System.out.println("Invalid Number.");
			chooseBookOptions();
		}
		
	}
	
	public void chooseAuthorOptions() {
		System.out.println("Choose one of the following actions:\n"
				+ "1)Create a new Author.\n"
				+ "2)Edit information of an existing Author\n"
				+ "3)Delete an existing Author\n"
				+ "4)See full list of Authors\n"
				+ "5)Go back to main menu");
		
		int authorChoice = Integer.parseInt(scan.nextLine());
		AuthorOperations authorObj = new AuthorOperations();
		switch(authorChoice) {
		case 1:
			authorObj.createAuthor();
			break;
		case 2:
			authorObj.updateAuthor();
			break;
		case 3:
			authorObj.deleteAuthor();
			break;
		case 4:
			authorObj.seeListOfAuthor();
			break;
		case 5:
			mainMenu();
			break;
		default:
			System.out.println("Invalid Number.");
			chooseAuthorOptions();
		}
		
	}
	
	public void choosePublisherOptions() {
		System.out.println("Choose one of the following actions:\n"
				+ "1)Create a new Publisher.\n"
				+ "2)Edit information of an existing Publisher\n"
				+ "3)Delete an existing Publisher\n"
				+ "4)See full list of Publisher\n"
				+ "5)Go back to main menu");
		
		PublisherOperations publisherObj = new PublisherOperations();
		int publisherChoice = Integer.parseInt(scan.nextLine());
		switch(publisherChoice) {
		case 1:
			publisherObj.createPublisher();
			break;
		case 2:
			publisherObj.updatePublisher();
			break;
		case 3:
			publisherObj.deletePublisher();
			break;
		case 4:
			publisherObj.seePublisherList();
			break;
		case 5:
			mainMenu();
			break;
		default:
			System.out.println("Invalid Number.");
			choosePublisherOptions();
		}
		
	}
	
}
