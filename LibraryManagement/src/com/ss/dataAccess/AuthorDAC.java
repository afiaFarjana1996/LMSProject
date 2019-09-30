/**
 * 
 */
package com.ss.dataAccess;

import java.io.*;
import java.util.*;

import com.ss.pojo.Author;
/**
 * @author afia
 *
 */
public class AuthorDAC {

private String filepath = "./Files/Author.txt";
	
	//method to read from file and save the data in an arraylist.
   private int lastIndex;
   
    public int getLastIndex() {
    	//lastIndex is the variable that keeps track of last index; so any time a user creates an object, id of that
    	//object is auto-incremented instead of user providing the id.
    	return this.lastIndex;
    }

	public ArrayList<Author> readFromAuthor(){
		ArrayList<Author> authorList= new ArrayList<Author>();
		try {
		FileInputStream fileIn = new FileInputStream(filepath);
		BufferedReader br = new BufferedReader(new InputStreamReader(fileIn));
		String lineRead;
		
		while((lineRead = br.readLine() )!= null) {
			String[] splitFile = lineRead.split(",");
			Author authObj = new Author();
			authObj.setAuthorId(Integer.parseInt(splitFile[0]) );
			authObj.setAuthorName(splitFile[1]);
			authObj.setAuthorPhoneNumber(splitFile[2]);
			authorList.add(authObj);
			//This method reads from files and sets properties of the object for every entry in that file. after setting
			//value the object is added to the ArrayList that will returned from this method.
		}
		br.close();
		fileIn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		lastIndex = authorList.get(authorList.size()-1).getAuthorId(); //updating lastIndex after every read.
		return authorList;
	}
	
	//method to write ArrayList back to file after the update is done.
	public void writeToAuthor(ArrayList<Author> newAuthorList) {
		String tempFile = "temp.txt"; //temporary file where we will write the info and the replace it with the original file.
		File oldFile = new File(filepath);
		File newFile = new File(tempFile);
		FileWriter fw;
		try {
			fw = new FileWriter(newFile,true); //creating a filewriter to write to temp file.
			for(int i=0;i<newAuthorList.size();i++) {
				fw.write(newAuthorList.get(i).getAuthorId()+","+newAuthorList.get(i).getAuthorName()+","
						+ newAuthorList.get(i).getAuthorPhoneNumber()+"\n");
			}
			oldFile.delete();
			File dump = new File(filepath); //creating a new file with the same file location and file name
			newFile.renameTo(dump); //rename our temp file to original filename.
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		lastIndex = newAuthorList.get(newAuthorList.size()-1).getAuthorId(); //updating last index after every write.
	}
}
