/**
 * 
 */
package fromFIHRtoPrescriptome2;

import java.util.Date;
import java.util.List;
import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.StringType;

import ca.uhn.fhir.rest.client.api.IGenericClient;


public class AdressParsing {
	public org.hl7.fhir.r4.model.Patient getPatientFromFhirServer(IGenericClient client) {
		Patient patient = client.read()
				.resource(Patient.class)
				.withId("24739")
				.execute();
		
		return patient ;
	}
	
	// ==========================  Function to get Adress ========================
		public prescriptomeCore.Adress getAdress(Patient patient){
			Address address = patient.getAddressFirstRep();
			List<StringType> line= address.getLine();
			String ligne1 = "";
			String ligne2 = "";
			int i =1;
			for(StringType lab : line) {
				if(i==1) {
					ligne1 = lab.asStringValue();
				}
				else if(i==2) {
					ligne2 = lab.asStringValue();
				}
			}
			String city = address.getCity();
			String state = address.getCountry();
			String country = address.getCountry();
			String adressID = address.getPostalCode();
			String zipCode = address.getPostalCode();
			Date date = new Date();

			Date createtimeDate = date; 
			Date modifytimeDate = date ;
			Date validitytimeDate = date ;
			if (address.getPeriod().getStart() != null) {
				createtimeDate = address.getPeriod().getStart(); 
				modifytimeDate = address.getPeriod().getStart();
			}
			if (address.getPeriod().getEnd() != null) {
				validitytimeDate = address.getPeriod().getEnd(); 
			}
			
			boolean validityDate = false;
			if(date.compareTo(validitytimeDate)<1) {
				validityDate = true;
			}
			else {
				validityDate = false;
			}
			
			prescriptomeCore.Adress adress = new prescriptomeCore.Adress(
					adressID, ligne1, ligne2, city, state, zipCode, country, validitytimeDate, 
					validityDate, createtimeDate, modifytimeDate
			);
			return adress ;
		}
}
