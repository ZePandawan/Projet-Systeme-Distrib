package com.polytechancy.BigCloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PagesController {
	
	@GetMapping("/")
	public String home(Model model) {
		//model.addAttribute("message","uwu");
		return "Rules";
	}
	
}
