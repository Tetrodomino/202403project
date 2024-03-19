package com.team3.findmyhome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Main {

	@GetMapping("main")
	public String main() {
		return "main";
	}
}
