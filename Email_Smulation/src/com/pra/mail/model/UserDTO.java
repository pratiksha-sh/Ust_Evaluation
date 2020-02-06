package com.pra.mail.model;

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
private String User_name;
@Column(unique=true)
  private String Email;
  private String Password;
 private String questions;
 private String answer;
@OneToMany(cascade=CascadeType.ALL)
@JoinColumn(referencedColumnName="Id")
private List<MailDTO> From_id;
 public int getId() {
	return Id;
}
public void setId(int id) {
	Id = id;
}
public String getUser_name() {
	return User_name;
}
public void setUser_name(String user_name) {
	User_name = user_name;
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
public List<MailDTO> getFrom_id() {
	return From_id;
}
public void setFrom_id(List<MailDTO> from_id) {
	From_id = from_id;
}
public String getQuestions() {
	return questions;
}
public void setQuestions(String questions) {
	this.questions = questions;
}
public String getAnswer() {
	return answer;
}
public void setAnswer(String answer) {
	this.answer = answer;
}
}
