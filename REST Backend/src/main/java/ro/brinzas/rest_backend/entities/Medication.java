package ro.brinzas.rest_backend.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "medication")
public class Medication {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private long id;

	@NonNull
	@Column(name = "name", nullable = false)
	private String name;
	
	// TODO: clarify whether this should be some numeric value instead
	@Column(name = "dosage")
	private String dosage;
	
	// TODO: clarify whether this should really be a list instead
	// The official requirements say that we should have a list of side effects.
	// In principle, this leads me to assume that they want a SideEffect entity and
	// have this field be a OneToMany field instead. If that's so, this should be refactored.
	// Still, I have reasons to believe that the field will never be used for more than
	// just being listed on the screen, case in which "String" sounds good to me.
	@Column(name = "side_effects")
	private String sideEffects;
	
	@OneToMany(mappedBy = "medication", cascade = CascadeType.ALL)
	private final Set<IntakeInterval> intakeIntervals = new HashSet<IntakeInterval>();

}
