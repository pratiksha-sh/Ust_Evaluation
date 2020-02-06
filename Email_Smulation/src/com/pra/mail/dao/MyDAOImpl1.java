package com.pra.mail.dao;
import java.util.Iterator;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pra.mail.model.MailDTO;
import com.pra.mail.model.UserDTO;


@Component
public class MyDAOImpl1 implements MyDAO{
	@Autowired
	SessionFactory sf;
    @Autowired
    HttpSession h;
    
	@Override
	public boolean register(UserDTO dto) {
	System.out.println("inside dao");
	Session ss=sf.openSession();
	
	Criteria cr=ss.createCriteria(UserDTO.class);
	cr.add(Restrictions.eq("Email", dto.getEmail()));
	UserDTO udto=(UserDTO) cr.uniqueResult();
	System.out.println("udto"+udto);
	//String email=udto.getEmail();

	if(udto==null){
	ss.save(dto);
	ss.beginTransaction().commit();
	ss.close();
	return true;
	}else{
		ss.close();
		return false;
	}
	}
	
	@Override
	public UserDTO login(UserDTO dto) {
	System.out.println("inside dao");
	Session ss=sf.openSession();
	Criteria cr=ss.createCriteria(UserDTO.class);
	cr.add(Restrictions.eq("Email", dto.getEmail()));
	cr.add(Restrictions.eq("Password", dto.getPassword()));
	UserDTO udto=(UserDTO) cr.uniqueResult();
	ss.close();
	return udto;
	}

	@Override
	public List<MailDTO> inbox() {
				System.out.println("inside dao");
				Session ss=sf.openSession();
				String email=(String) h.getAttribute("email");
				Criteria cr =ss.createCriteria(UserDTO.class);
				cr.add(Restrictions.eq("Email",email));
				UserDTO udto1= (UserDTO) cr.uniqueResult();
				int id=udto1.getId();
				Criteria cr1 =ss.createCriteria(MailDTO.class);
				cr1.add(Restrictions.eq("To_id",id));
				Criterion status = Restrictions.ne("Status","draft");
				Criterion status1=Restrictions.ne("Status","inboxdeleted");
				Criterion status2=Restrictions.eq("Status","sentdeleted" );
				Criterion status3=Restrictions.ne("Status","draft");
				Criterion oneCondition = 
					    Restrictions.conjunction().add(status).add(status1).add(status3);
				Criterion completeCondition = 
				    Restrictions.disjunction().add(oneCondition).add(status2);

				cr1.add(completeCondition);
				List<MailDTO> mlist=cr1.list();

				ss.close();
				return mlist;
	}

	@Override
	public List<MailDTO> sentitem() {
				System.out.println("inside dao");
				Session ss=sf.openSession();
				String email=(String) h.getAttribute("email");
				Criteria cr =ss.createCriteria(UserDTO.class);
				cr.add(Restrictions.eq("Email",email));
				UserDTO udto1= (UserDTO) cr.uniqueResult();
				int id=udto1.getId();
				Criteria cr1 =ss.createCriteria(MailDTO.class);
      			Criterion sentby = Restrictions.eq("sentby",email);
				Criterion status = Restrictions.eq("Status","sent");
				Criterion status2=Restrictions.ne("Status","sentdeleted");
				Criterion status1=Restrictions.eq("Status","inboxdeleted" );
				Criterion oneCondition =Restrictions.conjunction().add(status).add(sentby).add(status2);
				Criterion completeCondition =Restrictions.disjunction().add(oneCondition).add(status1);
				cr1.add(completeCondition);
				List<MailDTO> mlist=cr1.list();
				ss.close();
				return mlist;
	}							

	@Override
	public List<MailDTO> deleteitem() {
				System.out.println("inside dao");
				Session ss=sf.openSession();
				String email=(String) h.getAttribute("email");
				Criteria cr =ss.createCriteria(UserDTO.class);
				cr.add(Restrictions.eq("Email",email));
				UserDTO udto1= (UserDTO) cr.uniqueResult();
				int id=udto1.getId();
				
				Criteria cr1 =ss.createCriteria(MailDTO.class);
				cr1.add(Restrictions.eq("sentto",email));
				Query q=ss.createQuery("from MailDTO where (Status=? OR Status=? OR Status=?) and (sentby=? OR sentto=?)");
				q.setParameter(0,"sentdeleted");
				q.setParameter(1,"inboxdeleted");
				q.setParameter(2,"draftdeleted");
				q.setParameter(3, email);
				q.setParameter(4, email);
				List<MailDTO> mlist=q.list();
				for(MailDTO dto:mlist){
				System.out.println("Id: "+dto.getId()+"Msg: "+dto.getMessage()+"Sentby "+dto.getSentby()+"Sentto "+dto.getSentto()+"Status "+dto.getStatus()+"Subject "+dto.getSubject()+"toid "+dto.getTo_id());
				}
				ss.close();
				return mlist;	}

	@Override
	public List<MailDTO> draft() {
		System.out.println("inside dao");
		Session ss=sf.openSession();
		String email=(String) h.getAttribute("email");
		int id=0;
		Criteria cr1 =ss.createCriteria(MailDTO.class);
		cr1.add(Restrictions.eq("To_id",id));
		cr1.add(Restrictions.eq("sentby",email));
		cr1.add(Restrictions.ne("Status", "draftdeleted"));
		List<MailDTO> mlist=cr1.list();
		ss.close();
		return mlist;
	}

	@Override
	public boolean composeEmail(HttpServletRequest req) {
		Session ss=sf.openSession();
		String to=req.getParameter("To");
		String subject=req.getParameter("Subject");
		String message= req.getParameter("Message");
		this.h = req.getSession(false);
		String from = (String) h.getAttribute("email");
		/*if (to==from) {
			to=from;
			re
		} else {

		}*/
		MailDTO mdto= null;
		MailDTO mdto1= null;
		Criteria cr=ss.createCriteria(UserDTO.class);
		cr.add(Restrictions.eq("Email",to)); 
		UserDTO udto= (UserDTO) cr.uniqueResult();
	
		if(udto!=null){
			int toid=udto.getId();
			mdto=new MailDTO();
			mdto.setMessage(message);
			mdto.setSubject(subject);
			mdto.setSentby(from);
			mdto.setTo_id(toid);
		mdto.setStatus("sent");
		mdto.setSentto(to);
			
			Criteria cr1 = ss.createCriteria(UserDTO.class);
			cr1.add(Restrictions.eq("Email",from));
			UserDTO udto1= (UserDTO) cr1.uniqueResult();
			List<MailDTO> From_id = udto1.getFrom_id();
			From_id.add(mdto);
			ss.saveOrUpdate(udto1);
			ss.beginTransaction().commit();
			ss.close();
			return true;
			
		}else{
			mdto =new MailDTO();
			mdto.setMessage(message);
			mdto.setSubject(subject);
			mdto.setTo_id(0);
			mdto.setSentby(from);
		mdto.setStatus("draft");
			mdto.setSentto(to);
			Criteria cr1 =ss.createCriteria(UserDTO.class);
			cr1.add(Restrictions.eq("Email",from));
			UserDTO udto1=(UserDTO) cr1.uniqueResult();
			int toid1=udto1.getId();
			List<MailDTO> From_id=udto1.getFrom_id();
			From_id.add(mdto);
			ss.saveOrUpdate(udto1);
			ss.beginTransaction().commit();
			
			mdto1 =new MailDTO();
			mdto1.setMessage("Mail Delivery Failed");
			mdto1.setSubject("Mail Delivery Failed");
			mdto1.setStatus("Mail Delivery failed");
			mdto1.setSentby("@account.google.com");
			mdto1.setTo_id(toid1);
            mdto1.setSentto(from);
			
			Criteria cr2 =ss.createCriteria(UserDTO.class);
			cr2.add(Restrictions.eq("Email","@account.google.com"));
			UserDTO udto2=(UserDTO) cr2.uniqueResult();
			List<MailDTO> Fromid=udto2.getFrom_id();
			Fromid.add(mdto1);
			ss.saveOrUpdate(udto2);
			ss.beginTransaction().commit();
			ss.close();
			return false;
		}	
	}

	@Override
	public boolean delete(int id) {
		Session ss=sf.openSession();
		Criteria cr=ss.createCriteria(MailDTO.class);
		cr.add(Restrictions.eq("Id", id));
		MailDTO dto=(MailDTO) cr.uniqueResult();
		boolean b=false;
		if(dto!=null) {
			
			MailDTO mdto=(MailDTO) cr.uniqueResult();
			String status = mdto.getStatus();
			System.out.println(status+"status");
			id =mdto.getId();
			if(status.equalsIgnoreCase("Deleted Mail")){
				MailDTO rdto=ss.load(MailDTO.class,id);
				System.out.println(rdto+"rdto");
				ss.beginTransaction().commit();
				b=true;
			}else{
			dto.setStatus("Deleted Mail");
			ss.saveOrUpdate(dto);
			ss.beginTransaction().commit();
			ss.close();
			
			b= true;
		}
		}else {
		b= false;
		}
		return b;
	}

	@Override
	public boolean forgotpassword(HttpServletRequest req) {
		// TODO Auto-generated method stub
		Session ss=sf.openSession();
		String email=req.getParameter("email");
		String question=req.getParameter("Questions");
		String answer=req.getParameter("answer");
		Criteria cr1 =ss.createCriteria(UserDTO.class);
		cr1.add(Restrictions.eq("Email",email));
		cr1.add(Restrictions.eq("questions",question));
		cr1.add(Restrictions.eq("answer",answer));
		UserDTO dto=(UserDTO) cr1.uniqueResult();
		if(dto!=null){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean changePass(HttpServletRequest req) {
		Session ss=sf.openSession();
		String email=req.getParameter("email");
		boolean b=false;
		String confirmpass=req.getParameter("confirmpassword");
		String newpass=req.getParameter("newpassword");
		if(!confirmpass.equals(newpass)){
			 b=false;
		}else{
		Criteria cr=ss.createCriteria(UserDTO.class);
		cr.add(Restrictions.eq("Email", email));
		UserDTO udto=(UserDTO) cr.uniqueResult();
		if (udto!=null) {
			udto.setPassword(newpass);
			ss.saveOrUpdate(udto);
			ss.beginTransaction().commit();
			ss.close();
			b=true;
		} else {
			b= false;
		}
	  }
		return b;
	}

	@Override
	public List<MailDTO> mail(int id) {
		Session ss=sf.openSession();
		Criteria cr=ss.createCriteria(MailDTO.class);
		cr.add(Restrictions.eq("Id", id));
		List<MailDTO> mlist=cr.list();
		ss.close();
		return mlist;
}

	@Override
	public MailDTO editdraft(int id) {
		Session ss=sf.openSession();
		Criteria cr=ss.createCriteria(MailDTO.class);
		cr.add(Restrictions.eq("Id", id));
		MailDTO mlist=(MailDTO) cr.uniqueResult();
		ss.close();
		return mlist;
	}

	@Override
	public boolean composedraftEmail(HttpServletRequest req) {

		Session ss=sf.openSession();
		String to=req.getParameter("To");
		String subject=req.getParameter("Subject");
		String message= req.getParameter("Message");
		String id=req.getParameter("Id");
		int id1=Integer.parseInt(id);
		this.h = req.getSession(false);
		String from = (String) h.getAttribute("email");
		MailDTO mdto= null;
		MailDTO mdto1= null;
		Criteria cr=ss.createCriteria(UserDTO.class);
		cr.add(Restrictions.eq("Email",to));
		UserDTO udto= (UserDTO) cr.uniqueResult();
			if(udto!=null){
			int toid=udto.getId();
			MailDTO mdto22=ss.load(MailDTO.class,id1);
			mdto22.setMessage(message);
			mdto22.setSubject(subject);
			mdto22.setSentby(from);
			mdto22.setTo_id(toid);
		mdto22.setStatus("sent");
		mdto22.setSentto(to);
			
			Criteria cr1 = ss.createCriteria(UserDTO.class);
			cr1.add(Restrictions.eq("Email",from));
			UserDTO udto1= (UserDTO) cr1.uniqueResult();
			List<MailDTO> From_id = udto1.getFrom_id();
			From_id.add(mdto22);
			ss.saveOrUpdate(udto1);
			ss.beginTransaction().commit();
			ss.close();
			return true;
			
		}else{
			MailDTO mdto2=ss.load(MailDTO.class,id1);
			mdto2.setMessage(message);
			mdto2.setSubject(subject);
			mdto2.setTo_id(0);
			mdto2.setSentby(from);
		mdto2.setStatus("draft");
			mdto2.setSentto(to);
			Criteria cr1 =ss.createCriteria(UserDTO.class);
			cr1.add(Restrictions.eq("Email",from));
			UserDTO udto1=(UserDTO) cr1.uniqueResult();
			int toid1=udto1.getId();
			List<MailDTO> From_id=udto1.getFrom_id();
			From_id.add(mdto2);
			ss.saveOrUpdate(udto1);
			ss.beginTransaction().commit();
			
			mdto1 =new MailDTO();
			mdto1.setMessage("Mail Delivery Failed");
			mdto1.setSubject("Mail Delivery Failed");
			mdto1.setStatus("Mail Delivery failed");
			mdto1.setSentby("google@gmail.com");
			mdto1.setTo_id(toid1);
            mdto1.setSentto(from);
			
			Criteria cr2 =ss.createCriteria(UserDTO.class);
			cr2.add(Restrictions.eq("Email","google@gmail.com"));
			UserDTO udto2=(UserDTO) cr2.uniqueResult();
			List<MailDTO> Fromid=udto2.getFrom_id();
			Fromid.add(mdto1);
			ss.saveOrUpdate(udto2);
			ss.beginTransaction().commit();
			ss.close();
			return false;
		}	
		
	}

	@Override
	public boolean sentdelete(int id) {
		Session ss=sf.openSession();
		Criteria cr=ss.createCriteria(MailDTO.class);
		cr.add(Restrictions.eq("Id", id));
		MailDTO dto=(MailDTO) cr.uniqueResult();
		boolean b=false;
		if(dto!=null) {
			dto.setStatus("sentdeleted");
			ss.saveOrUpdate(dto);
			ss.beginTransaction().commit();
			ss.close();
			b=true;
		}
		return b;
	}

	@Override
	public boolean inboxdelete(int id) {
		Session ss=sf.openSession();
		Criteria cr=ss.createCriteria(MailDTO.class);
		cr.add(Restrictions.eq("Id", id));
		MailDTO dto=(MailDTO) cr.uniqueResult();
		boolean b=false;
		if(dto!=null) {
			dto.setStatus("inboxdeleted");
			ss.saveOrUpdate(dto);
			ss.beginTransaction().commit();
			ss.close();
			b=true;
		}
		return b;
	}

	@Override
	public boolean draftdelete(int id) {
		Session ss=sf.openSession();
		Criteria cr=ss.createCriteria(MailDTO.class);
		cr.add(Restrictions.eq("Id", id));
		MailDTO dto=(MailDTO) cr.uniqueResult();
		boolean b=false;
		if(dto!=null) {
			dto.setStatus("draftdeleted");
			ss.saveOrUpdate(dto);
			ss.beginTransaction().commit();
			ss.close();
			b=true;
		}
		return b;
	}

}
