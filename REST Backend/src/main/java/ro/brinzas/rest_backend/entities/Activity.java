package ro.brinzas.rest_backend.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
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
@Table(name = "activity")
public class Activity {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private long id;
	
	@NonNull
	@Column(name = "name", nullable = false)
	private String name;
	
	@NonNull
	@Column(name = "start", nullable = false)
	private LocalDateTime start;
	
	@NonNull
	@Column(name = "end", nullable = false)
	private LocalDateTime end;
	
	@Column(name = "abnormal", nullable = false)
	private boolean abnormal;
	
	@Column(name = "justified", nullable = false)
	private boolean justified;
	
	@Column(name = "recommendation")
	private String recommendation;
	
	@ManyToOne(cascade = {/*CascadeType.PERSIST, */CascadeType.MERGE})
	// TODO: Temporarily disabled, seems to break ActivityService
	@JoinColumn(name = "patient_id", nullable = false)
	private Patient patient;
}