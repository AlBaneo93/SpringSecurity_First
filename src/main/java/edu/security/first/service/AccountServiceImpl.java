package edu.security.first.service;

import edu.security.first.repository.AccountRepository;
import edu.security.first.vo.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

  private AccountRepository accountRepository;

  @Override
  public Account Signin(String username, String password) {
//    TODO : change the more like Optional
    return accountRepository.findByUsernameAndPassword(username, password).get();
  }

  @Override
  public Account Signup(String username, String password) {
    return accountRepository.save(Account.builder().username(username).password(password).build());
  }

//  @Override
//  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    Account account = accountRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
//    return Account.builder().username(account.getUsername()).password(account.getPassword()).build();
//  }
}
