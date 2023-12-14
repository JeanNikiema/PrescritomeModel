package processFHIR;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Practitioner;
import org.hl7.fhir.r4.model.Resource;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.client.api.IGenericClient;

public class GetFhirResources {
	//	 *************** Read the fhir resource file 
	public HashMap<String, Resource> getFile(String fileName) throws FileNotFoundException {
		HashMap<String, Resource> resources = new HashMap<String, Resource>();
		
		FhirContext ctxR4 = FhirContext.forR4();
		InputStream inputStream = new FileInputStream(fileName);
		IParser parser = ctxR4.newXmlParser().setPrettyPrint(true);
		Bundle bundle = parser.parseResource(Bundle.class, inputStream);

		for(int i=0; i<bundle.getEntry().size(); i++) {
			Resource resource = bundle.getEntry().get(i).getResource();
			String resourceName = bundle.getEntry().get(i).getResource().getClass().getSimpleName() ;
			resources.put(resourceName, resource);
		}
		return resources;
	}
	
	

		//	 *************** Read the fhir resource file 
		public HashMap<String, Resource> getFile2(File fileName) throws FileNotFoundException {
			HashMap<String, Resource> resources = new HashMap<String, Resource>();
			
			FhirContext ctxR4 = FhirContext.forR4();
			InputStream inputStream = new FileInputStream(fileName);
			IParser parser = ctxR4.newXmlParser().setPrettyPrint(true);
			Bundle bundle = parser.parseResource(Bundle.class, inputStream);

			for(int i=0; i<bundle.getEntry().size(); i++) {
				Resource resource = bundle.getEntry().get(i).getResource();
				String resourceName = bundle.getEntry().get(i).getResource().getClass().getSimpleName() ;
				resources.put(resourceName, resource);
			}
			return resources;
		}
	
	
	
	public static File[] getAllFolderFiles(String folderPath) {
		 // Set the folder path
//	    String folderPath = "C:\\folder\\";

	    // Create a File object for the folder
	    File folder = new File(folderPath);

	    // List all the files in the folder
	    File[] listOfFiles = folder.listFiles();
	    
//	    int nb = 0;
//	    // Print the names of the files
//	    for (int i = 0; i < listOfFiles.length; i++) {
//	      if (listOfFiles[i].isFile()) {
//	        System.out.println("File: " + listOfFiles[i].getName());
//	      } else if (listOfFiles[i].isDirectory()) {
//	        System.out.println("Directory: " + listOfFiles[i].getName());
//	      }
//	      
//	      nb=i;
//	    }
	    
	    if(listOfFiles.length >0) {
	    	return listOfFiles ;
	    }
	    else {
	    	System.out.println("Folder "+ folderPath +"is empty");
	    	return null;
	    }
	    
	}
	
	
//	public HashMap<String, Resource> mapResource(Bundle bundle) {
//		HashMap<String, Resource> resources = new HashMap<String, Resource>();
//		for(int i=0; i<bundle.getEntry().size(); i++) { 
//			Resource resource = bundle.getEntry().get(i).getResource();
//			String resourceName = bundle.getEntry().get(i).getResource().getClass().getSimpleName() ;
//			resources.put(resourceName, resource);
//		}
//		return resources;
//	}
	
	public static void main(String[] args) throws FileNotFoundException {
		getAllFolderFiles("sources/output/");
	}
	
}
