package com.wilson.hardin.jobs;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.eclipse.persistence.jaxb.MarshallerProperties;

import com.wilson.hardin.pojo.Schools;

public class GreatSchoolsJob implements ProcessingJob {
	
	// TODO: Move API key to a config file.
	private static final String GS_URL = 
			"http://api.greatschools.org/schools/WA/Seattle?key=e8w1s8bocy0yanumjauksaxx&limit=10000";
	
	// Temporary -- in the future the JSON will go to a mongoDB.
	private static final File OUT = new File("school.json");
	
	
	/**
	 * 
	 * Gets school information and outputs it to a file.
	 * 
	 */
	@Override
	public void run() throws Exception {
		// Setup target URL.
		URL urlObj = new URL(GS_URL);
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		
		// Get response;
		con.getResponseCode();
		
		// Extract the response message.
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		StringBuffer response = new StringBuffer();
 
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		// Marshal the XML into a POJO.
		JAXBContext jc = JAXBContext.newInstance(Schools.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Schools schools = (Schools) unmarshaller.unmarshal(new StringReader(response.toString()));
		
		// Export the schools to the specified file in a JSON format.
		Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
        marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, false);
        marshaller.marshal(schools, OUT);
	}
}
