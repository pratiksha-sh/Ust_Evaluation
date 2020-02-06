package com.pra.Shop_Management.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Order_Info")
public class OrderDTO {
	@Id
	@GenericGenerator(name="auto",strategy="increment")
	@GeneratedValue(generator="auto")
	private int orderid;
	private double total_price;
	private double total_price_with_gst;
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="Order_History_Info",joinColumns=@JoinColumn(name="orderid"),inverseJoinColumns=@JoinColumn(name="productid"))
	private List<ProductDTO> plist;
	
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	
	
	public double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}
	public double getTotal_price_with_gst() {
		return total_price_with_gst;
	}
	public void setTotal_price_with_gst(double total_price_with_gst) {
		this.total_price_with_gst = total_price_with_gst;
	}
	public List<ProductDTO> getPlist() {
		return plist;
	}
	public void setPlist(List<ProductDTO> plist) {
		this.plist = plist;
	}
}
