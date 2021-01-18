package ro.brinzas.rest_backend.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medication_plan")
public class MedicationPlan {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private long id;

	@NonNull
	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;

	@NonNull
	@Column(name = "end_date", nullable = false)
	private LocalDate endDate;

	@NonNull
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "patient_id", nullable = false)
	private Patient patient;
	
	// TODO: clarify if we have a foreign key to the Doctor, too
	
	@OneToMany(mappedBy = "medicationPlan", cascade = CascadeType.ALL)
	private Set<IntakeInterval> intakeIntervals;

}
