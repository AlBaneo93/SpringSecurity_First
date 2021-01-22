package edu.security.first.controller;

import edu.security.first.service.AccountService;
import edu.security.first.vo.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class HomeController {

  private AccountService accountService;

  @GetMapping("/hello")
  public String hello() {
    return "hello";
  }

  @GetMapping("/index")
  public String index() {
    return "index";
  }

  @GetMapping("/my")
  public String my() {
    return "my";
  }

  @PostMapping("/signin")
  public String signin(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
    Account account = accountService.Signin(username, password);
    return "index";
  }

  @PostMapping("/signup")
  public String signup(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
    Account account = accountService.Signup(username, password);
    return "index";
  }
}
