package edu.security.first.service;

import edu.security.first.vo.Account;

public interface AccountService {


  Account Signin(String username, String password);

  Account Signup(String username, String password);
}
