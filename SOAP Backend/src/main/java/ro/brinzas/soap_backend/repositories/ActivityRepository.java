package ro.brinzas.soap_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.brinzas.soap_backend.entities.PlainActivity;

@Repository
public interface ActivityRepository extends JpaRepository<PlainActivity, Long> {

	List<PlainActivity> findByPatientId(long id);
}
