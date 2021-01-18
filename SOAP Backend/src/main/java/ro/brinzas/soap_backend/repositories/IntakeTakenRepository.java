package ro.brinzas.soap_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.brinzas.soap_backend.entities.PlainIntakeTaken;

@Repository
public interface IntakeTakenRepository extends JpaRepository<PlainIntakeTaken, Long> {

	List<PlainIntakeTaken> findByIntakePlanPatientId(long patientId);
}
