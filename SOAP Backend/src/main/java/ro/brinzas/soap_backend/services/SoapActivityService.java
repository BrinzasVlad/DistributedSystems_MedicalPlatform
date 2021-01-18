package ro.brinzas.soap_backend.services;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import ro.brinzas.soap_backend.entities.PlainActivity;

@WebService
public interface SoapActivityService {
	
	@WebMethod
	List<PlainActivity> getAllActivitiesForPatient(long patientId);
	
	@WebMethod
	void updateActivity(PlainActivity activity);
}
