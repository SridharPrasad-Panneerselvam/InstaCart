package com.instacart.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.instacart.dao.CartDao;
import com.instacart.dao.ProductDao;
import com.instacart.model.Admin;
import com.instacart.model.Cart;
import com.instacart.model.Product;
import com.instacart.model.User;

@Component
@Controller
public class CartController {
	
	@Autowired
	Admin admin;
	
	@Autowired
	User user;
	
	@Autowired
	Product product;
	
	
	@Autowired
	ProductDao productDao;
	
	@Autowired 
	Cart cart;
	
	@Autowired
	CartDao cartDao;
	
	@RequestMapping(value="/addToCartButton.htm", method = RequestMethod.POST)
	public ModelAndView addProducts(HttpServletRequest request,
    HttpServletResponse response)throws Exception{
		try {
		ModelAndView mv = null;
		
		int initialQuantity = 1;
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("userLoggedIn");
		if (user == null) {
			
			return new ModelAndView("index");
			
		}
		
		int pid = Integer.parseInt(request.getParameter("item"));
		

		
		Product product = productDao.productSearchById(pid);

		cart.setProduct(product);
		cart.setUser(user);
		
		int productId = product.getPid();
		int userId = user.getUserId();
		String cartStatus = "In Cart";
		
		int result = cartDao.validateExistingCart(productId, userId, cartStatus);
		
		if(result==1) {
			List<Product> productList = new ArrayList();
			productList= productDao.getAllProducts();
			mv = new ModelAndView();
			mv.addObject("productList",productList);
			mv.addObject("error", true);
			mv.setViewName("User-ViewProducts");
			
		}else {
			cart.setCartStatus("In Cart");
			cart.setCartQuantity(initialQuantity);
			cartDao.addCart(cart);	
			List<Product> productList = new ArrayList();
			productList= productDao.getAllProducts();
			mv = new ModelAndView();
			mv.addObject("productList",productList);
			mv.addObject("success", true);
			mv.setViewName("User-ViewProducts");
			
			
		}
		return mv;
		}catch(Exception e){
			ModelAndView mv = null;
			HttpSession httpSession = request.getSession();
			httpSession.invalidate();
			mv = new ModelAndView("index");
			return mv;
		}
		
	}
	
	@RequestMapping(value="/viewCart.htm", method = RequestMethod.GET)
	public ModelAndView viewCart(HttpServletRequest request)throws Exception{
		try {
		ModelAndView mv = null;
		
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("userLoggedIn");
		if (user == null) {
			
			return new ModelAndView("index");
			
		}
		
		List<Cart> cartList = new ArrayList();
		mv = new ModelAndView();
		
		int userId= user.getUserId();
		cartList = cartDao.getAllCartProductsbyId(userId);
		
		mv.addObject("cartList", cartList);
		mv.setViewName("User-ViewCart");
		
		return mv;
		}catch(Exception e){
			ModelAndView mv = null;
			HttpSession httpSession = request.getSession();
			httpSession.invalidate();
			mv = new ModelAndView("index");
			return mv;
		}

}
	
	@RequestMapping(value="/updateQuantity.htm", method = RequestMethod.POST)
	public ModelAndView updateQuantity(HttpServletRequest request,
    HttpServletResponse response)throws Exception{
		try {
		ModelAndView mv = null;
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("userLoggedIn");
		if(user == null) {
			return new ModelAndView("index");
		}
		
		
		int cid = Integer.parseInt(request.getParameter("item"));


		int cartQuantity = Integer.parseInt(request.getParameter("cartQuantity"));
		int totalQuantity = Integer.parseInt(request.getParameter("totalQuantity"));
		
		
		
		if(cartQuantity>totalQuantity) {
			List<Cart> cartList = new ArrayList();
			mv = new ModelAndView();
			
			int userId= user.getUserId();
			cartList = cartDao.getAllCartProductsbyId(userId);
			mv.addObject("error",true);
			mv.addObject("cartList", cartList);
			mv.setViewName("User-ViewCart");
		}
		
		else {
			int result = cartDao.editCart(cartQuantity,cid);
		if(result==1) {
			List<Cart> cartList = new ArrayList();
			mv = new ModelAndView();
			
			int userId= user.getUserId();
			cartList = cartDao.getAllCartProductsbyId(userId);
			mv.addObject("success",true);
			mv.addObject("cartList", cartList);
			mv.setViewName("User-ViewCart");
		}
		}
		return mv;
		}catch(Exception e){
			ModelAndView mv = null;
			HttpSession httpSession = request.getSession();
			httpSession.invalidate();
			mv = new ModelAndView("index");
			return mv;
		}
	}
	
	@RequestMapping(value="/removeCartButton.htm", method = RequestMethod.POST)
	public ModelAndView removeCart(HttpServletRequest request,
    HttpServletResponse response)throws Exception{
		try {
		ModelAndView mv = null;
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("userLoggedIn");
		if(user == null) {
			return new ModelAndView("index");
		}
		
		
		int cid = Integer.parseInt(request.getParameter("item"));

		int result = cartDao.removeCart(cid);
		
		if(result==1) {
			List<Cart> cartList = new ArrayList();
			mv = new ModelAndView();
			
			int userId= user.getUserId();
			cartList = cartDao.getAllCartProductsbyId(userId);
			mv.addObject("success",true);
			mv.addObject("cartList", cartList);
			mv.setViewName("User-ViewCart");
		}
		
		return mv;
		}catch(Exception e){
			ModelAndView mv = null;
			HttpSession httpSession = request.getSession();
			httpSession.invalidate();
			mv = new ModelAndView("index");
			return mv;
		}
	}
	
	
	@RequestMapping(value="/checkout.htm", method = RequestMethod.POST)
	public ModelAndView checkout(HttpServletRequest request,HttpServletResponse response)throws Exception{
		try {
		ModelAndView mv = null;
		
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("userLoggedIn");
		if (user == null) {
			
			return new ModelAndView("index");
			
		}
		
		List<Cart> cartList = new ArrayList();
		mv = new ModelAndView();
		
		int userId= user.getUserId();
		cartList = cartDao.getAllCartProductsbyId(userId);
		
		mv.addObject("cartList", cartList);
		mv.setViewName("User-Checkout");
		
		return mv;
		}catch(Exception e){
			ModelAndView mv = null;
			HttpSession httpSession = request.getSession();
			httpSession.invalidate();
			mv = new ModelAndView("index");
			return mv;
		}

}
	
	@RequestMapping(value="/placeOrder.htm", method = RequestMethod.POST)
	public ModelAndView placeOrder(HttpServletRequest request)throws Exception{
		try {
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userLoggedIn");
		if(user == null) {
			System.out.println("user inside");
			return new ModelAndView("index");	
		}
		
		List<Cart> cartList = new ArrayList();
		
		int userId= user.getUserId();
		cartList = cartDao.getAllCartProductsbyId(userId);
		
		for(Cart c :cartList) {
			int cartQuantity= c.getCartQuantity();
			int productQuantity = c.getProduct().getProductQuantity();
			 int quantity = productQuantity -  cartQuantity;
			 productDao.updateQuantity(quantity,c.getProduct().getPid());
			 cartDao.changeStatus(c);
			
		}
		
		String deliveryMobile = request.getParameter("userMobile");
		String deliveryAddress = request.getParameter("userAddress");
		
		
		request.getSession().setAttribute("cartList", cartList);
		request.getSession().setAttribute("deliveryAddress", deliveryAddress);
		request.getSession().setAttribute("deliveryMobile", deliveryMobile);
		
		return new ModelAndView("User-PlaceOrder","cartList",cartList);
		
		}catch(Exception e){
			ModelAndView mv = null;
			HttpSession httpSession = request.getSession();
			httpSession.invalidate();
			mv = new ModelAndView("index");
			return mv;
		}
		}
	
	
	@RequestMapping(value="/viewOrderHistory.htm", method = RequestMethod.GET)
	public ModelAndView viewOrderHistory(HttpServletRequest request) throws Exception{
		try {
		ModelAndView mv = null;
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userLoggedIn");
		if(user == null) {
			System.out.println("user inside");
			return new ModelAndView("index");	
		}
		
		List<Cart> cartList = new ArrayList();
	
		int userId= user.getUserId();
		String cartStatus = "Ordered";
		cartList= cartDao.getAllCartProductsbyStatus(userId,cartStatus);
		
		mv = new ModelAndView();
		
		mv.addObject("cartList",cartList);
		mv.setViewName("User-ViewOrderHistory");
		return mv;
		}catch(Exception e){
			ModelAndView mv = null;
			HttpSession httpSession = request.getSession();
			httpSession.invalidate();
			mv = new ModelAndView("index");
			return mv;
		}
}
	
	@RequestMapping(value = "/generatepdf.pdf", method = RequestMethod.GET)
	public ModelAndView generatePdf(HttpServletRequest request,HttpServletResponse response, PdfReport pdf) throws Exception {
		try {
		
		ModelAndView mv = null;
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("userLoggedIn");
		if (user == null) {
			return new ModelAndView("index");
		}
		
	
		
		Map<String,Object> cart = new HashMap<String, Object>();
		cart.put("cartList", request.getSession().getAttribute("cartList"));
		cart.put("user", request.getSession().getAttribute("userLoggedIn"));
		cart.put("userMobile", request.getSession().getAttribute("deliveryMobile"));
		cart.put("userAddress",request.getSession().getAttribute("deliveryAddress"));
		return new ModelAndView(new PdfReport(), cart);
		}catch(Exception e){
			ModelAndView mv = null;
			HttpSession httpSession = request.getSession();
			httpSession.invalidate();
			mv = new ModelAndView("index");
			return mv;
		}
	}
}
