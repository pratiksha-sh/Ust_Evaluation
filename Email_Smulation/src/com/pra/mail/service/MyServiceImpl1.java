package com.pra.mail.service;


import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pra.mail.dao.MyDAO;
import com.pra.mail.model.MailDTO;
import com.pra.mail.model.UserDTO;


@Component
public class MyServiceImpl1 implements MyService {
	@Autowired
	MyDAO mdao;

	@Override
	public boolean register(UserDTO dto) {
		System.out.println("inside service");
		boolean b =mdao.register(dto);
		return b;
	}



	@Override
	public UserDTO login(UserDTO dto) {
		System.out.println("inside service");
		UserDTO udto=mdao.login(dto);
		return udto;
	}



	@Override
	public List<MailDTO> inbox() {
		// TODO Auto-generated method stub
		List<MailDTO> From_id=mdao.inbox();
		return From_id;
	}



	@Override
	public List<MailDTO> sentitem() {
		// TODO Auto-generated method stub
		List<MailDTO> From_id=mdao.sentitem();
		return From_id;
	}



	@Override
	public List<MailDTO> deleteitem() {
		// TODO Auto-generated method stub
		List<MailDTO> From_id=mdao.deleteitem();
		return From_id;
	}



	@Override
	public List<MailDTO> draft() {
		// TODO Auto-generated method stu
		List<MailDTO> From_id=mdao.draft();
		return From_id;
	}



	@Override
	public boolean composeEmail(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return mdao.composeEmail(req);
	}



	@Override
	public boolean delete(int id) {
		boolean b=mdao.delete(id);
		return b;
	}



	@Override
	public boolean forgotpasswword(HttpServletRequest req) {
		// TODO Auto-generated method stub
		System.out.println("inside service");
		return mdao.forgotpassword(req);
	}



	@Override
	public boolean changePass(HttpServletRequest req) {
		// TODO Auto-generated method stub
		boolean b= mdao.changePass(req);
	return b;
	}
	@Override
	public List<MailDTO> mail(int id) {
		// TODO Auto-generated method stub
		List<MailDTO> From_id=mdao.mail(id);
		return From_id;
	}

	@Override
	public MailDTO editdraft(int id) {
		MailDTO From_id=mdao.editdraft(id);
		return From_id;
	}



	@Override
	public boolean composedraftEmail(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return mdao.composedraftEmail(req);
	}



	@Override
	public boolean sentdelete(int id) {
		boolean b=mdao.sentdelete(id);
		return b;
	}



	@Override
	public boolean inboxdelete(int id) {
		boolean b=mdao.inboxdelete(id);
		return b;
	}



	@Override
	public boolean draftdelete(int id) {
		boolean b=mdao.draftdelete(id);
		return b;
	}
}