package com.instacart.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.instacart.dao.AdminDao;
import com.instacart.dao.UserDao;
import com.instacart.encryption.UserEncryption;
import com.instacart.model.Admin;
import com.instacart.model.Cart;
import com.instacart.model.User;

@Component
@Controller
public class MainController {

	@Autowired
	UserDao userDao;
	
	@Autowired
	AdminDao adminDao;
	
	@Autowired
	Admin admin;
	
	@Autowired
	User user;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "index";
	}
	
	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
	public String homes() {
		return "index";
	}
	
	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public ModelAndView Logout(HttpServletRequest request, HttpServletResponse Response) throws Exception {
		ModelAndView mv = null;
		
		HttpSession httpSession = request.getSession();
		httpSession.invalidate();
		
		mv = new ModelAndView("index");
		return mv;
	}
	
	@RequestMapping(value= "/userlogin.htm", method = RequestMethod.POST)
	protected ModelAndView LoginUser(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		try {
        ModelAndView mv = null;
        
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");
        
        
        String en = UserEncryption.PasswordEncryption(userPassword); 
        System.out.println("userPassword -> "+en);
        User user = userDao.getUser(userName, en);
        
        if(user == null)
        {
        	return new ModelAndView("index","NoUser",true);
        	
        }
        
        HttpSession session = request.getSession();
       
        session.setAttribute("userLoggedIn", user);
        session.setAttribute("user", user);
        session.setAttribute("role","user");
        System.out.println(user);
        
		mv = new ModelAndView("User-MainPage");
        return mv;
		}catch(Exception e){
			ModelAndView mv = null;
			HttpSession httpSession = request.getSession();
			httpSession.invalidate();
			mv = new ModelAndView("index");
			return mv;
		}
        
	}
	
	@RequestMapping(value = "/userregister.htm", method = RequestMethod.GET)
	public String UserRegister(Locale locale, Model model) {		
		return "User-Register";
	}
	
	@RequestMapping(value = "/AdminMainPage.htm", method = RequestMethod.GET)
	public  ModelAndView adminHomePage(HttpServletRequest request) throws Exception {
		try {
		ModelAndView mv = null;
		
		HttpSession session = request.getSession();
		Admin user = (Admin)session.getAttribute("userLoggedIn");
		if (user == null) {
			System.out.println("Inside null");
			return new ModelAndView("index");
		}
		
		mv =  new ModelAndView("Admin-MainPage");
		return mv;
		}catch(Exception e){
			ModelAndView mv = null;
			HttpSession httpSession = request.getSession();
			httpSession.invalidate();
			mv = new ModelAndView("index");
			return mv;
		}
	}
	
	@RequestMapping(value = "/userSuccess.htm", method = RequestMethod.POST)
	public ModelAndView userSignUp(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		try {
		ModelAndView mv = null;
		
		
		
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		String enPass = UserEncryption.PasswordEncryption(userPassword);
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userMobile = request.getParameter("userMobile");
		String userAddress = request.getParameter("userAddress");
		String userRole = "user";
		
		boolean checkUserName = userDao.validateExistingUser(userName);
		if(checkUserName == true) {
			return new ModelAndView("User-Register","errorMessage",true);
		}
		
		
		user.setUserName(userName);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUserPassword(enPass);
		user.setUserMobile(userMobile);
		user.setUserAddress(userAddress);
		user.setUserRole(userRole);
		
		String userMessage = "Welcome to InstaCart "+firstName+" "+lastName+" Enjoy shopping at InstaCart";
		
		userDao.addToDb(user);
		
		
		mv = new ModelAndView("index");
		return mv;
		}catch(Exception e){
			ModelAndView mv = null;
			HttpSession httpSession = request.getSession();
			httpSession.invalidate();
			mv = new ModelAndView("index");
			return mv;
		}
	}
	

		
	

	@RequestMapping(value = "/adminlogin.htm", method = RequestMethod.GET)
	public String adminLogin(Locale locale, Model model) {
		return "Admin-Login";
	}
	
	@RequestMapping(value = "/adminpage.htm",method = RequestMethod.POST)
	public ModelAndView adminSignin(HttpServletRequest request,
            HttpServletResponse response) throws Exception{
		try {
		ModelAndView mv = null;
		
		String adminName = request.getParameter("adminName");
		String adminPassword = request.getParameter("adminPassword");
		
		Admin admin = adminDao.getAdmin(adminName,adminPassword);
		if(admin==null) {
			return new ModelAndView("Admin-Login","NoAdmin",true);
		}
		
		 HttpSession session = request.getSession();
		 	session.setAttribute("userLoggedIn", admin);
	        session.setAttribute("admin", admin);
	        session.setAttribute("role", "admin");
		
	        
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
	
	
	@RequestMapping(value = "/UserMainPage.htm", method = RequestMethod.GET)
	public  ModelAndView userHomePage(HttpServletRequest request) throws Exception {
		
		try {
		ModelAndView mv = null;
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("userLoggedIn");
		if (user == null) {
			System.out.println("Inside null");
			return new ModelAndView("index");
		}
		
		mv =  new ModelAndView("User-MainPage");
		return mv;
		}catch(Exception e){
			ModelAndView mv = null;
			HttpSession httpSession = request.getSession();
			httpSession.invalidate();
			mv = new ModelAndView("index");
			return mv;
		}
	}
	
	
	@RequestMapping(value = "/viewUserProfile.htm", method = RequestMethod.GET)
	public ModelAndView viewUserProfile(HttpServletRequest request) {
		try {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("userLoggedIn");
		if (user == null) {
			return new ModelAndView("index");
		}
		return new ModelAndView("View-UserProfile");
		}catch(Exception e){
			ModelAndView mv = null;
			HttpSession httpSession = request.getSession();
			httpSession.invalidate();
			mv = new ModelAndView("index");
			return mv;
		}
	}
		
	
	@RequestMapping(value = "/viewAdminProfile.htm", method = RequestMethod.GET)
	public ModelAndView viewAdminProfile(HttpServletRequest request) {
		try {
		HttpSession session = request.getSession();
		Admin user = (Admin)session.getAttribute("userLoggedIn");
		if (user == null) {
			return new ModelAndView("index");
		}
		return new ModelAndView("Admin-ViewProfile");
		}catch(Exception e){
			ModelAndView mv = null;
			HttpSession httpSession = request.getSession();
			httpSession.invalidate();
			mv = new ModelAndView("index");
			return mv;
		}
	}
	
	
	
}
