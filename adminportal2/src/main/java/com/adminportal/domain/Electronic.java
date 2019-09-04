package com.adminportal.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Electronic {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String modelName;
	private String color;
	private String brandName;
	private String releaseDate;
	private String category;
	private String mainCategory;
	private int dimension_height;
	private int dimension_width;
	private int stars;
	private int isbn;
	private double shippingWeight;
	private double listPrice;
	private double ourPrice;
	private int Warranty;
	private boolean active=true;
	
	@Column(columnDefinition="text")
	private String description;
	private int inStockNumber;
	
	@Transient
	private MultipartFile electronicImage;
	
	@Transient
	private MultipartFile CompanyImage;
	
	
	@OneToMany(mappedBy = "electronic")
	@JsonIgnore
	private List<ElectronicToCartItem> electronicToCartItemList;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getModelName() {
		return modelName;
	}


	public void setModelName(String modelName) {
		this.modelName = modelName;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public String getBrandName() {
		return brandName;
	}


	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}


	public String getReleaseDate() {
		return releaseDate;
	}


	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getMainCategory() {
		return mainCategory;
	}


	public void setMainCategory(String mainCategory) {
		this.mainCategory = mainCategory;
	}


	public int getDimension_height() {
		return dimension_height;
	}


	public void setDimension_height(int dimension_height) {
		this.dimension_height = dimension_height;
	}


	public int getDimension_width() {
		return dimension_width;
	}


	public void setDimension_width(int dimension_width) {
		this.dimension_width = dimension_width;
	}


	public int getStars() {
		return stars;
	}


	public void setStars(int stars) {
		this.stars = stars;
	}


	public int getIsbn() {
		return isbn;
	}


	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}


	public double getShippingWeight() {
		return shippingWeight;
	}


	public void setShippingWeight(double shippingWeight) {
		this.shippingWeight = shippingWeight;
	}


	public double getListPrice() {
		return listPrice;
	}


	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}


	public double getOurPrice() {
		return ourPrice;
	}


	public void setOurPrice(double ourPrice) {
		this.ourPrice = ourPrice;
	}


	public int getWarranty() {
		return Warranty;
	}


	public void setWarranty(int warranty) {
		Warranty = warranty;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getInStockNumber() {
		return inStockNumber;
	}


	public void setInStockNumber(int inStockNumber) {
		this.inStockNumber = inStockNumber;
	}


	public MultipartFile getElectronicImage() {
		return electronicImage;
	}


	public void setElectronicImage(MultipartFile electronicImage) {
		this.electronicImage = electronicImage;
	}


	public MultipartFile getCompanyImage() {
		return CompanyImage;
	}


	public void setCompanyImage(MultipartFile companyImage) {
		CompanyImage = companyImage;
	}


	public List<ElectronicToCartItem> getElectronicToCartItemList() {
		return electronicToCartItemList;
	}


	public void setElectronicToCartItemList(List<ElectronicToCartItem> electronicToCartItemList) {
		this.electronicToCartItemList = electronicToCartItemList;
	}


	

	
	
	
}