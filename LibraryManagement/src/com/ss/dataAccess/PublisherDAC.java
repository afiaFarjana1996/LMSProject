/**
 * 
 */
package com.ss.dataAccess;
import java.io.*;
import java.util.*;

import com.ss.pojo.Publisher;
/**
 * @author afia
 *
 */
public class PublisherDAC {

private String filepath = "./Files/Publisher.txt";
private int lastIndex;

public int getLastIndex() {
	//lastIndex is the variable that keeps track of last index; so any time a user creates an object, id of that
	//object is auto-incremented instead of user providing the id.
	return this.lastIndex;
}
	
	//method to read from file and save the data in an arraylist.
	public ArrayList<Publisher> readFromPublisher(){
		ArrayList<Publisher> publisherList= new ArrayList<Publisher>();
		
		try {
			FileInputStream fileIn = new FileInputStream(filepath);
			BufferedReader br = new BufferedReader(new InputStreamReader(fileIn));
			String lineRead;
			
			while((lineRead = br.readLine() )!= null) {
				String[] splitFile = lineRead.split(",");
				Publisher publisherObj = new Publisher();
				publisherObj.setPublisherId(Integer.parseInt(splitFile[0]));
				publisherObj.setPublisherName(splitFile[1]);
				publisherObj.setPublisherAddress(splitFile[2]);
				publisherList.add(publisherObj);
				//This method reads from files and sets properties of the object for every entry in that file. after setting
				//value the object is added to the ArrayList that will returned from this method.
			}
			br.close();
			fileIn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		lastIndex = publisherList.get(publisherList.size()-1).getPublisherId(); //updating lastIndex after every read.
		return publisherList;
	}
	
	//method to write ArrayList back to file after the update is done.
	public void writeToPublisher(ArrayList<Publisher> newPublisherList) {
		String tempFile = "temp.txt"; //temporary file where we will write the info and the replace it with the original file.
		File oldFile = new File(filepath);
		File newFile = new File(tempFile);
		FileWriter fw;
		try {
			fw = new FileWriter(newFile,true); //creating a filewriter to write to temp file.
			for(int i=0;i<newPublisherList.size();i++) {
				fw.write(newPublisherList.get(i).getPublisherId()+","+newPublisherList.get(i).getPublisherName()+","
						+ newPublisherList.get(i).getPublisherAddress()+"\n");
			}
			oldFile.delete();
			File dump = new File(filepath); //creating a new file with the same file location and file name
			newFile.renameTo(dump); //rename our temp file to original filename.
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		lastIndex = newPublisherList.get(newPublisherList.size()-1).getPublisherId(); //updating last index after every write.
	}

}
