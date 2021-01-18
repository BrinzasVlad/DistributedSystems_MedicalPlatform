package ro.brinzas.rest_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.brinzas.rest_backend.entities.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

	List<Activity> findByPatientId(long id);
}
