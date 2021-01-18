package ro.brinzas.rest_backend.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import ro.brinzas.rest_backend.entities.enums.Gender;
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
@Table(name = "patient")
public class Patient {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private long id;
	
	@NonNull
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "birth_date")
	private LocalDate birthDate;
	
	@Enumerated(value = EnumType.STRING)
	@Column(name = "gender")
	private Gender gender;
	
	@Column(name = "address")
	private String address;
	
	@ManyToMany(mappedBy = "patients", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private final Set<Caregiver> caregivers = new HashSet<Caregiver>();
	
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
	private final Set<Activity> activities = new HashSet<Activity>();
	
	@PreRemove
	private void removePatientFromCaregivers() {
		// Necessary crutch for JPA delete
		caregivers.forEach(c -> c.getPatients().remove(this));
	}

}
