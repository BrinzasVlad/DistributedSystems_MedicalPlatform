package ro.brinzas.soap_backend.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.brinzas.soap_backend.dto.PlainIntakeTakenDTO;
import ro.brinzas.soap_backend.repositories.IntakeTakenRepository;

@Service
@WebService(endpointInterface = "ro.brinzas.soap_backend.services.SoapIntakeTakenService")
public class SoapIntakeTakenServiceImpl implements SoapIntakeTakenService {
	
	private IntakeTakenRepository repository;
	
	@Autowired
	public SoapIntakeTakenServiceImpl(IntakeTakenRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<PlainIntakeTakenDTO> getAllIntakeTakenForPatient(long patientId) {
		return repository.findByIntakePlanPatientId(patientId).stream()
				.map(PlainIntakeTakenDTO::from)
				.collect(Collectors.toList());
	}

}
