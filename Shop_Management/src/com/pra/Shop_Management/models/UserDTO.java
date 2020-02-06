package com.pra.Shop_Management.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="User_info")
public class UserDTO {
	@Id
	@GenericGenerator(name="auto",strategy="increment")
	@GeneratedValue(generator="auto")
  private int Id;
private String Username;
@Column(unique=true)
  private String Email;
  private String Password;
private long Contact;
public int getId() {
	return Id;
}
public void setId(int id) {
	Id = id;
}
public String getUsername() {
	return Username;
}
public void setUsername(String username) {
	Username = username;
}
public String getEmail() {
	return Email;
}
public void setEmail(String email) {
	Email = email;
}
public String getPassword() {
	return Password;
}
public void setPassword(String password) {
	Password = password;
}
public long getContact() {
	return Contact;
}
public void setContact(long contact) {
	Contact = contact;
}


}