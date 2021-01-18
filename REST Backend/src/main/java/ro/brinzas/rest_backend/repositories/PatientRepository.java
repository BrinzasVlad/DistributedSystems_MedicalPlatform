package ro.brinzas.rest_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.brinzas.rest_backend.entities.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
	
	List<Patient> findByCaregiversId(long id);
}
