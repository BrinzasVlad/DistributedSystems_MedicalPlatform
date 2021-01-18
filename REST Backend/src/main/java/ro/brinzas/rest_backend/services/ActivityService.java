package ro.brinzas.rest_backend.services;

import java.time.Duration;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.brinzas.rest_backend.dto.ActivityRabbitDTO;
import ro.brinzas.rest_backend.entities.Activity;
import ro.brinzas.rest_backend.entities.Patient;
import ro.brinzas.rest_backend.repositories.ActivityRepository;
import ro.brinzas.rest_backend.repositories.PatientRepository;

@Service
@RabbitListener(queuesToDeclare = @Queue(name = "patient-activities", autoDelete = "true"),
				containerFactory = "jsaFactory")
public class ActivityService {
	
	private final ActivityRepository activityRepository;
	private final PatientRepository patientRepository;
	
	@Autowired
	public ActivityService(ActivityRepository activityRepository, PatientRepository patientRepository) {
		this.activityRepository = activityRepository;
		this.patientRepository = patientRepository;
	}

	@RabbitHandler
	public void receive(ActivityRabbitDTO in) {
        insert(in);
    }
	
	public void insert(ActivityRabbitDTO activityToInsert) {
		Activity activityEntity = activityToInsert.toEntityData();
		verifyActivity(activityEntity);
		// TODO: maybe test that there actually is a patient with that id?
		activityEntity.setPatient(patientRepository.getOne(activityToInsert.getPatientId()));
		activityEntity = activityRepository.save(activityEntity);
	}
	
	private void verifyActivity(Activity activityToVerify) {
		long durationThreshold;
		
		switch(activityToVerify.getName()) {
			case "Sleeping": durationThreshold = 12; break;
			case "Leaving": durationThreshold = 12; break;
			case "Toileting": durationThreshold = 1; break;
			default: return; // Not an activity with a duration threshold
		}
		
		Duration activityDuration = Duration.between(activityToVerify.getStart(), activityToVerify.getEnd());
		Duration thresholdDuration = Duration.ofHours(durationThreshold);
		
		if(activityDuration.compareTo(thresholdDuration) > 0) {
			// Mark activity as abnormal
			activityToVerify.setAbnormal(true);
			
			// TODO: use WebSockets to message frontend
		}
	}
}
