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
@Table(name="Products_Info")
public class ProductDTO {
@Id
@GenericGenerator(name="auto",strategy="increment")
@GeneratedValue(generator="auto")
private int productid;
private String name;
private String category;
private String company;
private int quantity;
private double price;
@ManyToMany(cascade=CascadeType.ALL)
@JoinTable(name="Order_History_Info",joinColumns=@JoinColumn(name="productid"),inverseJoinColumns=@JoinColumn(name="orderid"))
private List<OrderDTO> olist;

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public String getCompany() {
	return company;
}
public void setCompany(String company) {
	this.company = company;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public int getProductid() {
	return productid;
}
public void setProductid(int productid) {
	this.productid = productid;
}
public List<OrderDTO> getOlist() {
	return olist;
}
//public void setOlist(List<OrderDTO> olist) {
//	this.olist = olist;
//}

}
