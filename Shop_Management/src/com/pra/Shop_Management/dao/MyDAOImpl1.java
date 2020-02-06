package com.pra.Shop_Management.dao;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pra.Shop_Management.models.OrderDTO;
import com.pra.Shop_Management.models.ProductDTO;
import com.pra.Shop_Management.models.UserDTO;

@Component
public class MyDAOImpl1 implements MyDAO{
	@Autowired 
	SessionFactory sf;
    @Autowired
    HttpSession h;
    static double totalpricewithgst=0.0;

	 List<ProductDTO> plist=new ArrayList();
    @Override
	public boolean register(UserDTO dto) {
		System.out.println("inside dao");
		Session ss=sf.openSession();
		Criteria cr=ss.createCriteria(UserDTO.class);
		cr.add(Restrictions.eq("Email", dto.getEmail()));
		UserDTO udto=(UserDTO) cr.uniqueResult();
		System.out.println("udto"+udto);

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
	public boolean addData(ProductDTO dto) {
		System.out.println("inside dao");
		Session ss=sf.openSession();
		Criteria cr=ss.createCriteria(ProductDTO.class);
		cr.add(Restrictions.eq("name", dto.getName()));
		ProductDTO pdto=(ProductDTO) cr.uniqueResult();
		System.out.println("udto"+pdto);

		if(pdto==null){
		ss.save(dto);
		ss.beginTransaction().commit();
		System.out.println("Product added Successfully.");
		ss.close();
		return true;
		}else{
			ss.close();
			return false;
		}
	}

	@Override
	public List<ProductDTO> view() {
		System.out.println("inside dao");
		Session ss=sf.openSession();
		Criteria cr =ss.createCriteria(ProductDTO.class);
		List<ProductDTO> plist=cr.list();

		ss.close();
		return plist;
	}

	@Override
	public ProductDTO edit(int id) {
		Session ss=sf.openSession();
		Criteria cr=ss.createCriteria(ProductDTO.class);
		cr.add(Restrictions.eq("id", id));
		ProductDTO p=(ProductDTO) cr.uniqueResult();
		ss.close();
		return p;
	}

	@Override
	public boolean updatedata( ProductDTO dto) {
		Session ss=sf.openSession();
		Criteria cr=ss.createCriteria(ProductDTO.class);
		cr.add(Restrictions.eq("name", dto.getName()));
		ProductDTO p=(ProductDTO) cr.uniqueResult();
		if (p!=null) {
			p.setName(dto.getName());
			p.setCategory(dto.getCategory());
			p.setCompany(dto.getCompany());
			p.setPrice(dto.getPrice());
			p.setQuantity(dto.getQuantity());
			ss.saveOrUpdate(p);
			ss.beginTransaction().commit();
			ss.close();
			return true;
		} else {
			return false;
		}
					
		}

	@Override
	public List<ProductDTO> search(HttpServletRequest req) {
		System.out.println("inside dao");
		Session ss=sf.openSession();
		String search=req.getParameter("search");
		Criteria cr =ss.createCriteria(ProductDTO.class);
		Criterion name = Restrictions.like("name",search,MatchMode.ANYWHERE);
		Criterion company = Restrictions.like("company",search,MatchMode.ANYWHERE);
		Criterion category = Restrictions.like("category",search,MatchMode.ANYWHERE);

		Criterion completeCondition = 
		    Restrictions.disjunction().add(company).add(category).add(name);

		cr.add(completeCondition);
		List<ProductDTO> list=cr.list();
		ss.close();
		return list;
	}

	@Override
	public List<ProductDTO> addtocart(int id){
		System.out.println("inside dao");
		Session ss=sf.openSession();
		ProductDTO l=ss.load(ProductDTO.class,id);   
		 List<ProductDTO> plist=new ArrayList<ProductDTO>();
	     plist.add(l);
	     double price=l.getPrice();
	     System.out.println("Price"+price);
	    
	     if(l.getCategory().equalsIgnoreCase("Electronics")){
	     System.out.println("Totalprice"+price);
	     totalpricewithgst=price*0.05+price;
	     }else if (l.getCategory().equalsIgnoreCase("Footwear")) {
	    	 totalpricewithgst=price*0.05+price;
		}else if (l.getCategory().equalsIgnoreCase("Beauty")) {
			totalpricewithgst=price*0.05+price;
		}
		else if (l.getCategory().equalsIgnoreCase("Clothes")) {
			totalpricewithgst=price*0.05+price;
		}
		else if (l.getCategory().equalsIgnoreCase("Mobile")) {
			totalpricewithgst=price*0.05+price;
		}
	     int quantity =l.getQuantity();
		    quantity=quantity-1;
		    l.setQuantity(quantity);
		    ss.update(l);
		    ss.beginTransaction().commit();
		    
		    OrderDTO o=new OrderDTO();
		     o.setTotal_price(price);
		      o.setTotal_price_with_gst(totalpricewithgst);
		      o.setPlist(plist);
		      ss.save(o);
		      ss.beginTransaction().commit();
	      for(ProductDTO list:plist){
	    	  System.out.println("Id"+list.getProductid()+"  Quantity"+list.getQuantity());
	      }
	      System.out.println("Totalprice outside"+price);
	      Criteria cr=ss.createCriteria(ProductDTO.class);
	      List<ProductDTO> list=cr.list();
	      ss.close();
		return list;
	}

	@Override
	public List<OrderDTO> mycart() {
		System.out.println("inside dao");
		Session ss=sf.openSession();
		Criteria cr=ss.createCriteria(OrderDTO.class);
		List<OrderDTO> list=cr.list();

		return list;
	}
	}
