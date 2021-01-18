package ro.brinzas.rest_backend.entities;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
@Entity
@Table(name = "user_data")
public class UserData {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private long id;
	
	@NaturalId
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password; // NEVER EVER STORE PLAIN-TEXT PASSWORDS!

	// TODO: add "role" field once you figure out Spring security
	
}
