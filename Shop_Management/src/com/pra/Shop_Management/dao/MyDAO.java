package com.pra.Shop_Management.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pra.Shop_Management.models.OrderDTO;
import com.pra.Shop_Management.models.ProductDTO;
import com.pra.Shop_Management.models.UserDTO;
public interface MyDAO {
	 public boolean register(UserDTO dto);
	 public UserDTO login(UserDTO dto);
	public boolean addData(ProductDTO dto);
	public List<ProductDTO> view();
	public ProductDTO edit(int id);
	public boolean updatedata(ProductDTO dto);
	public List<ProductDTO> search(HttpServletRequest req);
	public List<ProductDTO> addtocart(int id);
//	public List<ProductDTO> generatebill(HttpServletRequest req);
	//public List<ProductDTO> order();
	public List<OrderDTO> mycart();

}
