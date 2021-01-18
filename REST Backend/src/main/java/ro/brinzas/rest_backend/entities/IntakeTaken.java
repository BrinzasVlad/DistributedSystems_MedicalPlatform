package ro.brinzas.rest_backend.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "intake_taken")
public class IntakeTaken {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "taken", nullable = false)
	private boolean taken;
	
	@NonNull
	@Column(name = "date", nullable = false)
	private LocalDate date;
	
	@NonNull
	@ManyToOne // No cascade
	@JoinColumn(name = "intake_id", nullable = false)
	private IntakeInterval intakeInterval;

}
