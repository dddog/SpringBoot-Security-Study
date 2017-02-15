package xyz.dddog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Account {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique=true)
	private String userid;
	
	private String password;
	
	private String role;
	private String nick;
}
