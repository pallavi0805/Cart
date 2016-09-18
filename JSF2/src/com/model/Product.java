package com.model;

import java.io.ByteArrayInputStream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Entity
public class Product  {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="mysequence")
	@SequenceGenerator(name="mysequence",sequenceName="PRODUCT_SEQ",allocationSize=1)
	@Column(name="PRODUCT_ID")
	int productId;
	@Column(name="PRODUCT_NAME")
	String productName;
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="CATEGORY_ID")
	Category category;	
	double price;
	String description;
	int review;	
	@Transient
	private boolean edited;
	public boolean isEdited() {
	return edited;
	}
	public void setEdited(boolean edited) {
	this.edited = edited;
	}
   public Product() {
		// TODO Auto-generated constructor stub
		category=new Category();
	}
	@Lob

	 protected byte[] image;
	public StreamedContent getImage() {	
		if(image!=null)			
		return new DefaultStreamedContent(new ByteArrayInputStream(image));//,"image/jpeg"
		else
		return new DefaultStreamedContent(); 
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
    
	@Column(name="STOCK_AVAILABILITY")
	int stockAvailability;
	
//	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="mysequence")
//	@SequenceGenerator(name="mysequence",sequenceName="PRODUCT_SEQ",allocationSize=1)
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getReview() {
		return review;
	}
	public void setReview(int review) {
		this.review = review;
	}
	public int getStockAvailability() {
		return stockAvailability;
	}
	public void setStockAvailability(int stockAvailability) {
		this.stockAvailability = stockAvailability;
	}
	

}
