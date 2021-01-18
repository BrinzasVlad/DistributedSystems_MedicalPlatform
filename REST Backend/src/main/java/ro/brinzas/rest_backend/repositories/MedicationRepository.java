package ro.brinzas.rest_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.brinzas.rest_backend.entities.Medication;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

}
