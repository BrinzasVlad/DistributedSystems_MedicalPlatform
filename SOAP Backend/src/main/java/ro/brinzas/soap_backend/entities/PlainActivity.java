package ro.brinzas.soap_backend.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import ro.brinzas.soap_backend.adapters.LocalDateTimeAdapter;
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
// on the constructor ends up generating the "end" and "start" fields twice

@Entity
@Table(name = "activity")
public class PlainActivity {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private long id;
	
	@NonNull
	@Column(name = "name", nullable = false)
	private String name;
	
	@NonNull
	@Column(name = "start", nullable = false)
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime start;
	
	@NonNull
	@Column(name = "end", nullable = false)
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime end;
	
	@Column(name = "abnormal", nullable = false)
	private boolean abnormal;
	
	@Column(name = "justified", nullable = false)
	private boolean justified;
	
	@Column(name = "recommendation")
	private String recommendation;
	
	@Column(name = "patient_id", nullable = false)
	private long patientId;

}
