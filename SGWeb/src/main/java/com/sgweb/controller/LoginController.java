package com.sgweb.controller;

import javax.servlet.http.HttpServlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController extends HttpServlet {


	@RequestMapping("/login")
	public String loginPage() {
		return "login";
	}
}
