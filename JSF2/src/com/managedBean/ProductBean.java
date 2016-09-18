package com.managedBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.servlet.http.Part;

import com.model.Category;
import com.model.Product;
import com.util.CategoryDAO;
import com.util.ProductDAO;

@ManagedBean(name="pb")
@ViewScoped
public class ProductBean
{
	private ProductDAO prodDAO;
	private CategoryDAO catDAO;
	private List<Product> products=new ArrayList<>();
	private List<Category> categoryList=new ArrayList<>();
	private Product prod=new Product();
	private Part img;
	private HtmlDataTable productDatatable;

	public HtmlDataTable getProductDatatable() {
		return productDatatable;
	}

	public void setProductDatatable(HtmlDataTable productDatatable) {
		this.productDatatable = productDatatable;
	}

	public Part getImg() {
		return img;
	}

	public void setImg(Part img) {
		this.img = img;
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> category) {
		this.categoryList = category;
	}

	public Product getProd() {
		return prod;
	}

	public void setProd(Product prod) {
		this.prod = prod;
	}

	public ProductBean() {
		prodDAO = new ProductDAO();
		products=prodDAO.getProductList();
		catDAO=new CategoryDAO();
		categoryList=catDAO.getCategoryList();
	}

	public List<Product> getProducts() {
		products=prodDAO.getProductList();
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String editRow(Product p) {
		p.setEdited(true);
		return null;
	}

	// Action
	public String updateProduct() 
	{
		for (Product p : products) {
			p.setEdited(false);
		}
		//System.out.println("OUTPUT:  "+getProd().getProductName()+" "+getProd().getProductId()+" "+getProd().getCategory().getCategoryId());
		Product p = (Product) productDatatable.getRowData();
		System.out.println("Hello"+p.getCategory().getCategoryId()+" "+p.getCategory().getCategoryName());
		prodDAO.updateProductToDb(p );	
		return "Product";
	}

	public String deleteProduct()
	{
		if (productDatatable != null) {
			Product p = (Product) productDatatable.getRowData();
			products.remove(p);
			prodDAO.deleteProductFromDb(p);
		}
		return "Product";
	}

	public String addProduct() throws IOException
	{  	
		if (img != null)
		{
			InputStream is = getImg().getInputStream();
			byte[] buffer = new byte[4000];
			FileOutputStream fos = new FileOutputStream("/images/tmp.jpg");
			
			int bytesRead = 0;
			while( (bytesRead = is.read(buffer)) > 0)
				fos.write(buffer, 0, bytesRead);
	        
			fos.close();
			is.close();
		}
		prodDAO.addProductToDb(prod);
		
		if(img != null)
		{
			File file = new File("/images/tmp.jpg");
			file.renameTo(new File("/imagess/" + prod.getProductId() + ".jpg"));
		}
		return "Product";
	}
}