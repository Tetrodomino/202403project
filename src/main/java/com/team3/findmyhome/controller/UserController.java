package com.team3.findmyhome.controller;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team3.findmyhome.entity.Favorite;
import com.team3.findmyhome.entity.User;
import com.team3.findmyhome.service.FavoriteService;
import com.team3.findmyhome.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired private UserService uSvc;
	@Autowired private FavoriteService fSvc;
	@Value("${spring.servlet.multipart.location}") private String uploadDir;

	@GetMapping("/register")
	public String registerForm() {
		return "user/register";
	}
	
	@PostMapping("/register") 
	public String registerProc(Model model,
			String uid, String pwd, String pwd2, String uname, String email,
			String location, String tel) {
		if (uSvc.getUserByUid(uid) != null) {
			model.addAttribute("msg", "사용자 ID가 중복되었습니다.");
			model.addAttribute("url", "/fmh/user/register");
			return "common/alertMsg";
		}
		if (pwd.equals(pwd2) && pwd != null) {
			User user = new User(uid, pwd, uname, email, location, tel );
			uSvc.registerUser(user);
			model.addAttribute("msg", "등록을 마쳤습니다. 로그인하세요.");
			model.addAttribute("url", "/fmh/user/login");
			return "common/alertMsg";
		} else {
			model.addAttribute("msg", "패스워드 입력이 잘못되었습니다.");
			model.addAttribute("url", "/fmh/user/register");
			return "common/alertMsg";
		}
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "user/login";
	}
	
	@PostMapping("/login")
	public String loginProc(String uid, String pwd, HttpSession session, Model model) {
		int result = uSvc.login(uid, pwd);
		switch(result) {
		case UserService.CORRECT_LOGIN:
			User user = uSvc.getUserByUid(uid);
			session.setAttribute("sessUid", uid);
			session.setAttribute("sessUname", user.getUname());
			session.setAttribute("email", user.getEmail());
			session.setAttribute("location", user.getLocation());
			
			// 환영 메세지 설정
			log.info("Info Login: {}, {}", uid, user.getUname());
			model.addAttribute("msg", user.getUname() + "님 환영합니다");
			model.addAttribute("url", "/fmh/building/search");
			break;
		case UserService.USER_NOT_EXIST:
			model.addAttribute("msg", "존재하지 않는 ID입니다");
			model.addAttribute("url", "/fmh/user/login");
			return "common/alertMsg";
		case UserService.WRONG_PASSWORD:
			model.addAttribute("msg", "패스워드 입력이 잘못되었습니다.");
			model.addAttribute("url", "/fmh/user/login");
			return "common/alertMsg";
		default:
			break;
		}
		
		return "common/alertMsg";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/user/login";
	}
	
	@GetMapping("/mypage/{uid}")
	public String mypage(@PathVariable String uid, Model model) {
		User user = uSvc.getUserByUid(uid);
		model.addAttribute("user", user);
		
		List<Favorite> favoriteList = fSvc.getFavoriteList(uid);
		model.addAttribute("favoriteList", favoriteList);
	
		return "user/mypage";
	}
	
	@GetMapping("/update/{uid}")
	public String update(@PathVariable String uid, Model model) {
		User user = uSvc.getUserByUid(uid);
		model.addAttribute("user", user);
	
		return "user/update";
	}
	
	@PostMapping("/update")
	public String update(Model model,String uid, String pwd, String pwd2,
			String uname, String email,String location, String tel,
			String hashedPwd, HttpSession session) {
		String sessUid = (String) session.getAttribute("sessUid");
		System.out.println(uid);
		if (!uid.equals(sessUid)) {
			model.addAttribute("msg", "수정 권한이 없습니다.");
			model.addAttribute("url", "/fmh/user/login");
			return "common/alertMsg";
		}
		if (pwd != null && pwd.length() > 1 && pwd.equals(pwd2)) {
			hashedPwd = BCrypt.hashpw(pwd, BCrypt.gensalt());
		}
		User user = new User(uid, hashedPwd, uname, email, location, tel);
		System.out.println(user);
		uSvc.updateUser(user);
		return "redirect:/main";
	}
	
	@GetMapping("/detail/{uid}")
	public String detail(@PathVariable String uid, Model model) {
		User user = uSvc.getUserByUid(uid);
		model.addAttribute("user", user);
		
		return "user/userDetail";
	}
	
//	@GetMapping("/delete/{uid}")
//	public String delete(@PathVariable String uid, HttpSession session, Model model) {
//		String sessUid = (String) session.getAttribute("sessUid");
//		if (sessUid.equals("admin")) {
//			uSvc.deleteUser(uid);
//			return "redirect:/user/login";
//		} else if (sessUid.equals(uid)) {
//			uSvc.deleteUser(uid);
//			session.invalidate();
//			return "redirect:/user/login";
//		} else {
//			model.addAttribute("msg","삭제 권한이 없습니다.");
//			model.addAttribute("url","/fmh/user/login");
//			return "common/alertMsg";
//		}
//	}
}