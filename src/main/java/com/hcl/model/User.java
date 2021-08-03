package com.hcl.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

		@Id
		@Column(name = "user_id")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		private String username;
		private String password;
		private String role;
		private boolean enabled;
}
