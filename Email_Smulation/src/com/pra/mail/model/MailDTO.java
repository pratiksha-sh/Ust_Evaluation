package com.pra.mail.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
@Entity
@Table(name="Mail_info")
public class MailDTO {
	@Id
	@GenericGenerator(name="auto",strategy="increment")
	@GeneratedValue(generator="auto")
  private int Id;
	private int To_id;
private String Subject;
  private String Message;
  private String Status;
  private String sentto;
public int getId() {
	return Id;
}
public void setId(int id) {
	Id = id;
}
public int getTo_id() {
	return To_id;
}
public void setTo_id(int to_id) {
	To_id = to_id;
}
public String getSubject() {
	return Subject;
}
public void setSubject(String subject) {
	Subject = subject;
}
public String getMessage() {
	return Message;
}
public void setMessage(String message) {
	Message = message;
}
public String getStatus() {
	return Status;
}
public void setStatus(String status) {
	Status = status;
}
private String sentby;
public String getSentby() {
	return sentby;
}
public void setSentby(String sentby) {
	this.sentby = sentby;
}
public String getSentto() {
	return sentto;
}
public void setSentto(String sentto) {
	this.sentto = sentto;
}
}

