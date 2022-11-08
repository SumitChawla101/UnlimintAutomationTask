package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class ConfigDataProvider {

	
	Properties pro;                 // creating object
	File src;

	
	public ConfigDataProvider(String propertyFilePath) {        // Constructor

		
		//Make connection with File
		
		src = new File(propertyFilePath);
		try {
			FileInputStream fis = new FileInputStream(src);

			pro=new Properties();
			pro.load(fis);
			fis.close();
		}

		catch (Exception e) {

			System.out.println("Not able to load Property File >> " + e.getMessage());
		}
	}
	
	
	
	
	// method to get key value
	
	public String getDataFromPropertyFile(String keyToSearch) {      

		return pro.getProperty(keyToSearch);
	}

	
	
	
	
	// method to set value
	
	public void setDataIntoPropertyFile(String keyToSearch, String NewValue) throws Exception {

		FileOutputStream fileOut = new FileOutputStream(src);
		pro.setProperty(keyToSearch, NewValue);	 
		pro.store(fileOut, null);
		fileOut.close();

	}

	
}

