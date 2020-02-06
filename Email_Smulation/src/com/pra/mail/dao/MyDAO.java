package com.pra.mail.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pra.mail.model.MailDTO;
import com.pra.mail.model.UserDTO;


public interface MyDAO {
	 public boolean register(UserDTO dto);
	 public UserDTO login(UserDTO dto);
	public List<MailDTO> inbox();
	public List<MailDTO> sentitem();
	public List<MailDTO> deleteitem();
	public List<MailDTO> draft();
	public boolean composeEmail(HttpServletRequest req);
	public boolean forgotpassword(HttpServletRequest req);
	public boolean delete(int id);
	public boolean changePass(HttpServletRequest req);
	public List<MailDTO> mail(int id);
	public MailDTO editdraft(int id);
	public boolean composedraftEmail(HttpServletRequest req);
	public boolean sentdelete(int id);
	public boolean inboxdelete(int id);
	public boolean draftdelete(int id);
}
