package ro.brinzas.soap_backend.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import ro.brinzas.soap_backend.adapters.LocalDateAdapter;
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

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
// XML accessor FIELD needed because else the annotation on the field and the root element
// on the constructor ends up generating the "date" field twice

@Entity
@Table(name = "intake_taken")
public class PlainIntakeTaken {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "taken", nullable = false)
	private boolean taken;
	
	@NonNull
	@Column(name = "date", nullable = false)
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	private LocalDate date;
	
	@NonNull
	@ManyToOne // No cascade
	@JoinColumn(name = "intake_id", nullable = false)
	private StubIntakeInterval intake;

}
