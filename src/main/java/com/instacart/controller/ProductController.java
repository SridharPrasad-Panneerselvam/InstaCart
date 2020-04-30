package com.instacart.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.instacart.dao.AdminDao;
import com.instacart.dao.ProductDao;
import com.instacart.dao.UserDao;
import com.instacart.model.Admin;
import com.instacart.model.Product;
import com.instacart.model.User;

@Component
@Controller
public class ProductController {


	@Autowired
	Admin admin;
	
	@Autowired
	User user;
	
	@Autowired
	Product product;
	
	
	@Autowired
	ProductDao productDao;
	
	@RequestMapping(value = "/addProducts.htm", method = RequestMethod.GET)
	public ModelAndView aboutInstaCart(HttpServletRequest request) {
		try {
		HttpSession session = request.getSession();
		Admin user = (Admin)session.getAttribute("userLoggedIn");
		if (user == null) {
			return new ModelAndView("index");
		}
		return new ModelAndView("Admin-AddProducts");
		}catch(Exception e){
			ModelAndView mv = null;
			HttpSession httpSession = request.getSession();
			httpSession.invalidate();
			mv = new ModelAndView("index");
			return mv;
		}
	}
	
	
	@RequestMapping(value="/addProductSuccess.htm", method = RequestMethod.POST)
	public ModelAndView addProducts(HttpServletRequest request,
    HttpServletResponse response)throws Exception{
		try {
		ModelAndView mv = null;
		
		HttpSession session = request.getSession();
		Admin user = (Admin)session.getAttribute("userLoggedIn");
		if (user == null) {
			
			return new ModelAndView("index");
			
		}
		
		
		
		String productName= request.getParameter("productName");
		int productPrice= Integer.parseInt(request.getParameter("productPrice"));
		int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
		String productImagePath = request.getParameter("productImagePath");
		String productStatus = "";
		if(productQuantity==0) {
			productStatus = "Out of Stock";
		}else if(productQuantity>0 && productQuantity<10) {
			productStatus = "Low on Stock";
		}
		else {
			productStatus = "In Stock";
		}
		
		
		boolean checkproductName = productDao.validateExistingProduct(productName);
		if(checkproductName == true) {
			return new ModelAndView("Admin-AddProduct","errorMessage",true);
		}
		
		product.setProductName(productName);
		product.setProductPrice(productPrice);
		product.setProductQuantity(productQuantity);
		product.setProductImagePath(productImagePath);
		product.setProductStatus(productStatus);
		
		productDao.addProduct(product);
		
		mv = new ModelAndView("Admin-MainPage");
		return mv;
		}catch(Exception e){
			ModelAndView mv = null;
			HttpSession httpSession = request.getSession();
			httpSession.invalidate();
			mv = new ModelAndView("index");
			return mv;
		}
		

	}
	
	@RequestMapping(value="/viewProducts.htm", method = RequestMethod.GET)
	public ModelAndView viewProducts(HttpServletRequest request)throws Exception{
		try {
		ModelAndView mv = null;
		
		HttpSession session = request.getSession();
		Admin user = (Admin)session.getAttribute("userLoggedIn");
		if (user == null) {
			return new ModelAndView("index");
		}
		
		List<Product> productList = new ArrayList();
		mv = new ModelAndView();
		
		productList = productDao.getAllProducts();
		
		mv.addObject("productList", productList);
		mv.setViewName("Admin-ViewProducts");
		
		
			return mv;
		}catch(Exception e){
			ModelAndView mv = null;
			HttpSession httpSession = request.getSession();
			httpSession.invalidate();
			mv = new ModelAndView("index");
			return mv;
		}
		
	}
	
	@RequestMapping(value="/productSearch.htm", method= RequestMethod.POST)
	public ModelAndView productSearch(HttpServletRequest request, HttpServletResponse response)throws Exception{
		try {
		ModelAndView mv = null;
		
		HttpSession session = request.getSession();
		Admin user = (Admin)session.getAttribute("userLoggedIn");
		if (user == null) {
			return new ModelAndView("index");
		}
		
		String productSearch= request.getParameter("nameSearch");
		mv = new ModelAndView();
		
		List<Product> productList = new ArrayList();
		
		productList = productDao.productSearch(productSearch);
		mv.addObject("productList",productList);
		mv.setViewName("Admin-ViewProducts");
		return mv;
		}catch(Exception e){
			ModelAndView mv = null;
			HttpSession httpSession = request.getSession();
			httpSession.invalidate();
			mv = new ModelAndView("index");
			return mv;
		}
		
	}
	
	@RequestMapping(value="/productSearchforUser.htm", method= RequestMethod.POST)
	public ModelAndView productSearchforUser(HttpServletRequest request, HttpServletResponse response)throws Exception{
		try {
		ModelAndView mv = null;
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("userLoggedIn");
		if (user == null) {
			return new ModelAndView("index");
		}
		
		String productSearch= request.getParameter("nameSearch");
		mv = new ModelAndView();
		
		List<Product> productList = new ArrayList();
		
		productList = productDao.productSearch(productSearch);
		mv.addObject("productList",productList);
		mv.setViewName("User-ViewProducts");
		return mv;
		}catch(Exception e){
			ModelAndView mv = null;
			HttpSession httpSession = request.getSession();
			httpSession.invalidate();
			mv = new ModelAndView("index");
			return mv;
		}
	}
	
	
	@RequestMapping(value="/editProduct.htm", method=RequestMethod.POST)
	public ModelAndView editProduct(HttpServletRequest request, HttpServletResponse response)throws Exception{
		try {
		ModelAndView mv = null;
		
		HttpSession session = request.getSession();
		Admin user = (Admin) session.getAttribute("userLoggedIn");
		if(user== null) {
			return new ModelAndView("index");
			
		}
		
		int pid = Integer.parseInt(request.getParameter("pid"));
		
		String productName= request.getParameter("productName");
		int productPrice= Integer.parseInt(request.getParameter("productPrice"));
		int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
		String productImagePath = request.getParameter("productImagePath");
		String productStatus = "";
		if(productQuantity==0) {
			productStatus = "Out of Stock";
		}else if(productQuantity>0 && productQuantity<10) {
			productStatus = "Low on Stock";
		}
		else {
			productStatus = "In Stock";
		}
		
		product.setPid(pid);
		product.setProductName(productName);
		product.setProductPrice(productPrice);
		product.setProductQuantity(productQuantity);
		product.setProductImagePath(productImagePath);
		product.setProductStatus(productStatus);
		
		productDao.editProduct(product);
		
		mv = new ModelAndView("Admin-MainPage","Update",true);
		
		return mv;
		}catch(Exception e){
			ModelAndView mv = null;
			HttpSession httpSession = request.getSession();
			httpSession.invalidate();
			mv = new ModelAndView("index");
			return mv;
		}
		
	}
	
	@RequestMapping(value="/editProductButton.htm",method=RequestMethod.POST)
	public ModelAndView editProductButton(HttpServletRequest request, HttpServletResponse response)throws Exception{
		try {
		ModelAndView mv = null;
		
		HttpSession session = request.getSession();
		Admin user = (Admin) session.getAttribute("userLoggedIn");
		if(user== null) {
			return new ModelAndView("index");
			
		}
		
		int pid = Integer.parseInt(request.getParameter("item"));
		
		Product product = productDao.productSearchById(pid);
		
		
		mv = new ModelAndView();
		mv.addObject("product", product);
		mv.setViewName("Admin-UpdateProduct");
		return mv;
		}catch(Exception e){
			ModelAndView mv = null;
			HttpSession httpSession = request.getSession();
			httpSession.invalidate();
			mv = new ModelAndView("index");
			return mv;
		}
	}
	
	@RequestMapping(value="/removeProductButton.htm", method=RequestMethod.POST)
	public ModelAndView removeProductButton(HttpServletRequest request, HttpServletResponse response)throws Exception{
		try {
		ModelAndView mv = null;
		
		HttpSession session = request.getSession();
		Admin user = (Admin) session.getAttribute("userLoggedIn");
		if(user== null) {
			return new ModelAndView("index");
			
		}
		
		int pid = Integer.parseInt(request.getParameter("item"));
		
		productDao.removeProduct(pid);
		
		return new ModelAndView("Admin-MainPage","Delete",true);
		}catch(Exception e){
			ModelAndView mv = null;
			HttpSession httpSession = request.getSession();
			httpSession.invalidate();
			mv = new ModelAndView("index");
			return mv;
		}
}
	
	@RequestMapping(value="/viewUserProducts.htm", method = RequestMethod.GET)
	public ModelAndView viewUserProducts(HttpServletRequest request)throws Exception{
		try {
		ModelAndView mv = null;
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("userLoggedIn");
		if (user == null) {
			return new ModelAndView("index");
		}
		
		List<Product> productList = new ArrayList();
		mv = new ModelAndView();
		
		productList = productDao.getAllProducts();
		
		mv.addObject("productList", productList);
		mv.setViewName("User-ViewProducts");
		
		
		
			return mv;
		}catch(Exception e){
			ModelAndView mv = null;
			HttpSession httpSession = request.getSession();
			httpSession.invalidate();
			mv = new ModelAndView("index");
			return mv;
		}
		
	}
}
