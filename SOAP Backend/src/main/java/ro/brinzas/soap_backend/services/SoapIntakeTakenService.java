package ro.brinzas.soap_backend.services;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import ro.brinzas.soap_backend.dto.PlainIntakeTakenDTO;

@WebService
public interface SoapIntakeTakenService {

	@WebMethod
	List<PlainIntakeTakenDTO> getAllIntakeTakenForPatient(long patientId);
}
