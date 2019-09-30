/**
 * 
 */
package com.ss.userMenu;
import java.util.Scanner;
/**
 * @author afia
 *
 */
public class WelcomePage {

	/**
	 * @param args
	 */
	public static Scanner scan =new Scanner(System.in);
	public static void main(String[] args) {
	   System.out.println("Welcome to the library management system!");
	   while(true) {
	   System.out.println("Enter m for main menu, b for book menu, a for author menu, p for publisher menu or x to quit.");
	    MenuPage menuObj = new MenuPage();
		String answer = scan.nextLine();
		if(answer.equalsIgnoreCase("x")) {
			System.out.println("The program is terminated."); //if user gives "x" then, the loop will break and program will terminate.
			break;
		}
		switch(answer) {
		case "m":
			menuObj.mainMenu(); //calling main method
			break;
		case "b":
			menuObj.chooseBookOptions(); //calling book menu options
			break;
		case "a":
			menuObj.chooseAuthorOptions(); //calling author menu options
			break;
		case "p":
			menuObj.choosePublisherOptions(); //calling publisher menu options
			break;
		default:
			System.out.println("Invalid option."); //If there is an invalid input, it will loop back and start again.
		}
	   }
     
	}
	
}
