package edu.security.first.service;

import edu.security.first.repository.AccountRepository;
import edu.security.first.vo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AccountServiceImpl implements AccountService, UserDetailsService {

  @Autowired
  private AccountRepository accountRepository;

  @Override
  public Account Signin(String username, String password) {
//    TODO : change the more like Optional
    return accountRepository.findByUsernameAndPassword(username, password).get();
  }

  @Override
  public Account Signup(String username, String password, String role) {
    return accountRepository.save(Account.builder().username(username).password(password).role(role).build());
  }


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account account = accountRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
    return new User(account.getUsername(), account.getPassword(), Arrays.asList(new SimpleGrantedAuthority(account.getRole())));
  }
}
