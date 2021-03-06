package xyz.dddog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	@RequestMapping({"/","/index"})
	public String index() {
		return "index";
	}
	@RequestMapping({"/user/hello"})
	public String userHello() {
		return "/user/hello";
	}
	@RequestMapping({"/admin/hello"})
	public String adminHello() {
		return "/admin/hello";
	}
}
