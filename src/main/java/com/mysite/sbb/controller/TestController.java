package com.mysite.sbb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@GetMapping("/test4")
	@ResponseBody
	public String testGet() {
		return "Hello World12";
	}
	
	//http://localhost:9696/main2
	@GetMapping("/main2")
	public String testMain2 () {
		return "test/main2.html";
	}
	
}
