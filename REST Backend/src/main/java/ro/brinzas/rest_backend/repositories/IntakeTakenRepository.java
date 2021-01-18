package ro.brinzas.rest_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.brinzas.rest_backend.entities.IntakeTaken;

@Repository
public interface IntakeTakenRepository extends JpaRepository<IntakeTaken, Long> {

}
