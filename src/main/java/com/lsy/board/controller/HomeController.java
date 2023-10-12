package com.lsy.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lsy.board.config.auth.dto.SessionUser;
import com.lsy.board.model.User;
import com.lsy.board.service.UserService;

@Controller
@RequestMapping("/")
public class HomeController {
//	private final HttpSession httpSession = null;
	
	@Autowired
	private UserService userService;
	
////	작업중 OAuth2
//	private  HttpSession httpSession;
//	
////	작업중 OAuth2
//	@GetMapping(value={"","index"})
//	public String index(Model model) {
//		SessionUser user = (SessionUser) httpSession.getAttribute("user");
//		if (user != null) {
//			model.addAttribute("sUser", user.getUsername());
//		}
//		return "index";
//	}
//	
	
	@GetMapping(value={"","index"})
	public String index() {
		return "index";
	}
	
	@GetMapping("join")
	public String joinForm() {
		return "member/join";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("sUser");
		return "redirect:/board/list";
	}
	
	@PostMapping("update")
	@ResponseBody
	public String update(@RequestBody User user) {
		User user2 = userService.findByUsername(user.getName());
		user2.setEmail(user.getEmail());
		userService.update(user2);
		return "success";
	}
	
	@PostMapping("delete")
	@ResponseBody
	public String delete(@RequestBody User user) {
		userService.delete(user.getId());
		return "success";
	}
	
	@PostMapping("join")
	@ResponseBody
	public String register(@RequestBody User user) {
		
		if(userService.findByUsername(user.getName())!=null) {
			return "fail";
		}
		userService.register(user);
		return "success";
	}
	
	@PostMapping("idCheck")
	@ResponseBody
	public String idCheck(String username) {
		User user=userService.findByUsername(username);
		boolean usernameYes = userService.existsByUsername(username);
		String result = "success";
		if(user!=null) {
			if(usernameYes) {
				return "fail";
				} 
			
		} else if(user==null && "".equals(username)){
		return "no"; 
	}
		return result;
	}
	
	@GetMapping("update")
	public String update() {
		return "member/update";
	}
	
	
	@GetMapping("login")
	public String loginForm() {
		return "member/login";
	}
	
	@PostMapping("login")
	@ResponseBody
	public String loginPro(String username, String password,HttpSession session) {
		User user=userService.findByUsername(username);
		String result="no";
//		if(user!=null) {
//			if(user.getPassword().equals(password)) {
//				session.setAttribute("sUser", user);
//				result="success";
//			}else {
//				result="fail";
//			}
//		}
		return result;
	}

}
