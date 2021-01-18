package ro.brinzas.rest_backend.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "caregiver")
public class Caregiver {
	
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
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "caregiver_has_patient",
			   joinColumns = @JoinColumn(name = "caregiver_id"),
			   inverseJoinColumns = @JoinColumn(name = "patient_id"))
	private Set<Patient> patients;

}
