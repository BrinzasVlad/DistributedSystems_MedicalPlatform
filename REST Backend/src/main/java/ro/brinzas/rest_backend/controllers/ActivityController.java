package ro.brinzas.rest_backend.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.brinzas.rest_backend.soap_artifacts.PlainActivity;
import ro.brinzas.rest_backend.soap_artifacts.SoapActivityService;
import ro.brinzas.rest_backend.soap_artifacts.SoapActivityServiceImplService;
import ro.brinzas.rest_backend.dto.ActivityDTO;

@RestController
@CrossOrigin
@RequestMapping("/patient/{id}/activities") // FIXME: refactor to refer to a constant value somewhere, not /patient/{id}
// Alternatively, move over to a URL that makes more sense, like /activities, so we don't need
// to provide a patient id to update an activity
public class ActivityController {

	@GetMapping
	private List<ActivityDTO> getPatientActivities(@PathVariable("id") long patientId) {
		List<ActivityDTO> activities = new ArrayList<ActivityDTO>();

		SoapActivityServiceImplService service = new SoapActivityServiceImplService();
		SoapActivityService proxy = service.getSoapActivityServiceImplPort();

		proxy.getAllActivitiesForPatient(patientId).forEach(activity -> activities.add(
					ActivityDTO.builder()
						.id(activity.getId())
						.name(activity.getName())
						.start(activity.getStart())
						.end(activity.getEnd())
						.patientId(patientId)
						.abnormal(activity.isAbnormal())
						.justified(activity.isJustified())
						.recommendation(activity.getRecommendation())
						.build())
				);
		
		return activities;
	}
	
	@PutMapping
	private void update(@RequestBody ActivityDTO activity) {
		// If the URL is still at patient/{id}/activities, maybe we
		// should check the activity we're asked to update actually belongs to
		// the patient at this id?

		SoapActivityServiceImplService service = new SoapActivityServiceImplService();
		SoapActivityService proxy = service.getSoapActivityServiceImplPort();

		PlainActivity activityToUpdate = new PlainActivity();
		activityToUpdate.setId(activity.getId());
		activityToUpdate.setPatientId(activity.getPatientId());
		activityToUpdate.setName(activity.getName());
		activityToUpdate.setStart(activity.getStart());
		activityToUpdate.setEnd(activity.getEnd());
		activityToUpdate.setAbnormal(activity.isAbnormal());
		activityToUpdate.setJustified(activity.isJustified());
		activityToUpdate.setRecommendation(activity.getRecommendation());

		proxy.updateActivity(activityToUpdate);
	}
}
