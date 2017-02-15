package xyz.dddog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import xyz.dddog.Repository.AccountRepository;
import xyz.dddog.entity.Account;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
		Account account = accountRepository.findByUserid(userid);
		if( account == null ) {
			throw new UsernameNotFoundException(userid);
		}
		return new UserDetailsImpl(account);
	}

}
