package com.pra.Shop_Management.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pra.Shop_Management.models.OrderDTO;
import com.pra.Shop_Management.models.ProductDTO;
import com.pra.Shop_Management.models.UserDTO;
import com.pra.Shop_Management.services.MyService;


@Component
@RequestMapping("/")
public class MyController {
	@Autowired
	MyService ms;
	HttpSession h;
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	@RequestMapping("/search")
	public ModelAndView search(){
		if(h.getAttribute("email")!=null) {
	return new ModelAndView("searchproduct");
			  }else {
				  return new ModelAndView("login","msg3","Login First!!!!");
			  }
		
	}

	@RequestMapping("/registration")
	public String registration(){
		return "registration";
	}
	@RequestMapping(value="/loginValidation",method=RequestMethod.POST)
	public ModelAndView loginValidation(@ModelAttribute UserDTO dto,HttpServletRequest req){
		UserDTO udto=ms.login(dto);
		if(udto!=null){
			h=req.getSession();
			h.setAttribute("email",dto.getEmail());
			System.out.println("Login Success");
			return new ModelAndView("Home","dto",udto);
		}else{
			System.out.println("Login Failed");
			 return new ModelAndView("login","msg","Login failed.. Invalid email and password!!");
		
		}
	}
	@RequestMapping(value="/registrationData",method=RequestMethod.POST)
	public ModelAndView registrationData(@ModelAttribute UserDTO rdto){
		boolean b=ms.register(rdto);

		System.out.println("inside controller");
			
			if(b){
	return new ModelAndView("login","dto","Registration Successfull.. User can login to their account... ");
			}else{
				return new ModelAndView("registration","dto","User Already Registered..Registration Not Done ...");
			}
	}
	
	@RequestMapping("/viewproducts")
	public ModelAndView viewproducts(){
		if(h.getAttribute("email")!=null) {
		List<ProductDTO> list=ms.view();
	
		return new ModelAndView("viewpage","l",list);
		  }else {
			  return new ModelAndView("login","msg3","Login First!!!!");
		  }
		
	}
	
	@RequestMapping(value="/logout")
	public ModelAndView logout(HttpServletRequest req){
		System.out.println("logout");
		 if(h.getAttribute("email")!=null) {
			//  h.invalidate();
			h.setAttribute("email",null);
			 return new ModelAndView("login","msg2","Logout Successful.. Login First");
			
		}else{
			return new ModelAndView("login","msg2","Login First.. User is not loggged in ");
		}
		
	}
	@RequestMapping("/addproduct")
	public ModelAndView addproduct(){
		if(h.getAttribute("email")!=null) {
			return new ModelAndView("addprod");
		  }else {
			  return new ModelAndView("login","msg3","Login First!!!!");
		  }
	}
	
	@RequestMapping(value="/addData",method=RequestMethod.POST)
	public ModelAndView addData(@ModelAttribute ProductDTO dto){
		System.out.println("Registration done");
		System.out.println(dto.getProductid()+" "+dto.getName()+" "+dto.getCategory()+" "+dto.getCompany()+" "+dto.getPrice()+" "+dto.getQuantity());
		System.out.println("inside controller");
		boolean b=ms.addData(dto);
				if(b){
			return new ModelAndView("Home","dto1","Product added.. ");
					}else{
						return new ModelAndView("addprod","dto1","Product Not added ...");
					}
	}
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam int id){
		if(h.getAttribute("email")!=null) {
		ProductDTO obj=ms.edit(id);
		return new ModelAndView("updatepage","obj",obj);
		  }else {
			  return new ModelAndView("login","msg3","Login First!!!!");
		  }
	}
	@RequestMapping(value="/UpdateData",method=RequestMethod.POST)
	public ModelAndView UpdateData(@ModelAttribute ProductDTO dto){
		boolean b=ms.updatedata(dto);
		if(b){
			return new ModelAndView("Home","msg1","Data updated Successfully ");
			
		}else{
			return new ModelAndView("updatepage","msg1","Data not Updated");
		}
	
	}
	@RequestMapping("/searchdata")
	public ModelAndView searchdata(HttpServletRequest req){
		if(h.getAttribute("email")!=null) {
		List<ProductDTO> list=ms.search(req);
		return new ModelAndView("viewpage","l",list);
		  }else {
			  return new ModelAndView("login","msg3","Login First!!!!");
		  }
	}
	@RequestMapping("/addtocart")	
	public ModelAndView addtocart(@RequestParam int id){
		if(h.getAttribute("email")!=null) {
		List<ProductDTO>list= ms.addtocart(id);
     return new ModelAndView("viewpage","l",list);
	  }else {
		  return new ModelAndView("login","msg3","Login First!!!!");
	  }
    }
	@RequestMapping("/mycart")	
	public ModelAndView mycart() {
		if(h.getAttribute("email")!=null) {
	
		List<OrderDTO> list= ms.mycart();
     return new ModelAndView("viewpagecart","list",list);
	  }else {
		  return new ModelAndView("login","msg3","Login First!!!!");
	  }
    }
	}
