package edu.security.first.controller;

import edu.security.first.service.AccountService;
import edu.security.first.vo.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class HomeController {

  @Autowired
  private AccountService accountService;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

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

//  @PostMapping("/login")
//  public String signin(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
//    System.out.println("name : " + username + " password : " + password);
////    Account account = accountService.Signin(username, password);
////    System.out.println("Signin Info : " + account.toString());
//    return "redirect:index";
//  }

  @GetMapping("/login")
  public String customSignin() {
    return "signinpage";
  }

  @GetMapping("/signup")
  public String Signup() {
    return "signup";
  }

  @PostMapping("/signup")
  public String signup(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, @RequestParam(name = "password2") String password2, @RequestParam(name = "role") String role) {
    if (password.equals(password2)) {
      // password encoder를 통해 특정한 값으로 인코딩해 저장
      Account account = accountService.Signup(username, bCryptPasswordEncoder.encode(password), role);
      log.info("Sing UP : " + account.toString());
    }
    return "redirect:/";
  }

  @PostMapping("/signout")
  public String Signout() {
    log.info("Signout Method Acc");
    System.out.println("ALKSJDLKSAJlkdjslkfjsdlkjfslkdf");
    return "index";
  }
}
