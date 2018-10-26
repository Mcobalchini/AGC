package com.sgweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;

@Controller
public class IndexController extends HttpServlet {



    @RequestMapping ("/")
    public String mainPage() {
        return "index";
    }
}
