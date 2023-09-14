package com.cos.security1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;

@Controller
public class IndexController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	//localhost:8090/
	//localhost:8090
	@GetMapping({"","/"})
	public @ResponseBody String index() {
		return "index";
	}

	@GetMapping("/user")
	public @ResponseBody String user() {
		return "user";
	}

	@GetMapping("/admin")
	public @ResponseBody String admin() {
		return "admin";
	}

	@GetMapping("/manager")
	public @ResponseBody String manager() {
		return "manager";
	}
	
	//스프링시큐리티가 해당 주소를 낚아채가기때문에 나중에 수정하기!! -SecurityConfig파일 생성 후 작동안함.
	@GetMapping("/loginFrom")
	public String loginFrom() {
		return "loginForm";
	}
	//회원가입 페이지
	@GetMapping("/joinForm")
	public @ResponseBody String joinForm() {
		return "joinForm";
	}
	//실제 회원가입을 시키는 곳
	@PostMapping("/join")
	public String join(User user) {
		System.out.println(user);
		user.setRole("ROLE_USER");
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		//인코딩된 비밀번호로 값을 넣어줍니다.
		user.setPassword(encPassword;)
		userRepository.save(user);//회원가입이 잘됩니다. 문제는 비밀번호:1234로 들어감 => 시큐리티로 로그인을 할 수 없음. => 비밀번호가 암호화가 안되었기 때문.
		return "redirect:/loginForm";
	}
	
	
}
