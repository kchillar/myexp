package com.alacriti.saml.idp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IdpController {
  Logger logger = LoggerFactory.getLogger(IdpController.class);

  @GetMapping("/logout")
  public String logout(Model model, HttpServletResponse response) {
  logger.info("GET logout");
    Cookie cookie = new Cookie(Constants.CookieName, null);
    cookie.setMaxAge(0); // expires now
    response.addCookie(cookie);
    model.addAttribute("userInfo", new UserInfo());
    return "login";
}

  @GetMapping("/login")
  public String loginForm(Model model) {
    logger.info("GET login");
    model.addAttribute("userInfo", new UserInfo());
    return "login";
  }

  @GetMapping("/home")
  public String homeView(Model model) {
    logger.info("GET home");
    //model.addAttribute("userInfo", new UserInfo());
    return "home";
  }

  @GetMapping("/samlRequest")
  public RedirectView createSamlResponse(Model model, RedirectAttributes attributes) {
    logger.info("GET samlRequest");
    attributes.addAttribute("testParam1", "testValue1");
    return new RedirectView(Constants.SAMLAssertionUrlInSP);
  }

  @PostMapping("/login")
  public String loginSubmit(@ModelAttribute UserInfo userInfo, Model model, HttpServletResponse response) {
    logger.info("POST login");
    logger.info("userInfo.loginId:{}, userInfo.password:{} ", userInfo.getLoginId(), userInfo.getPassword());
    model.addAttribute("userInfo", userInfo);
    String id = userInfo.getLoginId();
    String crd = userInfo.getPassword();

    if (id != null && crd != null && id.trim().length() > 0 && crd.trim().length() > 0) {
      if (id.equals("john.doe@alacriti.com") && crd.equals("pass1234")) {
        Cookie cookie = new Cookie(Constants.CookieName, "CK123456789");
        cookie.setMaxAge(Constants.CookieExpirySeconds); // expires in 5 minutes days
        response.addCookie(cookie);
        return "home";
      } else {
        logger.error("Error1, invalid credentials, id:{}, crd:{}", id, crd);
        model.addAttribute("errorMsg", "Invalid login credentials!!!");
        return "login";
      }
    } else {
      logger.error("Error2, invalid credentials, id:{}, crd:{}", id, crd);
      model.addAttribute("errorMsg", "Invalid login credentials!!!");
      return "login";
    }
  }

  @GetMapping("/test")
  public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
      Model model) {
    model.addAttribute("name", name);
    return "test";
  }

}