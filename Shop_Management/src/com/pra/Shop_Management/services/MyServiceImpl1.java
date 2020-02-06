package com.pra.Shop_Management.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pra.Shop_Management.dao.MyDAO;
import com.pra.Shop_Management.models.OrderDTO;
import com.pra.Shop_Management.models.ProductDTO;
import com.pra.Shop_Management.models.UserDTO;

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
	public boolean addData(ProductDTO dto) {
		System.out.println("inside service");
		boolean b =mdao.addData(dto);
		return b;
	}



	@Override
	public List<ProductDTO> view() {
		// TODO Auto-generated method stub
				List<ProductDTO> list=mdao.view();
				return list;
	}



	@Override
	public ProductDTO edit(int id) {
	ProductDTO obj=mdao.edit(id);
		return obj;
	}



	@Override
	public boolean updatedata(ProductDTO dto) {
		boolean b=mdao.updatedata(dto);
				return b;
	}



	@Override
	public List<ProductDTO> search(HttpServletRequest req) {
		List<ProductDTO> list=mdao.search(req);
		return list;
	}



	@Override
	public List<ProductDTO> addtocart(int id) {
		List<ProductDTO> list=mdao.addtocart(id);
		return list;
	}



	@Override
	public List<OrderDTO> mycart() {
		List<OrderDTO> list=mdao.mycart();
		return list;
	}


}
