package com.team.alpha.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(nullable=true, name="username")
	private String username;
	
	@Column(nullable=true, name="password")
	private String password;

	@Column(nullable=true, name="display_name")
	private String displayName;

	@Column(nullable=true, name="email")
	private String email;

	@Column(nullable=true, name="phone")
	private String phone;

	public User() {
	}

	public User(final int id, final String username, final String password, final String displayName, final String email, final String phone) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.displayName = displayName;
		this.email = email;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	@Override
	public int hashCode() {
		return id;
	}
	
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		} else if (obj instanceof User) {
			return Objects.equals(email, ((User) obj).getEmail())
					&& id == ((User) obj).getId()
					&& Objects.equals(phone, ((User) obj).getPhone());
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", displayName=" + displayName + ", email=" + email
				+ ", phone=" + phone + "]";
	}
	
	
	
}
