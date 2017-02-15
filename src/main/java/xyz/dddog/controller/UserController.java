package xyz.dddog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xyz.dddog.Repository.AccountRepository;
import xyz.dddog.entity.Account;
import xyz.dddog.service.impl.UserDetailsImpl;

@Controller
public class UserController {

	@RequestMapping("/login")
	public String hello() {
		return "/login";
	}

	@RequestMapping("/registerForm")
	public void registerForm() {
	}

	// 필요한 부분은 Serivce계층으로 옮겨줘야 합니다.
	@Autowired
	AccountRepository accountRepository;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(Account account) {
		// 회원정보 데이터베이스에 저장
		accountRepository.save(account);

		// SecurityContextHolder에서 Context를 받아 인증 설정
		UserDetailsImpl userDetails = new UserDetailsImpl(account);
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
				userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "redirect:/";
	}
}
