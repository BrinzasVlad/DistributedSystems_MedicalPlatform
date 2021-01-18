package ro.brinzas.rest_backend.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalTime;
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
@Table(name = "intake_interval")
public class IntakeInterval {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private long id;

	@NonNull
	@Column(name = "start_time", nullable = false)
	private LocalTime startTime;
	
	@NonNull
	@Column(name = "end_time", nullable = false)
	private LocalTime endTime;

	@NonNull
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "medication_id", nullable = false)
	private Medication medication;

	@NonNull
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "plan_id", nullable = false)
	private MedicationPlan medicationPlan;
	
	@OneToMany(mappedBy = "intakeInterval", cascade = CascadeType.ALL)
	private Set<IntakeTaken> intakeTakens;

}
