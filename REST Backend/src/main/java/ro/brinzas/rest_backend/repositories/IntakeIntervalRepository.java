package ro.brinzas.rest_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.brinzas.rest_backend.entities.IntakeInterval;

@Repository
public interface IntakeIntervalRepository extends JpaRepository<IntakeInterval, Long> {

}
