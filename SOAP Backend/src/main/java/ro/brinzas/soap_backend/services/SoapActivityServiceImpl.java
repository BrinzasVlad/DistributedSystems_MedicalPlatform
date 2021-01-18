package ro.brinzas.soap_backend.services;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.brinzas.soap_backend.entities.PlainActivity;
import ro.brinzas.soap_backend.repositories.ActivityRepository;

@Service
@WebService(endpointInterface = "ro.brinzas.soap_backend.services.SoapActivityService")
public class SoapActivityServiceImpl implements SoapActivityService {

	private ActivityRepository repository;
	
	@Autowired
	public SoapActivityServiceImpl(ActivityRepository activityRepository) {
		this.repository = activityRepository;
	}

	public List<PlainActivity> getAllActivitiesForPatient(long patientId) {
		return repository.findByPatientId(patientId);
	}
	
	public void updateActivity(PlainActivity activity) {
		repository.save(activity);
	}

}
