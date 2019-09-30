/**
 * 
 */
package com.ss.dataAccess;

import java.io.*;
import java.util.*;

import com.ss.pojo.Book;

/**
 * @author afia
 *
 */
public class BookDAC {
	private String filepath = "./Files/Books.txt";
	private int lastIndex;
	   
    public int getLastIndex() {
    	//lastIndex is the variable that keeps track of last index; so any time a user creates an object, id of that
    	//object is auto-incremented instead of user providing the id.
    	return this.lastIndex;
    }
	
	//method to read from file and save the data in an arraylist.
	public ArrayList<Book> readFromBook(){
		ArrayList<Book> bookList= new ArrayList<Book>();
		try {
			FileInputStream fileIn = new FileInputStream(filepath);
			BufferedReader br = new BufferedReader(new InputStreamReader(fileIn));
			String lineRead;
			
			while((lineRead = br.readLine() )!= null) {
				String[] splitFile = lineRead.split(",");
				Book bookObj = new Book();
				bookObj.setBookId(Integer.parseInt(splitFile[0]));
				bookObj.setBookName(splitFile[1]);
				bookObj.setBookAuthorId(Integer.parseInt(splitFile[2]) );
				bookObj.setBookPublisherId(Integer.parseInt(splitFile[3]));
				bookList.add(bookObj);
				//This method reads from files and sets properties of the object for every entry in that file. after setting
				//value the object is added to the ArrayList that will returned from this method.
			}
			br.close();
			fileIn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		lastIndex = bookList.get(bookList.size()-1).getBookId(); //updating lastIndex after every read.
		return bookList;
	}
	
	//method to write ArrayList back to file after the update is done.
	public void writeToBook(ArrayList<Book> newBookList) {
		String tempFile = "temp.txt"; //temporary file where we will write the info and the replace it with the original file.
		File oldFile = new File(filepath);
		File newFile = new File(tempFile);
		FileWriter fw;
		try {
			fw = new FileWriter(newFile,true); //creating a filewriter to write to temp file.
			for(int i=0;i<newBookList.size();i++) {
				
				fw.write(newBookList.get(i).getBookId()+","+newBookList.get(i).getBookName()+","
						+ newBookList.get(i).getBookAuthorId()+","+newBookList.get(i).getBookPublisherId()+"\n");
			}
			oldFile.delete();
			File dump = new File(filepath); //creating a new file with the same file location and file name
			newFile.renameTo(dump); //rename our temp file to original filename.
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
		lastIndex = newBookList.get(newBookList.size()-1).getBookId(); //updating last index after every write.
	}
}
