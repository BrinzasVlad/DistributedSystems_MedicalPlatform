package ro.brinzas.soap_backend.entities;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

/**
 * Stub class meant to enable JPA queries.
 * Do NOT actually use - it only serves to allow us to dig for connections
 */
@Getter
@Entity
@Table(name = "medication_plan")
public class StubMedicationPlan {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "patient_id", nullable = false)
	private long patientId;

}
