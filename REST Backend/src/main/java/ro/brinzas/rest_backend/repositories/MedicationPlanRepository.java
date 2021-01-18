package ro.brinzas.rest_backend.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.brinzas.rest_backend.entities.MedicationPlan;

@Repository
public interface MedicationPlanRepository extends JpaRepository<MedicationPlan, Long> {

	List<MedicationPlan> findByPatientId(long id);
	List<MedicationPlan> findByPatientIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(long id, LocalDate aDate, LocalDate sameDate);
}
